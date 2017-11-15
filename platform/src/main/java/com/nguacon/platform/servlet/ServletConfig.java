package com.nguacon.platform.servlet;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.nguacon.platform.spring.DefaultConfigurationService;


/**
 * Created by minh on 14/11/2017.
 */

@Configuration
public class ServletConfig implements ServletContextInitializer
{
	private static final Logger LOG = Logger.getLogger(ServletConfig.class);

	@Autowired
	private DefaultConfigurationService defaultConfigurationService;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		List<String> activeProfiles = defaultConfigurationService.getActiveProfiles();
		for (int i=0; i<activeProfiles.size(); i++)
		{
			String profile = activeProfiles.get(i);
			String applicationContextKey = profile + "." + "application-context";
			String contextFile = defaultConfigurationService.getConfiguration().getString(applicationContextKey);
			String webrootKey = profile + "." + "webroot";
			String webroot = defaultConfigurationService.getConfiguration().getString(webrootKey);
			if(contextFile == null) {
				LOG.warn(String.format("Module " + profile + " has not appContext configuration properties: %s", applicationContextKey));
				continue;
			}

//			if(webroot == null) {
//				//NOT need register servlet for module without customizing webroot, running with default root: /
//				continue;
//			}

			DispatcherServlet dispatcherServlet = new DispatcherServlet();
			XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
			applicationContext.setConfigLocation("classpath:"+contextFile);

			dispatcherServlet.setApplicationContext(applicationContext);

			ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(profile+"-servlet", dispatcherServlet);
			servletRegistration.setLoadOnStartup(1);
			if(webroot != null)
			{
				servletRegistration.addMapping(webroot + "/*");
			}
		}
	}
}
