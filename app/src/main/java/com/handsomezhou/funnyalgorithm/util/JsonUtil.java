package com.handsomezhou.funnyalgorithm.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by zhoujq on 2017/4/20.
 * reference:
 * 1.http://blog.csdn.net/wanghao200906/article/details/45889955
 * 2.http://stackoverflow.com/questions/10174898/how-to-check-whether-a-given-string-is-valid-json-in-java
 */

public class JsonUtil {
    private static Gson sGson = null;

    static {
        if (null == sGson) {
            sGson = new Gson();
        }
    }

    private JsonUtil() {
    }

    public enum JSON_TYPE {
        JSON_OBJECT,
        JSON_ARRAY,
        JSON_UNKNOW,
    }


    /**
     * get json type
     *
     * @param jsonSrc
     * @return
     */
    public static JSON_TYPE getJsonType(Object jsonSrc) {
        JSON_TYPE jsonType = JSON_TYPE.JSON_UNKNOW;

        do {
            if (JSONObject.NULL == jsonSrc) {
                break;
            }

            if (jsonSrc instanceof JSONObject) {
                jsonType = JSON_TYPE.JSON_OBJECT;
            } else if (jsonSrc instanceof JSONArray) {
                jsonType = JSON_TYPE.JSON_ARRAY;
            } else {
                jsonType = JSON_TYPE.JSON_UNKNOW;
            }
        } while (false);

        return jsonType;
    }

    /**
     * @param src
     * @return
     */
    public static boolean isJSONObject(String src) {
        boolean jsonObject = false;
        do {
            try {
                if (TextUtils.isEmpty(src)) {
                    break;
                }

                new JSONObject(src);
                jsonObject = true;
            } catch (JSONException ex) {
                jsonObject = false;
            }
        } while (false);
        return jsonObject;
    }

    /**
     *
     * @param src
     * @return
     */
    public static boolean isJSONArray(String src){
        boolean jsonArray=false;
        do{
            if (TextUtils.isEmpty(src)) {
                break;
            }

            try {
                new JSONArray(src);
                jsonArray = true;
            } catch (JSONException ex1) {
                jsonArray = false;
            }
        }while (false);

        return jsonArray;
    }

    /**
     * @param src
     * @return
     */
    public static boolean isJsonString(String src) {
        boolean jsonString = false;

        do {
            if (TextUtils.isEmpty(src)) {
                break;
            }

            jsonString=isJSONObject(src)||isJSONArray(src);

        } while (false);

        return jsonString;
    }

    /**
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String jsonString = null;
        if (sGson != null) {
            jsonString = sGson.toJson(object);
        }
        return jsonString;
    }

    /**
     * 转成bean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> cls) {
        T t = null;
        if (sGson != null) {
            t = sGson.fromJson(jsonString, cls);
        }
        return t;
    }

    /**
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> fromJsonToList(String jsonString, Class<T> cls) {
        List<T> list = null;
        if (sGson != null) {
            list = sGson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * @param jsonString
     * @return
     */
    public static <T> List<Map<String, T>> fromJsonToListMaps(String jsonString) {
        List<Map<String, T>> list = null;
        if (sGson != null) {
            list = sGson.fromJson(jsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> fromJsonToMaps(String jsonString) {
        Map<String, T> map = null;
        if (sGson != null) {
            map = sGson.fromJson(jsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
