### store是一个网络购物商城项目

***
###### 项目的技术点:JSP/Servlet,JAVA,Mysql,redis,Json,Ajax,JSTL,反射等等。请求使用了面向对象继承的思想，抽取了一个基类用于其他实现功能的类去继承，简化了开发难度。导航条使用Ajax请求，使用redis数据库对数据进行缓存，有效的缓冲Mysql数据库的压力。数据库连接池使用了C3P0，提升了系统响应速度，使用BeanUtils简化了对象数据的填充,降低了代码的冗余，使用DBUtils简化了SQL语句的编写。

***
##### 功能简介:实现了用户注册，登录，退出，浏览商品，查阅商品详情，加入购物车，清除某一项商品，清空整个购物车，使用了权限过滤，用户登录后蔡可以查看订单，我的订单。用户登陆后提交订单，查看我的订单。
***
###### 使用方式:创建数据库，数据表(根据store.sql进行创建)，打开redis数据库，C3P0-config.xml进行配置数据库账户密码,使用tomcat启动项目
***
部分效果图:Store/showImage
