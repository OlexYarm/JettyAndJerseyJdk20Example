echo "Starting Jetty server"
java -version
java -jar JettyJersey.jar STOP.PORT=28282 STOP.KEY=secret
echo "Jetty server stopped"
