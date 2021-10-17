package de.kyleonaut.geopip.entity;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.kyleonaut.geopip.GeoIpPlugin;
import lombok.Data;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 17.10.2021
 */
@Data
@Singleton
public class LangData {
    private final String wrongSyntax;
    private final String playerNotFound;
    private final String dataNotFound;
    private final String dataPlayer;
    private final String dataIpV4;
    private final String dataCountry;
    private final String dataCity;
    private final String dataZipCode;
    private final String dataProvider;

    @Inject
    public LangData(GeoIpPlugin plugin) {
        this.wrongSyntax = plugin.getConfig().getString("syntax_error");
        this.playerNotFound = plugin.getConfig().getString("player_not_found");
        this.dataNotFound = plugin.getConfig().getString("data_not_found");
        this.dataPlayer = plugin.getConfig().getString("data.player");
        this.dataIpV4 = plugin.getConfig().getString("data.ipv4");
        this.dataCountry = plugin.getConfig().getString("data.country");
        this.dataCity = plugin.getConfig().getString("data.city");
        this.dataZipCode = plugin.getConfig().getString("data.zip_code");
        this.dataProvider = plugin.getConfig().getString("data.provider");
    }
}
