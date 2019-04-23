package stu.cx.bs.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 航线自有属性
 */
public class FlightProperties {

    /**
     * 航线长度
     */
    private String  length;
    /**
     * 所需时间,单位分钟
     */
    @Field ("spend_time")
    private String SpendTime;
    /**
     * 途经城市
     */
    @Field ("pass_city")
    private List<String> passCity;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSpendTime() {
        return SpendTime;
    }

    public void setSpendTime(String spendTime) {
        SpendTime = spendTime;
    }

    public List<String> getPassCity() {
        return passCity;
    }

    public void setPassCity(List<String> passCity) {
        this.passCity = passCity;
    }
}
