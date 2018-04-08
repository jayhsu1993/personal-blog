package com.jayhsu.entity;

/**
 * 博客类型实体
 * @author Administrator
 *
 */
public class BlogType {

	private Integer id;
	private String typeName;//类型名称
	private Integer orderNo;//博客排序方式从小到大
	private Integer blogCount;//博客数量
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}
	
}
