# JT-progra
## 京淘项目

15年12月 视频

正在跟视频更新源码中。。。
跑项目目前请用maven来跑，
配置位置：
eclipse-->Run-->Run Configurations...-->Maven Build
中新建一个Maven Build
Main页签：
Base directory 选中jt-manage-web工程
Goals 填写 tomcat7:run
Source页签：
Add 加入全部工程
其他一般都用默认的，jre用自己的

###1.京淘架构

学习方法：
思想，业务，表设计，代码

特点：
技术新
技术范围广，6个大点
分布式
高并发、集群、负载均衡、高可用
海量数据
业务复杂
系统安全

软件分析：
nginx 负载均衡 自动分配服务器
tomcat 负载最大大概150-200/秒
weblogic 负载200-500/秒
apache 
动静分离web服务器 常用PHP
可被nginx替代，是apache中间件100倍，
c写的，Linux epoll技术

集群，单点故障，
高可用 zookeeper
分布式，高并发

Maven继承，聚合
mysql主从复制，读写分离



###第一天：

####第一天草稿：
1.创建一个父工程
jt-parent，目标管理依赖
pom.xml类型packaging修改为pom

2.增加jar依赖，只是管理。
首先加核心jar，
其次数据源相关的，
重点的技术，
最后加外围不重要的

3.创建工具类工程 jt-common

4.创建后台工程
jt-manage
纵向拆分：
pojo（JPA注解）
mapper（接口）
service
controller

聚合 jt-manage pom工程 继承jt-parent
jt-manage-pojo 依赖 jt-common
jt-manage-mapper 依赖 jt-pojo
jt-manage-service 依赖 jt-pojo
jt-manage-web 依赖 jt-service

聚合工程：
maven module

通用Mapper（只支持单表）
这个只处理单表，他会自动生成所有的文件，
映射文件，sql语句，
只需要在pojo上使用JTA注解。
User.java

@Table("user_c") //表名
public class User
@id //主键
private String id
@Column("user_name") //字段名
private String username
条件自己写

通用mapper少 根据ID批量删除方法
需要自行继承扩充


需求：
1）展现后台页面，easyui。
直接访问一个路径，直接转向到某个页面
http://localhost:8082/page/index
访问WEB-INF/views/index.jsp


controller分配新方式
RESTFul方式
/page/item-add/100
这个就是参数
以get请求实现post



EasyUI界面布局：
自己提供很多组件
布局、树型、页夹、按钮
局部刷新：ajax

树形组件 异步加载
带条件查询


表设计特点：
超大型项目特点
分类表：（京东菜单）
只有三类，大中小，用户体验好
大—中—小 —商品列表

1.主键 bigint
2.常查询字段做索引
3.关联关系作为普通字段，用代码来维护

实现步骤
1.pojo jpa
2.接口文件


####第一天总结：
1）京淘电商，互联网电子商城，类似京东、淘宝大型电商网站。
主要业务就包括：
用户在商城浏览商品，看中的商品加入购物车，提交订单。
-后台系统
（管理商品、维护商品分类、维护商品信息、
内容管理、用户管理），
-前台系统
（展示商品分类分为三大类、展示商品分页列表、
展示商品详细的信息、商品规格管理），
-购物车
（加载商品到购物车、将用户选择的数据存放到cookie中、
提交订单时合并cookie到数据库中这个用户的购物车中），
-订单系统
-物流系统
-sso单点登录 + redis

2）Maven 继承、聚合
在大型项目中就将项目分割成多个子项目，maven直接支持。
例如：
jt-parent POM 项目，父项目，管理整个京淘项目的所有的jar的依赖
jt-common JAR 项目，工具类的集合，继承 jt-parent
jt-manage POM 项目，聚合项目，在聚合项目中增加moudles标签，
里面加载所有的聚合的子项目，项目是有顺序性
jt-manage-pojo 持久化类 + JPA注解，
实现数据库表和字段对应 PO 的属性，
在类上加 @Table 标识类对应的是哪个数据库的表；
在主键上加 @Id 主键标识；
在主键上加 @GeneratedValue(strategy=GenerationType.IDENTITY)，
自增，前提是设计数据库表必须支持自增（mysql、sqlserver）,
Oracle序列需要单独配置；
在数据库字段和属性不一致的情况下，加 @Column 标识对应关系
jt-manage-mapper 接口文件，
必须继承SysMapper，通用Mapper第三方组件，
封装该组件，扩展CRUD，扩展了一个批量删除根据IDS，
充当DAO层，利用注解可注入到service层，
只封装单表CRUD操作（好处不用映射），特殊方法需自行扩展，
为了好扩展，习惯性写好接口
jt-manage-service 业务层，业务层直接注入mybatis接口
jt-manage-web web项目 后台系统的前台展现，
必须有资源文件，必须有静态文件

Maven 项目管理
maven多个子项目分别部署，开发时非常适合团队开发，
每个子项目高度聚合，可以单独发布到本地仓库，
其他的项目依赖即可。传递依赖。可以轻松管理大型项目。

SVN 版本控制
冲突：
1）提供多个版本的比较，自动显示文件中的差异
2）可以对文件进行加锁，检出的加锁文件不能提交
（核心配置文件常用加锁）

商品分类表设计：
1）主键类型考量
UUID易于合并和导出
速度：int>long>uuid
电商：数据非常 量大，操作非常集中，并发压力大，
采用长整型，(mysql上亿效率明显降低)，
上千万后一般会分表（保持当前表数据量在近百万）
2）对查询条件中常用的字段进行索引（查询），
大量查询时就非常块。
3）表都成为单表。
超大型项目，没有表之间关联，无数据库约束，
将表直接的关系都变成代码维护关系，
POJO的关系要根据当前的业务操作，
还是要建立对象之间的关系，不要太复杂，最多4层，
利用mybatisSQL的映射，
查询多个表的数据，将数据映射到各个层次的对象中。

EasyUI控件
了解基本的用法，山寨，错误学习法
布局：
class属性控制，
jsp解析后，执行js，easyUI渲染render，加工标签，menu菜单/tabs页夹
tree 数据动态：
从数据库来组织数据，
绑定按钮点击事件，弹框窗口，初始化tree，
发送ajax请求，自动带id（父id），第一次为空，后台默认为0
响应固定json字符串（传输中会序列化成文本流），tree自动解析json，
json带有的特殊字段，需要自行在POJO添加get方法。

easyUI组件 + 后台准备数据形成json格式 + 前台ajax提交

springmvc：
1）RESTFul访问形式 
http://localhost:8081/item/cat/list/100 
在 controller @RequestMapping中要写占位符{name}，
在方法的形参上接收的注解 @PathVariable 标识
2）在方法上增加 @ResponseBody，
springmvc会自动将返回的java对象转换为json串。


###第二天：

####第二天草稿：

商品管理
价格：
java浮点数室友精度问题，bigDecimal可以处理，
但是整型永远比浮点型计算快，金额： 9.99 <=> 999，
在计算价格时非常快。
图片：
存地址，多个链接用分隔符分割

商品新增：
1）POJO文件 + JPA
2）映射接口 ItemMapper
3）创建 ItemService
4）分析页面，得到 controller 访问地址，参数，返回值
a） /item/save
b）通过 jQuery.serialize 方法将表单中的所有的 html 控件值拼接，
controller item 对象来接收数据
c）返回参数对象中必须有一个status的属性。
200 约定执行成功，构建SysResult对象
5）创建 controller

商品列表：
easyui-datagrid 表格控件，自动带分页
访问地址：/item/query
参数：分页参数 
easyui固定写死的分页参数
page 当前页、rows 每页条数
返回值：EasyUIResult 结构是为datagrid量身定制

controller
四种返回情况：
1）直接逻辑名
2）返回 java 对象，转为 json 串
3）返回 SysResult 对象，增强页面提示。状态码提示成功/失败
4）返回 EasyUIResult 对象，主要用于 datagrid 控件

分页：
引入分页工具类 PageHelper、PageInfo，
原理：
1）记录是在执行 SQL 前，被 mybatis 拦截器拦截，加入分页语句
2）total 总记录数，在后台共执行2个 SQL 语句，count/select

商品修改

商品删除
$.post("/item/delete", params, function(data)
可以 id 接，可以 id[] 接

商品描述
大文本框，图文结构。
大字段，为了加快处理速度，不能把它的信息直接放在商品表中
设计了1对1的关联表，单独存放它的扩展的信息
在页面中需要配合图文的控件，kindEditor富文本编辑器，
利用它就可以直接输入图文的内容
kindEditor工作原理，将页面上的一个 textarea 进行渲染，
实际上是一个 iframe 。
kindEditor控件在保存时，会将 iframe 内容形成一个 html 串，
写入到 textarea 里

SELECT MAX(id)+1 FROM tb_item
有高并发问题，两个人拿到的id一样

SELECT LAST_INSERT_ID()
跟随在插入动作之后，拿到id
mysql对该语句加锁，无高并发问题
mybatis底层封装插入后返回id到插入对象中

程序处理高并发，加锁

Oracle序列


图片上传
KindEditor功能，
分为一次只上传一张图片，一次可以上传多个图片，
不能独立完成，必须配合后台类来实现，
springmvc文件上传组件，
单独发出ajax请求，独立进行图片保存，
保存后将图片的链接返回

本地图片路径转换：
1.修复本地host文件
通过修改下个文件中解析转换
C:\Windows\System32\drivers\etc\host
window在解析域名时，会先读取该文件看是否可解析
在文件底部加入
127.0.0.1 www.jt.com
127.0.0.1 manage.jt.com
127.0.0.1 image.jt.com
127.0.0.1 sso.jt.com
127.0.0.1 order.jt.com
127.0.0.1 sso.jt.com
127.0.0.1 solr.jt.com
127.0.0.1 cart.jt.com
127.0.0.1 search.jt.com

2.SwitchHosts工具
可通过工具转换，更方便
有可能有BUG，需要每次检查下文件

图片的nginix本地配置：
解析域名后面的路径

Nginx：
高负载均衡的服务器
是高性能的HTTP和反向代理服务器
也是IMAP/POP3/SMTP代理服务器
性能高：c语言 + linux epoll方式 
支持 5w 个并发请求

安装：
安装直接解压即可，选择非中文目录
在nginx/conf目录下
修改 nginx.conf 配置文件
在文件尾部加入

	#后台服务器
	server {
		listen 80;
		server_name manage.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			proxy_pass http://127.0.0.1:8081;
			proxy_connect_timeout 600;
			proxy_read_timeout 600;
		}
	}
	
	#前台服务器
	server {
		listen 80;
		server_name www.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			proxy_pass http://127.0.0.1:8081;
			proxy_connect_timeout 600;
			proxy_read_timeout 600;
		}
	}
	
	#SSO服务器
	server {
		listen 80;
		server_name sso.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			proxy_pass http://127.0.0.1:8081;
			proxy_connect_timeout 600;
			proxy_read_timeout 600;
		}
	}
	
	#购物车服务器
	server {
		listen 80;
		server_name cart.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			proxy_pass http://127.0.0.1:8081;
			proxy_connect_timeout 600;
			proxy_read_timeout 600;
		}
	}
	
	#订单服务器
	server {
		listen 80;
		server_name order.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			proxy_pass http://127.0.0.1:8081;
			proxy_connect_timeout 600;
			proxy_read_timeout 600;
		}
	}
	
	#前台搜索服务器
	server {
		listen 80;
		server_name search.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			proxy_pass http://127.0.0.1:8081;
			proxy_connect_timeout 600;
			proxy_read_timeout 600;
		}
	}
	#图片服务器
	server {
		listen 80;
		server_name image.jt.com;
		#charset koi8-r;
		#access_log logs/host.access.log main;
		
		proxy_set_header X-Forwarded-Host $host;
		proxy_set_header X-Forwarded-Server $host;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		
		location / {
			root d:\\jt-upload;
		}
	}

启动：
请勿双击.exe文件

####第二天总结：
1）商品管理
商品关联商品描述信息，表设计将一张表的内容拆成 2 部分，
商品基本信息表，描述表，
才能保证查询主表信息时，效率最高。

hibernate 对象设计时，按照一对一设计时，
表设计无用的，反而效率更低，
无懒加载，一对一立即加载，
hibernate就不应该设置一对一关联，而用一对一特殊设计，
即直接用主表的ID进行查询

mybatis没有，配置 resultMap 来分成多种情况。
只查主表信息，单独对应一个 ResultType(ResultMap)，
如果需要关联的对象，单独配置一个 ResultMap 这里配置一对一映射，
即两条路，两条sql，分别走的不同。
mybatis按需求来订制，才能保证最高性能。


2）商品 CRUD 操作
EasyUI 对 html 控件进行加强，校验，限定输入的内容。
KindEditor 富文本编辑器，图文编辑，
利用 KindEditor 控件实现图片上传，
需要后台类支持（java/php/.net）。

利用 springmvc 文件上传，文件解析器，
在方法的参数上直接用 MultiparFile 对象，
这个对象会自动封装页面上的 multipart 文件头，
解析它封装到这个对象中。

（commons）页面上传文件，不能上传很大文件，控制在 10M 以内，
如果过大会被其他请求中断，1G 大约 1小时。

大文件上传：
ftp
c/s客户端，多线程上传 + 断点续传，一般10个线程最佳，
文件分割 + 文件合并，htm5改进了，增加了本地缓存，也可以用这种方式，


3）商品列表
EasyUI datagrid 控件，直接有表格，分页。
组织 List 数据，必须封装到 EasyUIResult data 属性中。
页面直接获取它的 json 串。
工具栏、多选控制，选择一个/多个的校验。


4）商品修改
在 item-list.jsp 中配置修改按钮，它会打开一个 window，修改时回显数据。
救被分成两种方式，第一种直接从 datagrid 中读取数据，这样修改页面加载非常快，
隐患可能导致多用户编辑时，数据不同步，还可能造成数据覆盖。
第二种方式，它直接查询数据库，发起 ajax 请求，获取到数据没在页面显示。


5）商品删除时
利用 easyUI给我们提供的 js 方法获取到当前用户选择的 id


6）nginx webServer 
负载均衡，只单点转发，转发效率特别高。
1.使用 c 语言
2.占内存少
3.所做的事情少

PHP
apache 静态资源转发
tomcats 动态
第七层转发协议

被nginx替代
nginx 静态资源装
tomcats 动态
第七层转发协议

lvs
linux 底层提供的 API  操作系统级别
第四层转发协议

F5 硬件设备
贵的几十万
第四层转发协议

nginx用法：
反向代理（从外到内访问），（正向从内到外访问），
这样可以直接提高系统的安全性，外部不能直接访问内网。
需要配置 IP 地址转换，
映射内网地址和端口，还可以直接映射路径
（针对静态资源文件就直接返回）



###第三天：

####第三天草稿：

商品规格
1）记录商品规则，方便后期统计查询，还可以做同类商品的比较。
2）分类动态，分类项动态，一个分类对应
数据库存 json 串
结合 EasyUI 动态添加树结构，绑定一个模版。

模版怎么实现？
某一个商品分类，只能有一个模版

JsonView 工具
用来查看 Json 结构
在线版：
http://www.bejson.com/jsonviewernew/

RESTFul形式提交 id 参数
var url = "/item/param/save/" + $("#itemParamAddTable [name=cid]").val();

mybatis 它的删除，如果有多个关联对象，
必须一一删除，这点就不如 hibernate 级联删除。
但是mybatis性能相对高点


搭建前台系统
为了便于编码展示，不使用聚合工程，直接用一个web工程
后台系统中springmvc是拦截所有，在前台系统中是*.html，
伪静态，实质是jsp

在Tomcat的web.xml中，mime标签配置了一些常用后缀的解析
    <mime-mapping>
        <extension>bmp</extension>
        <mime-type>image/bmp</mime-type>
    </mime-mapping>

防止病毒项目：
将所有的文件后缀修改，
例如：将 js 改成 jsnu
只要木马病毒会修改的文件后缀全部重新命名，
之后只要修改Tomcat的conf/web.xml 中，
mime类型都对应修改即可

伪静态的目的，为了爬虫，爬虫只收集静态网站内容，
将其抓取，分解

eclipse 设置中的Validation可以关闭/开启文件校验


####第三天总结：
知识回顾：
商品规格+搭建前台系统

1）商品规格
商品有一个特殊属性，两个目的，
实现商品详细特性参数比较，
这个参数根据不同的商品有不同的内容。
[group,{name,value},...]

2）规格参数模版
形成规格参数模版，
把程序写活了。

3）新增商品/修改
新增商品时，新增商品描述，新增规格参数。

hibernate 构建对象关联关系，注意时刻绑定关系。
hibernate 集中对象关联，面向对象。
mybatis 全部打乱，分别处理，

例如：保存商品，保存描述，保存规格参数，
三个对象的外键必须设置，后期代码实现关系，
外键变为了每个单独对象的属性。
面向过程。

4）级联删除
hibernate 中非常简单，只需要配置上下级的关联关系，
删除当前对象时，hibernate 会自动生成调用删除其他对象的 SQL 语句。

框架的目的是解放编码，集中到业务处理上

mybatis 级联删除不存在的，必须一个一个删除。
需要开发人员自己控制。

5）创建前台系统
步骤：
1. 创建 Maven 工程
2. 继承父项目 jt-parent，依赖 jar 就都有了
3. 没有持久层，修改数据库数据的地方，应该尽量的少，
操作核心的地方尽量一个入口，方便进行监控
P2P，钱，底层只负责出和进
4. springmvc 映射 .html 伪静态，欺骗爬虫


###第四天：

####第四天草稿：


需求：
1. 访问后台系统，获取到商品的分类，在前台页面展现
http://manage.jt.com/web/itemcat/all?callback=category.getDataService

跨域：
js 规范，同源策略，限定。
必须是同源的，不同源就会提示报错：
XMLHttpRequest cannot load http://manage.jt.com/test.json. No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://www.jt.com' is therefore not allowed access.
必须域名相同，端口相同。
只要有一个不容，就称之为跨域

jsonp
实现跨域
javascript中有一个特殊的标签可以跨域<script src="">
原理：
将json串包装起来，即在外层加一个函数扩起来，
作为js方法的传参，传入的代码中需要提前定义这个函数。
test({
	"key": 123
})

搭建环境
springmvc 和 .html 发生冲突，
必须加一个 json 的特殊转换配置


@JsonProperty("u")
jackson提供的，它实现将 java 对象的属性名称做一个映射，
例如将 name 映射成 n。形成的整改 json 串就长度减少。


Redis 缓存
EhCache 二级缓存，最早不支持分布式，
一般作为框架的小型应用，在单机版使用。
MemCache 支持分布式，
致命缺陷：掉电丢失。重启后加载就会漫，用户体验不好，
支持的数据类型少。
Redis 支持数据落地，定时会自动将内存中的数据写磁盘，
因为很多信息在落地日志中，如果发生断电、重启，重建速度快，
支持的数据类型多，程序代码处理简单。
每秒11w次

Redis NOSQL not only sql

安装环境
windows/linux
[图片]

redis.conf 文件
两个重点：端口，内存
一般内存200mb+，
1）redis不设上限会持续耗完电脑内存，部署必须设置
2）6379 默认端口，如果起多个服务，必须修改端口
3）默认16个数据库（即分块）
4）只有string、set、list、hash、zset类型，简单、快

使用：
Jedis java 来操作 redis 服务器
池化：直接使用 jedis 有隐患，需要使用池化技术
多节点，就写多个xx.conf文件就ok，配置不同端口
redis-cli -p 6380
新复制的 redis-server 会有遗存值
分片：hash一致性算法，固定的key，就得到固定的值，
hash值固定，根据离那个hash值近，就放入哪个桶，
多个节点时，数据会比较平均分配到这几个节点上，
存入节点永远固定的。

如果加到三个节点，数据是否会偏移？
原来节点值如何处理？
重新分配会偏移，不处理原值

缓存中的数据一定要有保存，例如数据库中一定有它的值。
当缓存失效时，可以直接去数据库中再次查找。

缓存命中率：使用频度高，数据修改少的。
将redis，jedis集成到项目中，封装成一个redisService，
封装了日常的get,set方法。


####第四天总结：
知识回顾：
1）跨域
IP地址或者访问端口不同时，
js要求必须同源，script标签它允许访问跨域链接，
根据它的原理来构建结构

2）script为什么能跨域
实现js函数的调用，写一个script标签，自定义 function aa(执行 js 语句)，
返回 json 格式串，返回就不能直接是json串，而必须是jsonp。
在 json 串外包装了一个 function 方法，这个方法就是上面定义 aaa。

3）JSONP，有两个要素：
1. json格式串
2. 必须用一个 callback 自定义函数名进行包装，
调用时就按函数名进行包装。jQuery中广泛使用。
解决跨域问题。

在前台页面中，需要调用一个分类展现。
不能直接访问数据库，由前台jsp中调用ajax请求，
ajax发出jsonp访问后台系统的controller，
后台的controller调用后台service，
后台的service调用持久层，持久层访问数据库，
层层的返回，最后到后台的controller，
由后台的controller经过springmvc进行封装，
封装结果为一个json串，
在springmvc-config.xml核心配置文件中，
加入对json返回时进行拦截，
自定义一个格式转换器加上页面请求时传递过来的callback参数，
这个参数就是script标签中自定义的函数名称。
经过自定义转换器拼接，返回json串。
页面获取到返回，下面就调用这个函数，
把返回值就座位这个函数体。
直接调用，从json串中解析数据显示到页面上。

4）Redis 安装、配置、简单使用
安装：
从官网上下载（linux）
window在官网外链上，链到github
配置：
conf/redis.conf
1. 一定要设置maxmemory，默认redis会一直申请内存，
可能占满物理内存，导致系统瘫痪。
常用 200m， 500m， 2G。
可以给多个业务分别起redis服务。
2.默认端口 6379，可以进行修改
启动，redis-server，
如果要起第二个服务，拷贝配置文件，
修改配置文件的名称和端口，redus-server 6380.conf，
如果要为外部服务器提供服务，必须开放 iptables 中端口

Jedis
它是java来操作redis客户端API
shard分片技术，redis-server分布在不同的服务器。
产生问题，写数据该写到哪个服务器上，
读数据怎么就读到我写的那台服务器上呢？
1. 取余 i%服务器个数。 轮询。
服务器吞吐率不同，无法负载均衡。
2. hashcode，将一个值进行hash算法，唯一的一个值，
加上取余 = hash一致性。
找到服务器和程序的key直接的依赖关系。
当动态扩充分片的个数时，会导致原来落入a节点数据，
现在被落到c节点上，程序该从哪里去获取数据呢？
程序因为按新的算法来获取数据，必然就从c节点上获取数据。
这时可能c节点的数据还未写入，就无法获取信息。
这时程序不能进行报错，就直接从数据库中获取。

*雪崩：
内存成为访问瓶颈，导致redis这台服务器挂了。
大量访问就直接跳过缓存服务器，直接访问数据库服务器，
连锁反映，数据库挂的更快。导致数据丢失。
解决方案：
加硬件：耗电、带宽
不是万能的，扩充到一定数量后，维护的成本就越来越高。
高可用。
最终解决方案：（绝招）
停止服务。把很多服务进行抛弃

国美在线：
挖墙脚-来了就能解决实际问题。
缓存架构 redis，数据库 mysql，队列 rabbitmq，
dubbo 服务层抽象（阿里，内部已放弃，开源， // 已无人维护
当当山寨 dubbox） // 提高 spring 等版本， 加入 RESTFul 支持
海尔、国美在线都在使用 dubbox
京东最老是.net框架做的，曾经账务乱了。



