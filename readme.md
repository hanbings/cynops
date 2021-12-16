# 📦 Cynops Toolbox



![Github License](https://img.shields.io/github/license/hanbings/cynops?style=for-the-badge) ![GitHub Workflow Status](https://img.shields.io/github/workflow/status/hanbings/cynops/Cynops%20Build%20Github%20Actions?style=for-the-badge) ![Jenkins](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.hanbings.io%2Fjob%2FCynops%2F&label=build&style=for-the-badge) ![Codacy grade](https://img.shields.io/codacy/grade/acf95e2e5fb54a748606e8db08b169f7?style=for-the-badge) ![Version](https://img.shields.io/badge/version-java11-orange?style=for-the-badge) ![GitHub Last Commit](https://img.shields.io/github/last-commit/hanbings/cynops?style=for-the-badge) [![Blog](https://img.shields.io/badge/website-cynops-lightgrey.svg?style=for-the-badge)](https://cynops.tech) [![Blog](https://img.shields.io/badge/blog-@hanbings-blue.svg?style=for-the-badge)](https://blog.hanbings.io)



### 🍔 自我介绍：

你好！很高兴遇见你！我的名字叫蝾螈！<br />
我是为了减少搜索频率，增加键盘寿命而生的！<br />
你也可以把我视作一个把各种要频繁搜索的内容放到一起而已，并不是什么认真设计的项目



### 👷 构建状态：

| 构建 / Build                                                 | 状态 / Status                                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Github Actions](https://github.com/Hanbings/Cynops/actions) | ![GitHub Workflow Status](https://img.shields.io/github/workflow/status/hanbings/cynops/Cynops%20Build%20Github%20Actions?style=flat-square) |
| [CI Server](https://ci.hanbings.io/blue/organizations/jenkins/Cynops/activity) | ![Jenkins](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.hanbings.io%2Fjob%2FCynops%2F&label=build&style=flat-square) |
| [Code Check](https://www.codacy.com/)                        | ![Codacy grade](https://img.shields.io/codacy/grade/acf95e2e5fb54a748606e8db08b169f7?style=flat-square) |



### ✨ 功能：

| 状态 / Status | 项目 / Project  | 功能 / Function                                              |
| ------------- | --------------- | ------------------------------------------------------------ |
| ⚓️             | cynops-database | 数据库封装                                                   |
| 🍻             | cynops-event    | Event Bus 实现                                               |
| ⚓️             | cynops-extra    | 邮件工具 二维码工具 OAuth 工具 2FA 工具 一些稀奇古怪的第三方API |
| ⚓️             | cynops-io       | IO 工具                                                      |
| 🚧             | cynops-lang     | 基本类型工具以及扩展基本类型                                 |
| 🕸             | cynops-script   | 脚本引擎                                                     |
| 🚧             | cynops-security | 安全工具 目前是摘要、对称/非对称加密算法的封装               |
| 🕸             | cynops-server   | 计划包括 Http Websocket TCP UDP KCP 等服务端封装             |
| ⚓️             | cynops-task     | 线程池 进程池 定时任务 守护进程                              |
| 🕸             | cynops-tool     | 送一个下崽器（错乱）                                         |

🚧 表示该模块正在编写  <br />

🍻 表示该模块已经完成了某一轮开发期 正在修复 bug 优化 提高性能与等待发布<br />

⚓️表示该模块的优先级较低 但完成当前活跃模块后进行编写<br />

🕸 表示该模块在短期内将不会进行开发



### 🎯 TODO：

查看 TODO 列表：[TODO](https://github.com/Hanbings/Cynops/projects/2)



### 📝 文档：

EventBus 解析：[Java实现一个简单的EventBus](https://blog.hanbings.io/2021/08/27/Java%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA%E7%AE%80%E5%8D%95%E7%9A%84EventBus/)



### 🍺 使用：

目前，Cynops 发布在私有仓库<br />

https://repository.hanbings.com/<br />

前往仓库查看需要的版本并引用它即可



### 🎉 贡献：

**欢迎任何人对项目进行贡献！** ~~即使你认为你的水平很低~~ 呜呜呜 没有人会比我还菜了吧

1. 如果你觉得任何一处的代码**烂得不行** 请直接发起 issues 或 pr 即可 项目出现的意义就是学习更新的技术和更好的编写方式 ~~而且寒冰的脾气好得很~~
2. 尽可能的多一些**注释** 代码风格贴近 阿里 或 Google
3. 为了 Github 平台观感更好一些 项目使用大量 emoji 对 commit readme 等进行修饰 请按照下面的表格来修饰 commit 记录



### 🎨 命名约定：

**为了类名 方法名更能被按照正常思维的理解 项目遵循一定的命名约定**

1. 类名大写驼峰 方法名小写驼峰 **不使用匈牙利命名法** 即类型缩写
2. 包名全小写 包名类名方法名有需要时**使用复数**
3. **优先使用方法重载**
4. 同功能不同参数的方法 **优先使用方法重载** 如果确实无法进行重载 则在功能后使用 with 连接词 **功能 + With + 参数类型**
5. 同功能不同参数也不同返回值 使用 **功能 + To + 返回值类型** 如 base64ToBytes() 需与上一条规则连用 如 base64WithMimeToBytes() 有时候可能不需要 To 作为连接词能让名字读起来更自然一些 那么就不需要 To 如 byte[] randomDesKeyBytes() 当然 如果无法分辨 优先使用 **功能 + To + 返回值类型** 格式

