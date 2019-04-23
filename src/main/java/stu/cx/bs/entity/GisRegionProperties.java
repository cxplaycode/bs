package stu.cx.bs.entity;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 省级地理数据属性集
 */
public class GisRegionProperties{
    /**
     * 省级
     */
    @Field("sheng")
    private String province;
    /**
     * 地区
     */
    @Field("di")
    private String regoin;
    /**
     * 县级
     */
    @Field("xian")
    private String county;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegoin() {
        return regoin;
    }

    public void setRegoin(String regoin) {
        this.regoin = regoin;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "GisRegionProperties{" +
                "province='" + province + '\'' +
                ", regoin='" + regoin + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
