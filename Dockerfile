FROM openjdk:17

EXPOSE 8080
ENV TZ=Asia/Seoul

COPY ./ida-http-application/build/libs/ida-http-application-1.0-SNAPSHOT.jar app.jar


ENTRYPOINT ["java","-jar","/app.jar"]