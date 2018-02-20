package systems.opalia.interfaces.soa.osgi;

import com.typesafe.config.Config;


public interface ServiceLauncher {

    void create(Config config);

    String getServiceName();
}
