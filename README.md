
# Backend for basic crud application


Este es un backend con servicios rest. 

Esta aplicación son los servicios generados para crear, listar, buscar, filtrar, cargar y descargar desde excel 
información



Con vertx, se tiene un a infraestructura lista para cluster, y con la base de datos en memoria
de Hazelcast

Los pojos y servicios se generan desde un script de generación de código.


To create the database:
```
createdb <dbaseName>
```

To populate the database
```
psql  <dbaseName> < initSql.sql
```

To package your application:
```
./gradlew clean assemble
```

To run your application:
```
./gradlew clean run
```

##Running with Docker Composer

Descargar docker compose
bash 
```
sudo curl -L --fail https://github.com/docker/compose/releases/download/1.29.2/run.sh -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```
view https://docs.docker.com/compose/install/


### Creating image
docker build -t vxlon .

### Running 

bash 
```
docker-compose up --scale vxlon=2

```