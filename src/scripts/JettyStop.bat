echo "Stopping Jetty server"
java -version
java -jar JettyAndJerseyJdk20Example-1.0.0.jar STOP.PORT=28282 STOP.KEY=secret --stop
echo "Jetty server stopped"

