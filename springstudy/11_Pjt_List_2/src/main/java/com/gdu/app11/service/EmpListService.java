package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpListService {
	public void getAllEmployees(HttpServletRequest request, Model model);
}
