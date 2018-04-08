package com.jayhsu.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jayhsu.entity.Blog;
import com.jayhsu.entity.Comment;
import com.jayhsu.entity.PageBean;
import com.jayhsu.service.CommentService;
import com.jayhsu.util.ResponseUtil;
import com.jayhsu.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 管理员审核评论控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

	@Resource
	private CommentService commentService;
	
	/**
	 * 分页查询评论信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,@RequestParam(value="state",required=false)String state, HttpServletResponse response)throws Exception {
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		//这里给的是一个用户在前台输入的关键字作为blog的title用来模糊查询的
		map.put("state", state);
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Comment> commentList=commentService.list(map);
		long total=commentService.total(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(commentList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 业务性质的评论审核处理
	 * @return
	 */
	@RequestMapping("/review")
	public String review(@RequestParam(value="ids",required=false)String ids,@RequestParam(value="state",required=false)Integer state, HttpServletResponse response )throws Exception {
		String []idsStr=ids.split(",");
		for(String id:idsStr) {
			Comment comment=new Comment();
			comment.setId(Integer.parseInt(id));
			comment.setState(state);
			commentService.update(comment);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 删除评论控制器
	 * @param ids
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response) throws Exception{
		String []idsStr=ids.split(",");
		JSONObject result=new JSONObject();
		for(String id:idsStr) {
			commentService.delete(Integer.parseInt(id));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
