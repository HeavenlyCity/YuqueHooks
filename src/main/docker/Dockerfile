FROM adoptopenjdk/openjdk11:latest
COPY *.jar /yuque-hooks.jar
CMD ["--server.port=40406"]
EXPOSE 40406
ENTRYPOINT ["java","-jar","yuque-hooks.jar"]
