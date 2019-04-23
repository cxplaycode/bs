package stu.cx.bs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "flights")
public class Flight extends GisLine {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("fromName")
    private String fromCity;

    @Field("toName")
    private String toCity;

    private GeoJsonLineString line;

    //此时properties表示该航线的特有属性
    private FlightProperties planeLineProperties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlightProperties getPlaneLineProperties() {
        return planeLineProperties;
    }

    public void setPlaneLineProperties(FlightProperties planeLineProperties) {
        this.planeLineProperties = planeLineProperties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public GeoJsonLineString getLine() {
        return line;
    }

    public void setLine(GeoJsonLineString line) {
        this.line = line;
    }

    @Override
    public FlightProperties getProperties() {
        return planeLineProperties;
    }

    @Override
    public GeoJson getGeometry() {
        return null;
    }
}
