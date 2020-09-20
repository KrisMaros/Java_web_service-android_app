
package resource;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Krzychu
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(resource.CustomerResource.class);
        resources.add(resource.DriverResource.class);
        resources.add(resource.ParcelDriverResource.class);
        resources.add(resource.ParcelResource.class);
    }
    
}
