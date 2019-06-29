# spring-boot2-schedule

【@scheduled定时器】技术点

功能介绍

1. 定时器计划用法介绍
2. 开启并行多线程任务两种方式
3. 场景案例分析

## 本项目教程

[@Scheduled定时器用法和场景案例分析](https://blog.csdn.net/hemin1003/article/details/90454462)

## 该系列教程

[SpringBoot2系列](https://blog.csdn.net/hemin1003/column/info/40170)

## 场景案例分析

### 1. 分布式部署下任务重复执行问题

可以通过分布式锁实现，多节点部署时，在任务启动时先获取锁是否存在，如果不存在，则加锁后、自身执行

比如通过redis setnx实现，向redis中存一个key值，每次会先判断该key值是否存在

### 2. 当服务器有问题如宕机，那失败或丢失的任务如何处理，怎么补偿

可以把每次执行时间持久化到db中（我们目前使用中的方式），或者存入到redis中，然后判定周期区间，重新执行任务

## 推荐平台

1. [Elastic-Job](http://elasticjob.io/index_zh.html)

Elastic-Job是一个分布式调度解决方案，由两个相互独立的子项目Elastic-Job-Lite和Elastic-Job-Cloud组成。
Elastic-Job-Lite定位为轻量级无中心化解决方案，使用jar包的形式提供分布式任务的协调服务；Elastic-Job-Cloud采用自研Mesos Framework的解决方案，额外提供资源治理、应用分发以及进程隔离等功能。

2. [XXL-JOB](http://www.xuxueli.com/xxl-job/#/)，这里推荐此家，轻量、简单易用

XXL-JOB是一个轻量级分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展，与springboot集成十分简单。

### 附加

当项目不大、业务逻辑不是很复杂时，利用@Scheduled来执行任务或跑批，一般来说就够用了

随着项目发展壮大，业务越来越复杂时，就可以考虑使用上面提到的推荐平台其中之一了


## 个人说明

期望和大家”一起学习，一起成长“，共勉，O(∩_∩)O谢谢

不讲虚的，只做实干家

Talk is cheap，show me the code

<br/>


## [关于我](http://heminit.com/about/)

欢迎交流问题，可加我的个人QQ 469580884，或Q群号 751925591，一起探讨交流问题

[我的博客地址](http://blog.csdn.net/hemin1003)

[个人域名](http://heminit.com)