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
import com.jayhsu.entity.BlogType;
import com.jayhsu.entity.PageBean;
import com.jayhsu.service.BlogService;
import com.jayhsu.service.BlogTypeService;
import com.jayhsu.util.ResponseUtil;
import com.jayhsu.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 管理层类别控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BlogService blogService;
	
	/**
	 * 分页查询博客类别信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Blog s_blog,HttpServletResponse response)throws Exception {
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogType> blogTypeList=blogTypeService.list(map);
		long total=blogTypeService.total(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 添加或修改博客类型路径
	 * @param blogType
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(BlogType blogType,HttpServletResponse response)throws Exception {
		int resultTotal=0;
		if(blogType.getId()==null) {
			resultTotal=blogTypeService.add(blogType);
		}else {
			//修改
			resultTotal=blogTypeService.update(blogType);
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
	 * 删除博客控制器
	 * @param ids
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response) throws Exception{
		String []idsStr=ids.split(",");
		JSONObject result=new JSONObject();
		for(String id:idsStr) {
			if(blogService.getBlogByTypeId(Integer.parseInt(id))>0) {
				result.put("exist","博客类别下面是有博客的，不能删除");
			}else {
				blogTypeService.delete(Integer.parseInt(id));
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
