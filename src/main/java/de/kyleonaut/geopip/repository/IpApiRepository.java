package de.kyleonaut.geopip.repository;

import com.google.inject.ImplementedBy;
import de.kyleonaut.geopip.entity.IpData;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@ImplementedBy(IpApiRepositoryImpl.class)
public interface IpApiRepository {
    IpData getIpData(String ip);
}
