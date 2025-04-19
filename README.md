## Java贪吃蛇
使用Java编写的贪吃蛇小程序  
该项目主要使用了以下技术：

### Java基础语法​​
面向对象编程（类、继承、多态等）  
集合框架（如ArrayList/LinkedList管理蛇身坐标）  
随机数生成（Random类生成食物位置）     

### Swing GUI框架​​
JFrame作为游戏主窗口  
JPanel实现游戏画布绘制  
KeyListener处理键盘方向键输入  
java.awt.Graphics进行图形绘制（蛇、食物、背景等）    

### ​​​​游戏核心机制​​
javax.swing.Timer控制游戏循环（移动速度和帧率   
碰撞检测（蛇头与边界/蛇身的坐标比对）  
贪吃蛇生长逻辑（链表结构管理身体坐标）     

### 开发工具
IntelliJ IDEA项目结构（.iml文件），已发行idea项目文件  
未使用Maven/Gradle（无构建配置文件   
基础Java SE API（无需第三方库）     

### 设计模式
MVC架构（可能将数据模型与界面分离）  
状态管理（游戏开始/暂停/结束状态切换   
