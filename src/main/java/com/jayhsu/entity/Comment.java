package com.jayhsu.entity;

import java.util.Date;

/**
 * 评论实体
 * @author Administrator
 *
 */
public class Comment {

	private Integer id;
	private String userIp;//评论者ip
	private String content;//评论内容
	private Blog blog;//被评论的博客
	private Date commentDate;//评论日期
	private Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}//审核状态，0待审核，1审核通过，2审核不通过

    
}
