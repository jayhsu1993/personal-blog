package com.jayhsu.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jayhsu.entity.Link;
import com.jayhsu.entity.PageBean;
import com.jayhsu.service.LinkService;
import com.jayhsu.util.ResponseUtil;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 管理层友情链接控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;
	
	/**
	 * 分页查询友情链接信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Link link,HttpServletResponse response)throws Exception {
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Link> linkList=linkService.list(map);
		long total=linkService.total(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(linkList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 添加或修改友情链接路径
	 * @param Link
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Link Link,HttpServletResponse response)throws Exception {
		int resultTotal=0;
		if(Link.getId()==null) {
			resultTotal=linkService.add(Link);
		}else {
			//修改
			resultTotal=linkService.update(Link);
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
	 * 删除友情链接控制器
	 * @param ids
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response) throws Exception{
		String []idsStr=ids.split(",");
		JSONObject result=new JSONObject();
		for(String id:idsStr) {
			linkService.delete(Integer.parseInt(id));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
