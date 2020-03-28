# 基于Saas的IHRM系统

## 开发环境

* JDK : Oracle JDK 1.8
* mysql ： 5.7
* Maven version ： 3.5.2



## 前后端技术

* 前端 : Vue + element-ui
* 后端 ： SpringBoot + SpringCloud + SpringMVC + SpringData



# 数据库文件

数据库主要一个主数据库，数据库名为ihrm；另一个数据库名为act，是activity7使用的，可以自动生成

ihrm：http://www.filedropper.com/ihrm

act ： http://www.filedropper.com/act



## 项目存在的问题

### 社保模块

* 归档后日期应该为新归档的日期,但仍未老日期
* 前端在归档在获取用户详细相信时，id读取不到
* 在归档个人详细信息页面保存时，传往后面的cityId为空

