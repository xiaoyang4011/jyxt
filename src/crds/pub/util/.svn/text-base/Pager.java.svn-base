package crds.pub.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @specification :分页对象
 * @version : 1.0
 * @auther : yangy
 * @date : Nov 20, 2008 2:23:59 PM
 * @email : qilaug@gmail.com
 */
public class Pager {
	private int totalRows; //总行数
	private int pageSize = 10; //每页显示的行数
	private int currentPage; //当前页号
	private int totalPages; //总页数
	private int startRow; //当前页在数据库中的起始行
	private String queryField; //查询的字段
	private String queryField1;//查询的字段1
	private String queryField2;//查询的字段2

	public String getQueryField() {
		return queryField;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

	public String getQueryField1() {
		return queryField1;
	}

	public void setQueryField1(String queryField1) {
		this.queryField1 = queryField1;
	}

	public String getQueryField2() {
		return queryField2;
	}

	public void setQueryField2(String queryField2) {
		this.queryField2 = queryField2;
	}

	public Pager() {
	}

	public Pager(int _totalRows) {
		totalRows = _totalRows;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		currentPage = 1;
		startRow = 1; //0和1  看着办
	}

	public int getStartRow() {
		return startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void first() {
		currentPage = 1;
		startRow = 1; //0和1  看着办
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize + 1;
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize + 1;
	}

	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize + 1;
	}

	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			last();
		}
	}

	public static Pager getPager(HttpServletRequest httpServletRequest,
			int totalRows) {

		// 定义pager对象，用于传到页面
		Pager pager = new Pager(totalRows);

		// 从Request对象中获取当前页号
		String currentPage = httpServletRequest.getParameter("currentPage");

		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (currentPage != null) {
			pager.refresh(Integer.parseInt(currentPage));
		}

		// 获取当前执行的方法，首页，前一页，后一页，尾页。
		String pagerMethod = httpServletRequest.getParameter("pageMethod");

		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			}
		}
		return pager;
	}
}
