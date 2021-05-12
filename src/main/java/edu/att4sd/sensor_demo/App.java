package edu.att4sd.sensor_demo;

import edu.att4sd.sensor_demo.utilities.DummySensor;
import edu.att4sd.sensor_demo.utilities.DummySensorConfiguration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/* READ ARGUMENTS */
		String brokerUrl = args.length==1 ? args[0] : "";
		
		/* CONFIGURATION */
		// Thermometer
		DummySensorConfiguration thermometerConf = new DummySensorConfiguration();
		thermometerConf.setName("THERMOMETER");
		thermometerConf.setTopicPath("home/room_1/temperature");
		thermometerConf.setMsgTemplate("Room temperature: %d");
		thermometerConf.setGenerationTime(5);
		if(brokerUrl.length() > 0)
			thermometerConf.setBrokerUrl(brokerUrl);
		
		// Umidity Sensor
		DummySensorConfiguration umidityConf = new DummySensorConfiguration();
		umidityConf.setName("UMIDITY SENSOR");
		umidityConf.setTopicPath("home/room_1/umidity");
		umidityConf.setMsgTemplate("Umidity: %f");
		umidityConf.setGenerationTime(3);
		if(brokerUrl.length() > 0)
			umidityConf.setBrokerUrl(brokerUrl);
		
		/* SENSORS */
		Thread thermometer = new Thread(new DummySensor(thermometerConf));
		Thread umidity = new Thread(new DummySensor(umidityConf));
		
		/* LAUNCH */
		thermometer.start();
		umidity.start();	
	}
}
