package com.edubean.common.listener;  
  
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
   
public class ApplicationListener extends ContextLoaderListener{  
  
    public void contextDestroyed(ServletContextEvent sce){  
  
    }  
  
    public void contextInitialized(ServletContextEvent sce){  
        String webAppRootKey = sce.getServletContext().getRealPath("/").replaceAll("\\\\", "/");  
        System.setProperty("web.root" , webAppRootKey);  
        String path =System.getProperty("web.root"); 
        System.out.println("web.root"+path);  
    }  
  
}