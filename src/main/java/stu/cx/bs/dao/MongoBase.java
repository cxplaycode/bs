package stu.cx.bs.dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface MongoBase<T> {
    //添加,若出现相同主键则会提示异常
    void insert(T object, String collectionName);

//    //根据条件查找一个
//    T findOne(Map<String, Object> params, String collectionName);

    //查找所有
    List<T> findAll();

    //根据条件查找所有
    List<T> find(Query query, String collectionName);

    //根据修改更新
    void update(Query query, Update update, String collectionName);

    //根据条件删除
    void remove(Query query, String collectionName);

}
