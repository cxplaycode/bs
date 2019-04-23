package stu.cx.bs.utils;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GisQuery {
    public static Query getQuery(Map<String,Object>params){
        Query query=new Query();
        for(String param:params.keySet()){
            query.addCriteria(new Criteria(param).is(params.get(params.get(param))));
        }
        return query;
    }
    public static Update getUpdate(Map<String,Object>UpdateParams){
        Update update=new Update();
        for(String param:UpdateParams.keySet()){
            update.set(param,UpdateParams.get(param));
        }
        return update;
    }

}
