package com.cpf.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author jieping
 * @create 2018-05-18 13:29
 * @desc 对象操作工具
 **/
public class BeanUtil {
    /**
     * 对象深度复制，运用序列化和反序列化的思想
     * @param object 想要复制的对象(需要有空构造函数)
     * @param <T>
     * @return 复制后的对象
     */
    public static  <T> T  copy(T object){
        String jsonObject = JSONObject.toJSONString(object);
        return (T) JSON.parseObject(jsonObject, object.getClass());
    }
}
