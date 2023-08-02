The project is example of Restful web application with use of Eclipse Jersey JAX-RS implementation and Eclipse Jetty server library.

It was developed and tested with Eclipse Jersey 3.1.3 (https://projects.eclipse.org/projects/ee4j.jersey/releases/3.1.3),
Eclipse Jetty 11.0.15 server library (https://download.eclipse.org/oomph/jetty/release/10.0.15/index.html, https://github.com/eclipse/jetty.project/releases/tag/jetty-11.0.15)
and Oracle JDK 20 on Windows 11.
The logback library 1.4.8 and SLF4J 2.0.7 was added and configured for application logging.

1) Prerequisites

The following software should be installed and configured on Windows 11 machine.
1.1) Oracle Java SE Development Kit 20.0.1 (https://www.oracle.com/java/technologies/downloads/#jdk20-windows)
1.2) Apache Maven 3.9.3 (https://maven.apache.org/download.cgi)

2) Building

2.1) Clone Git repository from GitHub.
2.2) Start Windows command-line shell.
2.3) Change directory to cloned repository (in my case it was F:\ProjectsJava\JettyAndJerseyJdk20Example).
2.4) Run maven command "mvn clean install".

The Apache Maven should create following in directory "target":
- JettyJersey.jar file with example application;
- "lib" directory with all dependencies required for running example application (JettyJersey.jar file);
- two Windows bat files for starting and stopping example application (JettyStart.bat and JettyStop.bat).
- more directories with building artifacts (classes, generated-sources, etc.)

Truncated console log is shown below.

F:\ProjectsJava\JettyAndJerseyJdk20Example>mvn clean package
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< com.yarm:JettyAndJerseyJdk20Example >-----------------
[INFO] Building JettyAndJerseyJdk20Example 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ JettyAndJerseyJdk20Example ---
[INFO] Deleting F:\ProjectsJava\JettyAndJerseyJdk20Example\target
[INFO]
[INFO] --- resources:3.3.1:copy-resources (copy-script-files) @ JettyAndJerseyJdk20Example ---
[INFO] Copying 2 resources from src\scripts to target
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ JettyAndJerseyJdk20Example ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ JettyAndJerseyJdk20Example ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 4 source files with javac [debug deprecation target 20] to target\classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ JettyAndJerseyJdk20Example ---
[INFO] skip non existing resourceDirectory F:\ProjectsJava\JettyAndJerseyJdk20Example\src\test\resources
[INFO]
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ JettyAndJerseyJdk20Example ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO]
[INFO] --- surefire:3.1.2:test (default-test) @ JettyAndJerseyJdk20Example ---
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ JettyAndJerseyJdk20Example ---
[INFO] Building jar: F:\ProjectsJava\JettyAndJerseyJdk20Example\target\JettyJersey.jar
[INFO]
[INFO] --- dependency:3.6.0:copy-dependencies (copy-dependencies) @ JettyAndJerseyJdk20Example ---
[INFO] Copying jetty-server-11.0.15.jar to F:\ProjectsJava\JettyAndJerseyJdk20Example\target\lib\jetty-server-11.0.15.jar
...
[INFO] Copying logback-classic-1.4.8.jar to F:\ProjectsJava\JettyAndJerseyJdk20Example\target\lib\logback-classic-1.4.8.jar
[INFO] Copying logback-core-1.4.8.jar to F:\ProjectsJava\JettyAndJerseyJdk20Example\target\lib\logback-core-1.4.8.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.123 s
[INFO] Finished at: 2023-08-01T17:35:12-04:00
[INFO] ------------------------------------------------------------------------

F:\ProjectsJava\JettyAndJerseyJdk20Example>

3) Run example application

3.1) Change directory to "target".
3.2) Run bat file "JettyStart.bat".

Truncated console log is shown below.

F:\ProjectsJava\JettyAndJerseyJdk20Example\target>JettyStart.bat

F:\ProjectsJava\JettyAndJerseyJdk20Example\target>echo "Starting Jetty server"
"Starting Jetty server"

F:\ProjectsJava\JettyAndJerseyJdk20Example\target>java -version
java version "20.0.1" 2023-04-18
Java(TM) SE Runtime Environment (build 20.0.1+9-29)
Java HotSpot(TM) 64-Bit Server VM (build 20.0.1+9-29, mixed mode, sharing)

F:\ProjectsJava\JettyAndJerseyJdk20Example\target>java -jar JettyJersey.jar STOP.PORT=28282 STOP.KEY=secret
17:45:02,061 |-INFO in ch.qos.logback.classic.LoggerContext[default] - This is logback-classic version 1.4.8
17:45:02,092 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
17:45:02,092 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [jar:file:/F:/ProjectsJava/JettyAndJerseyJdk20Example/target/JettyJersey.jar!/logback.xml]
17:45:02,092 |-INFO in ch.qos.logback.core.joran.spi.ConfigurationWatchList@cb0ed20 - URL [jar:file:/F:/ProjectsJava/JettyAndJerseyJdk20Example/target/JettyJersey.jar!/logback.xml] is not of type file
...
17:45:02,465 |-INFO in ch.qos.logback.core.rolling.helper.Compressor - Done ZIP compressing [..\logs\archived\JettyAndJerseyExample.2023-07-31.0.log531234237208700.tmp] as [..\logs\archived\JettyAndJe
rseyExample.2023-07-31.0.log.gz]
17:45:02,487 |-INFO in c.q.l.core.rolling.helper.TimeBasedArchiveRemover - Removed  0 Bytes of files
2023-08-01T17:45:02.956-0400 LEVEL=INFO, MSG=Started o.e.j.s.ServletContextHandler@5ff60a8c{/,null,AVAILABLE}, org.eclipse.jetty.server.handler.ContextHandler:doStart
2023-08-01T17:45:02.976-0400 LEVEL=INFO, MSG=Opened F:\ProjectsJava\JettyAndJerseyJdk20Example\logs\2023_08_01.jetty.request.log, org.eclipse.jetty.server.RequestLogWriter:doStart
2023-08-01T17:45:03.075-0400 LEVEL=INFO, MSG=Started ServerConnector@68e965f5{HTTP/1.1, (http/1.1)}{0.0.0.0:8080}, org.eclipse.jetty.server.AbstractConnector:doStart
2023-08-01T17:45:03.085-0400 LEVEL=INFO, MSG=Started Server@3bf9ce3e{STARTING}[11.0.15,sto=0] @2014ms, org.eclipse.jetty.server.Server:doStart
2023-08-01T17:45:03.086-0400 LEVEL=INFO, MSG=#### Started Jetty server., com.yarm.jettyandjerseyjdk20example.MainApp:main

4) Test running example application

Send request from browser to Jetty server and check response.

4.1) Request:
http://localhost:8080/api/v1/users/ping

Response:
ping

4.2) Request:
http://localhost:8080/api/v1/users/all

Response:
{"user":[{"id":1,"firstName":"John","lastName":"Smith"},{"id":2,"firstName":"Jane","lastName":"Doe"}]}

4.3) Request:
http://localhost:8080/api/v1/users/1

Response:
{"id":1,"firstName":"John","lastName":"Smith"}

4.4) Request:
http://localhost:8080/api/v1/users/user2

Response:
{"id":2,"firstName":"Jane","lastName":"Doe"}

4.5) Request:
http://localhost:8080/shutdown?token=secretPassword

Response:
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1"/>
<title>Error 404 Not Found</title>
</head>
<body><h2>HTTP ERROR 404 Not Found</h2>
<table>
<tr><th>URI:</th><td>/shutdown</td></tr>
<tr><th>STATUS:</th><td>404</td></tr>
<tr><th>MESSAGE:</th><td>Not Found</td></tr>
<tr><th>SERVLET:</th><td>org.eclipse.jetty.servlet.ServletHandler$Default404Servlet-7526515b</td></tr>
</table>
<hr/><a href="https://eclipse.org/jetty">Powered by Jetty:// 11.0.15</a><hr/>

</body>
</html>

5) Stop example application

Click Ctrl+C in command-line window and confirm batch job termination as shown below.

^CTerminate batch job (Y/N)? y

F:\ProjectsJava\JettyAndJerseyJdk20Example\target>

6) Notes

6.1) The value for request log file location is hardcoded in "MainApp.java" class similar to provided in Eclipse Jetty documentation
https://eclipse.dev/jetty/documentation/jetty-11/programming-guide/index.html#pg-server-http-request-logging
The directory in the location should match the value for log application file in logback.xml file.
Another case Jetty server may fail to start with confusing error "java.io.IOException: Cannot write log directory ..."

2023-07-29T19:15:33.823-0400 LEVEL=INFO, MSG=#### Starting Jetty server., com.yarm.jettyandjerseyjdk20example.MainApp:main
2023-07-29T19:15:33.833-0400 LEVEL=INFO, MSG=jetty-11.0.15; built: 2023-04-11T18:37:53.775Z; git: 5bc5e562c8d05c5862505aebe5cf83a61bdbcb96; jvm 20.0.1+9-29, org.eclipse.jetty.server.Server:doStart
2023-07-29T19:15:34.317-0400 LEVEL=INFO, MSG=Started o.e.j.s.ServletContextHandler@26d820eb{/,null,AVAILABLE}, org.eclipse.jetty.server.handler.ContextHandler:doStart
2023-07-29T19:15:34.327-0400 LEVEL=INFO, MSG=Stopped o.e.j.s.ServletContextHandler@26d820eb{/,null,STOPPED}, org.eclipse.jetty.server.handler.ContextHandler:doStop
2023-07-29T19:15:34.331-0400 LEVEL=ERROR, MSG=#### Cannot start Jetty server. Exception=java.io.IOException: Cannot write log directory F:\ProjectsJava\JettyAndJerseyJdk20Example\log, com.yarm.jettyadjerseyjdk20example.MainApp:main

6.2) I could stop example application with Ctrl+C as explained above.
The org.eclipse.jetty.server.handler.ShutdownHandler did not work.
Stopping Jetty server from remote as explained in Eclipse Jetty documentation (https://eclipse.dev/jetty/documentation/jetty-11/operations-guide/index.html#og-start-stop-remote) failed.

6.3) The org.eclipse.jetty.server.handler.DefaultHandler 



