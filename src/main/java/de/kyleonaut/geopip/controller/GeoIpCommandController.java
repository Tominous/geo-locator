package de.kyleonaut.geopip.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.kyleonaut.geopip.GeoIpPlugin;
import de.kyleonaut.geopip.entity.LangData;
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
    private final LangData langData;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length != 1) {
            player.sendMessage(langData.getWrongSyntax());
            return false;
        }
        final String targetName = args[0];
        final Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            player.sendMessage(String.format(langData.getPlayerNotFound(), targetName));
            return false;
        }
        ipApiService.findIpDataByInetSocketAddress(target.getAddress()).whenComplete((ipData, throwable) -> {
            Bukkit.getScheduler().runTask(plugin, () -> {
                if (ipData == null) {
                    player.sendMessage(langData.getDataNotFound());
                    return;
                }
                player.sendMessage("§8========= [§6GeoIp§8] =========");
                player.sendMessage(String.format(langData.getDataPlayer(), targetName));
                player.sendMessage(String.format(langData.getDataIpV4(), ipData.getQuery()));
                player.sendMessage(String.format(langData.getDataCountry(), ipData.getCountry()));
                player.sendMessage(String.format(langData.getDataCity(), ipData.getCity()));
                player.sendMessage(String.format(langData.getDataZipCode(), ipData.getZip()));
                player.sendMessage(String.format(langData.getDataProvider(), ipData.getOrg()));
            });
        });
        return true;
    }
}
