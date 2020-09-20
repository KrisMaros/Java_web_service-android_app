
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import java.io.Serializable;



public class MyClient implements Serializable {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:26893/Parcel_Tracking-war/webresources";

    public MyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("my_path");
    }
    
    public <T> T putJson(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .put(javax.ws.rs.client.Entity.entity(requestEntity,
                                                              javax.ws.rs.core.MediaType.APPLICATION_JSON),
                                                              responseType);
    }

    public void postJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                 .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getJson(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
