myamap 是基于高德地图封装的一个flutter插件

该demo实现了Flutter的基础布局，其中包括（通讯录，列表，搜索，登录等)
[在线案例](https://github.com/mymaizi/flutter_hello_world)

### 可能会遇到的问题

* 包引用（建议跟着高德地图官网一步一步的去做）
* 关于引入so文件后出错，建议看错误提示，一般会提示couldn't find "libflutter.so"文件，建议看下这条错误的具体信息，删除对应的so文件即可
* 切记这是重点在高德api文档中明确指示（需要onCreate重写）那么想办法把对应的参数传递过来即可
