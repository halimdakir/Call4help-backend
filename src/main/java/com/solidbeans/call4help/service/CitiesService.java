package com.solidbeans.call4help.service;

import com.solidbeans.call4help.file.Location;
import com.solidbeans.call4help.file.ReadJsonFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesService {

    public List<Location> allCities(){
        ReadJsonFile readJsonFile = new ReadJsonFile();
        return readJsonFile.GetCities();
    }
}
