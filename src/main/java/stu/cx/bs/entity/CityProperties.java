package stu.cx.bs.entity;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 表示城市的自有属性
 */
public class CityProperties {
    /**
     * pm2.5含量
     */
    @Field("pm25")
    private double pm25;
    /**
     * 城市面积
     */
    @Field("area")
    private double area;

    /**
     * 人口数量
     */
    @Field("people")
    private double populationSize;

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(double populationSize) {
        this.populationSize = populationSize;
    }
}
