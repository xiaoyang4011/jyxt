package crds.pub.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.InitializingBean;

/**
 * @specification :由spring管理的线程池类  
 * @version : 1.0
 * @author : liuxx
 * @date : Oct 24, 2008 2:24:46 PM
 * @email : liuxx.adam@gmail.com
 */
public class EasyMailExecutorPool implements InitializingBean {   
  
    //线程池大小，spring配置文件中配置   
    private int poolSize;   
    
    //执行线程
    private ExecutorService service;   
  
    public ExecutorService getService() {   
        return service;   
    }   
  
    public int getPoolSize() {   
        return poolSize;   
    }   
  
    public void setPoolSize(int poolSize) {   
        this.poolSize = poolSize;   
    }  
    
	/**
	 * @specification :在 bean 被初始化成功之后初始化线程池大小  
	 * @param :NAN
	 * @return :void
	 * @exception :Exception
	 */
    public void afterPropertiesSet() throws Exception {   
        service = Executors.newFixedThreadPool(poolSize);   
    }   
}  

