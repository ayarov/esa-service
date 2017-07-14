package yarov.esa;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class EsaResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() throws IOException {
        String responseMsg = target
                .path("esa")
                .queryParam("textA", "New York City")
                .queryParam("textB", "Lower Manhattan")
                .request()
                .get(String.class);

        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseMsg);
        double score = node.get("score").doubleValue();

        Assert.assertTrue("ESA score > 0", score > 0.0);
    }
}
