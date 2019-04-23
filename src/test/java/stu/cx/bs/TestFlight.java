package stu.cx.bs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import stu.cx.bs.entity.Flight;
import stu.cx.bs.entity.FlightProperties;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestFlight {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void save(){
        Flight flight=new Flight();
        flight.setFromCity("萍乡");
        flight.setToCity("长沙");
        flight.setName("flight-12138");

        List<Point> points=new ArrayList<>();
        points.add(new Point(50,50));
        points.add(new Point(20,20));
        GeoJsonLineString line=new GeoJsonLineString(points);
        flight.setLine(line);

        FlightProperties properties=new FlightProperties();
        properties.setLength("10000");

        mongoTemplate.save(flight);

    }
}
