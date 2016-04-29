# MultipleDialog
本项目集成了各种各样的对话框  
目前已经有11种对话框，包含了  
AlertDialog  	普通对话框  
ProgressDialog  进度对话框  
DatePickerDialog  日期选择对话框  
TimePickerDialog  时间选择对话框  
DialogFragment：google官方推荐使用DialogFragment创建对话框。
# 页面显示
![](http://i.imgur.com/1VQhDCq.jpg)  
![](http://i.imgur.com/vFrtqVi.jpg)
# 对话框显示
- 普通提示对话框  
![](http://i.imgur.com/RXQ1rby.jpg)
- 确认取消对话框  
![](http://i.imgur.com/bvLne0Y.jpg)
- 列表对话框  
![](http://i.imgur.com/HiJ9utG.jpg)
- 列表单选对话框  
![](http://i.imgur.com/72CV2j7.jpg)
- 列表多选对话框  
![](http://i.imgur.com/3evyxSn.jpg)
- 自定义对话框  
![](http://i.imgur.com/7hXiwil.jpg)
- 进度条对话框  
![](http://i.imgur.com/eeS7Hbr.jpg)
- 日期选择对话框  
![](http://i.imgur.com/wbsxkKq.jpg)
- 时间选择对话框  
![](http://i.imgur.com/yndGt0O.jpg)  
# DialogFragment  
1、 概述
DialogFragment在android 3.0时被引入。是一种特殊的Fragment，用于在Activity的内容之上展示一个模态的对话框。典型的用于：展示警告框，输入框，确认框等等。
在DialogFragment产生之前，我们创建对话框：一般采用AlertDialog和Dialog。注：官方不推荐直接使用Dialog创建对话框。  
2、 好处与用法
使用DialogFragment来管理对话框，当旋转屏幕和按下后退键时可以更好的管理其声明周期，它和Fragment有着基本一致的声明周期。且DialogFragment也允许开发者把Dialog作为内嵌的组件进行重用，类似Fragment（可以在大屏幕和小屏幕显示出不同的效果）。  
使用DialogFragment至少需要实现onCreateView或者onCreateDIalog方法。onCreateView即使用定义的xml布局文件展示Dialog。onCreateDialog即利用AlertDialog或者Dialog创建出Dialog。  
![](http://i.imgur.com/0rAlTHo.jpg)  
![](http://i.imgur.com/5kzvhQp.jpg)  

本文参考了：[http://blog.csdn.net/lmj623565791/article/details/37815413/](http://blog.csdn.net/lmj623565791/article/details/37815413/ "博客")