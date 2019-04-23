package stu.cx.bs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.stereotype.Component;

@Component()
public class Restaurant implements Feature{
    @Id
    private String id;

    private String name;

    private GeoJson location;

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

    public GeoJson getLocation() {
        return location;
    }

    public void setLocation(GeoJson location) {
        this.location = location;
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
        return location;
    }
}
