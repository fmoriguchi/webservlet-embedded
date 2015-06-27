package com.fmoriguchi.server.tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.fmoriguchi.server.ServerRunnable;
import com.fmoriguchi.servlet.HelloWorld;

public final class TomcatServer implements ServerRunnable {

	private Tomcat server;
	
	@Override
	public void start() {

		try {
			server = new Tomcat();
			server.setPort(9000);
			
			File base = new File(System.getProperty("java.io.tmpdir"));
			Context context = server.addContext("/fmoriguchi", base.getAbsolutePath());
			Tomcat.addServlet(context, "hello", new HelloWorld());
		
			context.addServletMapping("/hello", "hello");
			
			server.start();
			server.getServer().await();
			
		}catch(Exception e) {
			
			throw new RuntimeException("Cannot start Tomcat server", e);
		}
	}

	@Override
	public void shutdown() {
		try {
			server.stop();
			
		} catch (Exception e) {
			throw new RuntimeException("Cannot shutdown Tomcat server.", e);
		}
	}

	
	/*
	 * 
	 * Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		File base = new File(System.getProperty("java.io.tmpdir"));
		Context rootCtx = tomcat.addContext("/app", base.getAbsolutePath());
		Tomcat.addServlet(rootCtx, "dateServlet", new DatePrintServlet());
		rootCtx.addServletMapping("/date", "dateServlet");
		tomcat.start();
		tomcat.getServer().await();*/
}
