package com.weitian.auth;

import com.weitian.ResultRnum.ResultEnum;
import com.weitian.exception.ResultException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by Administrator on 2018/11/21.
 */
public class CredentialMatcher extends SimpleCredentialsMatcher{

    /*
    验证器，在这里可以自定义登录校验规则
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        String loginUsername=usernamePasswordToken.getUsername();
        String loginPassword=new String(usernamePasswordToken.getPassword());


        String dbUserName=(String)info.getPrincipals().getPrimaryPrincipal();
        String dbPassword=(String)info.getCredentials();

        if(!(this.equals( loginUsername,dbUserName ) && this.equals( loginPassword,dbPassword ))){
            throw new ResultException( ResultEnum.LOGIN_IS_ERROR );
        }
        return true;
    }
}
