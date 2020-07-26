本项目为对编译完成后打包的java类文件加密，防止包被反编译破解查看
目前支持jar包

##### 使用方法：
添加klock-maven-plugin插件，并配置密钥
```
            <plugin>
                 <groupId>org.mmfmilku.klock</groupId>
                 <artifactId>klock-maven-plugin</artifactId>
                 <version>1.0-SNAPSHOT</version>
                 <configuration>
                     <encryptDirs>
                         <!-- 需要加密的类 -->
                         <encryptDir>MainTest</encryptDir>
                         <encryptDir>ccc</encryptDir>
                         <encryptDir>ddd</encryptDir>
                     </encryptDirs>
                     <!-- 是否加密所有类 -->
                     <fullEncrypt>true</fullEncrypt>
                     <!-- 加密后的包名 -->
                     <encryptName>test-with-encrypt</encryptName>
                     <!-- 配置密钥 -->
                     <key>111222333</key>
                 </configuration>
                 <executions>
                     <execution>
                         <id>e1</id>
                         <phase>package</phase>
                         <goals>
                             <goal>kencrypt</goal>
                         </goals>
                     </execution>
                 </executions>
             </plugin>`
```
执行maven后将出现加密后的包

使用以下命令执行程序
```
java -"Dklock.key"="123456abc" 
-javaagent:.\klock-agent-1.0-SNAPSHOT.jar 
-classpath .\lib\*
-jar .\test-with-encrypt.jar 
```
-"Dklock.key"="123456abc"表示解密密钥为123456abc

-javaagent:.\klock-agent-1.0-SNAPSHOT.jar


---
### 各工程介绍
###### klock-maven-plugin: 
- 支持类文件全加密或根据类名加密
- 目前使用DES对称加密，后续添加其他加密算法
- 文件加密后的内容计划为 魔数+hash+密文,目前为全密文

###### klock-agent:
为加载的字节码数据解密

###### klock-test: 
插件测试工程
