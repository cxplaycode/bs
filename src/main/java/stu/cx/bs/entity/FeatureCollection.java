package stu.cx.bs.entity;

import java.util.List;

/**
 * 特征对象集合集合
 */
public interface FeatureCollection {

    String getType();


    List<Feature> getFeatures();

}
