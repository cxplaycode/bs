package stu.cx.bs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 表示一个范围区域
 */
@Document(collection="xzbj")
public  class GisRegion implements Feature<GisRegionProperties>{
    @Id
    /**
     * _id
     */
    private String id;
    /**
     * 描述的地址
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 地理位置所含自有属性
     */
    private GisRegionProperties properties;
    /**
     * 空间地理数据(表示地区范围的最外环)
     */
    private GeoJson box;
    /**
     * 空间地理数据
     */
    private GeoJson geometry;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperties(GisRegionProperties properties) {
        this.properties = properties;
    }

    public GeoJson getBox() {
        return box;
    }

    public void setBox(GeoJson box) {
        this.box = box;
    }

    public void setGeometry(GeoJson geometry) {
        this.geometry = geometry;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public GisRegionProperties getProperties() {
        return properties;
    }

    @Override
    public GeoJson getGeometry() {
        return geometry;
    }
}
