package com.solidbeans.call4help.util;

import com.solidbeans.call4help.entities.*;
import com.solidbeans.call4help.repository.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Configuration
public class SetUpDatabase {
    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ProfileRepository profileRepository, AlertRepository alertRepository, ReportRepository reportRepository, PositionRepository positionRepository) {
        return args -> {
            if (userRepository.count() ==0 && profileRepository.count()==0 && alertRepository.count()==0 && reportRepository.count()==0 && positionRepository.count()==0){


                var user1 = new Users("100MA100", "QWERTYUIOP0123456", ZonedDateTime.now(ZoneId.of("UTC")), null);
                var user2 = new Users("100MA200", "QWERTYUIOP0123488", ZonedDateTime.now(ZoneId.of("UTC")), null);
                var user3 = new Users("100MA300", "QWERTYUIOP0123499", ZonedDateTime.now(ZoneId.of("UTC")), null);
                var user4 = new Users("100MA400", "QWERTYUIOP0123500", ZonedDateTime.now(ZoneId.of("UTC")), null);


                var profile1 = new Profile(true, null, null,null,null, user1);
                var profile2 = new Profile(true, null, null,null, null, user2);
                var profile3 = new Profile(false, "Hal Dak", "hal.dak@email.fr", "0722220000", ZonedDateTime.now(ZoneId.of("UTC")), user3);
                var profile4 = new Profile(true, null, null,null, null, user4);



                profileRepository.save(profile1);
                profileRepository.save(profile2);
                profileRepository.save(profile3);
                profileRepository.save(profile4);


                var alert1 = new Alert( ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.708116, 11.967694)), user1);
                var alert2 = new Alert( ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.707617, 11.967883)), user3);

                alertRepository.save(alert1);
                alertRepository.save(alert2);


                var report1 = new Report("Action A", ZonedDateTime.now(ZoneId.of("UTC")), user4, alert1);
                var report2 = new Report("Action B", ZonedDateTime.now(ZoneId.of("UTC")), user2, alert1);


                reportRepository.save(report1);
                reportRepository.save(report2);


                var position1 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.708116, 11.967694)), profile1);
                var position2 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.708104, 11.967196)), profile2);
                var position3 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(59.326450, 18.069037)), profile3); //in other city
                var position4 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), geometryFactory.createPoint(new Coordinate(57.710730, 11.976234)), profile4); //More than 500 meters



                positionRepository.save(position1);
                positionRepository.save(position2);
                positionRepository.save(position3);
                positionRepository.save(position4);
            }
        };
    }
}

