package edu.att4sd.sensor_demo.utilities;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DummySensor implements Runnable {
	private static Logger logger;
	private DummySensorConfiguration configuration;

	public DummySensor(DummySensorConfiguration configuration) {
		this.configuration = configuration;
		logger = Logger.getLogger(configuration.getName());
		logInfo("Created!");
	}

	@Override
	public void run() {
		try {
			String sensorName = configuration.getName();
			long timeout = configuration.getGenerationTime() * 1000;
			// Start connection
			MqttClient client = new MqttClient(configuration.getBrokerUrl(), sensorName);
			client.connect();
			logInfo("Started!");
			// Start routine
			while (true) {
				MqttMessage toSend = generateMessage();
				client.publish(configuration.getTopicPath(), toSend);
				Thread.sleep(configuration.getGenerationTime() * 1000);
			}
		} catch (MqttException | InterruptedException e) {
			logger.severe("ERROR - " + e.toString());
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

	/* UTILITIES */

	private MqttMessage generateMessage() {
		String payload = fillTemplate(configuration.getMsgTemplate());
		logInfo("Generated new payload: " + payload);
		return new MqttMessage(payload.getBytes());
	}

	private String fillTemplate(String msgTemplate) {
		String msg = Stream.of(msgTemplate.split(" "))
										  .map(word -> randomFormatter(word))
										  .collect(Collectors.joining(" "));
		return msg;
	}

	private String randomFormatter(String word) {
		switch (word) {
			case "%d": return Integer.toString(ThreadLocalRandom.current().nextInt(20,35));
			case "%f": return Float.toString(ThreadLocalRandom.current().nextFloat()*100);
			default: return word;
		}
	}
	
	private void logInfo(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(configuration.getTopicPath());
		sb.append("] - ");
		sb.append(configuration.getName());
		sb.append(" - ");
		sb.append(msg);
		logger.info(sb.toString());
	}

}
