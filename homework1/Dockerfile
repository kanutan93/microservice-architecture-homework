FROM maven:3.8.3-openjdk-11 as build
COPY /src/ src/
COPY pom.xml .
RUN mvn clean install

FROM openjdk:11-jre AS run
COPY --from=build target/homework1-*.jar homework1.jar
CMD java -jar homework1.jar
EXPOSE 8000