package com.jayhsu.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jayhsu.entity.Blogger;
import com.jayhsu.service.BloggerService;
import com.jayhsu.util.CryptographyUtil;
import com.jayhsu.util.DateUtil;
import com.jayhsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * ����Ա����controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Resource
	private BloggerService bloggerService;
	
	/**
	 * ��ѯ������Ϣ
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/find")
	public String find(HttpServletResponse response)throws Exception {
		Blogger blogger=bloggerService.find();
		JSONObject result=JSONObject.fromObject(blogger);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * �޸ĸ�����Ϣ��save����
	 * @return
	 */
	@RequestMapping("/save")
	public String save(@RequestParam("imageFile")MultipartFile imageFile,Blogger blogger,HttpServletResponse response,HttpServletRequest request) throws Exception{
		if(!imageFile.isEmpty()) {
			//��imageFile���ļ�·������localhost��·���õ�������·��
			String filePath=request.getServletContext().getRealPath("/");
			String imageName=DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
			//��ǰ̨��ͼƬ�ϴ�����Ŀ����
			imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			blogger.setImageName(imageName);
		}
		int resultTotal=bloggerService.update(blogger);
		StringBuffer result=new StringBuffer();
		if(resultTotal>0) {
			result.append("<script language='javascript'>alert('�޸ĳɹ�')</script>");
		}else {
			result.append("<script language='javascript'>alert('�޸�ʧ��')</script>");
		}
		ResponseUtil.write(response, result );
		return null;
	}
	/**
	 * �޸Ĳ�������
	 * @param newPassword
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,HttpServletResponse response) throws Exception{
		Blogger blogger=new Blogger();
		blogger.setPassword(CryptographyUtil.md5(newPassword, "java1234"));
		int resultTotal=bloggerService.update(blogger);
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
	 * �û�ע��
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception {
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
