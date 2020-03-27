# spring-boot2-parent

基于springboot 2.0.6.RELEASE版本的代码演示集合

因以前在一个项目中集成技术点的内容太多，导致有时不好剥离，介绍演示时职责不单一

现在优化成：每一个技术点，会新建一个小项目，彼此不影响，能独立运行，便于学习和上手

# 项目列表

 [一、sprint-boot2-autotest](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-autotest)

介绍Springboot2【自动化单元测试】的用法：

1. 如何编写测试案例，具体用法有哪些
2. 单元测试场景：四种业务场景，例如如何进行接口api测试，服务层代码测试，MockMvc用法等


[二、spring-boot2-interceptor](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-interceptor)

介绍Springboot2【拦截器】的用法：

1. 拦截器用法详细介绍
2. 如何把服务层对象注入到拦截器中，调用相关方法
3. 开启跨域访问功能


[三、spring-boot2-rocketmq](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-rocketmq)

介绍Springboot2【RocketMQ集成】的用法：

1. 如何与springboot集成
2. 如何建立连接，发送不同消息数据类型
3. 如何订阅，且消费不同消息数据类型
4. 场景案例分析


[四、spring-boot2-schedule](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-schedule)

介绍Springboot2【@scheduled定时器】的用法：

1. 定时器计划用法介绍
2. 开启并行多线程任务两种方式
3. 场景案例分析


[五、spring-boot2-tomcat](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-tomcat)

介绍Springboot2【Tomcat容器自定义】的用法：

1. Tomcat容器配置用法，使用.yml文件方式
2. 设置tomcat的最大连接数和最大并发数
3. Springboot1和Springboot2版本之间的差异用法


[六、spring-boot2-mysql-mybatis-xml](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-mysql-mybatis-xml)

介绍Springboot2【Mysql和Mybatis+XML用法详解】的用法：

1. Mysql+Datasource集成
2. Mybatis+XML用法详解


[七、spring-boot2-mysql-multi-datasource](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-mysql-multi-datasource)

介绍Springboot2【Mysql多数据源和Hikari用法详解】的用法：

1. Mysql多数据源配置用法
2. Hikari用法


[八、spring-boot2-multi-resources](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-multi-resources)

介绍Springboot2【多环境配置文件用法】的用法：

1. 多环境配置文件用法

[九、spring-boot2-docker](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-docker)

介绍Springboot2【Docker集成+容器化部署详解-上篇】的用法：

1. 使用Maven插件构建Docker镜像

[十、spring-boot2-dockerfile](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-dockerfile)

介绍Springboot2【Docker集成+容器化部署详解-下篇】的用法：

1. 使用Dockerfile构建Docker镜像

[十一、spring-boot2-redis](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-redis)

介绍Springboot2【Redis分布式缓存服务集成】的用法：

1. Redis集成，常用api用法
2. 统一封装工具类（兼容解决中文乱码问题）

[十二、spring-boot2-dubbo-provider](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-dubbo-provider)

介绍Springboot2【Dubbo服务提供者、服务消费者整合/Zookeeper集成】的用法：

1. Dubbo服务提供者、服务消费者整合/Zookeeper集成

[服务提供者-源码](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-dubbo-provider)

[服务消费者-源码](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-dubbo-consumer)

[十三、spring-boot2-distributed-id](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-distributed-id)

介绍Springboot2【全局唯一ID/分布式ID解决方案】的用法：

1. 分布式ID解决方案

[十四、spring-boot2-mysql-druid](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-mysql-druid)

介绍Springboot2【Druid连接池集成】的用法：

1. Druid连接池集成
2. Druid Web界面用法

[十五、spring-boot2-logback](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-logback)

介绍Springboot2【Logback日志框架集成】的用法：

1. Logback日志文件讲解说明

[十六、spring-boot2-logback-elk](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-logback-elk)

介绍Springboot2【ELK日志集成】的用法：

1. ELK日志集成说明

[十七、spring-boot2-logback-async](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-logback-async)

介绍Springboot2【日志异步化处理用法】的用法：

1. 日志异步化处理

[十八、spring-boot2-exception](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-exception)

介绍Springboot2【全局异常处理】的用法：

1. 全局异常处理方式（自定义error请求返回结果）

[十九、spring-boot2-api-protect](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-api-protect)

介绍Springboot2【接口防刷机制】的用法：

1. 接口防刷机制

[二十、spring-boot2-distributed-lock](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-distributed-lock)

介绍Springboot2【分布式锁用法】的用法：

1. 分布式锁用法（基于Redis实现）

[二十一、spring-boot2-mongodb](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-mongodb)

介绍Springboot2【MongoDB集成】的用法：

1. MongoDB集成用法

[二十二、spring-boot2-oauth2-opaque-server](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-oauth2-opaque-server)

介绍Springboot2【Oauth2+Token详细用法/SpringSecurity】的用法：

[认证服务-源码](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-oauth2-opaque-server)

[资源服务-源码](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-oauth2-opaque-resource)

[二十三、spring-boot2-oauth2-jwt-server](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-oauth2-jwt-server)

介绍Springboot2【Oauth2+JWT集成/SpringSecurity集成】的用法：

[认证服务-源码](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-oauth2-jwt-server)

[资源服务-源码](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-oauth2-jwt-resource)

[二十四、spring-boot2-swagger](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-swagger)

介绍Springboot2【Swagger集成用法】的用法：

1. Swagger2集成用法

[二十五、spring-boot2-mybatis-plus](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-mybatis-plus)

介绍Springboot2【Mybatis-Plus快速开发框架用法】的用法：

1. Mybatis-Plus快速开发框架用法

[二十六、spring-boot2-swagger-req-params](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-swagger-req-params)

介绍Springboot2【header/body接口参数+Swagger2集成用法】的用法：

1. header/body接口参数+Swagger2集成用法
2. 接口常用规范用法

[二十七、spring-boot2-valid](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-valid)

介绍Springboot2【@Valid注解用法详解+全局处理器Exception优雅处理参数验证用法】的用法：

1. @Valid注解用法详解
2. 全局处理器Exception优雅处理参数验证

[二十八、spring-boot2-log-level](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-log-level)

介绍Springboot2【动态修改日志输出级别】的用法：

1. 动态修改日志输出级别用法

[二十九、spring-boot2-wechat-pay](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-wechat-pay)

介绍Springboot2【微信企业支付集成（五分钟集成）】的用法：

1. 微信企业支付集成（五分钟集成）

[三十、spring-boot2-alipay](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-alipay)

介绍Springboot2【支付宝企业支付集成（五分钟集成）】的用法：

1. 支付宝企业支付集成（五分钟集成）

[三十一、spring-boot2-crawler](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-crawler)

介绍Springboot2【爬虫框架集成】的用法：

1. 爬虫框架集成

[三十二、spring-boot2-mybatis-plus-multi-datasource](https://github.com/hemin1003/spring-boot-study/tree/master/spring-boot2-study/spring-boot2-parent/spring-boot2-mybatis-plus-multi-datasource)

介绍Springboot2【MybatisPlus框架动态数据源用法】的用法：

1. MybatisPlus框架动态数据源用法：支持多个DB，切换数据源


<br/>
<br/>
<br/>

其他内容，持续更新中，敬请期待

<br/>
<br/>
<br/>

## 该系列文档教程

[SpringBoot系列教程-从入门到精通](https://hemin.blog.csdn.net/column/info/40170)

## 个人说明

期望和大家”一起学习，一起成长“，共勉，O(∩_∩)O谢谢

不讲虚的，只做实干家

Talk is cheap，show me the code

<br/>


## [关于我](http://heminit.com/about/)

欢迎交流问题，可加我的个人QQ 469580884，或Q群号 751925591，一起探讨交流问题

[我的博客地址](http://blog.csdn.net/hemin1003)

[个人域名](http://heminit.com)

## 感谢
如果觉得内容赞，您可以请我喝一杯咖啡：
<br/>
<img src="http://cdn.popstar.toponegames.mobi/img/wechat.jpeg" width="240px" height="240px" />&nbsp;&nbsp;&nbsp;&nbsp;
<img src="http://cdn.popstar.toponegames.mobi/img/alipay.jpeg" width="240px" height="240px" />
