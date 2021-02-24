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
    CommandLineRunner initDatabase(UserRepository userRepository, PositionRepository positionRepository, AlertRepository alertRepository) {
        return args -> {
            if (userRepository.count() ==0 && positionRepository.count()==0 && alertRepository.count()==0 ){


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

                var position1 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), "Göteborg", user1);
                var position2 = new Position( ZonedDateTime.now(ZoneId.of("UTC")), "Göteborg", user2);
                var position3 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), "Stockholm", user3);
                var position4 = new Position(ZonedDateTime.now(ZoneId.of("UTC")), "Malmö", user4);



                positionRepository.save(position1);
                positionRepository.save(position2);
                positionRepository.save(position3);
                positionRepository.save(position4);


                var alert = new Alert( ZonedDateTime.now(ZoneId.of("UTC")),position1.getMunicipality(), user1);

                alertRepository.save(alert);



            }
        };
    }
}
