package rest.logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
  
  
  public static final Config instance = new Config();
  public String analysisClass;
  public String traceFileName;
  public String[] excludeList;
  public String[] includeList;
  public boolean printTrace;
  public String jsonFile;
  private String loggerClass;

  public Config() {
      loggerClass = "rest.logger.FileLogger";
      printTrace = true;
      traceFileName = "trace.txt"; 
      analysisClass ="rest/logger/DJVM";
      jsonFile = "output.json";
     
  }

  private Object getObject(String className) {
    try {
      Class<?> clazz = Class.forName(className);
      
      Object ret = clazz.newInstance();
      System.out.println("getclass:"+className);
      return ret;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (InstantiationException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      System.exit(1);
    }
    return null;
  }

  public Logger getLogger() {
	    if (loggerClass == null || loggerClass.isEmpty()) {
	      return null;
	    }
	    return (Logger) getObject(loggerClass);
  }
  

 
}
