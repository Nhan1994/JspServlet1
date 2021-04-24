package com.testservlet.paging;

import com.testservlet.sort.Sorter;

public class PageRequest implements Pageable{
	
	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	
	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	@Override
	public Integer getOffSet() {
		if (page != null) {
			return (getPage() - 1) * getLimit();
		}
		return null;
	}

	public Sorter getSorter() {
		return sorter;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

	@Override
	public Integer getPage() {
		return page;
	}

	@Override
	public Integer getLimit() {
		return maxPageItem;
	}

}
