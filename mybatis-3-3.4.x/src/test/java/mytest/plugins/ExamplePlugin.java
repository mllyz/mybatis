package mytest.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

// ExamplePlugin.java
@Intercepts({@Signature(type= Executor.class,
  method = "query",
  args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class ExamplePlugin implements Interceptor {

  private Properties properties = new Properties();

  // 拦截方法
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("成功拦截了excutor的query....我在query方法执行之前....");
    Object returnObject = invocation.proceed();
    System.out.println("成功拦截了excutor的query....我在query方法执行之后....");
    return returnObject;
  }

  //应用插件。如应用成功，则会创建目标对象的代理对象
  @Override
  public Object plugin(Object target) {
   // System.out.println("将要包装的目标对象"+target);
    return Plugin.wrap(target,this);
  }
  @Override
  public void setProperties(Properties properties) {
    System.out.println("我是设置属性name ： " + properties.getProperty("name"));
    this.properties = properties;
  }
}