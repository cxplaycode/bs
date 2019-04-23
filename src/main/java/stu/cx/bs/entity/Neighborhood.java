package stu.cx.bs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.stereotype.Component;

@Component
public class Neighborhood implements Feature{
    @Id
    private String id;

    private String name;

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

    public void setGeometry(GeoJson geometry) {
        this.geometry = geometry;
    }

    @Override
    public String getType() {
        return "Feature";
    }

    @Override
    public Object getProperties() {
        return null;
    }

    @Override
    public GeoJson getGeometry() {
        return geometry;
    }
}
