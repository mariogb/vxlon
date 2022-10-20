/* 
 * 
 * 
 * 
 */
/**
 * Author:  MGB
 * Created: Nov 26, 2021
 */

#INSATALL


##Docker Composer


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