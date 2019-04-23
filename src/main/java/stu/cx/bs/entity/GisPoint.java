package stu.cx.bs.entity;


/**
 * 抽象点类,
 * 1.实现Feature的getType（）方法，表示返回的是一个point类型。
 * 2.确认泛型T是String，表示点的其他属性只能是一个String类型
 */
public abstract class GisPoint implements Feature<CityProperties>{


    @Override
    public String getType() {
        return "Point";
    }


}
