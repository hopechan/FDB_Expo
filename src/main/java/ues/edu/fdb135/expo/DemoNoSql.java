/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.fdb135.expo;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
/**
 *
 * @author esperanza
 */
public class DemoNoSql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        final String URI_BASE = "http://0.0.0.0:8080/";
        final ResourceConfig rc = new ResourceConfig().packages("ues.edu.fdb135.expo");
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(URI_BASE), rc);
        
        System.out.println("Jersey app running...");
        new CountDownLatch(1).await();
        server.stop();
    }
    
}
