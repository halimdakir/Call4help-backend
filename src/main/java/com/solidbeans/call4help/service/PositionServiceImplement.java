package com.solidbeans.call4help.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.dto.UsersDTO;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PositionServiceImplement implements PositionService{

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    //private String Gothenburg_Topic_ARN = "arn:aws:sns:eu-north-1:372046788717:gothenburg-topic";
    //private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public Position createUserPosition(PositionDTO positionDTO) {
        if (positionDTO.getMunicipality() == null || positionDTO.getMunicipality().equals("") &&  positionDTO.getUserId() == null || positionDTO.getUserId().equals("")){

            throw new RegistrationException("All fields are required!");

        }else{

            Users user = userService.findUserByUserId(positionDTO.getUserId());

            if (user!=null){

                Position position = new Position(Instant.now().atZone(ZoneOffset.UTC), positionDTO.getMunicipality(), user);

                positionRepository.save(position);

                //entityManager.detach(position);


                return positionRepository.save(position);

            }else {

                throw new NotFoundException("User with id :"+positionDTO.getUserId()+" is not found");
            }
        }

    }


    @Override
    public Position updateUserPosition(String city, String userId) {
        return positionRepository.findPositionByUserId(userId)
                .map(position -> {
                    position.setMunicipality(city);
                    return positionRepository.save(position);
                })
                .orElseThrow(() -> new NotFoundException("Position not found with user id :" + userId)
                );
    }

    @Override
    public List<UsersDTO> nearestPersonsList(String userId) {
        List<UsersDTO> nearestUsersList = new ArrayList<>();
        var position = positionRepository.findPositionByUserId(userId);

        if (position.isPresent()){

            List<Position> positionList = positionRepository.findAllByCityAndUserId(position.get().getMunicipality(), userId);

            for (Position p: positionList){
                Users user = userService.findUserById(p.getUsers().getId());
                nearestUsersList.add(new UsersDTO(user.getId(), user.getUserId()));
            }
        }

        return nearestUsersList;
    }

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAllPosition();
    }


    private void deletePreviousPosition(String id){
        //var position = repository.findPositionByUserId(id);
        var position = positionRepository.findPositionByUserId(id);
        position.ifPresent(value -> positionRepository.deleteById(position.get().getId()));
    }

}
