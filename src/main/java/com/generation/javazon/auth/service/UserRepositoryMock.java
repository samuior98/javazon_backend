package com.generation.javazon.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryMock
{

	private Map<String,User> users = new HashMap<>();


	public UserRepositoryMock()
	{
		users.put("stefano", new User("stefano", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
				new ArrayList<>()));
		users.put("irene", new User("irene", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
				new ArrayList<>()));
		users.put("samior98", new User("samior98", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1pb3I5OCIsImV4cCI6MTY5ODI1OTk5NCwiaWF0IjoxNjk4MjQxOTk0fQ.zKSqXkEuE_KwQJlS3RJsLovurCENvXeIjPOxO2UGnetUS_Z5Fo8e16QTEO_QMf2bQuLwBZh2h-runX-8yToIxA",
				new ArrayList<>()));

	}

	User getUserByUsername(String username)
	{
		if(users.containsKey(username))
		{
			User old = users.get(username);
			return new User(old.getUsername(),old.getPassword(),old.getAuthorities());
		}

		return null;
	}
}
