package crds.pub.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class BeanUtil {
	
	private static Logger logger = Logger.getLogger(BeanUtil.class);
	//
	private static final String SuperClassName = "java.lang.Object";
	private static final String fieldTypeString = "java.lang.String";
	private static final String fieldSymbol = "#";//多个字段之间的分隔符
	//
	private static Map<String,Object> fieldValueMap;
	public static final String GET = "get";
	public static final String SET = "set";
	
	/**
	 * 获取一个对象的所有字段属性以及父类的所有字段属性集合,并返回.如果不想获取父类的属性请将参数superField设置为false,要从中排除一些字段属性,请设置参数excludedFiledName的值.
	 * @param clazz 类对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param excludedFiledName 要排除的字段名称字符串,要排除多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将不会被返回
	 * @return Field[]
	 */
	public static Field[] getFiledAll(Class<?> clazz,boolean superField, String excludedFiledName){
		List<Field> filedList = new ArrayList<Field>();
		if(null != clazz && !clazz.getName().equals(SuperClassName)){//不为null并且不是java.lang.Object
			filedList.addAll(Arrays.asList(clazz.getDeclaredFields()));
			if(superField){
				Class<?> supClazz = clazz.getSuperclass();
				while(!supClazz.getName().equals(SuperClassName)){//如果不是java.lang.Object
					filedList.addAll(Arrays.asList(supClazz.getDeclaredFields()));
					supClazz = supClazz.getSuperclass();
				}
			}
			if(null != excludedFiledName && excludedFiledName.length()>0){
				String[] fieldNames = excludedFiledName.split(fieldSymbol);
				if(fieldNames.length > 0){
					for (String fieldName : fieldNames) {
						for (int i = 0; i < filedList.size(); i++) {
							Field field = filedList.get(i);
							if(field.getName().equals(fieldName)){
								filedList.remove(i);
								i--;
							}
						}
					}
				}
			}
		}
		return filedList.toArray(new Field[filedList.size()]);
	}
	/**
	 * 此方法相当于调用getFiledAll(Class, superField, null),详情请参阅:{@link #getFiledAll(Class, boolean, String)}
	 */
	public static Field[] getFiledAll(Class<?> clazz,boolean superField){
		return getFiledAll(clazz,superField,null);
	}
	/**
	 * 此方法相当于调用getFiledAll(Class, true, excludedFiledName),详情请参阅:{@link #getFiledAll(Class, boolean, String)}
	 */
	public static Field[] getFiledAll(Class<?> clazz,String excludedFiledName){
		return getFiledAll(clazz,true,excludedFiledName);
	}
	/**
	 * 此方法相当于调用getFiledAll(Class,true,null),详情请参阅:{@link #getFiledAll(Class, boolean, String)}
	 */
	public static Field[] getFiledAll(Class<?> clazz){
		return getFiledAll(clazz,true,null);
	}
	/**
	 * 获取一个对象的所有字段属性以及父类的所有字段属性集合中的某一部分字段属性集合,并返回.如果不想获取父类的属性请将参数superField设置为false,此方法的第三个参数与{@link #getFiledAll(Class, boolean, String)}的第三个参数的意义刚好相反.
	 * @param clazz 类对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param includeFiledName 要获取的是哪些字段,要获取多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将会被返回
	 * @return Field[]
	 */
	public static Field[] getFileds(Class<?> clazz,boolean superField, String includeFiledName){
		List<Field> filedList = new ArrayList<Field>();
		if(null != clazz && !clazz.getName().equals(SuperClassName) && null != includeFiledName){//不为null并且不是java.lang.Object
			String[] fieldNames = includeFiledName.split(fieldSymbol);
			for (String fieldName : fieldNames) {
				Field field = getFiled(clazz,superField,fieldName);
				if(null != field){
					filedList.add(field);
				}
			}
		}
		return filedList.toArray(new Field[filedList.size()]);
	}
	
	/**
	 * 此方法相当于调用getFileds(clazz, true, includeFiledName);具体详情请参阅{@link #getFileds(Class, boolean, String)}
	 */
	public static Field[] getFileds(Class<?> clazz, String includeFiledName){
		return getFileds(clazz, true, includeFiledName);
	}
	/**
	 * 获取一个对象以及父类的某个字段属性,找到则返回.如果找不到根据参数superField的设置,继续向父类查找,如果已经查找到了基类{@link java.lang.Object}还没有找到则返回null;
	 * @param clazz 类对象
	 * @param superField 如果在clazz中找不到此字段是否获取父类的属性字段,true[获取],false[不获取]
	 * @param fieldName 字段名称
	 * @return Field[]
	 */
	public static Field getFiled(Class<?> clazz,boolean superField,String fieldName){
		if(null == clazz && null == fieldName)
			return null;
		Field field = null;
		try{
			field = clazz.getDeclaredField(fieldName);
		} catch (SecurityException se) {
			logger.info(BeanUtil.class.getName() + ".getFiled(Class,boolean,String) Exception[" + se.getMessage() + "]");
			field = null;
		} catch (NoSuchFieldException nsfe) {
			logger.info(BeanUtil.class.getName() + ".getFiled(Class,boolean,String) Exception[" + nsfe.getMessage() + "]");
			field = null;
		}
		if(superField && null == field && !clazz.getName().equals(SuperClassName)){//是否要获取父类相应的字段,并且field为null,且clazz还有父类
			Class<?> supClazz = clazz.getSuperclass();
			while(null == field && !clazz.getName().equals(SuperClassName)){//如果为null并且还未遍历到Object
				try {
					field = supClazz.getDeclaredField(fieldName);
				} catch (SecurityException e) {
					field = null;
				} catch (NoSuchFieldException e) {
					field = null;
				}
				supClazz = supClazz.getSuperclass();//获取父类
			}
		}
		return field;
	}
	
	/**
	 * 获取一个对象以及父类的某个字段属性,找到则返回.如果找不到则返回null.
	 * 此方法相当于调用getFiled(clazz,true,fieldName),具体详情请参阅{@link #getFiled(Class, boolean, String)}
	 */
	public static Field getFiled(Class<?> clazz, String fieldName){
		return getFiled(clazz,true,fieldName);
	}
	/**
	 * 将一个bean对象的所有字段属性名称转换为一个String数组,并返回这个数组
	 * @param clazz 类对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param excludedFiledName 要排除的字段名称字符串,要排除多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将不会被返回
	 * @return String[]
	 */
	public static String[] filedNameToArray(Class<?> clazz, boolean superField, String excludedFiledName){
		if(null == clazz)
			return new String[]{};
		Field[] fields = getFiledAll(clazz,superField,excludedFiledName);
		List<String> fieldNameList = new ArrayList<String>();
		for (Field field : fields) {
			fieldNameList.add(field.getName());
		}
		return fieldNameList.toArray(new String[fieldNameList.size()]);
	}
	
	/**
	 * 将一个bean对象的所有属性名称转换为一个String数组,并返回这个数组.<br>
	 * 此方法相当于调用filedNameToArray(clazz,true,excludedFiledName),具体参阅{@link #filedNameToArray(Class, boolean, String)
	 * @return String[]
	 */
	public static String[] filedNameToArray(Class<?> clazz, String excludedFiledName){
		return filedNameToArray(clazz,true,excludedFiledName);
	}
	/**
	 * 将一个bean对象的所有属性名称转换为一个String数组,并返回这个数组.<br>
	 * 此方法相当于调用filedNameToArray(clazz,superField,null),具体参阅{@link #filedNameToArray(Class, boolean, String)
	 * @return String[]
	 */
	public static String[] filedNameToArray(Class<?> clazz, boolean superField){
		return filedNameToArray(clazz,superField,null);
	}
	/**
	 * 将一个bean对象的所有属性名称转换为一个String数组,并返回这个数组.<br>
	 * 此方法相当于调用filedNameToArray(clazz,true,null),具体参阅{@link #filedNameToArray(Class, boolean, String)
	 * @return String[]
	 */
	public static String[] filedNameToArray(Class<?> clazz){
		return filedNameToArray(clazz,true,null);
	}
	/**
	 * 从beanObj对象中获取字段名称为fieldName的值,如果beanObje为null或fieldName不符合字段定义规则(并且还要有此字段对应的get方法)或beanObje中不存在此字段则返回null;
	 * @param beanObj 将要被获取值的bean对象
	 * @param fieldName 将要被获取值的bean对象的字段名称
	 * @return Object
	 */
	public static Object getProperty(Object beanObj, String fieldName) {
		if(null == beanObj || null == fieldName || "".equals(fieldName.trim()))
			return null;
		try {
			String methodName = beanMethod(GET, fieldName,true);
			Method method = beanObj.getClass().getMethod(methodName, new Class[] {});
			return method.invoke(beanObj);
		} catch (SecurityException se) {
			logger.info(BeanUtil.class.getName() + ".getProperty(Object,String) Exception[" + se.getMessage() + "]");
		} catch (NoSuchMethodException nme) {
			logger.info(BeanUtil.class.getName() + ".getProperty(Object,String) Exception[" + nme.getMessage() + "]");
		} catch (IllegalArgumentException iae) {
			logger.info(BeanUtil.class.getName() + ".getProperty(Object,String) Exception[" + iae.getMessage() + "]");
		} catch (IllegalAccessException iae) {
			logger.info(BeanUtil.class.getName() + ".getProperty(Object,String) Exception[" + iae.getMessage() + "]");
		} catch (InvocationTargetException ite) {
			logger.info(BeanUtil.class.getName() + ".getProperty(Object,String) Exception[" + ite.getMessage() + "]");
		}
		return null;
	}
	/**
	 * 将value赋予beanObj对象中的字段名称为fieldName的字段
	 * @param beanObj 将要被赋予的bean对象
	 * @param fieldName 将要被赋予bean对象的字段名称
	 * @param value 将要被赋予beanObj对象中的字段名称为fieldName的值
	 * @return boolean true[赋予值成功] false[赋予值失败]
	 */
	public static boolean setProperty(Object beanObj, String fieldName, Object value) {
		if(null == beanObj || null == fieldName || "".equals(fieldName.trim()))
			throw new NullPointerException("call " + BeanUtil.class.getName()+".setProperty(Object,String,Object),params[beanObj or fieldName] is null");
		Field field = getFiled(beanObj.getClass(),fieldName);
		if(null != field){
			return setProperty(beanObj,field,value);
		}
		return false;
	}
	
	/**
	 * 将value赋予beanObj对象中的字段名称为fieldName的字段
	 * @param beanObj 将要被赋予的bean对象
	 * @param field 将要被赋予bean对象的字段
	 * @param value 将要被赋予beanObj对象中的字段名称为field的值
	 * @return boolean true[赋予值成功] false[赋予值失败]
	 */
	public static boolean setProperty(Object beanObj, Field field, Object value) {
		if(null == beanObj || null == field)
			return false;
		try {
			String methodName = beanMethod(SET, field.getName(),true);
			Method method = beanObj.getClass().getMethod(methodName, new Class[] {field.getType()});
			method.invoke(beanObj, new Object[]{value});
			return true;
		} catch (SecurityException se) {
			logger.info(BeanUtil.class.getName() + ".setProperty(Object,Field,Object) Exception[" + se.getMessage() + "]");
		} catch (NoSuchMethodException nme) {
			logger.info(BeanUtil.class.getName() + ".setProperty(Object,Field,Object) Exception[" + nme.getMessage() + "]");
		} catch (IllegalArgumentException iae) {
			logger.info(BeanUtil.class.getName() + ".setProperty(Object,Field,Object) Exception[" + iae.getMessage() + "]");
		} catch (IllegalAccessException iae) {
			logger.info(BeanUtil.class.getName() + ".setProperty(Object,Field,Object) Exception[" + iae.getMessage() + "]");
		} catch (InvocationTargetException ite) {
			logger.info(BeanUtil.class.getName() + ".setProperty(Object,Field,Object) Exception[" + ite.getMessage() + "]");
		}
		return false;
	}
	/**
	 * 将某一个Bean对象的字段名称组合为get或set方法.
	 * <br>如:<br>
	 * beanMethod("set","fieldName",null);return setFieldName<br>
	 * beanMethod("get","fieldName",false);return getFIELDNAME<br>
	 * beanMethod("get","fieldName",true);return getFieldname<br>
	 * beanMethod("ss","field",true);throw IllegalArgumentException<br>
	 * beanMethod("get","",false);throw NullPointerException<br>
	 * beanMethod("get",null,false);throw NullPointerException<br>
	 * @param set_get 组合标志,这个参数的值必须为set或get否则会抛出异常IllegalArgumentException异常
	 * @param fieldName bean对象的字段名称,不能为空,否则会抛出NullPointerException异常
	 * @param flag 字段名第一个字符之后的字符串是否进行大小写转换,null不进行大小写转换,false大写,true小写
	 * @exception IllegalArgumentException
	 * @exception NullPointerException
	 */
	public static String beanMethod(String set_get,String fieldName,Boolean flag){
		if(!GET.equals(set_get) && !SET.equals(set_get))
			throw new IllegalArgumentException(BeanUtil.class.getName()+"#beanMethod(String,String,Boolean) param set_get is not [set get],please Reference "+BeanUtil.class.getName()+".GET or "+BeanUtil.class.getName()+".SET");
		if(null == fieldName || "".equals(fieldName.trim()))
			throw new NullPointerException(BeanUtil.class.getName()+"#beanMethod(String,String,Boolean) param fieldName is null");
		fieldName = fieldName.trim();
		return null == flag ? set_get+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1)
				: flag ? set_get+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1).toLowerCase()
				: set_get+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1).toUpperCase();
	}
	
	/**
	 * 获取bean对象的字段值,返回值为:{@code Map<fieldName,fieldValue>},此方法保证返回的都是有值的字段,字段值为null或为""的不在返回的Map范围内.
	 * @param bean bean对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param excludedFiledName 要排除的字段名称字符串,要排除多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将不会被返回
	 * @param warnProcessFiledNameMap 待处理字段值,Map<"field1#field2",BeanUtilProcess>,即Map&lt;String,BeanUtilProcess&gt;.详情请参阅{@link #processObjectValue(Map&lt;String,Object&gt;, Map&lt;String,BeanUtilProcess&gt;)}
	 * @return Map<String,?>
	 */
	public static Map<String,Object> getFiledNamesAndValues(Object bean,boolean superField, String excludedFiledName,Map<String,BeanProc> warnProcessFiledNameMap) {
		if(null == bean)
			throw new NullPointerException(BeanUtil.class.getName()+"#getFiledNamesAndValues() param bean is null");
		Field[] fields = getFiledAll(bean.getClass(), superField, excludedFiledName);
		if(null != fields){
			fieldValueMap = new HashMap<String, Object>();
			for (Field field : fields) {
				String fieldName = field.getName();
				Object fieldValue = getProperty(bean, fieldName);
				if(null != fieldValue){
					if(fieldTypeIsString(field) && "".equals(fieldValue.toString())) {//如果是字符串并且为""
						continue;
					}
					fieldValueMap.put(fieldName, fieldValue);
				}
			}
			processObjectValue(fieldValueMap,warnProcessFiledNameMap);
			return fieldValueMap;
		}
		return null;
	}
	/**
	 * 获取bean对象的字段值,返回值为:{@code Map<fieldName,fieldValue>}.<br>
	 * 此方法相当于调用getFiledNamesAndValues(bean,superField,excludedFiledName,null),详情请参阅{@link #getFiledNamesAndValues(Object, boolean, String, Map)}
     */
    public static Map<String,Object> getFiledNamesAndValues(Object bean,boolean superField, String excludedFiledName) {
    	return getFiledNamesAndValues(bean,superField,excludedFiledName,null);
    }
	/**
	 * 获取bean对象的字段值,返回值为:{@code Map<fieldName,fieldValue>}.<br>
	 * 此方法相当于调用getFiledNamesAndValues(bean,false,excludedFiledName,null),详情请参阅{@link #getFiledNamesAndValues(Object, boolean, String, Map)}
	 */
	public static Map<String,Object> getFiledNamesAndValues(Object bean, String excludedFiledName) {
		return getFiledNamesAndValues(bean,false,excludedFiledName,null);
	}
	/**
	 * 获取bean对象的字段值,返回值为:{@code Map<fieldName,fieldValue>}.<br>
	 * 此方法相当于调用getFiledNamesAndValues(bean,superField,null,null),详情请参阅{@link #getFiledNamesAndValues(Object, boolean, String, Map)}
	 */
	public static Map<String,Object> getFiledNamesAndValues(Object bean,boolean superField) {
		return getFiledNamesAndValues(bean,superField,null,null);
	}
	
	/**
	 * 获取bean对象的字段值,返回值为:{@code Map<fieldName,fieldValue>}.<br>
	 * 此方法相当于调用getFiledNamesAndValues(bean,false,null,null),详情请参阅{@link #getFiledNamesAndValues(Object, boolean, String, Map)}
	 */
	public static Map<String,Object> getFiledNamesAndValues(Object bean) {
		return getFiledNamesAndValues(bean,false,null,null);
	}
	/**
	 * 通过HttpServletRequest对象获取页面中的标签的值,然后将这些值赋予beanObj对象的相应字段,此方法的步骤为:<br>
	 * 1.根据beanObj对象获取相应的字段名称;<br>
	 * 2.根据这些字段名称从HttpServletRequest中获取相应的值(前提是这些字段名称要与页面中的标签控件的名称[name属性的值]一样);<br>
	 * 3.如果可以从HttpServletRequest中获取相应字段的值则给beanObj的相应字段赋值,否则忽略.<br>
	 * 注意:<br>
	 * 1.对于参数beanObj,superField,excludedFiledName请参阅方法{@link #getFiledAll(Class, boolean, String)}<br>
	 * 2.参数warnProcessFiledNameMap,即获取页面中的相应字段的值之后要怎样处理,处理完之后再赋予beanObj相应的字段
	 * @param beanObj bean对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param excludedFiledName 要排除的字段名称字符串,要排除多个字段请用#进行分割,如:"field1#field2#field3#field4",存在这些字段名称的属性字段将不会被返回
	 * @param request {@link javax.servlet.http#HttpServletRequest}对象
	 * @param pageFormFieldSuffix 页面字段名称后缀,此参数是为了字段名称在页面中的一些扩展,如:beanObj对象有一个字段为:userName,而页面中为了显示多个userName,于是采用名称为name="userName_list"控件了记录userName的具体的值.此时此参数的值就为"_list",调用此方法之后
	 * @param pageFormFieldIndex {@code 即要获取第几个,<0则默认为0.有时页面中有很多个<input type="text" name="userName"/>,但是只想获取第5个,则此参数就为5,执行此方法后会将页面中的第5个控件<input type="text" name="userName"/>的值赋予beanObj对象的相应字段.}
	 * @return void
	 * <br><b>example:</b><pre>
	 * beanObj有两个字段userID,userName
	 * 页面中有标签控件为:
	 * {@code <input type="hidden" name="userID" value="登录用户ID"><input type="hidden" name="userID_list" value="用户列表ID1"><input type="hidden" name="userID_list" value="用户列表ID2">}
	 * {@code <input type="hidden" name="userName" value="登录用户名称"><input type="text" name="userName_list" value="用户列表名称1"><input type="text" name="userName_list" value="用户列表名称2">}
	 * 应用:
	 * setValuesByRequest(beanObj,false,null,request,null,0);//此时beanObj的字段属性值为:{userID=登录用户ID,userName=登录用户名称}
	 * setValuesByRequest(beanObj,false,null,request,null,1);//此时beanObj的字段属性值为:{userID=defaulvalue,userName=defaulvalue},此时不赋值,在调用此方法之前该对象beanObj的userID和userName是什么就是什么.
	 * setValuesByRequest(beanObj,false,null,request,"_list",0);//此时beanObj的字段属性值为:{userID=用户列表ID1,userName=用户列表名称1}
	 * setValuesByRequest(beanObj,false,null,request,"_list",1);//此时beanObj的字段属性值为:{userID=用户列表ID2,userName=用户列表名称2}
	 * setValuesByRequest(beanObj,false,null,request,"_list",1);//此时beanObj的字段属性值为:{userID=用户列表ID2,userName=这是我吗?用户列表名称2}
	 * setValuesByRequest(beanObj,false,"userID#userName",request,"_list",1);//此时beanObj的字段属性值为:{userID=defaulvalue,userName=defaulvalue},此时不赋值,在调用此方法之前该对象beanObj的userID和userName是什么就是什么.因为两个字段都已经被排除,即第三个参数为排除字段
	 * </pre>
	 */
	public static void setValuesByRequest(Object beanObj, boolean superField, String excludedFiledName, HttpServletRequest request,String pageFormFieldSuffix, int pageFormFieldIndex) {
		if(null == beanObj || null == request)
			throw new NullPointerException("call "+BeanUtil.class.getName()+".setValuesByRequest(),params[beanObj or request] is null.");
		pageFormFieldSuffix = null == pageFormFieldSuffix ? "" : pageFormFieldSuffix;
		pageFormFieldIndex = pageFormFieldIndex < 0 ? 0 : pageFormFieldIndex;
		//获取beanObj对象的相关字段
		Field fieldList[] = getFiledAll(beanObj.getClass(), superField, excludedFiledName);
		for (int i = 0; i < fieldList.length; i++) {
			Field field = fieldList[i];
			String fieldName = field.getName();
			String[] values = request.getParameterValues(fieldName+pageFormFieldSuffix);
			if(null != values){
				setProperty(beanObj, field, values[pageFormFieldIndex]);
			}
		}
	}
	/**
	 * 通过HttpServletRequest对象获取页面中的标签的值,然后将这些值赋予beanObj对象的相应字段,此方法的步骤为:<br>
	 * 1.根据beanObj对象获取相应的字段名称;<br>
	 * 2.根据这些字段名称从HttpServletRequest中获取相应的值(前提是这些字段名称要与页面中的标签控件的名称[name属性的值]一样);<br>
	 * 3.获取值之后将这些值赋予beanObj对象的相应字段.<br>
	 * 注意:<br>
	 * 此方法相当于调用setValuesByRequest(beanObj, superField, null, request, pageFormFieldSuffix, pageFormFieldIndex),详情请参阅{@link #setValuesByRequest(Object, boolean, String, HttpServletRequest, String, int)};
	 * @param beanObj bean对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param request {@link javax.servlet.http#HttpServletRequest}对象
	 * @param pageFormFieldSuffix 页面字段名称后缀,此参数是为了字段名称在页面中的一些扩展,如:beanObj对象有一个字段为:userName,而页面中为了显示多个userName,于是采用名称为name="userName_list"控件了记录userName的具体的值.此时此参数的值就为"_list",调用此方法之后
	 * @param pageFormFieldIndex {@code 即要获取第几个,<0则默认为0.有时页面中有很多个<input type="text" name="userName"/>,但是只想获取第5个,则此参数就为5,执行此方法后会将页面中的第5个控件<input type="text" name="userName"/>的值赋予beanObj对象的相应字段.}
	 * @return void
	 */
	public static void setValuesByRequest(Object beanObj, boolean superField, HttpServletRequest request,String pageFormFieldSuffix, int pageFormFieldIndex) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		setValuesByRequest(beanObj, superField, null, request, pageFormFieldSuffix, pageFormFieldIndex);
	}
	/**
	 * 通过HttpServletRequest对象获取页面中的标签的值,然后将这些值赋予beanObj对象的相应字段,此方法的步骤为:<br>
	 * 1.根据beanObj对象获取相应的字段名称;<br>
	 * 2.根据这些字段名称从HttpServletRequest中获取相应的值(前提是这些字段名称要与页面中的标签控件的名称[name属性的值]一样);<br>
	 * 3.获取值之后将这些值赋予beanObj对象的相应字段.<br>
	 * 注意:<br>
	 * 此方法相当于调用setValuesByRequest(beanObj, superField, null, request, null, 0),详情请参阅{@link #setValuesByRequest(Object, boolean, String, HttpServletRequest, String, int)};
	 * @param beanObj bean对象
	 * @param superField 是否获取父类的属性字段,true[获取],false[不获取]
	 * @param request {@link javax.servlet.http#HttpServletRequest}对象
	 * @param pageFormFieldSuffix 页面字段名称后缀,此参数是为了字段名称在页面中的一些扩展,如:beanObj对象有一个字段为:userName,而页面中为了显示多个userName,于是采用名称为name="userName_list"控件了记录userName的具体的值.此时此参数的值就为"_list",调用此方法之后
	 * @param pageFormFieldIndex {@code 即要获取第几个,<0则默认为0.有时页面中有很多个<input type="text" name="userName"/>,但是只想获取第5个,则此参数就为5,执行此方法后会将页面中的第5个控件<input type="text" name="userName"/>的值赋予beanObj对象的相应字段.}
	 * @return void
	 */
	public static void setValuesByRequest(Object beanObj, boolean superField, HttpServletRequest request) {
		setValuesByRequest(beanObj, superField, null, request, null, 0);
	}
	/**
	 * 通过HttpServletRequest对象获取页面中的标签的值,然后将这些值赋予beanObj对象的相应字段,此方法的步骤为:<br>
	 * 1.根据beanObj对象获取相应的字段名称;<br>
	 * 2.根据这些字段名称从HttpServletRequest中获取相应的值(前提是这些字段名称要与页面中的标签控件的名称[name属性的值]一样);<br>
	 * 3.获取值之后将这些值赋予beanObj对象的相应字段.<br>
	 * 注意:<br>
	 * 此方法相当于调用setValuesByRequest(beanObj,false,null,request,null,0),详情请参阅{@link #setValuesByRequest(Object, boolean, String, HttpServletRequest, String, int)};
	 * @param beanObj bean对象
	 * @param request {@link javax.servlet.http#HttpServletRequest}对象
	 * @return void
	 */
	public static void setValuesByRequest(Object beanObj, HttpServletRequest request) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		setValuesByRequest(beanObj,false,null,request,null,0);
	}
	/**
	 * 将beanValueMap中某些字段的值进行处理,处理方式由接口{@link BeanProc#valueProcess(Object)}进行处理.<br>
	 * 如果beanValueMap的key存在于warnProcessFiledNameMap中的key,则调用warnProcessFiledNameMap中的value来处理beanValueMap中的value.即warnProcessFiledNameMap的value就是接口{@link BeanProc#valueProcess(Object)}的实例
	 * <br>example:<br>
	 * Map<Strng,Object> valueMap = new HashMap<Strng,Object>();<br>
	 * valueMap.put("filed1","你好");<br> valueMap.put("filed2","你坏");<br> valueMap.put("filed3","中国");<br>
	 * Map<Strng,Object> procMap = new HashMap<Strng,Object>();<br>
	 * procMap.put("filed1#filed3",new BeanUtilProcess(){public Object processValue(Object object){<br>
	 * 		return "你们真好!"<br>
	 * }});<br>
	 * 处理之后valueMap结果将变为:<br>
	 * {filed1=你们真好!,filed2=你坏,filed3=你们真好!}
	 * @param beanValueMap {@code Map<String,Object>,即Map<fieldName,fieldValue>}
	 * @param warnProcessFiledNameMap {@code Map<String,BeanUtilProcess>,即Map<fieldNameString,BeanUtilProcess>},如果多个字段的处理方式一样,此参数的key值请用分隔符"#"分开
	 * @return void
	 */
	public static void processObjectValue(Map<String,Object> beanValueMap, Map<String,BeanProc> warnProcessFiledNameMap){
		if(null == beanValueMap || null == warnProcessFiledNameMap)
			return;
		Set<String> warnFieldSet = warnProcessFiledNameMap.keySet();
		for (String warnFieldKey : warnFieldSet) {
			if(null != warnFieldKey && !"".equals(warnFieldKey.trim())){
				String[] fieldNames = warnFieldKey.trim().split(fieldSymbol);
				for (int i = 0; i < fieldNames.length; i++) {
					String fieldName = fieldNames[i];
					if(null != fieldName && !"".equals(fieldName.trim())){
						BeanProc bv = warnProcessFiledNameMap.get(warnFieldKey);
						Object valueObj = beanValueMap.get(fieldName);
						if(null != bv && null != valueObj){
							beanValueMap.put(fieldName, bv.valueProcess(valueObj));
						}
					}
				}
			}
		}
	}
	/**
	 * 从warnProcessFiledNameMap中的key中查找beanObj对象中的字段名称是否与此key相等,如果相等则map.get(key)获取相应的{@link BeanProc#valueProcess(Object)}对象实例的方法进行处理此字段的值.
	 * @param beanObj 要处理的bean对象
	 * @param warnProcessFiledNameMap 待处理字段值,Map<"field1#field2",BeanUtilProcess>,即Map&lt;String,BeanUtilProcess&gt;.详情请参阅{@link #processObjectValue(Map&lt;String,Object&gt;, Map&lt;String,BeanUtilProcess&gt;)}
	 * @return void
	 */
	public static void processObjectValue(Object beanObj, Map<String,BeanProc> warnProcessFiledNameMap){
		if(null == beanObj || null == warnProcessFiledNameMap)
			return;
		Set<String> warnFieldSet = warnProcessFiledNameMap.keySet();
		for (String warnFieldKey : warnFieldSet) {
			if(null != warnFieldKey && !"".equals(warnFieldKey.trim())){
				String[] fieldNames = warnFieldKey.trim().split(fieldSymbol);
				for (int i = 0; i < fieldNames.length; i++) {
					String fieldName = fieldNames[i];
					if(null != fieldName && !"".equals(fieldName.trim())){
						fieldName = fieldName.trim();
						Object value = getProperty(beanObj,fieldName);//获取对象beanObj的字段为fieldName的值
						BeanProc bv = warnProcessFiledNameMap.get(warnFieldKey);//获取要处理值的对象实例
						if(null != value && null != bv){
							value = bv.valueProcess(value);
							setProperty(beanObj,fieldName,value);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 判断某个字段的类型是否是字符串类型
	 * @param field Field对象
	 * @return 
	 */
	protected static boolean fieldTypeIsString(Field field){
		return field.getType().getName().equals(fieldTypeString);
	}
}
