package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class AlertServiceImplement implements AlertService{

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private UserService userService;


    @Override
    public Alert registerHelpAlert(String userId) {
        Users user = userService.findUserByUserId(userId);
        if (user!=null){

            return alertRepository.save(new Alert(ZonedDateTime.now(ZoneId.of("UTC")), user));

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }

    @Override
    public Optional<Alert> findAlertById(Long id) {
        return alertRepository.findById(id);
    }
}
