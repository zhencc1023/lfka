package baseUntil;

import java.io.*;

public class ToolsUnit {


    /**
     * Object.clone()方法不做任务处理情况下，引用类型都是浅拷贝，导致数据异常，基本数据类型不受影响，so
     * 利用序列化和反序列化对java对象进行深都克隆，消除引用类型的数据混淆
     * @param object
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T deepCloneObject(T object) {
        T deepOb=null;
        ByteArrayOutputStream baos=null;
        ObjectOutputStream oos=null;
        ByteArrayInputStream bais=null;
        ObjectInputStream ois=null;
        try {
            baos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(baos);
            oos.writeObject(object);
            bais=new ByteArrayInputStream(baos.toByteArray());
            ois=new ObjectInputStream(bais);
            deepOb=(T)ois.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }finally {

            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if(oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bais!=null){
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
           return  deepOb;
        }

}
