package com.weitian.auth;

import com.weitian.ResultRnum.ResultEnum;
import com.weitian.entity.Permission;
import com.weitian.entity.Role;
import com.weitian.entity.User;
import com.weitian.exception.ResultException;
import com.weitian.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/11/21.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /*
    该类用于保存身份认证信息，即登录信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*
        token中保存了用户登录时的信息，查源码可以看出token.getPrincipal()方法返回用户名
         */
        String username=(String)token.getPrincipal();

        User user=userService.findByUserName( username );
        if(null==user){
            throw new ResultException( ResultEnum.USER_NOT_EXISTS);
        }
        /*
         将从数据库中查询得到的用户信息保存在shiro框架的AuthenticationInfo中，准备与token中的用户登录信息进行校验
          */
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo( user.getUsername(),user.getPassword(),this.getName() );
        return authenticationInfo;
    }
    //权限信息
    //该类用户保存登录用户的权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        /*用户登录后，从session中取出用户权限、角色信息，填充到AuthorizationInfo对象中，进行后续验证*/
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo(  );
        String username=(String)principals.getPrimaryPrincipal();
        User user=userService.findByUserName( username );

        List<Role> roleList=user.getRoleList();
        for(Role role:roleList){
            //保存用户的角色信息
            authorizationInfo.addRole( role.getName() );
            List<Permission> permissionList=role.getPermissionList();
            for(Permission permission:permissionList){
                //保存用户的权限信息
                authorizationInfo.addStringPermission( permission.getName() );
            }
        }
        return authorizationInfo;
    }


}
