package stu.cx.bs.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import stu.cx.bs.dao.Impl.CityDao;
import stu.cx.bs.entity.City;
import stu.cx.bs.entity.ResponseContent;
import stu.cx.bs.utils.JsonUtil;
import stu.cx.bs.utils.ResponseContentUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 接收、处理和发送city相关的内容
 */
@RestController
@RequestMapping(value="/gis/city")
public class CityController {
    @Autowired
    private CityDao cityDao;

    private ResponseContentUtil responseContentUtil=new ResponseContentUtil<City>();
    @GetMapping(value = "/save")
    public void saveGis(City city) throws Exception {

        cityDao.insert(city,"cities");
    }

    @GetMapping(value="/find")
    public ResponseContent find(HttpServletRequest request){

        List<String> params=new ArrayList<String>();
        Enumeration paraEnum=request.getParameterNames();
        while(paraEnum.hasMoreElements()){
            params.add((String)paraEnum.nextElement());
        }

        return findAll();
    }

    @GetMapping(value="/findAll")
    public ResponseContent findAll(){
        List<City> cities=cityDao.findAll();
        return responseContentUtil.getResponseContent(cities);
    }

    @RequestMapping(value="/findNear",method= RequestMethod.GET)
    @ResponseBody
    public ResponseContent findNear(HttpServletRequest request){
        String gePlaceName=request.getParameter("geoPlaceName");
        double maxDistance=Double.parseDouble(request.getParameter("maxDistance"));
        double mixDistance=Double.parseDouble(request.getParameter("minDistance"));


        Query where=new Query();
        where.addCriteria(new Criteria("name").is(gePlaceName));
        City city=cityDao.findOne(where);

        Query query=new Query();
        Criteria c1=new Criteria("geometry").nearSphere((GeoJsonPoint)city.getGeometry()).maxDistance(maxDistance).minDistance(mixDistance);
        query.addCriteria(c1);

        List<City> cities=cityDao.find(query,"cities");
        return responseContentUtil.getResponseContent(cities);
    }

    @RequestMapping(value="/del",method= RequestMethod.POST)
    @ResponseBody
    public void del(HttpServletRequest request){
        String id=request.getParameter("id");
        Query query=new Query();
        query.addCriteria(new Criteria("id").is(id));
        cityDao.remove(query,"cities");
            }

    @RequestMapping(value="/saveCity",method= RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request){
        City city=new JsonUtil().toObject(request.getParameter("city"), new TypeReference<City>() {
        });
        double x=Double.parseDouble(request.getParameter("x"));
        double y=Double.parseDouble(request.getParameter("y"));
        city.setGeometry(new GeoJsonPoint(x,y));
        cityDao.insert(city,"cities");
        System.out.println(city.getName());
    }

    @RequestMapping(value="/updateById",method= RequestMethod.POST)
    @ResponseBody
    public void updateById(HttpServletRequest request){
        Query query=new Query();
        query.addCriteria(new Criteria("_id").is(request.getParameter("id")));


        String newCityName=request.getParameter("newCityName");
        Double newX=Double.parseDouble(request.getParameter("newX"));
        Double newY=Double.parseDouble(request.getParameter("newY"));
        Double newPm25=Double.parseDouble(request.getParameter("newPm25"));
        Double newPopulationSize=Double.parseDouble(request.getParameter("newPopulationSize"));
        Double newArea=Double.parseDouble(request.getParameter("newArea"));

        GeoJsonPoint newPoint=new GeoJsonPoint(newX,newY);
        Update update=new Update();

        update.set("name",newCityName)
                .set("geometry",newPoint)
                .set("properties.pm25",newPm25)
                .set("properties.people",newPopulationSize)
                .set("properties.area",newArea);
        cityDao.update(query,update,"cities");
    }

    @RequestMapping(value="/findByName",method= RequestMethod.GET)
    @ResponseBody
    public ResponseContent findByPlaceName(HttpServletRequest request){
        String placeName="";
        double x=0.0;
        double y=0.0;
        placeName = request.getParameter("placeName");
        if(""!=placeName){
            Query query=new Query();
            Criteria c1= new Criteria().where("name").is(placeName);
            query.addCriteria(c1);
            List<City> cities=cityDao.find(query,"cities");
            return responseContentUtil.getResponseContent(cities);
        }
        else{
            if(request.getParameter("x")!=""){
                x = Double.parseDouble(request.getParameter("x"));
            }
            if(request.getParameter("y")!=""){
                y = Double.parseDouble(request.getParameter("y"));
            }
            if(x!=0.0&&y!=0.0){
                Query query=new Query();
                Criteria c1= new Criteria().where("geometry").is(new GeoJsonPoint(new Point(x,y)));
                query.addCriteria(c1);
                List<City> cities=cityDao.find(query,"cities");
                return responseContentUtil.getResponseContent(cities);
            }
            return findAll();
        }
    }

}
