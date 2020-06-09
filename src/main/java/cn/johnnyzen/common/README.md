# Common Component (Package) List
> 公共组件(包)、工具组件(包)清单

# 1 开源组件
> 开源社区的公共包

# 2 企业组件
> sefonsoft.com

# 3 个人组件
> johnnyzen.cn
### 第三方依赖
+ com.fasterxml.jackson.databind
    + JsonFilterUtil
``` xml
<!-- JSON处理工具包 -->
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.9.0</version>
</dependency>
```

+ org.slf4j:slf4j-api + org.slf4j:slf4j-log4j12
    + BusinessPropertyCheckUtil
    + CosFilter
    + SpringUtil
``` xml
    <!-- Slf<Simple Logging Facade For Java> + Log4J -->
    <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.25</version>
    </dependency>
    <!-- 实现方案1：Log4J -->
    <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
    <!-- slf4j与log4j的整合jar包 : 其将自动引入其log4j-1.2.17.jar -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.25</version>
    </dependency>
```
+ org.apache.http.client
    + HttpClientUtil
```xml
    <!-- HttpClient - https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
    <dependency>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpclient</artifactId>
      <version>4.5.2</version>
    </dependency>
```
+ redis.clients:jedis
    + JedisUtil
```xml
    <!-- Redis:Jedis (Redis database for Java) -->
    <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>3.0.1</version>
    </dependency>
```

+ org.springframework.<beans/context/stereotype.Component>
    + SpringUtil

+ org.openqa.selenium
    + RequestUtil
       
```xml
<!-- selenium -->
<!-- org.openqa.selenium.chrome.ChromeDriver/WebDriver -->
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.0.1</version>
</dependency>
```