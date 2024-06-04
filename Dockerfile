FROM gradle:8.6 as BUILD
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY src /app/src
RUN gradle war

FROM tomcat:10.1.18-jdk17
ARG WAR_NAME=weather-viewer-1.0.war
COPY --from=BUILD /app/build/libs/${WAR_NAME} /usr/local/tomcat/webapps/