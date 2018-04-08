package com.jayhsu.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jayhsu.entity.Blogger;
import com.jayhsu.service.BloggerService;

/**
 * �Զ���realm
 * @author Administrator
 *
 */
//@Component(value="myRealm")
public class MyRealm extends AuthorizingRealm{

	@Resource
	private BloggerService bloggerService;
	/**
	 * Ϊ��ǰ��¼���û������ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ��֤��ǰ��¼���û�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName=(String)token.getPrincipal();
        Blogger blogger=bloggerService.getByUserName(userName);
        if(blogger!=null) {
        		SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);//�ѵ�ǰ�û��浽session��
        		AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(),"xx");
        		return authcInfo;
        }else {
        	return null;
        }
	}

	
}
