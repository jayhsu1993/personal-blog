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
import com.jayhsu.entity.PageBean;
import com.jayhsu.lucene.BlogIndex;
import com.jayhsu.service.BlogService;
import com.jayhsu.util.ResponseUtil;
import com.jayhsu.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 管理员博客控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	@Resource
	private BlogService blogService;
	
	private BlogIndex blogIndex=new BlogIndex();
	
	/**
	 * 添加或修改博客路径
	 * @param blog
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse response)throws Exception {
		int resultTotal=0;
		if(blog.getId()==null) {
			resultTotal=blogService.add(blog);
			blogIndex.addIndex(blog);
		}else {
			//修改
			resultTotal=blogService.update(blog);
			blogIndex.updateIndex(blog);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 分页查询博客信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Blog s_blog,HttpServletResponse response)throws Exception {
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		//这里给的是一个用户在前台输入的关键字作为blog的title用来模糊查询的
		map.put("title", StringUtil.formatLike(s_blog.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogList=blogService.list(map);
		long total=blogService.total(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(blogList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 删除博客控制器
	 * @param ids
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response) throws Exception{
		String []idsStr=ids.split(",");
		for(String id:idsStr) {
			blogService.delete(Integer.parseInt(id));
			blogIndex.deleteIndex(id);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 通过id得到blog所有信息传到前台
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Blog blog=blogService.findById(Integer.parseInt(id));
		JSONObject result=JSONObject.fromObject(blog);
		ResponseUtil.write(response, result);
		return null;
		
	}
}
