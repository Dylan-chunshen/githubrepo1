参考地址：http://www.jb51.net/article/24219.htm

1、jquery.autocomplete参考地址 
http://bassistance.de/jquery-plugins/jquery-plugin-autocomplete/ 
http://docs.jquery.com/Plugins/Autocomplete 
2、jquery.autocomplete详解 
语法： 
autocomplete(urlor data, [options] ) 
参数： 
url or data：数组或者url 
[options]：可选项，选项解释如下： 
1) minChars (Number) 
在触发autoComplete前用户至少需要输入的字符数，Default:1，如果设为0，在输入框内双击或者删除输入框内内容时显示列表。 
2) width (Number) 
指定下拉框的宽度，Default: input元素的宽度 
3) max (Number) 
autoComplete下拉显示项目的个数，Default: 10 
4) delay (Number) 
击键后激活autoComplete的延迟时间(单位毫秒)，Default: 远程为400 本地10 
5) autoFill (Boolean) 
要不要在用户选择时自动将用户当前鼠标所在的值填入到input框，Default: false 
6) mustMatch (Booolean) 
如果设置为true，autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框，Default:false 
7) matchContains (Boolean) 
决定比较时是否要在字符串内部查看匹配，如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用.Default: false 
8) selectFirst (Boolean) 
如果设置成true,在用户键入tab或return键时autoComplete下拉列表的第一个值将被自动选择,尽管它没被手工选中(用键盘或鼠标).当然如果用户选中某个项目,那么就用用户选中的值. Default: true 
9) cacheLength (Number) 
缓存的长度.即对从数据库中取到的结果集要缓存多少条记录.设成1为不缓存.Default: 10 
10) matchSubset (Boolean) 
autoComplete可不可以使用对服务器查询的缓存,如果缓存对foo的查询结果,那么如果用户输入foo就不需要再进行检索了,直接使用缓存.通常是打开这个选项以减轻服务器的负担以提高性能.只会在缓存长度大于1时有效.Default: true 
11) matchCase (Boolean) 
比较是否开启大小写敏感开关.使用缓存时比较重要.如果你理解上一个选项,这个也就不难理解,就好比foot要不要到FOO的缓存中去找.Default: false 
12) multiple (Boolean) 
是否允许输入多个值即多次使用autoComplete以输入多个值. Default:false 
13) multipleSeparator (String) 
如果是多选时,用来分开各个选择的字符. Default:"," 
14) scroll (Boolean) 
当结果集大于默认高度时是否使用卷轴显示 Default: true 
15) scrollHeight (Number) 
自动完成提示的卷轴高度用像素大小表示 Default: 180 
16) formatItem (Function) 
为每个要显示的项目使用高级标签.即对结果中的每一行都会调用这个函数,返回值将用LI元素包含显示在下拉列表中.Autocompleter会提供三个参数(row, i, max): 返回的结果数组, 当前处理的行数(即第几个项目,是从1开始的自然数), 当前结果数组元素的个数即项目的个数.Default: none, 表示不指定自定义的处理函数,这样下拉列表中的每一行只包含一个值. 
17) formatResult (Function) 
和formatItem类似,但可以将将要输入到input文本框内的值进行格式化.同样有三个参数,和formatItem一样.Default: none,表示要么是只有数据,要么是使用formatItem提供的值. 
18) formatMatch (Function) 
对每一行数据使用此函数格式化需要查询的数据格式. 返回值是给内部搜索算法使用的. 参数值row 
19) extraParams (Object) 
为后台(一般是服务端的脚本)提供更多的参数.和通常的作法一样是使用一个键值对对象.如果传过去的值是{ bar:4 },将会被autocompleter解析成my_autocomplete_backend.php?q=foo&bar=4 (假设当前用户输入了foo). Default: {} 
20) result (handler) Returns:jQuery 
此事件会在用户选中某一项后触发，参数为： 
event: 事件对象. event.type为result. 
data: 选中的数据行. 
formatted:formatResult函数返回的值 
例如： 
$("#singleBirdRemote").result(function(event, data, formatted){ 
//如选择后给其他控件赋值，触发别的事件等等 
}); 