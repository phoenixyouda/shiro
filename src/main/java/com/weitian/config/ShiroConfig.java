package com.weitian.config;

import com.weitian.auth.CredentialMatcher;
import com.weitian.auth.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/21.
 */
@Configuration
public class ShiroConfig {

    /*
    4、根据业务需求配置授权过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager( securityManager );

        //设置登录页面
        factoryBean.setLoginUrl( "/login" );
        //设置登录成功后的跳转页面
        factoryBean.setSuccessUrl( "/index" );
        //设置无权访问的跳转页面
        factoryBean.setUnauthorizedUrl( "/unauthc" );


        //配置校验规则
        /*
        校验规则为枚举类型，常用的有：
        （1）anon：匿名过滤器，表示通过了url配置的资源都可以访问，例：“/statics/**=anon”表示statics目录下所有资源都能访问
        （2）authc：基于表单的过滤器，表示通过了url配置的资源需要登录验证，否则跳转到登录，例：“/unauthor.jsp=authc”如果用户没有登录访问unauthor.jsp则直接跳转到登录
        （3）authcBasic：Basic的身份验证过滤器，表示通过了url配置的资源会提示身份验证，例：“/welcom.jsp=authcBasic”访问welcom.jsp时会弹出身份验证框
        （4）perms：权限过滤器，表示访问通过了url配置的资源会检查相应权限，例：“/statics/**=perms["user:add:*,user:modify:*"]“表示访问statics目录下的资源时只有新增和修改的权限
        （5）port：端口过滤器，表示会验证通过了url配置的资源的请求的端口号，例：“/port.jsp=port[8088]”访问port.jsp时端口号不是8088会提示错误
        （6）rest：restful类型过滤器，表示会对通过了url配置的资源进行restful风格检查，例：“/welcom=rest[user:create]”表示通过restful访问welcom资源时只有新增权限
        （7）roles：角色过滤器，表示访问通过了url配置的资源会检查是否拥有该角色，例：“/welcom.jsp=roles[admin]”表示访问welcom.jsp页面时会检查是否拥有admin角色
        （8）ssl：ssl过滤器，表示通过了url配置的资源只能通过https协议访问，例：“/welcom.jsp=ssl”表示访问welcom.jsp页面如果请求协议不是https会提示错误
        （9）user：用户过滤器，表示可以使用登录验证/记住我的方式访问通过了url配置的资源，例：“/welcom.jsp=user”表示访问welcom.jsp页面可以通过登录验证或使用记住我后访问，否则直接跳转到登录
        （10）logout：退出拦截器，表示执行logout方法后，跳转到通过了url配置的资源，例：“/logout.jsp=logout”表示执行了logout方法后直接跳转到logout.jsp页面

            过滤器分类：
        （1）认证过滤器：anon、authcBasic、auchc、user、logout

        （2）授权过滤器：perms、roles、ssl、rest、port

            URL模糊规则：

        （1）“?”：匹配一个字符，如”/admin?”，将匹配“ /admin1”、“/admin2”，但不匹配“/admin”

        （2）“*”：匹配零个或多个字符串，如“/admin*”，将匹配“ /admin”、“/admin123”，但不匹配“/admin/1”

        （3）“**”：匹配路径中的零个或多个路径，如“/admin/**”，将匹配“/admin/a”、“/admin/a/b”

         */
        Map<String,String> filterChianDefinitionMap=new HashMap<String,String>(  );


        filterChianDefinitionMap.put( "/index","authc" );

        filterChianDefinitionMap.put( "/login","anon" );
        filterChianDefinitionMap.put( "/**","user" );
        filterChianDefinitionMap.put( "/druid/**","anon" );

        filterChianDefinitionMap.put("/admin", "roles[admin]");
        filterChianDefinitionMap.put("/authc/renewable", "perms[Create,Update]");
        filterChianDefinitionMap.put("/authc/removable/*", "perms[Delete]");


        factoryBean.setFilterChainDefinitionMap( filterChianDefinitionMap );
        return factoryBean;
    }

    /*
    3、将shirorealm对象交给shiro框架的SecurityManager管理
    SecurityManager对象，并将shirorealm纳入其中管理
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm){
        DefaultWebSecurityManager webSecurityManager=new DefaultWebSecurityManager( shiroRealm );
        return webSecurityManager;
    }
    /*
    2、告诉shirorealm，我们的校验规则
    生成ShiroRealm对象,并为该对象设置自定义的校验规则
     */
    @Bean("shiroRealm")
    public ShiroRealm shiroRealm(@Qualifier("credentialMatcher") CredentialsMatcher credentialsMatcher){
        ShiroRealm shiroRealm=new ShiroRealm();
        shiroRealm.setCredentialsMatcher( credentialsMatcher );
        //使用shiro缓存管理
        shiroRealm.setCacheManager( new MemoryConstrainedCacheManager() );
        return shiroRealm;
    }

    /*
        1、由spring生成我们自己的校验规则对象
     */
    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher(){
        CredentialMatcher credentialMatcher=new CredentialMatcher();
        return credentialMatcher;
    }
}
