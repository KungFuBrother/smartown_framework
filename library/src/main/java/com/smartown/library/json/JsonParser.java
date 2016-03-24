package com.smartown.library.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiger on 2016-03-24.
 */
public class JsonParser {

    public static JsonParser instance;

    public static JsonParser getInstance() {
        if (instance == null) {
            instance = new JsonParser();
        }
        return instance;
    }


    private String getString(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return "";
        }
        String value = jsonObject.optString(key);
        if (value.equalsIgnoreCase("null")) {
            return "";
        }
        return value;
    }

    private JSONObject getJSONObject(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return new JSONObject();
        }
        JSONObject value = jsonObject.optJSONObject(key);
        if (value == null) {
            return new JSONObject();
        }
        return value;
    }

    private JSONArray getJSONArray(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return new JSONArray();
        }
        JSONArray value = jsonObject.optJSONArray(key);
        if (value == null) {
            return new JSONArray();
        }
        return value;
    }

    private int getInt(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return 0;
        }
        return jsonObject.optInt(key);
    }

    private long getLong(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return 0;
        }
        return jsonObject.optLong(key);
    }

    private double getDouble(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return 0;
        }
        double value = jsonObject.optDouble(key);
        if (Double.isNaN(value)) {
            return 0;
        }
        return value;
    }

    private boolean getBoolean(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return false;
        }
        return jsonObject.optBoolean(key);
    }

    public Object parse(Class aClass, JSONObject jsonObject) {
        try {
            Object returnObject = aClass.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                //变量名称
                String fieldName = field.getName();
                //变量类型
                Class fieldClass = field.getType();
                //变量Setter
                String fieldSetMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method fieldSetMethod = aClass.getDeclaredMethod(fieldSetMethodName, fieldClass);

                //解析注解
                String fieldJsonKey = "";
                boolean fieldIsObject = false;
                boolean fieldIsList = false;
                Class fieldListContentClass = Object.class;

                if (field.isAnnotationPresent(JsonDescription.class)) {
                    fieldJsonKey = field.getAnnotation(JsonDescription.class).key();
                    fieldIsObject = field.getAnnotation(JsonDescription.class).isObject();
                    fieldIsList = field.getAnnotation(JsonDescription.class).isList();
                    fieldListContentClass = field.getAnnotation(JsonDescription.class).listContentClass();
                } else {

                }

                if (fieldIsList) {
                    //变量为集合
                    JSONArray fieldJsonArray = getJSONArray(jsonObject, fieldJsonKey);
                    List list = new ArrayList();
                    if (fieldIsObject) {
                        for (int i = 0; i < fieldJsonArray.length(); i++) {
                            list.add(parse(fieldListContentClass, fieldJsonArray.optJSONObject(i)));
                        }
                    } else {
                        String jsonOptMethodName = "opt" + fieldListContentClass.getSimpleName().substring(0, 1).toUpperCase() + fieldListContentClass.getSimpleName().substring(1);
                        Method jsonOptMethod = JSONArray.class.getDeclaredMethod(jsonOptMethodName, int.class);
                        for (int i = 0; i < fieldJsonArray.length(); i++) {
                            list.add(jsonOptMethod.invoke(fieldJsonArray, i));
                        }
                    }
                    fieldSetMethod.invoke(returnObject, list);
                } else {
                    if (fieldIsObject) {
                        Object fieldObject = parse(fieldClass, getJSONObject(jsonObject, fieldJsonKey));
                        fieldSetMethod.invoke(returnObject, fieldObject);
                    } else {
                        String jsonGetMethodName = "get" + fieldClass.getSimpleName().substring(0, 1).toUpperCase() + fieldClass.getSimpleName().substring(1);
                        Method jsonGetMethod = getClass().getDeclaredMethod(jsonGetMethodName, JSONObject.class, String.class);
                        Object fieldJsonValue = jsonGetMethod.invoke(this, jsonObject, fieldJsonKey);
                        fieldSetMethod.invoke(returnObject, fieldJsonValue);
                    }
                }
            }
            return returnObject;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject toJsonObject(Object object) {
        try {
            JSONObject jsonObject = new JSONObject();
            Class aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                //变量名称
                String fieldName = field.getName();
                //变量类型
                Class fieldClass = field.getType();
                //变量Getter
                String fieldGetMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method fieldGetMethod = aClass.getDeclaredMethod(fieldGetMethodName);
                //变量值
                Object fieldValue = fieldGetMethod.invoke(object);

                //解析注解
                String fieldJsonKey = "";
                boolean fieldIsObject = false;
                boolean fieldIsList = false;
                Class fieldListContentClass = Object.class;

                if (field.isAnnotationPresent(JsonDescription.class)) {
                    fieldJsonKey = field.getAnnotation(JsonDescription.class).key();
                    fieldIsObject = field.getAnnotation(JsonDescription.class).isObject();
                    fieldIsList = field.getAnnotation(JsonDescription.class).isList();
                    fieldListContentClass = field.getAnnotation(JsonDescription.class).listContentClass();
                } else {

                }

                if (fieldIsList) {
                    //变量为集合
                    JSONArray fieldJsonArray = new JSONArray();
                    List list = (List) fieldValue;
                    if (fieldIsObject) {
                        for (int i = 0; i < list.size(); i++) {
                            fieldJsonArray.put(toJsonObject(list.get(i)));
                        }
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            fieldJsonArray.put(list.get(i));
                        }
                    }
                    jsonObject.put(fieldJsonKey, fieldJsonArray);
                } else {
                    if (fieldIsObject) {
                        jsonObject.put(fieldJsonKey, toJsonObject(fieldValue));
                    } else {
                        jsonObject.put(fieldJsonKey, fieldValue);
                    }
                }
            }
            return jsonObject;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
