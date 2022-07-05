FROM adoptopenjdk/openjdk11:latest
COPY *.jar /yuque-hooks.jar
CMD ["--server.port=40406"]
EXPOSE 40406
ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:40407","yuque-hooks.jar"]

