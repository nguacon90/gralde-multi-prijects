package com.nguacon.platform.spring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefaultConfigurationService
{
	@Autowired
	private EcommerceConfiguration ecommerceConfiguration;

	public Configuration getConfiguration()
	{
		return ecommerceConfiguration;
	}

	public Map<String, Object> getParametersByPattern(String pattern) {
		Map<String, Object> origParams = ecommerceConfiguration.getAllProperties();
		Map<String, Object> params = new HashMap(origParams);
		Iterator var4 = origParams.keySet().iterator();

		while(var4.hasNext()) {
			String key = (String)var4.next();
			if(!key.startsWith(pattern)) {
				params.remove(key);
			}
		}

		return params;
	}

	public List<String> getActiveProfiles() {
		String[] activeProfiles = ecommerceConfiguration.environment.getActiveProfiles();
		return Arrays.stream(activeProfiles).collect(Collectors.toList());
	}

}