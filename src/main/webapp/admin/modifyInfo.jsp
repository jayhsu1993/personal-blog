<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
  
    function submitData(){
    	var nickName=$("#nickName").val();
    	var sign=$("#sign").val();
    	var profile=UE.getEditor('profile').getContent();
    	if(nickName==null||nickName==''){
    		alert("请输入昵称");
    	}else if(sign==null||sign==''){
    		alert("请输入签名");
    	}else if(profile==null||profile==''){
    		alert("请输入简介");
    	}else{
    		$("#pf").val(profile);
    		$("#form1").submit();
    	}
    }
</script>

</head>
<body style="margin:10px;">
<div id="p" class="easyui-panel" title="修改个人信息" style="padding:10px">
   <form action="${pageContext.request.contextPath}/admin/blogger/save.do" method="post" enctype="multipart/form-data" id="form1">
	   <table cellspacing="20px">
	     <tr>
	       <td width="80px">用户名:</td>
	       <td>
	        <input type="hidden" id="id" name="id" value="${currentUser.id }"/>
	        <input id="userName" name="userName" type="text" style="width:200px" readonly="readonly" value="${currentUser.userName }"/>
	       </td>
	     </tr>
	     <tr>
	       <td>昵称:</td>
	       <td>
	         <input id="nickName" name="nickName" type="text" style="width:200px"  />
	       </td>
	     </tr>
	     <tr>
	       <td>个性签名:</td>
	       <td>
	         <input id="sign" name="sign" type="text" style="width:400px"  />
	       </td>
	     </tr>
	     <tr>
	       <td>个人头像: </td>
	       <td>
	         <input type="file" name="imageFile" id="imageFile"/>
	       </td>
	     </tr>
	     <tr>
          <td valign="top">个人简介:</td>
          <td>
           <script id="profile"  type="text/plain" style="width:100%;height:500px;"></script>
           <input type="hidden" id="pf" name="profile"/>
          </td>
         </tr>
		     <tr>
		       <td></td>
		       <td>
		         <a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
		       </td>
		     </tr>
	   </table>
   </form>
</div>

<!--实例化编辑器-->
<script type="text/javascript">
    var ue = UE.getEditor('profile');
    ue.addListener("ready",function(){
    	//通过ajax请求数据
    	UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/find.do",
    	{
    		method:"post",
    		async:false,
    	    data:{},
    	    onsuccess:function(result){
    	    	result=eval("("+result.responseText+")");
    	    	$("#nickName").val(result.nickName);
    	    	$("#sign").val(result.sign);
    	    	UE.getEditor('profile').setContent(result.profile);
    	    }
    	});
    });
</script>
</body>
</html>