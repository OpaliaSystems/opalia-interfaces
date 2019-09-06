package systems.opalia.interfaces.soa.osgi;

import java.util.*;
import java.util.stream.Collectors;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
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

    public <T> List<T> getServices(BundleContext bundleContext, Class<T> clazz, String filter)
            throws InvalidSyntaxException {

        return bundleContext.getServiceReferences(clazz, filter).stream()
                .map((x) -> {

                    serviceReferences.add(x);

                    return bundleContext.getService(x);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
