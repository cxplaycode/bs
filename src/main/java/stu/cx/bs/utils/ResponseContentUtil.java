package stu.cx.bs.utils;

import stu.cx.bs.entity.ResponseContent;

import java.util.List;

public class ResponseContentUtil<T>{
    public  ResponseContent getResponseContent(List<T> list){
        ResponseContent res=new ResponseContent();
        res.setCode("0");
        res.setMsg("cities find all");
        res.setCount(list.size());
        res.setData(list);
        return res;
    }
}
