package de.kyleonaut.geopip;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.kyleonaut.geopip.api.controller.UserController;
import de.kyleonaut.geopip.controller.GeoIpCommandController;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
public class GeoIpPlugin extends JavaPlugin {

    @Getter
    private Injector injector;

    @Inject
    private GeoIpCommandController geoIpCommandController;

    @Inject
    private UserController userController;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getScheduler().runTaskLater(this, () -> {
            configureGuice();
            registerController();
        }, 20);
    }

    private void configureGuice() {
        final GeoIpModule module = new GeoIpModule(this);
        this.injector = module.getInjector();
        this.injector.injectMembers(this);
    }

    private void registerController() {
        Objects.requireNonNull(getCommand("geoip")).setExecutor(geoIpCommandController);
        Bukkit.getPluginManager().registerEvents(userController, this);
    }
}
