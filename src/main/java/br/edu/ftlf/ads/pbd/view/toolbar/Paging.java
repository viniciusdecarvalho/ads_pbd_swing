package br.edu.ftlf.ads.pbd.view.toolbar;

import org.springframework.data.domain.PageRequest;

public class Paging extends PageRequest {

	private static final long serialVersionUID = 1L;
	private int currentPage;

	public Paging(int currentPage, int pages, int pageSize) {
		super(pages, pageSize);
		this.currentPage = currentPage;
	}

	public Paging(int pages, int pageSize) {
		this(1, pages, pageSize);
	}

	public int getStart() {
		if (currentPage > 1) {
			return (getPageSize() * (currentPage - 1));
		}
		return 0;
	}
	
	public boolean hasPrevious() {
		return currentPage > 1;
	}

	public boolean hasNext() {
		return currentPage < getPageNumber();
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	
}