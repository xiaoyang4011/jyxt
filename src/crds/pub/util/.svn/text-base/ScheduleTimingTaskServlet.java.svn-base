/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.io.File;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import crds.pub.upload.UploadTool;

/**
 * @specification : 定时任务
 * @version 1.0
 * @auther MaCi Hotesion
 * @date 2010-08-06 16:17:35
 * @email houtingsong163@163.com
 */
public class ScheduleTimingTaskServlet  extends HttpServlet implements HttpSessionListener{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ScheduleTimingTaskServlet.class);//日志

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ServletContext servletContext = null;
	private static ServletContext staticServletContext = null;

	/**
	 * @tip :
	 * @see javax.servlet.GenericServlet#init()
	 * @specification :定时任务定时器
	 * @param :NAN
	 * @return :void
	 * @exception :ServletException
	 */
	public void init() throws ServletException {
		staticServletContext = servletContext = this.getServletContext();
		//初始化系统文件或目录
		initSysFileOrDir();
		//服务器启动，初始化用户状态为"不在线"
		initUserState();
		// 定时任务
		try {
			//1. 获取系统的系统类型配置信息.
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			InitCrds initCrds = (InitCrds)ctx.getBean("initCrds");
			String sysType;//系统类型
			if(null == initCrds || (!(sysType=initCrds.getSystype()).equals("F"))){
				//logger.warn("未知的系统类型:任务调度将不能执行.");
				return;
			}
			//logger.info("\nCRDS系统应用类型:"+sysType);
			//2. 获取任务调度的配置信息
			String conFigString = getInitParameter("procedureTaskTimeRunList");
			//如果不为空则清除换行符和tab符并清除两边的空格
			if(null==conFigString || (conFigString=conFigString.replaceAll("\\n|\\t", "").trim()).length()==0){
				return;
			}
			String[] procConfigs = conFigString.split("#-#");
			for(int i = 0;i<procConfigs.length;i++){
				String[] config = procConfigs[i].trim().split("#");
				if(config.length!=5 || config[0].indexOf(sysType)==-1){
					//logger.warn("第"+(i+1)+"个配置项不会将其加入到任务列表中.\n系统类型为:"+sysType+"\n配置项为:"+procConfigs[i]);
					continue;
				}
				String procNameAndParam = config[1].trim();//存储过程名称及参数
				String startRunTime = config[2].trim();//服务器启动后延迟运行的时间
				String delayRunTime = config[3].trim();//延迟运行周期时间(毫秒)
				String comment = config[4].trim();//存储过程对应的说明
				//
				int index = procNameAndParam.indexOf("(");
				long execDelay = -1;//执行前的延迟时间
				Date execDate = null;//某个时间开始执行
				long period = -1;//周期
				//存储过程名称及延迟时间处理
				if(index<1 || index>procNameAndParam.lastIndexOf(")") || (delayRunTime.length()!=0 && (period=parseData(delayRunTime))<600000)){
					//logger.warn("第"+(i+1)+"个配置项不会将其加入到任务列表中,延迟运行周期时间必须>=600000毫秒.\n系统类型为:"+sysType+"\n配置项为:"+procConfigs[i]);
					continue;
				}
				//执行前的延迟时间处理
				if(startRunTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") || startRunTime.matches("\\d{2}:\\d{2}:\\d{2}")) {
        			if(startRunTime.matches("\\d{2}:\\d{2}:\\d{2}")) {
        				String[] time = startRunTime.split(":");
            			Calendar toDayDateCal = Calendar.getInstance();
            			toDayDateCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            			toDayDateCal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            			toDayDateCal.set(Calendar.SECOND, Integer.parseInt(time[2]));
            			toDayDateCal.set(Calendar.MILLISECOND, 0);
            			execDate = toDayDateCal.getTime();
        			} else {
        				execDate = dateFormat.parse(startRunTime);
        			}
        			if(execDate.compareTo(new Date())<0) {
        				Calendar execDateCal = Calendar.getInstance();
        				execDateCal.setTime(execDate);
        				Calendar cal = Calendar.getInstance();
        				cal.add(Calendar.DAY_OF_MONTH, 0);
        				cal.set(Calendar.HOUR_OF_DAY, execDateCal.get(Calendar.HOUR_OF_DAY));
        				cal.set(Calendar.MINUTE, execDateCal.get(Calendar.MINUTE));
        				cal.set(Calendar.SECOND, execDateCal.get(Calendar.SECOND));
        				execDate = cal.getTime();
        			}
        		} else if((execDelay = parseData(startRunTime))<120000){
        			execDelay = 120000;
        		}
				//3.3 设置定时任务,任务调度,根据配置调用不同的方法.
            	Timer timer = new Timer();
        		TimerTask task = getTimerTask(procNameAndParam, comment, false);
    			if(period > 0){
	        		if(execDelay > 0){
        				timer.schedule(task,execDelay,period);
        				//logger.info("存储过程["+procNameAndParam+"]将于"+execDelay+"毫秒后开始执行,以后每"+period+"毫秒执行一次.");
        			} else if(execDate != null){
        				timer.schedule(task,execDate,period);
        				//logger.info("存储过程["+procNameAndParam+"]将于"+dateFormat.format(execDate)+"后开始执行,以后每"+period+"毫秒执行一次.");
        			}
        		} else {
        			if(execDelay > 0){
        				timer.schedule(task,execDelay);
        				//logger.info("存储过程["+procNameAndParam+"]将于"+execDelay+"毫秒后开始执行,以后不会将再次执行.");
        			} else if(execDate != null){
        				timer.schedule(task,execDate);
        				//logger.info("存储过程["+procNameAndParam+"]将于"+dateFormat.format(execDate)+"后开始执行,以后不会将再次执行.");
        			}
        		}
			}
            //logger.info("\nScheduleTimingTaskServlet 启动成功.");
		} catch (Exception e) {
			logger.error("schedule task servlet initialization error ");
			throw new ServletException(e);
		} finally {
		}
	}
	private TimerTask getTimerTask(String procConInfo,String comment, boolean isOtherProcImpl) throws Exception {
		//其它存储过程特定的实现方案
		if(isOtherProcImpl) {
			return new TimerTask(){
				public void run() {
					//待以后需求扩展
				}
			};
		} else {//返回公共的任务实现方案
			return new CommProcTask(procConInfo,comment);
		}
	}

	/**
	 * @specification :初始化用户状态为“不在线”
	 * @param :NAN
	 * @return :void
	 * @exception :ServletException
	 */
	private void initUserState() throws ServletException {
		try {
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
			JdbcTemplateExtend jdbcTemplate = (JdbcTemplateExtend) ctx.getBean("jdbcTemplate");
			jdbcTemplate.update("update system_user_info set out_in_state='1'");
		} catch (Exception e) {
			logger.error("初始化用户状态错误，请检查数据库连接是否异常。");
			throw new ServletException(e);
		}
	}
	//创建session
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
	}
	//销毁session
	public void sessionDestroyed(HttpSessionEvent event) {
		if (event.getSession() != null) {
			// 更新用户状态
			FormUserOperation formUser = Constant.getFormUserOperation(event.getSession());
			if (formUser != null) {
				CommonMethod.updateUserState(formUser.getUser_id(), "1", "");
			}
			//去除放入session的值
			event.getSession().removeAttribute(Constant.LoginUserSessionKey);
			event.getSession().removeAttribute("formComparision");
		}
	}
	//将数字类型的字符串转换为long
	private static long parseData(String data) throws ServletException{
		if(Pattern.matches("\\d+", data)){
			return Long.parseLong(data);
		} else if(Pattern.matches("\\d+(\\*\\d+)*",data)){
			String[] cycles = data.split("\\*");
			long period = 1;
			for (String str : cycles)
				period *= Long.parseLong(str);
			return period;
		} else {
			logger.warn("schedule task config error, error message: ["+data+"] is not long type ");
			return -1;
		}
	}
	private static void initSysFileOrDir(){
		UploadTool.webappsRootPath = new File(staticServletContext.getRealPath("/")).getParent()+File.separator;
		//创建目录
		for (String directory : UploadTool.DIRECTORYS) {
			File docDir = new File(UploadTool.webappsRootPath + directory);
			if (!docDir.exists()) {//不存在则创建
				docDir.mkdir();
			}
		}
	}

	/**
	 * 公共存储过程任务调度实现类
	 * @author Administrator
	 */
	class CommProcTask extends TimerTask {
		private String proc;
		private String comment;
		private String procName;
		private Map<String,String> inParam ;
		private List<String> outParam;

		public CommProcTask(String proc,String comment) throws Exception {
			this.proc = proc;
			this.comment = comment;
			paramProcess();
		}
		//存储过程参数处理
		private void paramProcess() throws Exception{
			int leftIndexOf = proc.indexOf("("), rightIndexOf = proc.indexOf(")");
			if(leftIndexOf>0 && rightIndexOf>0 && leftIndexOf<rightIndexOf){
				String execProcName = proc.substring(0,leftIndexOf);
				String questionMarkString = "";//存储过程参数?号字符串
				String paramString = proc.substring(leftIndexOf+1,rightIndexOf).trim();
				paramString = paramString.length()>0 ?(comment+","+paramString):comment;
				if(paramString.length()>0){//有参数
					inParam = new HashMap<String, String>();
					outParam = new ArrayList<String>();
					String[] params = paramString.split(",");
					String param="";
					for (int i = 0; i < params.length; i++) {
						if((param=params[i].trim()).matches("\\?")){//问号?表示输出
							outParam.add(String.valueOf(i+1));
						} else {
							inParam.put(String.valueOf(i+1), param.equals("''")?"":param);
						}
						questionMarkString += ",?";
					}
					questionMarkString="("+questionMarkString.substring(1)+")";
				}
				procName = "{call "+execProcName+questionMarkString+"}";
			} else {
				throw new Exception("schedule task config error,error message:"+proc);
			}
		}
		public void run() {
			try {
				logger.info("["+procName+ "] schedule task begin in " + dateFormat.format(new Date()));
				ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
				JdbcTemplateExtend jdbcTemplate = (JdbcTemplateExtend) ctx.getBean("jdbcTemplate");
				//	生成执行存储过程类
				CallableStatement cStatement = jdbcTemplate.getDataSource().getConnection().prepareCall(procName);
				//	输入参数
				if(null != inParam && !inParam.isEmpty()){
					for (String key : inParam.keySet()) {
						cStatement.setString(Integer.parseInt(key), inParam.get(key));
					}
				}
				//	输出参数
				if(null != outParam && !outParam.isEmpty()){
					for (String str : outParam) {
						cStatement.registerOutParameter(Integer.parseInt(str), java.sql.Types.VARCHAR);
					}
				}
				//	执行存储过程
				cStatement.execute();
				//	输出执行存储过程返回的结果
				if(null != outParam && !outParam.isEmpty()){
					logger.info("["+procName+"] procedure return result:");
					for (String str : outParam) {
						logger.info(str+":"+cStatement.getString(Integer.parseInt(str)));
					}
				}
				logger.info("["+procName + "] schedule task end in " + dateFormat.format(new Date()));
			} catch (Exception e) {
				logger.error("["+procName + "] schedule task error in " + dateFormat.format(new Date())+".\nExceptin Message:"+e.getMessage());
			}
		}
	}
}