package systems.opalia.interfaces.soa.osgi;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;


public class ServiceManager {

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();
    private final List<ServiceReference<?>> serviceReferences = new ArrayList<>();

    public <T> void registerService(BundleContext bundleContext, Class<T> clazz, T service) {

        registerService(bundleContext, clazz, service, null);
    }

    public <T> void registerService(BundleContext bundleContext, Class<T> clazz, T service,
                                    Dictionary<String, ?> properties) {

        serviceRegistrations.add(bundleContext.registerService(clazz, service, properties));
    }

    public <T> T getService(BundleContext bundleContext, Class<T> clazz) {

        return Optional.ofNullable(bundleContext.getServiceReference(clazz))
                .flatMap((x) -> {

                    serviceReferences.add(x);

                    return Optional.ofNullable(bundleContext.getService(x));
                })
                .orElseThrow(() -> new IllegalArgumentException("Cannot find service " + clazz.getName() + "."));
    }

    public void unregisterServices() {

        serviceRegistrations.forEach(ServiceRegistration::unregister);
        serviceRegistrations.clear();
    }

    public void ungetServices(BundleContext bundleContext) {

        serviceReferences.forEach(bundleContext::ungetService);
        serviceReferences.clear();
    }
}
