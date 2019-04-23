package stu.cx.bs.entity;

import org.springframework.data.mongodb.core.geo.GeoJson;

import java.util.List;

/**
 * 特征对象
 * properties表示feature所含有的属性
 * T:传入的是Feature的下一级的类，可以仍然是Feature，也可以是自定义的类,但需要是可迭代的
 */
public interface Feature<T> {

    /**
     * 返回type常量
     */
     String getType();
    /**
     * 获取当前特征对象的属性
     * @return
     */
      T getProperties();

    /**
     * 获取当前满足GeoJson格式的地理对象
     * @return
     */
      GeoJson getGeometry();

}
