package cn.jdk;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * List集合转换为map集合
 * @author JDK
 *
 */
public class ListToMapUtils<K,V> {
   private String keyProperty;  //存放设置为key的属性名称
   private String valueProperty; //存放为value的属性名称
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
    * 指定成员取的其对应的属性
    * @param obj 操作对象
    * @param fileName 属性名称
    * @return 返回指定的getter方法的返回结果
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
