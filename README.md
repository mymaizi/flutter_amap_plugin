myamap 是基于高德地图封装的一个flutter插件（本人非andorid，ios专业开发人员，可能在某些地方还是处于从0开始去接触，难免措辞会不恰当）

该demo实现了Flutter的基础布局，其中包括（通讯录，列表，搜索，登录等)
[在线案例](https://github.com/mymaizi/flutter_hello_world)

### 可能会遇到的问题，以下为本人亲测问题略显尴尬

* 包引用（建议跟着高德地图官网一步一步的去做）
* 关于引入so文件后出错，建议看错误提示，一般会提示couldn't find "libflutter.so"文件，建议看下这条错误的具体信息，删除对应的so文件即可
* 切记这是重点在高德api文档中明确指示（需要onCreate重写）那么想办法把对应的参数传递过来即可
* 高德key错误导致无法鉴权，提示errorCode=7,errorMessage=key错误
    * 1、确保当前项目下的包名和高德配置的一致
    * 2、建议使用高德提供的sHa1方法在MainActivity下调用一次，看是否和高德配置的一致
    * 3、关于高德的配置文件一定是放在插件对应的AndroidManifest中
    * 4、manifestPlaceholders 配置，提供动态配置
