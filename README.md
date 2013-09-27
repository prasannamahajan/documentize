This is project which generate legal documents.
1. User choose document to generate
2. System will ask series of question to user
3. Depending on answer system will generate pdf document

Technology Used:
1. Java
2. JBoss Application server
3. HTML
4. CSS
5. ext-js (GUI)
6. JPA Hibernate (ORM framework)
7. BootStrap CSS framework
8. iText library for PDF generation
9. DOM parser for parsing xml 
10. Log4j for logging mechanism

Copy these jar files in WEB-INF\lib folder 
Add them to Build path of project 
--------------------------------------------------------------------------
antlr-2.7.6.jar                        jta-1.1.jar
commons-collections-3.1.jar            log4j-1.2.17.jar
dom4j-1.6.1.jar                        mail.jar
hibernate-jpa-2.0-api-1.0.0.Final.jar  mysql-connector-java-5.1.25-bin.jar
hibernate3.jar                        
java-json.jar                          slf4j-api-1.7.1.jar
javassist-3.12.0.GA.jar                slf4j-log4j12-1.7.1.jar
---------------------------------------------------------------------------
To use mysql as database
[jboss-as-7.1.1.Final\modules\com\mysql\jdbc\main] must contain following files

1.mysql-connector-java-5.1.25-bin.jar
2.module.xml

Contents of module.xml

<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.0" name="com.mysql.jdbc">
  <resources>
    <resource-root path="mysql-connector-java-5.1.25-bin.jar"/>
        <!-- Insert resources here -->
  </resources>
  <dependencies>
    <module name="javax.api"/>
  </dependencies>
</module>


Setup :
Following things should be added to setup project using Jboss Application server
These changes are made in standalone.xml which is at JBOSS installation directory 
at Path
[jboss-as-7.1.1.Final\standalone\configuration\standalone.xml]

1. Add following things in datasources

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
                
 2. Add following thing in <drivers> in standalone.xml
 
 					<driver name="mysql" module="com.mysql.jdbc">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                    </driver>
                    
 3. To create mail session 
  Add following thing under 
  <subsystem xmlns="urn:jboss:domain:mail:1.0">

 <mail-session jndi-name="java:/gmail_system">
                <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp-gmail">
                    <login name="YOUR GMAIL EMAIL ID" password="PASSWORD"/>
                </smtp-server>
 </mail-session>
 
 4. Add this under <socket-binding-group>
 
  <outbound-socket-binding name="mail-smtp-gmail">
            <remote-destination host="smtp.gmail.com" port="465"/>
  </outbound-socket-binding>