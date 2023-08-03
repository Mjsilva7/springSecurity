package com.kamauro.springSecurity.datatables;

import java.util.LinkedHashMap;
import java.util.Map;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
@Component
public class Datatables {

    private HttpServletRequest request;
    private String[] colunas;

  public Map<String, Object> getResponse(Page<?> page) {		
		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw());
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());
		return json;
	}

    private int draw() {
		return Integer.parseInt(this.request.getParameter("draw"));
	}
	
	private int start() {
		return Integer.parseInt(this.request.getParameter("start"));
	}
	
	public int getLength() {
		return Integer.parseInt(this.request.getParameter("length"));
	}

	public int getCurrentPage() {
		return start() / getLength();
	}

	public String getColumnName() {
		int iCol = Integer.parseInt(this.request.getParameter("order[0][column]"));
		return this.colunas[iCol];
	}	

	public Sort.Direction getDirection() {
		String order = this.request.getParameter("order[0][dir]");
		Sort.Direction sort = Sort.Direction.ASC;
		if (order.equalsIgnoreCase("desc")) {
			sort = Sort.Direction.DESC;
		}
		return sort;
	}

	public String getSearch() {		
		return this.request.getParameter("search[value]");
	}
	
	public Pageable getPageable() {
		return PageRequest.of(getCurrentPage(), getLength(), getDirection(), getColumnName());
	}
    
}
