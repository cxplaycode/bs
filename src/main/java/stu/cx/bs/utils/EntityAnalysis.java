package stu.cx.bs.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于解析实体类
 */
@Component
public class EntityAnalysis<T> {
    public Map<String, Object> parseEntity(T t) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();
        /*
         * 解析其他属性
         */
        Method[] methods = t.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && method.getModifiers() == Modifier.PUBLIC) {
                String fieldName = parse2FieldName(method.getName());
                    map.put(fieldName, method.invoke(t));
                    System.out.println("filedName:"+fieldName+",value:"+method.invoke(t));
            }
        }
        return map;
    }

    /**
     * 将get方法名转换为对应的字段名称
     *
     * @param methodName 如：getName
     * @return 如：name
     */
    private String parse2FieldName(String methodName) {
        String name = methodName.replace("get", "");
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

}
