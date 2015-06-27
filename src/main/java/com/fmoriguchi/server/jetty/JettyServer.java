package com.fmoriguchi.server.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.fmoriguchi.server.ServerRunnable;
import com.fmoriguchi.servlet.HelloWorld;

public final class JettyServer implements ServerRunnable {

	private Server server;
	
	
	@Override
	public void start() {
		
		try {
			server = new Server(8000);
			
			ServerConnector c = new ServerConnector(server);
			c.setIdleTimeout(1000);
			c.setAcceptQueueSize(10);
			//c.setPort(8080);
			//c.setHost("localhost");
			ServletContextHandler handler = new ServletContextHandler(server,"/fmoriguchi", true, false);
			ServletHolder servletHolder = new ServletHolder(HelloWorld.class);
			handler.addServlet(servletHolder, "/hello");
			server.addConnector(c);
			
			server.start();
			
		}catch(Exception e) {

			throw new RuntimeException("Cannot start jetty server", e);
		}
	}

	@Override
	public void shutdown() {
	
		try {
			server.stop();
		}catch(Exception e) {
			throw new RuntimeException("Cannot stop jetty server", e);
		}
	}
	
	
	
	/*
	 * try {
			Server server = new Server();
			ServerConnector c = new ServerConnector(server);
			c.setIdleTimeout(1000);
			c.setAcceptQueueSize(10);
			c.setPort(8080);
			c.setHost("localhost");
			ServletContextHandler handler = new ServletContextHandler(server,
					"/app", true, false);
			ServletHolder servletHolder = new ServletHolder(
					DatePrintServlet.class);
			handler.addServlet(servletHolder, "/date");
			server.addConnector(c);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 * */

}
