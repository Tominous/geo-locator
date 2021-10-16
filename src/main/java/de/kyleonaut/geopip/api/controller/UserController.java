package de.kyleonaut.geopip.api.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.kyleonaut.geopip.api.GeoIpApi;
import de.kyleonaut.geopip.api.entity.User;
import de.kyleonaut.geopip.service.IpApiService;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserController implements Listener {
    private final GeoIpApi geoIpApi;
    private final IpApiService ipApiService;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        ipApiService.findIpDataByInetSocketAddress(player.getAddress()).whenComplete((ipData, throwable) -> {
            if (ipData == null) {
                return;
            }
            geoIpApi.addUser(new User(ipData, player.getName(), player.getUniqueId()));
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        geoIpApi.removeUser(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        geoIpApi.removeUser(event.getPlayer().getUniqueId());
    }
}
