
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

### Esquema funcionando con FrontEnd con Vue3


[frontend https://github.com/mariogb/vuelon](https://github.com/mariogb/vuelon)

![Esquema de interacción con el Backend!](https://sketchviz.com/@mariogb/3521333e92e7a7913b749f02a37665d8/3a26c07d9af48ed15abd101d84d6265a16c49499.sketchy.png "Esquema interacción con el BackEnd")




