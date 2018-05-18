package org.icec.common.page;

import java.util.List;

public class Page<T> {
	protected List<T> list;		//分页结果List
	protected long pageNumber;		//页数
	protected long totalPage;		//总页数
	protected long totalRow=-1;		//总行数,如果不为-1，则不需要再次查询
	/**
	 * 默认的每页纪录总数，
	 */
	public static long 	DEFAULT_PAGE_SIZE = 20 ;
	protected   long pageSize = DEFAULT_PAGE_SIZE;		//每页记录数
	private transient boolean  calc = false;
	public List<T> getList() {
		return list;
	}
	
	public long getPageNumber() {
		return pageNumber;
	}
	
	public long getPageSize() {
		return pageSize;
	}
	
	public long getTotalPage() {
		calcTotalPage();
		return totalPage;
	}
	
	public long getTotalRow() {
		return totalRow;
	}
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	
	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}
	
	
	public void setList(List list) {
		this.list = list;
	}

	
	/**
	 * 计算页数，在取得查询结果，设置完totalRow后
	 */
	private void calcTotalPage(){
		if(this.calc){
			return ;
		}		
		if(totalRow==0) this.totalPage= 1;
		else if(totalRow%this.pageSize==0){
			this.totalPage = totalRow/this.pageSize;
		}else{
			this.totalPage = totalRow/this.pageSize+1;
		}
		this.calc = true;
	}
}
