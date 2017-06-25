# 拦截器

## 自定义编码拦截器
com.feagle.interceptor.MyCharacterEncodingInterceptor
针对post请求，为了防止乱码，对项目中所有的post请求request和所有的post请求的响应response均设置编码为UTF-8。

## 自定义验证登录拦截器
com.feagle.interceptor.MyLoginInterceptor
为了防止死循环，针对请求路径/login，不进行拦截。




# 过滤器

## 自定义编码过滤器
com.feagle.filter.MyCharacterEncodingFilter


## 自定义异常过滤器
访问：http://localhost:8080/servlet/exceptionServlet，跳转到错误信息提示页面，并展示错误信息。
对不起，这是error500页面。
com.feagle.exception.MyException: in add() throws Exception!!

## 基于注解的，支持异步的自定义过滤器
com.feagle.filter.MyAsyncFilter
访问：http://localhost:8080/servlet/AsyncServlet
或 
http://localhost:8080/servlet/asyncServlet



## 自定义IP过滤器
com.feagle.filter.MyIpFilter
1.访问：http://127.0.0.1/login 会被拦截到forbid.jsp。
备注：发现@WebFilter注解起不了作用。
@WebFilter(filterName = "ipFilter",urlPatterns = "/*",initParams = {@WebInitParam(name="forbidIps",value = "127.0.0.1,192.168.1.1")})，不在web.xml对过滤器进行配置好像不可以。
必须要在web.xml中配置过滤器。
2.增加排除url,访问http://localhost:8080/index会被重定向到login/login1.jsp页面。
输入用户名，密码，点击登录，成功跳转到login/success.jsp。



## 自定义日志过滤器
com.feagle.filter.MyLogFilter
访问项目任何路径，都会打印出所请求的路径及花费时间。
后台日志打印：
doFilter() in MyLogFilter...开始过滤...
initParameter:null
doFilter()方法已截获到用户的请求地址：//index.jsp
来自index.jsp的响应结果：处理过程完毕
请求被定位到：/ ，所花时间：313
doFilter() in MyLogFilter...过滤结束...

## 自定义判断用户是否登录的过滤器
com.feagle.filter.MyAuthorityFilter
如果用户没有登录，那么只能访问/login/login1.jsp。登录成功时将用户写入session，并跳转到/login/success.jsp。

## 自定义用户登录过滤器
com.feagle.filter.MyLoginFilter
在web.xml配置初始化参数，添加需要放行的url。

