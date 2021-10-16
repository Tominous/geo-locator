package de.kyleonaut.geopip.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.kyleonaut.geopip.GeoIpPlugin;
import de.kyleonaut.geopip.service.IpApiService;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class GeoIpCommandController implements CommandExecutor {
    private final IpApiService ipApiService;
    private final GeoIpPlugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length != 1) {
            player.sendMessage("§8[§6GeoIp§8]§c Bitte benutze §4/geoip <Spieler>§c.");
            return false;
        }
        final String targetName = args[0];
        final Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            player.sendMessage(String.format("§8[§6GeoIp§8]§c Der Spieler §4%s §ckonnte nicht gefunden werden.", targetName));
            return false;
        }
        ipApiService.findIpDataByInetSocketAddress(player.getAddress()).whenComplete((ipData, throwable) -> {
            Bukkit.getScheduler().runTask(plugin, () -> {
                if (ipData == null) {
                    player.sendMessage("§8[§6GeoIp§8]§c Es konnten keine Daten gefunden werden.");
                    return;
                }
                player.sendMessage("§8========= [§6GeoIp§8] =========");
                player.sendMessage(String.format("§6Spieler: §e%s", targetName));
                player.sendMessage(String.format("§6IpV4 Adresse: §e%s", ipData.getQuery()));
                player.sendMessage(String.format("§6Land: §e%s", ipData.getCountry()));
                player.sendMessage(String.format("§6Stadt: §e%s", ipData.getCity()));
                player.sendMessage(String.format("§6ZIP Code: §e%s", ipData.getZip()));
                player.sendMessage(String.format("§6Provider: §e%s", ipData.getOrg()));
            });
        });
        return true;
    }
}
