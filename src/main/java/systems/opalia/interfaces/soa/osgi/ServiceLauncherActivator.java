package systems.opalia.interfaces.soa.osgi;

import com.typesafe.config.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import scala.concurrent.Await$;
import scala.concurrent.duration.Duration$;
import systems.opalia.interfaces.soa.Bootable;


public abstract class ServiceLauncherActivator
        implements BundleActivator {

    private Optional<Bootable> bootable = Optional.empty();
    private Optional<ServiceRegistration<ServiceLauncher>> launcherRegistration = Optional.empty();
    private List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();
    private List<ServiceReference<?>> serviceReferences = new ArrayList<>();

    public void start(BundleContext context)
            throws Exception {

        synchronized (ServiceLauncherActivator.this) {

            if (bootable.isPresent() || launcherRegistration.isPresent())
                throw new IllegalStateException("Bundle has already been initialized.");

            ServiceLauncher launcher =
                    new ServiceLauncher() {

                        public void create(Config config) {

                            synchronized (ServiceLauncherActivator.this) {

                                if (bootable.isPresent())
                                    throw new IllegalStateException("Bundle has already been configured.");

                                bootable = Optional.of(init(context, config));

                                bootable.ifPresent((x) -> {

                                    x.setup();

                                    try {

                                        Await$.MODULE$.result(x.awaitUp(), Duration$.MODULE$.Inf());

                                    } catch (Exception e) {

                                        throw new RuntimeException(e);
                                    }

                                });
                            }
                        }

                        public String getServiceName() {

                            return context.getBundle().getSymbolicName();
                        }
                    };

            launcherRegistration =
                    Optional.of(context.registerService(ServiceLauncher.class, launcher, null));
        }
    }

    public void stop(BundleContext context)
            throws Exception {

        synchronized (ServiceLauncherActivator.this) {

            launcherRegistration.ifPresent(ServiceRegistration::unregister);
            launcherRegistration = Optional.empty();

            serviceRegistrations.forEach(ServiceRegistration::unregister);
            serviceRegistrations.clear();

            bootable.ifPresent((x) -> {

                x.shutdown();

                try {

                    Await$.MODULE$.result(x.awaitDown(), Duration$.MODULE$.Inf());

                } catch (Exception e) {

                    throw new RuntimeException(e);
                }

            });

            bootable = Optional.empty();

            serviceReferences.forEach(context::ungetService);
            serviceReferences.clear();
        }
    }

    protected <T> T getServiceManaged(BundleContext context, Class<T> clazz) {

        return Optional.ofNullable(context.getServiceReference(clazz))
                .flatMap((x) -> {

                    serviceReferences.add(x);

                    return Optional.ofNullable(context.getService(x));
                })
                .orElseThrow(() -> new IllegalArgumentException("Cannot find service " + clazz.getName() + "."));
    }

    protected <T> void registerServiceManaged(BundleContext context, Class<T> clazz, T object) {

        serviceRegistrations.add(context.registerService(clazz, object, null));
    }

    protected abstract Bootable init(BundleContext context, Config config);
}
