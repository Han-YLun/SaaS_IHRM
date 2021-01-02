基于Saas的IHRM系统

## 开发环境

* JDK : Oracle JDK 1.8
* Mysql： 5.7
* Maven： 3.5.2
* Redis： 2.4.5

## 项目截图

### SaaS管理员模块
![](https://img-blog.csdnimg.cn/2021010212211313.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/2021010212211315.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/2021010212211315.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70)

### 企业管理员模块
![](https://img-blog.csdnimg.cn/20210102122320427.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320406.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320406.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320396.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320386.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320323.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320379.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320355.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320347.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)
![](https://img-blog.csdnimg.cn/20210102122320338.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3ODg4Mzgy,size_16,color_FFFFFF,t_70#pic_center)



## 系统架构



![系统架构图.png](https://i.loli.net/2020/06/18/S3WzdcZrqaIuAO4.png)





此图为博学谷宣传图，图中的招聘模块没有，也没有RabbitMq，其他都有，大家也可以对其进行改进。



![f2654072e7e6f8b481250d26910ef1c6.png](https://i.loli.net/2020/06/18/VDxlzhP1u4vM2cA.png)



## 微服务模块



![微信图片_20200618211632.png](https://i.loli.net/2020/06/18/9wbT1Qx6EY7IhVm.png)

各个模块的功能介绍：

![1877030-20200618213828772-1904163693.png](https://camo.githubusercontent.com/d71639cd21e6fdd29e64b34b238d1fde74e9d673/68747470733a2f2f692e6c6f6c692e6e65742f323032302f30362f32342f77356b79346353756a70614e6769582e706e67)



## 启动顺序

1.  ihrm_eureka   
2.  ihrm_gate
3.  ihrm_company
4.  ihrm_system
5.  ihrm_attendance
6.  ihrm_social_securitys
7.  ihrm_salarys 
8.  ihrm_employee 
9.  ihrm_audit



启动顺序原因：

* 因为所有模块都注册到了Eureka中，故最先启动Eureka模块
* 因为在gate模块后面都是业务模块，所以网关模块放在第二位启动
* 因为system模块通过feign调用了company模块的接口，所以company要在system模块之前启动
* 因为salarys模块通过feign调用了attendance和social_securitys模块的接口，所以salarys在其之后启动
* 后面两个顺序可以打乱
* 模块糅合了某个模块，最好是先去启动被调用的模块



## 使用技术

*  SpringBoot + SpringCloud + SpringMVC + SpringData



# 用户登录

登录时用户名为手机号，密码大多为123456。

帐号信息表在bs_user中。

登录时使用MD5加密，对密码进行三次MD5加密，salt为手机号。

登录时需开启redis，因为登录成功后会把登录信息存储在redis中。

因为我本机的redis使用的是默认，如果设置了帐号和密码，可在ShiroConfiguration.java中配置即可。



## Swagger接口

下载地址 ：

*  链接 ： https://pan.baidu.com/s/1Ych_vKkpjf2ZaxDEII0oDw 
* 提取码：o4ms

注意事项：

* 使用cmd进行目录运行nginx.exe，直接双击运行会无效
* 然后访问localhost:801即可，也可在nginx.conf进行设置接口



## 数据库文件

数据库主要一个主数据库，数据库名为ihrm；另一个数据库名为act，是activity7使用的，可以自动生成

ihrm：

* 链接：https://pan.baidu.com/s/1Alk4MYSU95Esh4QZXB-YOg  
* 提取码：hywc 
* 介绍
  * bs
    * bs开头的表，表示业务相关的内容
  * pe
    * pe开头的表，表示权限相关的内容
  * co
    * co开头的表，表示公司相关的内容
  * em
    * em开头的表，表示员工相关的内容

act ：

* 链接：https://pan.baidu.com/s/1Bi-tKbvnl1iqY7mH2I8qgg    
* 提取码：lzl1
* 介绍
  * act主要为activity7工作流用到的数据库



## BPMN流程文件

在加班、请假、离职时需要进行走流程，使用的是activity7工作流。

流程BPMN文件：

* 链接：链接：https://pan.baidu.com/s/1Sz2jc2782hw2LpuxVeXlSA 
* 提取码：t9hb
* 介绍
  * 将BPMN文件在线上Demo部署，会直接把BPMN文件解析到act数据库
  * act数据库会在线上Demo部署时自动创建
  * 线上Demo部署可以网上找下，官网提供的也有





##  项目讲义

市面只有前7章讲义，这边讲义是全17章

下载地址

* 链接：https://pan.baidu.com/s/1HHVlKG4DlNFtm05UYyeb4Q 
* 提取码：z1sp 
  



## 项目存在的问题

### 员工管理模块

* 保存用户信息时部门id和name不统一



## 前端

前端使用Vue编写，地址为：https://github.com/Han-YLun/SaaS_IHRM_Vue

