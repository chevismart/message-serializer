package org.gamecenter.serializer.messages;

import org.gamecenter.serializer.Decoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Chevis on 14-7-29.
 */
public abstract class AbstractMessage<T> {

    protected byte[] sequenceNum;
    protected byte[] deviceId;

    public static void getObjectValue(Object object) throws Exception {
        // 拿到该类
        Class<?> clz = object.getClass();
        // 获取实体类的所有属性，返回Field数组
        java.lang.reflect.Field[] fields = clz.getDeclaredFields();

        for (java.lang.reflect.Field field : fields) {// --for() begin
            System.out.println(field.getGenericType());//打印该类的所有属性类型

            // 如果类型是String
            if (field.getGenericType().toString().equals(
                    "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                // 拿到该属性的gettet方法
                /**
                 * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                 * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                 * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                 */
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));

                String val = (String) m.invoke(object);// 调用getter方法获取属性值
                if (val != null) {
                    System.out.println("String type:" + val);
                }

            }

            // 如果类型是Integer
            if (field.getGenericType().toString().equals(
                    "class java.lang.Integer")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Integer val = (Integer) m.invoke(object);
                if (val != null) {
                    System.out.println("Integer type:" + val);
                }

            }

            // 如果类型是Double
            if (field.getGenericType().toString().equals(
                    "class java.lang.Double")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Double val = (Double) m.invoke(object);
                if (val != null) {
                    System.out.println("Double type:" + val);
                }

            }

            // 如果类型是Boolean 是封装类
            if (field.getGenericType().toString().equals(
                    "class java.lang.Boolean")) {
                Method m = (Method) object.getClass().getMethod(
                        field.getName());
                Boolean val = (Boolean) m.invoke(object);
                if (val != null) {
                    System.out.println("Boolean type:" + val);
                }

            }

            // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
            // 反射找不到getter的具体名
            if (field.getGenericType().toString().equals("boolean")) {
                Method m = (Method) object.getClass().getMethod(
                        field.getName());
                Boolean val = (Boolean) m.invoke(object);
                if (val != null) {
                    System.out.println("boolean type:" + val);
                }

            }
            // 如果类型是Date
            if (field.getGenericType().toString().equals(
                    "class java.util.Date")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Date val = (Date) m.invoke(object);
                if (val != null) {
                    System.out.println("Date type:" + val);
                }

            }
            // 如果类型是Short
            if (field.getGenericType().toString().equals(
                    "class java.lang.Short")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Short val = (Short) m.invoke(object);
                if (val != null) {
                    System.out.println("Short type:" + val);
                }

            }
            // 如果还需要其他的类型请自己做扩展

        }//for() --end

    }//if (object!=null )  ----end

    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public byte[] getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(byte[] sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public byte[] getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(byte[] deviceId) {
        this.deviceId = deviceId;
    }

    abstract public byte[] build();

    protected void buildMessage(byte[] request, Decoder decoder) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Field> fieldList = decoder.decode(request);

        for (Field field : fieldList) {
            java.lang.reflect.Field tmpField = this.getClass().getDeclaredField(field.getName());
            tmpField.setAccessible(true);
            tmpField.set(this, field.getValue());
            tmpField.setAccessible(false);
        }
    }

    public Object getFieldValue(String fieldName) throws Exception {
        java.lang.reflect.Field tmpField = this.getClass().getDeclaredField(fieldName);
        if (tmpField.getGenericType().toString().equals(
                "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
            // 拿到该属性的gettet方法
            /**
             * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
             * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
             * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
             */
            Method m = (Method) this.getClass().getMethod(
                    "get" + getMethodName(tmpField.getName()));

            String val = (String) m.invoke(this);// 调用getter方法获取属性值
            if (val != null) {
                System.out.println("String type:" + val);
                return val;
            }
        }
        return null;
    }
}
