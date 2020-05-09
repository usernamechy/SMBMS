package com.smbms.util;

public class PageUtil {
	private Integer start;
	private Integer currentPageNo;
	private Integer pageSize;
	private Integer totalPageCount;
	private Integer totalCount;

    public PageUtil() {
        super();
    }

    public PageUtil(Integer currentPageNo, Integer totalCount) {
		this.currentPageNo = currentPageNo==null?1:currentPageNo;
		this.pageSize=5;
		this.start=(this.currentPageNo-1)*pageSize;
		this.totalPageCount=(int)Math.ceil(totalCount*1.0/this.pageSize);
		this.totalCount=totalCount;

	}

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
