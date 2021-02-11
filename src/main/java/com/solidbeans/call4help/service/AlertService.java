package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.AlertDTO;
import com.solidbeans.call4help.entity.Alert;

public interface AlertService {
    Alert registerHelpAlert(String userId);

}
