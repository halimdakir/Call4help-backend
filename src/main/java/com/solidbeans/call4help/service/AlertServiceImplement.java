package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.AlertDTO;
import com.solidbeans.call4help.entity.Alert;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImplement implements AlertService{

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private UserService userService;


    @Override
    public Alert registerAlertDate(AlertDTO alertDTO, Long userId) {
        Users user = userService.findUserById(userId);
        if (user!=null){


            return alertRepository.save(new Alert(user.getUserId(), alertDTO.getAlertDate()));
            //return alertRepository.save(new Alert(alertDTO.getAlertDate(), user));

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }
}
