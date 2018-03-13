# 使用SpringBoot开发内容管理系统(CMS)
&emsp;&emsp;此项目是实现前后端分离[vue-cms](https://github.com/LeeCP8/vue-cms)的后台系统，使用maven进行依赖管理，利用SpringBoot快速配置项目，而持久层是利用SpringBoot提供的JpaRepository接口实现的。

## 前提条件
&emsp;&emsp;jdk1.8 maven mysql

## 启动
1. 将根目录下的cms_test.sql导入到mysql中，运行命令source cms_test.sql(绝对目录)
2. 直接运行com.leecp.jpa.Application.java即可，端口为8081，可以在resources/application.yaml中进行更改

## 项目结构

```plain
|    ├── common -- 基础支持，如工具类，验证器类
|    ├── config -- 项目配置
|    ├── controller -- 控制器
|    ├── model -- 持久化实体
|    ├── repository -- Dao接口
|    ├── service -- 服务逻辑实现
|    ├── vo -- 视图实体
|    ├── Applicant -- 项目启动类
```

## 实现功能
实现了系统，菜单，类别，标签，专题等的CRUD及分页操作，同时提供数据验证。为[vue-cms](https://github.com/LeeCP8/vue-cms)前端调用提供数据。此项目主要是用于练习前后端分离而创建的项目，所以并不会像[upms](https://github.com/LeeCP8/upms)那样提供权限控制，当然此项目计划利用JWT来提供验证。所以此项目并不能直接用于生产环境，只适于单纯的前后端分离实践。
