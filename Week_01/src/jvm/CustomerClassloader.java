package jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

/**
 * @Author weiwei
 * @Date 2020-10-16 22:51
 * @description 自定义加载器
 **/
public class CustomerClassloader extends ClassLoader{


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = null;
        try {
            file = new File(URLDecoder.decode(getClass().getResource("../Hello.xlass").getPath(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FileInputStream fileInputStream = null;
        try {
             fileInputStream = new FileInputStream(file);
            //获取文件大小字节
            int len = 0;
            byte[] buf = new byte[fileInputStream.available()];
            fileInputStream.read(buf);
            for (int i = 0; i < buf.length; ++i) {
                buf[i] = (byte)(255 - buf[i]);
            }
            return defineClass(name, buf, 0, buf.length);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        Class<?> hellClass = new CustomerClassloader().findClass("Hello");
        Object hello = hellClass.newInstance();
        Method method = hellClass.getMethod("hello");
        method.invoke(hello);
    }
}
