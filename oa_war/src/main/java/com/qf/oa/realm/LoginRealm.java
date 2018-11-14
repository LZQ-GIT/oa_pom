package com.qf.oa.realm;

import com.qf.oa.entity.Emp;
import com.qf.oa.entity.Resc;
import com.qf.oa.service.IEmpService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Authoer lzq
 * @DateTime 2018/11/6  18:57
 * @Version 1.0
 */
@Component
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private IEmpService empService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得登录的用户
        Emp emp = (Emp) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Resc resc : emp.getRescList()) {
            if(resc.getRestate()!=1){
                //给该用户设置权限
                authorizationInfo.addStringPermission(resc.getRepath());
            }
        }
        return authorizationInfo;
    }

    @Override
    //登录的身份验证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String email = (String) authenticationToken.getPrincipal();
        Emp emp = empService.doLogin(email);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(emp,emp.getPassword(),this.getName());
        return authenticationInfo;
    }
}
