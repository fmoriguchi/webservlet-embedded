package com.fmoriguchi.server;

import com.fmoriguchi.server.jetty.JettyServer;
import com.fmoriguchi.server.tomcat.TomcatServer;

public class StartServer {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
	    ServerRunnable jetty = new JettyServer();
		jetty.start();
		
		ServerRunnable tomcat = new TomcatServer();
		tomcat.start();
	}

}
