package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReporterQuantityByAlert;
import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private LocationService locationService;


    @Override
    public Alert registerHelpAlert(String userId) {

        Users user = userService.findUserByUserId(userId);

        if (user!=null){

            //Get actual location
            var location = locationService.getLocationByUserId(user.getUserId());

            if (location.isPresent()){

                return alertRepository.save(new Alert(ZonedDateTime.now(ZoneId.of("UTC")), location.get().getMunicipality(), user));

            }else {

                throw new NotFoundException("Position with user id :"+userId+" is not found!");

            }

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }

    @Override
    public Optional<Alert> findAlertById(Long id) {
        return alertRepository.findById(id);
    }


    @Override
    public List<ReporterQuantityByAlert> getReporterQuantityByAlertAndUser(String userId) {
        return alertRepository.reporterQuantityByAlertAndUser(userId);
    }
}
