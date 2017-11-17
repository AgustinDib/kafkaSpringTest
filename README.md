# kafkaSpringTest

Para hacer funcionar el proyecto es necesario descargar Apache Kafka, que incluye el servidor Zookeeper donde corre el mismo.
Link de descarga: http://apache.dattatec.com/kafka/1.0.0/kafka_2.11-1.0.0.tgz

El archivo descargado se descomprime con el siguiente comando: tar -xzf kafka_2.11-1.0.0.tgz

Para el correcto funcionamiento se debe alterar el archivo config/server.properties, cambiando el valor de zookeeper.connection.timeout.ms que viene por defecto en 3000 por 24000.

Para iniciar el servidor Zookeper en windows, dentro de la carpeta de kafka ingresar el siguiente comando:
bin\windows\zookeeper-server-start.bat config\zookeeper.properties

Para iniciar el Kafka en windows, dentro de la carpeta de kafka ingresar el siguiente comando:
bin\windows\kafka-server-start.bat config\server.properties

Para crear el topic donde se guardan y leen los mensajes, ejecutar:
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topicName
