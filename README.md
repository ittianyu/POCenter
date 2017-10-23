## 外包集中营 ##

整合多个软件外包平台项目信息，替你筛选优质项目

![MIT License](https://img.shields.io/github/license/mashape/apistatus.svg) ![api 15+](https://img.shields.io/badge/API-14%2B-green.svg)

## 关于我们 ##
[![天宇工作室](https://github.com/ittianyu/MobileGuard/blob/master/read_me_images/logo-transparent.png?raw=true)](http://www.ittianyu.com)

## 功能 ##
- ### 首页 ###
展示你关注的外包信息，比如： 移动app、网站开发、微信/小程序。

- ### 发现 ###
展示所有的外包信息。

- ### 搜索 ###
展示标题或描述中含有指定关键词的外包信息。


## 截图 ##

![欢迎界面](/screenshots/splash.jpg) ![主界面](/screenshots/home.jpg)

![主界面滑动](/screenshots/home_scroll.jpg) ![发现界面](/screenshots/find.jpg)

![搜索界面](/screenshots/search.jpg) ![我的界面](/screenshots/mime.jpg)

![没有数据界面](/screenshots/empty.jpg) ![错误界面](/screenshots/error.jpg)


## 下载 ##

已在 [百度手机助手](http://shouji.baidu.com/software/10867391.html)、[91市场](http://apk.91.com/Soft/Android/com.ittianyu.pocenter-1.html)、[安卓市场](http://apk.hiapk.com/appinfo/com.ittianyu.pocenter/1) 上线。

贴出二维码方便下载

![](http://d.hiphotos.bdimg.com/wisegame/pic/item/72dfa9ec8a136327b9c91278988fa0ec08fac752.jpg)

## 技术点 ##

- 框架：MVP
- 网络访问：Retrofit2 + OkHttp3
- 网络缓存：RxCache
- 数据及解析：Gson 解析 json
- 异步框架：RxJava2
- 设计规范：Material Design
- 界面布局：BottomNavigationView + ViewPager + Fragment
- 第三方接入：ShareSdk、友盟 app 统计、TinkerPatch 热更新

## 亮点 ##

- ViewPager 懒加载：在 Activity 生命周期内仅加载一次，且第一次可见时加载。
- LCEE 界面逻辑：也就是加载、内容、错误、空视图。

## 项目包结构 ##

- com.ittianyu.pocenter
	- common &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;公用代码
		- api &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;网络访问代码
		- base &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 基类
		- bean &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 实体类
		- utils&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;工具类
	- features&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;功能
		- detail&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;详情
		- find &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 发现
		- home&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;主页
		- mime&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;我的
		- search &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 搜索
		- type &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;标签管理
		- version&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 版本更新
	- MainActivity&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 主界面
	- SplashActivity&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;欢迎界面


## 踩坑经历 ##

- 混淆：如果项目中有用到 Gson 解析数据，不要混淆实体类。最好养成用一个库，就加上一个库的混淆代码的习惯。


## 致谢 ##

感谢工作室的 UI大大 给与了关于配色方面的建议。


## 授权 ##

	MIT License
	
	Copyright (c) 2017 ittianyu
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
