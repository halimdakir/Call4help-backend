package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.dtos.ReporterQuantityByAlert;
import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.AlertRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImplement implements AlertService{

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private UserService userService;


    @PersistenceContext
    private EntityManager entityManager;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public Alert registerHelpAlert(String userId, PositionDTO positionDTO) {

        Users user = userService.findUserByUserId(userId);

        if (user!=null){


                Alert alert = new Alert(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())), user);

                alertRepository.save(alert);
                entityManager.detach(alert);

                return alert;



        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }

    @Override
    public Optional<Alert> findAlertById(Long id) {
        return alertRepository.findById(id);
    }


    @Override
    public List<Alert> getAllAlerts() {
        return (List<Alert>) alertRepository.findAll();
    }
}
