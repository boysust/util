package cn.jdk;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * List����ת��Ϊmap����
 * @author JDK
 *
 */
public class ListToMapUtils<K,V> {
   private String keyProperty;  //�������Ϊkey����������
   private String valueProperty; //���Ϊvalue����������
   public ListToMapUtils(String keyProperty,String valueProperty) {
	   this.keyProperty  = keyProperty;
	   this.valueProperty = valueProperty;
   }
   public Map<K,V> conver(List<?> allList){
	   Map<K,V> map = new HashMap<K,V>();
	   Iterator<?> iter = allList.iterator();
	   while(iter.hasNext()) {
		   Object obj = iter.next();
		   map.put((K)this.getProperty(obj,this.keyProperty),(V)this.getProperty(obj,this.valueProperty));
	   }
	   return map;
   }
   /**
    * ָ����Աȡ�����Ӧ������
    * @param obj ��������
    * @param fileName ��������
    * @return ����ָ����getter�����ķ��ؽ��
    */
   private Object getProperty(Object obj,String fileName) {
	   try {
		   Method getMethod = obj.getClass().getMethod("get", org.apache.commons.lang.StringUtils.capitalize(fileName));
		   return getMethod.invoke(obj);
	   }catch(Exception e)	 {
		   e.printStackTrace();
	   }
	   return null;
   }
}
