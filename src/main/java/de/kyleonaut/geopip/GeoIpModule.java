package de.kyleonaut.geopip;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.kyleonaut.geopip.requests.IpApiRequests;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
public class GeoIpModule extends AbstractModule {
    private final GeoIpPlugin plugin;
    private final IpApiRequests ipApiRequests;

    public GeoIpModule(GeoIpPlugin plugin) {
        this.plugin = plugin;
        this.ipApiRequests = new Retrofit.Builder()
                .baseUrl("http://ip-api.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IpApiRequests.class);
    }

    public Injector getInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(GeoIpPlugin.class).toInstance(plugin);
        //bind(Retrofit.class).toProvider(RetrofitProvider.class);
        bind(IpApiRequests.class).toInstance(this.ipApiRequests);
    }
}
