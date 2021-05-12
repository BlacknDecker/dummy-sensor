package edu.att4sd.sensor_demo.utilities;

public class DummySensorConfiguration {
	private String brokerUrl;
	private String name;
	private String topicPath;
	private String msgTemplate;
	private int generationTime; // Seconds
	
	public DummySensorConfiguration() {
		// Set default values
		brokerUrl = "tcp://localhost:1883";
		name = "dummySensor";
		topicPath = "";
		msgTemplate = "%d";
		generationTime = 10;
	}

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopicPath() {
		return topicPath;
	}

	public void setTopicPath(String topicPath) {
		this.topicPath = topicPath;
	}

	public String getMsgTemplate() {
		return msgTemplate;
	}

	public void setMsgTemplate(String msgTemplate) {
		this.msgTemplate = msgTemplate;
	}

	public int getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(int generationTime) {
		this.generationTime = generationTime;
	}

}
