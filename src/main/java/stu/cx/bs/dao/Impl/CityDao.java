package stu.cx.bs.dao.Impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import stu.cx.bs.dao.Gis;
import stu.cx.bs.entity.City;

import java.util.List;

@Component
public class CityDao extends Gis<City> {

    @Override
    public List<City> findAll() {
        List<City> cities=mongoTemplate.findAll(City.class);
            return cities;
    }

    @Override
    public List<City> find(Query query, String collectionName) {
        return mongoTemplate.find(query,City.class,collectionName);
    }

    @Override
    public void update(Query query, Update update, String collectionName) {
        mongoTemplate.updateFirst(query,update,collectionName);
    }

    @Override
    public void remove(Query query, String collectionName) {
        mongoTemplate.remove(query,City.class,collectionName);
    }

    //找寻规定范围内最近的几个城市
    public List<City> FindNear(Query query){
       return  mongoTemplate.find(query,City.class);

    }
    public City findOne(Query query){
       return mongoTemplate.findOne(query,City.class);
    }
}
