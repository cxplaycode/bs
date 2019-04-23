package stu.cx.bs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 使用城市这一实现类表示地理数据的点
 */
@Document(collection = "cities")
public class City extends GisPoint {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("geometry")
    private GeoJsonPoint geometry;

    @Field("properties")
    private CityProperties cityProperties;

    public void setProperties(CityProperties properties) {
        this.cityProperties = properties;
    }

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

    public void setGeometry(GeoJsonPoint geometry) {
        this.geometry = geometry;
    }

    @Override
    public CityProperties getProperties() {
        return cityProperties;
    }

    @Override
    public GeoJson getGeometry() {
        return geometry;
    }
}
