	package client.config;

public class Conf {
	private final String configFile = "config.xml";
	private String host;
	private int port;
	private String dir;
	
	public Conf(){}//default

	public String getConfigFile() {
		return configFile;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
	
	
}
