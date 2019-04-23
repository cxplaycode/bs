package stu.cx.bs.entity;

public abstract class GisLine implements Feature<FlightProperties> {

    @Override
    public String getType() {
        return "LineString";
    }
}
