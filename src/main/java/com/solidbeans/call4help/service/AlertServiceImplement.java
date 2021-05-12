package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.ServiceUnavailableException;
import com.solidbeans.call4help.repository.AlertRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImplement implements AlertService{

    private final AlertRepository alertRepository;
    private final UserService userService;
    private final ActiveAlertsService activeAlertsService;


    @PersistenceContext
    private EntityManager entityManager;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);

    public AlertServiceImplement(AlertRepository alertRepository, UserService userService, ActiveAlertsService activeAlertsService) {
        this.alertRepository = alertRepository;
        this.userService = userService;
        this.activeAlertsService = activeAlertsService;
    }

    @Override
    public Alert registerHelpAlert(String userId, PositionDTO positionDTO) {

        Users user = userService.findUserByUserId(userId);

        if (user!=null){

            List<Alert> activeAlerts = activeAlertsService.activeAlert(user.getUserId());

            if (activeAlerts.size() > 0 ){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedString = activeAlerts.get(activeAlerts.size()-1).getEndAlertDate().format(formatter);


                throw  new ServiceUnavailableException("You still have an active alert until : "+formattedString+". please try after this!");

            }else {

                Alert alert = new Alert(Instant.now().atZone(ZoneOffset.UTC), Instant.now().atZone(ZoneOffset.UTC).plusHours(1), geometryFactory.createPoint(new Coordinate(positionDTO.getLat(), positionDTO.getLng())), user);

                alertRepository.save(alert);
                entityManager.detach(alert);

                return alert;
            }

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }

    @Override
    public Optional<Alert> findAlertById(Long id) {
        return alertRepository.findById(id);
    }

}
