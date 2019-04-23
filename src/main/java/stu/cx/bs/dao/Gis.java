package stu.cx.bs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Gis地理数据的抽象类
 */
@Component
public abstract class Gis<T> implements MongoBase<T> {
    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * 插入操作，插入重复数据会出现异常
     * @param t
     * @param collectionName
     */
    @Override
    public void insert(T t, String collectionName) {
      mongoTemplate.insert(t,collectionName);
    }

}
