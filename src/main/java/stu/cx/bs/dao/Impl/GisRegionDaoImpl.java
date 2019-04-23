package stu.cx.bs.dao.Impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import stu.cx.bs.dao.Gis;
import stu.cx.bs.entity.GisRegion;

import java.util.List;
@Component
public class GisRegionDaoImpl extends Gis<GisRegion> {

//    /**
//     * 插入新的地理数据
//     * @param gisRegion
//     * @param collectionName
//     */
//    @Override
//    public void insert(GisRegion gisRegion, String collectionName) {
//        mongoTemplate.insert(gisRegion);
//    }

    /**
     * 找出第一个地理数据
     * @param query
     * @param collectionName
     * @return
     */
    public GisRegion findOne(Query query, String collectionName) {
        return mongoTemplate.findOne(query,GisRegion.class,collectionName);
    }

    /**
     * 找出所有的数据
     * @return
     */
    @Override
    public List<GisRegion> findAll() {
        Query query=new Query().addCriteria(new Criteria("geometry.properties").exists(false));
        query.addCriteria(new Criteria("type").is("Feature"));
        query.limit(20);
        long count=mongoTemplate.getCollection("xzbj").countDocuments();
        List<GisRegion> gisRegions=mongoTemplate.find(query,GisRegion.class,"xzbj");
        return gisRegions;
    }

    /**
     * 根据条件找出所有的数据
     * @param query
     * @param collectionName
     * @return
     */
    @Override
    public List<GisRegion> find(Query query, String collectionName) {
        return mongoTemplate.find(query,GisRegion.class,collectionName);
    }

    /**
     * 根据条件更新数据
     * @param query
     * @param update
     * @param collectionName
     */
    @Override
    public void update(Query query, Update update, String collectionName) {
        mongoTemplate.updateMulti(query,update,collectionName);
    }

    /**
     * 创建新集合
     * @param collectionName
     */

    public void createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
    }

    /**
     * 删除指定的某条数据
     * @param query
     * @param collectionName
     */
    @Override
    public void remove(Query query, String collectionName) {
        mongoTemplate.remove(query,GisRegion.class,collectionName);
    }

    public void save(GisRegion gisRegion, String collectionName){
        mongoTemplate.save(gisRegion);
    }

}
