web: java $JAVA_OPTS -Xmx512m -jar target/*.jar --spring.profiles.active=prod,heroku --server.port=$PORT
release: cp -R src/main/resources/config config && ./mvnw -ntp liquibase:update -Pprod,heroku
