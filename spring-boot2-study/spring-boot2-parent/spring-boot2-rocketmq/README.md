# spring-boot2-rocketmq

## 【RocketMQ】技术点

RocketMq是一个由阿里巴巴开源的消息中间件，在设计上借鉴了Kafka，其2012年开源，2017年成为Apache顶级项目。

RocketMQ基础组件：

1. Producer是消息生产者
2. Consumer是消息消费者
3. Topic是消息传递中间者，其中存放的是消息逻辑地址

## 功能介绍

1. 如何与springboot集成
2. 如何建立连接，发送不同消息数据类型
3. 如何订阅，且消费不同消息数据类型
4. 场景案例分析

## 场景案例分析

一般MQ适用场景如下

1. 流量削峰，提升系统高并发处理能力，比如秒杀场景
2. 异步处理，应用解耦，提高系统吞吐量

## 附加说明

使用命令新建Topic

1、安装完后，进入到apache-rocketmq目录
2、使用命令：sh /bin/mqadmin updateTopic -n localhost:9876 -b localhost:10911 -t test-topic-1

updateTopic命令主要是三个参数：

-n nameserver地址和端口，一般启动broker时要指定
-b broker_ip:broker_port，ip是启动broker机器的ip，默认是10911
-t 新主题名称

### 问题：注意事项（autoCreateTopicEnable=true）

1、使用默认设置autoCreateTopicEnable=true，这样会导致topic的设置不能规范管理，没有统一的审核等
2、使用默认设置autoCreateTopicEnable=true，还会造成负载均衡失效

所以，一般生产环境中会设置autoCreateTopicEnable=false

设置方式：启动broker时关闭掉，命令如nohup sh bin/mqbroker -n localhost:9876 autoCreateTopicEnable=false &

## 其他官方资料

[RocketMq官方网址](http://rocketmq.apache.org/)

[RocketMq官方安装详细步骤，和案例演示说明](http://rocketmq.apache.org/docs/quick-start/)

[RocketMQ-SpringBoot官方代码Demo](https://github.com/hemin1003/rocketmq-spring)

[RocketMQ-SpringBoot官方代码Demo-中文教程说明](https://github.com/hemin1003/rocketmq-spring/blob/master/README_zh_CN.md)

[RocketMQ控制台-运维管理系统搭建文档](https://github.com/apache/rocketmq-externals/blob/master/rocketmq-console/doc/1_0_0/UserGuide_CN.md)

[RocketMQ-常见问题FAQ](https://github.com/hemin1003/rocketmq-spring/blob/master/README_zh_CN.md)

## 本项目教程

[RocketMQ集成和场景案例分析](https://blog.csdn.net/hemin1003/article/details/90405506)

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
