# 基本实现

## 数据库设计

==user==表

---

|      Field      |    Type     | Null | Key  | Default |     Extra      |    Remark    |
| :-------------: | :---------: | :--: | :--: | :-----: | :------------: | :----------: |
|       id        |     int     |  NO  | PRI  |    -    | auto_increment |     主键     |
|    username     | varchar(32) | YES  |  -   |    -    |       -        |    用户名    |
|      salt       | varchar(16) | YES  |  -   |    -    |       -        |  密码散列值  |
|    password     | varchar(64) | YES  |  -   |    -    |       -        |   加密密码   |
|  register_time  |  timestamp  | YES  |  -   |    -    |       -        |   注册时间   |
| last_login_time |  timestamp  | YES  |  -   |    -    |       -        | 最近登录时间 |
|    max_score    |     int     | YES  |  -   |    -    |       -        |   最高得分   |



==record==表 

---

|  Field   |    Type     | Null | Key  | Default |     Extra      |  Remark  |
| :------: | :---------: | :--: | :--: | :-----: | :------------: | :------: |
|    id    |     int     |  NO  | PRI  |    -    | auto_increment |   主键   |
| username | varchar(32) | YES  |  -   |    -    |       -        |  用户名  |
|   time   |  timestamp  | YES  |  -   |    -    |       -        | 记录时间 |
|  score   |     int     | YES  |  -   |    -    |       -        |   得分   |

## 登陆注册页面



页面整体节能环保，实现 ==一框两用==。



### 请求处理

项目为==服务端客户端==混合，客户端直接调用相关函数向服务端请求数据。

由==Controller==直接接收请求 -> 转交==Service==实现类数据处理 -> ==MyBatisPlus==实现==DAO==完成数据库交互 -> ==Controller==返回结果。

##	游戏界面

游戏界面同时渲染==游戏内界面==和==菜单界面==，隐藏其中一个实现两个界面快速切换的效果。

### 菜单界面

* 使用固定坐标布局

### 游戏内界面

> 施工中。。。