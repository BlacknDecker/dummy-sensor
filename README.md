# Sensor Simulator
This application allows the creation of simulated IoT sensors.

Each sensor is able to generate a random value and publish it with the MQTT protocol on a provided topic on a given broker.

## Run the simulation
As an example the main method creates two sensors, a thermometer and a umidity sensor.
Sensor|Topic
------|------
Temperature|home/room_1/temperature
Umidity|home/room_1/umidity

To run the program:
```
mvn clean compile exec:java
```
To specify a different broker:
```
mvn clean compile exec:java -Dexec.args="tcp://broker.hivemq.com:1883"
```
