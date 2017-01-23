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
		- find&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;发现
		- home&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;主页
		- mime&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;我的
		- search &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 搜索
		- type &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;标签管理
		- version&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 版本更新
	- MainActivity&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 主界面
	- SplashActivity&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;欢迎界面


## 踩坑经历 ##

- 混淆：如果项目中有用到 Gson 解析数据，不要混淆实体类。最好养成用一个库，就加上一个库的混淆代码的习惯。

## 开发体会 ##

整个项目包括 服务器端、爬虫、app，但这里只开源了 app，主要是对其他端水平不自信，不想拿出来献丑。

难度不大，但一个人做难免觉得有点累(虽然我已经很久不敢说累了)。
虽说工作室有后端的，但不能让人家白干活。

本来还打算顺便做个网页版的，但愧于工作还没着落，所以决定先放一放。

## 致谢 ##

感谢工作室的 UI大大 给与了关于配色方面的建议。

## 求职 ##

最后请原谅我打一个求职广告。

年后打算去深圳找份安卓开发的工作（预计可以从3月工作到8月底），如果哪位大佬看得上鄙人的 [简历](http://ittianyu.deercv.com/)（没写学校是怕被抓到开除了），请把公司名、地址、职位、薪资、面试时间发到我邮箱 86839868@qq.com （最好支持远程面试）。

解释一下，本人大三，下学期没课(我没选课)，但没实习政策，学校规定擅自离校会被开除。
各位大佬也别先骂我心急，因为学校没有明文实习政策，问过好多老师也说大四下学期完全没课了才能出去实习(毕设都要搞一个多月，实习个...)，所以这是我毕业前最后一次机会，也请各位大佬给我一个机会。

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
