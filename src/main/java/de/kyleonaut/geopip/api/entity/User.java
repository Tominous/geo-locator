package de.kyleonaut.geopip.api.entity;

import de.kyleonaut.geopip.entity.IpData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private IpData ipData;
    private String name;
    private UUID uuid;
}
