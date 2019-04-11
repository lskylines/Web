package com.skyline.store.web.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;

import com.skyline.store.domain.User;

public class Test {
	@org.junit.Test
	public void Boy() throws IllegalAccessException, InvocationTargetException {
		Map<String, String[]> map = new HashMap();
		map.put("username", new String[] {"zhangsan"});
		map.put("password", new String[] {"234"});
		DateConverter dt = new DateConverter();
		dt.setPattern("yyyy-MM-dd");
		ConvertUtils.register(dt, java.util.Date.class);
		map.put("birthday", new String[] {"1999-10-11"});
		User user = new User();
		BeanUtils.populate(user, map);
		System.out.println(user);
	}
}
