package stu.cx.bs.controller;

import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stu.cx.bs.dao.Impl.GisRegionDaoImpl;
import stu.cx.bs.entity.GisRegion;
import stu.cx.bs.entity.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/gis/region")
public class MongoControlTest {
    @Autowired
    private GisRegionDaoImpl gisdao;

    @GetMapping(value = "/save")
    public void saveGis(GisRegion gisRegion) throws Exception {

        gisdao.insert(gisRegion,"xzbj");
    }

    @GetMapping(value="/findAll")
    public ResponseContent findAll(){
        List<GisRegion> regions=gisdao.findAll();

        ResponseContent res=new ResponseContent();
        res.setCode("0");
        res.setMsg("region find all");
        res.setCount(regions.size());
        res.setData(regions);
        return res;
    }
    @RequestMapping(value="/find",method= RequestMethod.GET)
    @ResponseBody
    public ResponseContent find(HttpServletRequest request){

        String id=request.getParameter("id");
        Query query=new Query();
        if(!"".equals(id))
        query.addCriteria(new Criteria("id").is(id));
        GisRegion region=gisdao.findOne(query,"xzbj");

        ResponseContent res=new ResponseContent();
        res.setCode("0");
        res.setMsg("region find by id");
        res.setCount(1);
        res.setData(region);
        return res;
    }



}
