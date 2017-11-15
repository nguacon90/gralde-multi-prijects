package com.nguacon.platform.spring;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;


/**
 * Created by minh on 10/11/2017.
 * Support read properties keys
 */
@Configuration("ecommerceConfiguration")
public class EcommerceConfiguration extends AbstractConfiguration
{
	private static final Logger LOG = Logger.getLogger(EcommerceConfiguration.class);
	protected Environment environment;
	private Map<String, Object> properties = new HashMap();

	@PostConstruct
	public void init() {
		AbstractEnvironment env = (AbstractEnvironment) environment;

		for(Iterator it =  env.getPropertySources().iterator(); it.hasNext(); ) {
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof MapPropertySource) {
				properties.putAll(((MapPropertySource) propertySource).getSource());
			}
		}
		if(LOG.isDebugEnabled())
		{
			LOG.debug("Properties: " + properties);
		}
	}

	@Autowired
	public EcommerceConfiguration(Environment environment) {
		this.environment = environment;
	}

	@Override
	protected void addPropertyDirect(String key, Object value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public boolean containsKey(String key)
	{
		return this.environment.getProperty(key) != null;
	}

	@Override
	public Object getProperty(String key)
	{
		return this.environment.getProperty(key);
	}

	@Override
	public Iterator<String> getKeys()
	{
		return this.properties.keySet().iterator();
	}

	public Map<String, Object> getAllProperties() {
		return this.properties;
	}
}
