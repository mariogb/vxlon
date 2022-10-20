FROM adoptopenjdk/openjdk15:ubi
COPY build/libs/lonvx-1.0.0-SNAPSHOT-fat.jar vx.jar
COPY config-lon-docker.json config-lon.json
COPY wait-for-postgres.sh wait-for-postgres.sh
RUN chmod +x wait-for-postgres.sh
COPY ssl/ ssl/
COPY certificate.p12 certificate.p12
EXPOSE 8333
#CMD  ["./wait-for-postgres.sh", "db_postgres", "java" ,"-jar", "vx.jar"]
CMD ["java", "-jar", "vx.jar"]