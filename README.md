# 关于本工程的介绍

## 0. 一些说明
- 本工程采用Spring Boot的内置容器，而非独立的Servlet容器，所以如果打war包，请使用java -jar启动，如果要部署到自己的tomcat服务器，请自行调试
- 如果更改独立容器，请一定检查websocket功能是否可用

## 1. 一些要求
- 为了系统拆分清晰，如无特殊性能要求，尽量不要用联表查询，要用单表多次查询。
- 上述要求的原理：SOA 面向服务架构。有利于提升组件可重用性，可维护性。 硬性杜绝了开发者和设计者为了省事而将复杂的关联关系写在SQL语句中的行为。迫使人使用程序代码代替SQL语句来处理问题。
- 关于bkserver-user模块，由于定制化、单点登陆等原因，本身应该有的bkserver-oauth鉴权模块统一合并到bkserver-user中，将来可根据业务需要将鉴权业务和对应的数据模型单独拆分出来。
- 请装Lombok插件，否则项目跑不起来
- 写好各自模块的.gitignore文件，不要把没用的系统配置文件上次SVN

## 2. 实体类对象
- **VO（View Object）**：Web <--> Controller
- **DTO（Data Transfer Object）**：Controller <--> Service
- **DO（Data Object）**：Service <--> DAO
- **Query**：数据查询对象，各层接收上层的查询请求。注意超过2个参数的查询封装，禁止使用Map类来传输。
- 本次项目中我们为了降低实体类多次转换的繁琐，选择性的屏蔽掉VO层，由**DTO层完成 Web <--> Controller <--> Service**
- 由于do为java保留着，所以DO层包名为dos复数形式

## 3. 实体类命名规约
- DO：xxxDO，xxx 即为数据表名。
- DTO：xxxDTO，xxx为业务领域相关的名称。
- Query：xxxQuery，xxx为查询业务领域的相关名称。

## 4. 功能分层及作用
- **Controller**：轻业务逻辑，参数校验，异常兜底。业务逻辑必须要轻，甚至不做具体逻辑。
- **Service**：进行业务编排逻辑。
- **DAO**：只允许自己的Service访问，其他Service要访问必须通过对应的Service。

## 5. 各层命名规约
- 获取单个对象的方法用 get 做前缀。
- 获取多个对象的方法用 list 做前缀，复数形式结尾如：listObjects。
- 获取统计值的方法用 count 做前缀。
- 插入的方法用insert 做前缀，批量用insertBatch。
- 删除的方法用delete 做前缀，批量用deleteBatch。
- 修改的方法用update 做前缀，批量用updateBatch。
