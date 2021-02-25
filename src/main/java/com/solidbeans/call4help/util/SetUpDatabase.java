package com.solidbeans.call4help.util;

import com.solidbeans.call4help.entities.*;
import com.solidbeans.call4help.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Configuration
public class SetUpDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, LocationRepository locationRepository, AlertRepository alertRepository, ReportRepository reportRepository) {
        return args -> {
            if (userRepository.count() ==0 && locationRepository.count()==0 && alertRepository.count()==0 && reportRepository.count()==0){


                var user1 = new Users("100MA100", "QWERTYUIOP0123456", ZonedDateTime.now(ZoneId.of("UTC")), null);
                var user2 = new Users("100MA200", "QWERTYUIOP0123488", ZonedDateTime.now(ZoneId.of("UTC")), null);
                var user3 = new Users("100MA300", "QWERTYUIOP0123499", ZonedDateTime.now(ZoneId.of("UTC")), null);
                var user4 = new Users("100MA400", "QWERTYUIOP0123500", ZonedDateTime.now(ZoneId.of("UTC")), null);

                /*
                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);
                 */

                /*var position1 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.708116, 11.967694)));
                var position2 = new Position( ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.708104, 11.967196)));
                var position3 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.707617, 11.967883)));
                var position4 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.707015, 11.968140)));*/

                var location1 = new Location(ZonedDateTime.now(ZoneId.of("UTC")), "Göteborg", user1);
                var location2 = new Location( ZonedDateTime.now(ZoneId.of("UTC")), "Göteborg", user2);
                var location3 = new Location(ZonedDateTime.now(ZoneId.of("UTC")), "Stockholm", user3);
                var location4 = new Location(ZonedDateTime.now(ZoneId.of("UTC")), "Göteborg", user4);



                locationRepository.save(location1);
                locationRepository.save(location2);
                locationRepository.save(location3);
                locationRepository.save(location4);


                var alert = new Alert( ZonedDateTime.now(ZoneId.of("UTC")),location1.getMunicipality(), user1);
                alertRepository.save(alert);

/*
                var report1 = new Report("Action A", ZonedDateTime.now(ZoneId.of("UTC")), user4, alert);
                var report2 = new Report("Action B", ZonedDateTime.now(ZoneId.of("UTC")), user2, alert);


                reportRepository.save(report1);
                reportRepository.save(report2);
*/
            }
        };
    }
}
