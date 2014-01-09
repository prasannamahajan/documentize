Documenttize
=================

This is project which generate legal documents.
<br>1.User choose document to generate
<br>2. System will ask series of question to user
<br>3. Depending on answer system will generate pdf document

Technology Used:
---------------------------

* Java
* JBoss Application server
* HTML
* CSS
* ext-js (GUI)
* JPA Hibernate (ORM framework)
* BootStrap CSS framework
* iText library for PDF generation
* DOM parser for parsing xml 
* Log4j for logging mechanism


Configuration
---------------------------------------------------------------------------
To use mysql as database
[jboss-as-7.1.1.Final\modules\com\mysql\jdbc\main] 
<br>must contain following files

* mysql-connector-java-5.1.25-bin.jar
* module.xml

Contents of module.xml


```<? xml version="1.0" encoding="UTF-8"?>```
```
<module xmlns="urn:jboss:module:1.0" name="com.mysql.jdbc">
 <resources>
  <resource-root path="mysql-connector-java-5.1.25-bin.jar"/>
        <!-- Insert resources here -->
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>
```

Setup :

Following things should be added to setup project using Jboss Application server

These changes are made in standalone.xml which is at JBOSS installation directory 
at Path
[jboss-as-7.1.1.Final\standalone\configuration\standalone.xml]

*  Add following things in datasources

```
<datasource jta="true" jndi-name="java:jboss/datasources/MySqlDS3" pool-name="MySqlDS3" enabled="true" use-java-context="true" use-ccm="false">
                    <connection-url>jdbc:mysql://localhost:3306/agreeya</connection-url>
                    <connection-property name="autoReconnect">
                        true
                    </connection-property>
                    <driver>mysql</driver>
                    <pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>100</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>YOUR USERNAME</user-name>
                        <password>YOUR PASSWORD</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <timeout>
                        <set-tx-query-timeout>true</set-tx-query-timeout>
                        <blocking-timeout-millis>5000</blocking-timeout-millis>
                        <idle-timeout-minutes>1</idle-timeout-minutes>
                    </timeout>
                    <statement>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
                </datasource>
``` 
                
 * Add following thing in ``` <drivers>``` in standalone.xml

 					<driver name="mysql" module="com.mysql.jdbc">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                    </driver>
            
 *  Add following thing under ```  <subsystem xmlns="urn:jboss:domain:mail:1.0"> ```
 to create mail session 

```  
 <mail-session jndi-name="java:/gmail_system">
                <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp-gmail">
                    <login name="YOUR GMAIL EMAIL ID" password="PASSWORD"/>
                </smtp-server>
 </mail-session>
```

 * and add this under ```<socket-binding-group>``` to bind port to mail session

 ``
  <outbound-socket-binding name="mail-smtp-gmail">
            <remote-destination host="smtp.gmail.com" port="465"/>
  </outbound-socket-binding>
``