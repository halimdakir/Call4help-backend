package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.LocationDTO;
import com.solidbeans.call4help.entities.Location;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;


@Service
public class LocationServiceImplement implements LocationService{

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    //private String Gothenburg_Topic_ARN = "arn:aws:sns:eu-north-1:372046788717:gothenburg-topic";
    //private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public Location createUserLocation(LocationDTO locationDTO) {
        if (locationDTO.getMunicipality() == null || locationDTO.getMunicipality().equals("") &&  locationDTO.getUserId() == null || locationDTO.getUserId().equals("")){

            throw new RegistrationException("All fields are required!");

        }else{

            Users user = userService.findUserByUserId(locationDTO.getUserId());

            if (user!=null){

                Location location = new Location(Instant.now().atZone(ZoneOffset.UTC), locationDTO.getMunicipality(), user);

                locationRepository.save(location);

                //entityManager.detach(position);


                return locationRepository.save(location);

            }else {

                throw new NotFoundException("User with id :"+ locationDTO.getUserId()+" is not found");
            }
        }

    }


    @Override
    public Location updateUserLocation(String city, String userId) {
        return locationRepository.findLocationByUserId(userId)
                .map(position -> {
                    position.setMunicipality(city);
                    return locationRepository.save(position);
                })
                .orElseThrow(() -> new NotFoundException("Position not found with user id :" + userId)
                );
    }


    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAllLocations();
    }

    @Override
    public Optional<Location> getLocationByUserId(String userId) {
        return locationRepository.findLocationByUserId(userId);
    }


}
