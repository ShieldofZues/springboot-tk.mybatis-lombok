# springboot-tk.mybatis-lombok-myBatisGeneratorPlugins
1、基于通用mapper插件，快速根据数据库表生成项目所需ENTITY(MODEL)层、MAPPER(DAO)层、*MAPPER.XML等

2、核心文件的MyBatis-generator.xml及的pom.xml中依赖的配置

3、目录结构供参考

使用方法:
1.MAVEN项目的pom.xml中引入相关插件及依赖
           <!----maven编译插件--->
           <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <!----mybatis-generator插件--->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
                <configuration>
                    <!----指向项目目录下的mybatis-generator.xml生成配置--->
                    <configurationFile>
                        ${basedir}/src/main/resources/mybatis-generator.xml
                    </configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
                <dependencies>
                    <!---执行mybatis-generator插件时,需要提供的数据库驱动程序依赖--->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.29</version>
                    </dependency>
                    <!---通用mappery依赖--->
                    <dependency>
                        <groupId>tk.mybatis</groupId>
                        <artifactId>mapper</artifactId>
                        <version>4.0.0</version>
                    </dependency>
                    <!---lombok相关依赖--->
                    <dependency>
                        <groupId>com.softwareloop</groupId>
                        <artifactId>mybatis-generator-lombok-plugin</artifactId>
                        <version>1.0</version>
                    </dependency>
                    <dependency>
                        <groupId>com.github.misterchangray.mybatis.generator.plugins</groupId>
                        <artifactId>myBatisGeneratorPlugins</artifactId>
                        <version>1.2</version>
                    </dependency>
                </dependencies>
            </plugin>

2.MyBatis-generator.xml中主要引入插件如下：
        <plugin type="com.softwareloop.mybatis.generator.plugins.LombokPlugin">
            <!-- enable annotations -->
            <property name="builder" value="true"/>
            <!-- annotation's option(boolean) -->
            <property name="builder.fluent" value="true"/>
            <!-- annotation's option(String) -->
            <property name="builder.builderMethodName" value="myBuilder"/>
            <property name="accessors" value="true"/>
            <!-- annotation's option(array of String) -->
            <property name="accessors.prefix" value="m_, _"/>
            <!-- disable annotations -->
            <property name="allArgsConstructor" value="false"/>
        </plugin>
        <!-- 自动为entity生成swagger2文档-->
        <plugin type="mybatis.generator.plugins.GeneratorSwagger2Doc">
            <property name="apiModelAnnotationPackage" value="io.swagger.annotations.ApiModel"/>
            <property name="apiModelPropertyAnnotationPackage" value="io.swagger.annotations.ApiModelProperty"/>
        </plugin>
        <plugin type="tk.mybatis.mapper.generator.MapperPlugin">
            <!---如果有自定义BaseMapper,此处的 BaseMapper注意一定不能被扫描到,此处也可以直接引用通用mapper--->
            <property name="mappers" value="com.example.demo.common.BaseMapper"/>
            <property name="caseSensitive" value="true"/>
            <property name="suppressTypeWarnings" value="true"/>
            <property name="forceAnnotation" value="true"/>
            <property name="generateColumnConsts" value="true"/>
        </plugin>
【说明】：网上有很多说法，可直接添加property来做lombok注解的生成，但我尝试过，不太好用，不知是否是版本的原因还是其他原因----所以才引入的LombokPlugin插件来进行注解的自动生成
            <!--配置是否启用lombok, 支持如下6种注解-->
            <!--当配置 Data 后，Getter Setter ToString EqualsAndHashCode 会被忽略-->
            <property name="lombok" value="Getter,Setter,Data,ToString,Accessors,EqualsAndHashCode"/>
            
【特别注意】：关于MyBatis-generator.xml中涉及的插件或属性等在demo中均有具体注释说明，可按自己需求进行引用。

