package com.solidbeans.call4help.service;

import com.solidbeans.call4help.file.ReadJsonFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    public ResponseEntity<?> allCities() {
        return ResponseEntity.ok(new ReadJsonFile().GetCities());
    }
}
