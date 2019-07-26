项目基于SpringBoot1.5
JDK1.8
项目依赖存储资源:redis/mysql(application*.properties)
启动方式:
IDE运行:
    IDE直接运行XserverApplication,配置文件加载application.properties(或自行IDE配置)
打包运行:
 a.指定application-dev.properties运行
    java -jar xserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
 b.指定application-prod.properties运行
    java -jar xserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
 c.指定application-schedulerprod.properties运行
     java -jar xserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=schedulerprod
 d.指定application-local.properties运行
      java -jar xserver-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
 e.指定本地配置文件运行
    java -jar -Dspring.config.location=${path}/application.properties xserver-0.0.1-SNAPSHOT.jar.jar


待续:
消息文案
