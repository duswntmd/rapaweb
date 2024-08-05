package com.test.sku;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class Gugu {
	
	private HttpServletRequest request;
	
	public Gugu() {}
	
	public Gugu(HttpServletRequest request) {
		this.request = request;
	}

	public List<String> getGugu(){
		String _dan = request.getParameter("dan");
		int dan = Integer.parseInt(_dan);
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
        	list.add(String.format("%d * %d = %d", dan, i, dan*i));
        }
		return list;
	}
	
	public List<String> getGugu(HttpServletRequest req){
		String _dan = req.getParameter("dan");
		int dan = Integer.parseInt(_dan);
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
        	list.add(String.format("%d * %d = %d", dan, i, dan*i));
        }
		return list;
	}
}
