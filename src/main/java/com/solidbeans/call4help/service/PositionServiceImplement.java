package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.DistanceDTO;
import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.dto.UsersDTO;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.PositionRepository;
import com.solidbeans.call4help.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Service
public class PositionServiceImplement implements PositionService{

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    //private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public Position createUserPosition(PositionDTO positionDTO, String userId) {
        Users user = userService.findUserByUserId(userId);
        Position position = new Position();
        if (user!=null){

            positionRepository.save(new Position(position.getDateTime(), position.getMunicipality(), user));
            entityManager.detach(position);

            return position;

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
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
