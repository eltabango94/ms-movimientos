# ms-movimiento
Tener ejecutado maven y docker
ejecutar
mvn clean package -DskipTests

Luego ejecutar  

# crear la red solo una vez
docker network create my_network 

# crear la red solo una vez en la ruta del Ms movimiento
 docker-compose up --build -d    

# Ejecutar 
 docker-compose up 