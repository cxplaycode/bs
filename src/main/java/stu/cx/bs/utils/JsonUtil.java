package stu.cx.bs.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.*;

public class JsonUtil {
    private static ObjectMapper mapper = null;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static{
//      创建json对象
        mapper = new ObjectMapper();
//      json处理时间格式
        mapper.setDateFormat(dateFormat);
    }
    //  将对象转换为json字符串
    public String toStr(Object object) {
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    //  将json字符串转换为对象
    public <T> T toObject(String json, TypeReference<T> ref) {
        T t = null;
        try {
            t = mapper.readValue(json, ref);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    public static void main(String[] args) {
        JsonUtil ju = new JsonUtil();
//      1.将字符串转换成对象
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 321);
        map.put("date", new Date());
        list.add(map);
        System.out.println("我是list : "+list);
        System.out.println("我是map : "+map);
        String result = ju.toStr(map);
        System.out.println("我是对象转换为json字符串 : "+result);
//      2.将json转换成对象
        String json = "[{\"name\":\"张三\",\"age\":321}]";
        List<Map<String,Object>> reObj = ju.toObject(json, new TypeReference<List<Map<String,Object>>>(){});
        System.out.println("我是json字符串转换为对象 : "+reObj);
    }

}

