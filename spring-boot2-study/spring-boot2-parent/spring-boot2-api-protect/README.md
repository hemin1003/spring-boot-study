# spring-boot2-api-protect

【接口防刷机制】技术点

功能介绍

1. 接口防刷机制用法

策略逻辑：

1. 可以在参数中增加一个动态随机字符参数，比如sId，每次请求时，对方都需要动态生成一个十位随机字符，防止sign值一直固化不变
2. 同时，服务器可以校验请求是否重复，比如可以通过redis存储已请求过的rId，每请求一次就需要重新生成一个新的rId（可设置过期时间，以免一直存储历史的rId值），防止别人利用固定请求链接刷请求
3. 可以使用公网ip，限制同一个ip访问次数（也可以在nginx层做限制，这个自行网上了解了）

## 本项目教程

[接口防刷机制](https://hemin.blog.csdn.net/article/details/99637749)

## 该系列教程

[SpringBoot2系列](https://blog.csdn.net/hemin1003/column/info/40170)

## 个人说明

期望和大家”一起学习，一起成长“，共勉，O(∩_∩)O谢谢

不讲虚的，只做实干家

Talk is cheap，show me the code

<br/>


## [关于我](http://heminit.com/about/)

欢迎交流问题，可加我的个人QQ 469580884，或Q群号 751925591，一起探讨交流问题

[我的博客地址](http://blog.csdn.net/hemin1003)

[个人域名](http://heminit.com)