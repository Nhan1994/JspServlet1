package com.testservlet.paging;

import com.testservlet.sort.Sorter;

public interface Pageable {
	public Integer getOffSet();
	
	public Integer getPage();
	
	public Integer getLimit();
	
	public Sorter getSorter();
}
