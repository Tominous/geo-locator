package de.kyleonaut.geopip.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.kyleonaut.geopip.entity.IpData;
import de.kyleonaut.geopip.repository.IpApiRepository;
import lombok.RequiredArgsConstructor;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class IpApiService {
    private final IpApiRepository ipApiRepository;

    public CompletableFuture<IpData> findIpDataByInetSocketAddress(InetSocketAddress address) {
        return CompletableFuture.supplyAsync(() -> ipApiRepository.getIpData(address.getHostName()));
    }

    public CompletableFuture<IpData> findIpDataByIpv4(String ip) {
        return CompletableFuture.supplyAsync(() -> ipApiRepository.getIpData(ip));
    }
}
