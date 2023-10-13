echo "Starting Jetty server in Debug mode"
java -version
cd /JettyAndJerseyJdk20Example/target/classes
java -classpath .;../lib/* -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000 com/yarm/jettyandjerseyjdk20example/MainApp
echo "Jetty server stopped"
