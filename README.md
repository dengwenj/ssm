### SSM

### Spring
* Spring 发展到今天已经成了一种开发的生态圈，Spring 提供了若干个项目，每个项目用于完成特定的功能

### 核心概念
* IOC（Inversion of Control）控制反转
* 使用对象时，由主动 new 产生对象转换为由外部提供对象，此过程中对象创建控制权由程序转移到外部，此思想称为控制反转
* Spring 技术对 IOC 思想进行了实现：
* 1、Spring 提供了一个容器，称为IOC容器，用来充当 IOC 思想中的“外部”
* 2、IOC 容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在 IOC 容器中统称为 Bean
* DI（Dependency Injection）依赖注入：
* 1、在容器中建立 bean 与 bean 之间的依赖关系的整个过程，称为依赖注入

### 目标（充分解耦）
* 使用 IOC 容器管理 bean（IOC）
* 在 IOC 容器内将有依赖关系的 bean 进行关系绑定（DI）
* 最终效果：使用对象时不仅可以直接从 IOC 容器中获取，并且获取到的 bean 已经绑定了所有的依赖关系

### bean 基础配置
* 功能：定义 Spring 核心容器管理的对象
* 属性列表：
* 1、id：bean 的 id，使用容器可以通过 id 值获取对应的 bean，在一个容器中 id 值唯一
* 2、class：bean 的类型，即配置的 bean 的全路径类名
* <bean id="bookDao" class="vip.dengwj.dao.impl.BookDaoImpl" />

### bean 别名配置
* name 属性：定义 bean 的别名，可定义多个，使用逗号 分号 空格分隔均可
* <bean id="bookService" name="bookService2 bookService3" class="vip.dengwj.service.impl.BookServiceImpl" />

### bean 作用范围配置
* scope 属性：定义 bean 的作用范围，singleton 单列，prototype 非单列
* <bean id="bookService" name="bookService2 bookService3" class="vip.dengwj.service.impl.BookServiceImpl" scope="prototype" />