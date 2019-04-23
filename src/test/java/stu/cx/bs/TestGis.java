package stu.cx.bs;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import stu.cx.bs.dao.Impl.GisRegionDaoImpl;
import stu.cx.bs.entity.City;
import stu.cx.bs.entity.GisRegion;
import stu.cx.bs.entity.GisRegionProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestGis {
    @Autowired
    private GisRegionDaoImpl dao;

    @Autowired
    MongoTemplate mongoTemplate;


    @Test
    public void save(){

        GisRegionProperties properties=new GisRegionProperties();
        properties.setProvince("贵州");
        properties.setRegoin("黔西南布依族苗族自治州");
        properties.setCounty("望谟县");

        GeoJsonPoint geoJson1=new GeoJsonPoint(1,1);
        GeoJsonPoint geoJson2=new GeoJsonPoint(3,3);
        List<Point> points=new ArrayList<>();
        points.add(geoJson1);
        points.add(geoJson2);
        GeoJsonPolygon pol=new GeoJsonPolygon(points);

        GisRegion gis=new GisRegion();
        gis.setName("贵州-黔西南布依族苗族自治州-望谟县");
        gis.setProperties(properties);
        gis.setBox(pol);

        dao.insert(gis,"GisRegion");
        GisRegion gisRegion=mongoTemplate.findOne(new Query().addCriteria(new Criteria("type").is("Feature")).limit(2),GisRegion.class);
        System.out.println(gisRegion);
    }
    @Test
    public void find(){
        Query query=new Query();
        query.addCriteria(new Criteria("properties.sheng").is("江西")).addCriteria(new Criteria());
        List<GisRegion> gisRegions=mongoTemplate.find(query,GisRegion.class,"xzbj");
        for(GisRegion region:gisRegions){
            System.out.println(region);
        }
    }
    @Test
    public void update(){
        Query query=new Query();
        query.addCriteria(new Criteria("_id").is("5cbea4001213f429a8de2671"));

        City city=mongoTemplate.findOne(query,City.class,"cities");
        System.out.println(city.getName());

        String newCityName="新";
        Double newX=13.0;
        Double newY=13.0;
        Double newPm25=13.0;
        Double newPopulationSize=13.0;
        Double newArea=13.0;

        GeoJsonPoint newPoint=new GeoJsonPoint(newX,newY);
        Update update=new Update();

        update.set("name",newCityName)
                .set("geometry",newPoint)
                .set("properties.pm25",newPm25)
                .set("properties.newPopulationSize",newPopulationSize)
                .set("properties.newArea",newArea);

        mongoTemplate.upsert(query,update,"cities");
    }
    @Test
    public void update2(){
        Map<String,Object> QueryParams=new HashMap();
        QueryParams.put("properties.sheng","贵州");
        Query query=new Query().addCriteria(new Criteria("properties.sheng").is("贵州"));
        Update update=new Update();
        update.set("sample_name","贵");
        mongoTemplate.updateMulti(query,update,"xzbj");
        List<GisRegion> gisRegions=mongoTemplate.find(query,GisRegion.class,"xzbj");
        for(GisRegion gis:gisRegions){
            System.out.println(gis);
        }
    }
    @Test
    public void showCount(){

        Query query=new Query().addCriteria(new Criteria("geometry.properties").exists(false));
        query.addCriteria(new Criteria("type").is("Feature"));
        long count=mongoTemplate.getCollection("xzbj").countDocuments();
        List<GisRegion> gisRegions=mongoTemplate.find(query,GisRegion.class,"xzbj");

        System.out.println("总数量："+count);
        System.out.println("满足条件的文档数量："+gisRegions.size());
    }

    @Test
    public void findNear(){
        Query query=new Query().addCriteria(new Criteria("name").is("杭州"));
        City hangzhou=mongoTemplate.findOne(query, City.class);
        System.out.println(hangzhou.getName()+","+hangzhou.getGeometry().getCoordinates());

        Query query2=new Query();
        Criteria c1=new Criteria("geometry").nearSphere((GeoJsonPoint)hangzhou.getGeometry()).maxDistance(50000).minDistance(10000);
        query2.addCriteria(c1);
        List<City> cities=mongoTemplate.find(query2,City.class);
        for(City city:cities){
            System.out.println(city.getName()+","+city.getGeometry().getCoordinates());
        }
    }
    @Test
    public void findByCoor(){
        Query query=new Query();
        Criteria c1= new Criteria().where("geometry").is(new GeoJsonPoint(new Point(120.38,37.35)));
        query.addCriteria(c1);
        List<City> list=mongoTemplate.find(query,City.class,"cities");
        for(City city:list){
            System.out.println(city.getName());
        }
    }
    @Test
    public void findOne(){
        City city=mongoTemplate.findOne(new Query(),City.class);
        System.out.println(city.getName());
    }
}
