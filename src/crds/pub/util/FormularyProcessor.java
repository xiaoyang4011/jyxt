package crds.pub.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.sun.tools.javac.Main;

public class FormularyProcessor extends CustomMethod{
	private static Logger logger = Logger.getLogger(FormularyProcessor.class);

	// 生成java文件
	@SuppressWarnings("unchecked")
	private static void generateJavaSource(Map<String, String> map, File file,
			String classname) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new FileOutputStream(file));
		out.println("import java.util.*;");
		out.println("public class " + classname
				+ " extends crds.pub.util.FormularyProcessor{");
		out.println("public Map<String, String> getValue(){");
		out
		.println("Map<String, String> resulr_map=new HashMap<String, String>();");
		out.println("double result=0.0;");
		out.println("String err_info=\"计算有误的指标：\";");
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();
			out.println("try{");
			out.println("result=" + val + ";");

			out
			.println("if(result==Double.POSITIVE_INFINITY || result==Double.NEGATIVE_INFINITY){");
			out.println("err_info+=\"" + key + ",\";");
			out.println("result=0.0;}");
			out.println("}catch(Exception e){");
			out.println("err_info+=\"" + key + ",\";");
			out.println("}");
			// 放入错误信息
			out.println("if(!\"计算有误的指标：\".equals(err_info)){");
			out
			.println("resulr_map.put(\"err_info\", err_info.substring(0,err_info.length()-1)+\".\");");
			out.println("}");
			out.println("resulr_map.put(\"" + key
					+ "\",String.valueOf(result));");

		}
		out.println("return resulr_map;");
		out.println("}");
		out.println("}");

		out.flush();
		out.close();

	}

	// 生成并编译文件 0：正常 1：异常
	private static int compile(Map<String, String> map, String path, File file,
			String filename, String classname) {

		// 生成java代码文件
		try {
			generateJavaSource(map, file, classname);

			String[] arg = new String[] { "-d", path,
					path + File.separator + filename };
			int status = Main.compile(arg);
			return status;
		} catch (Exception e) {
			return 1;
		}
	}

	// 加载class，执行计算方法获取结果
	@SuppressWarnings("unchecked")
	private static Map<String, String> exec(String classname) {
		Map<String, String> result = null;
		try {
			Class cls = Class.forName(classname);
			Object co = cls.newInstance();
			// 获取计算结果
			Method met = cls.getMethod("getValue");
			Object obj = met.invoke(co);
			result = (Map<String, String>) obj;
		} catch (Exception e) {
			logger.error("调用计算方法错误");
		}
		return result;
	}

	/**
	 * 
	 * @param map
	 *            传入的计算信息 key：指标名称 value：算式
	 * @return 计算结果Map key：指标名称 value：值 key:err_info value:错误信息
	 */
	public static Map<String, String> getResult(Map<String, String> map) {
		Map<String, String> result = new HashMap<String, String>();
		String msg = null;

		if(map==null || map.size()==0){
			msg = "传入的Map无法计算";
			result.put("err_info", msg);
			logger.error(msg);
			return result;
		}

		String path =FormularyProcessor.class.getResource("/").toString();
		path = path.substring(6, path.length() - 1);
		try {
			File file = File.createTempFile("RunTime", ".java", new File(path));
			String filename = file.getName();
			String classname = filename.substring(0, filename.indexOf('.'));
			int status = compile(map, path, file, filename, classname);
			// 删除java文件
			file.delete();
			if (status == 0) {
				result = exec(classname);
				// 删除class文件
				file = new File(file.getParent() + File.separator + classname
						+ ".class");
				file.delete();
			} else {
				msg = "编译java文件错误";
				result.put("err_info", msg);
				logger.error(msg);
			}
		} catch (Exception e) {
			msg = "生成java文件错误";
			result.put("err_info", msg);
			logger.error(msg);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Map map = new HashMap<String, String>();
		map.put("a", "1+2.0/0.8");
		map.put("b", "(1-2)*least(4,3,4)");
		map.put("c", "1*2/0.11");
	
	}
}