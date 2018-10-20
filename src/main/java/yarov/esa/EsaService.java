package yarov.esa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import java.io.IOException;
import java.net.URI;

public class EsaService {

    private HttpServer httpServer;
    private static final String BASE_URI = "http://localhost:8080/";

    public EsaService() {

    }

    public void start() {
        if (this.httpServer == null) {
            System.out.println("Creating resource configuration ...");
            ResourceConfig resourceConfig = new ResourceConfig();
            System.out.println("Configuring ESA resource ...");
            ResourceConfig esaResourceConfig = resourceConfig.packages("yarov.esa");
            System.out.println("ESA resource: " + esaResourceConfig.toString());
            this.httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), esaResourceConfig);
        }
    }

    public void stop() {
        if (this.httpServer != null && this.httpServer.isStarted()) {
            this.httpServer.shutdownNow();
        }
    }

    public static void main(String[] args) throws IOException {
        EsaService esaService = new EsaService();
        System.out.println("Starting HTTP server ...");
        esaService.start();
        System.out.println("HTTP server is ready. \nPress any key to exit...");
        System.in.read();
        esaService.stop();

    }
}
