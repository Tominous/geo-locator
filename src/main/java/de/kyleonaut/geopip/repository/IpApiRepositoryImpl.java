package de.kyleonaut.geopip.repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.kyleonaut.geopip.entity.IpData;
import de.kyleonaut.geopip.requests.IpApiRequests;
import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class IpApiRepositoryImpl implements IpApiRepository {
    private final IpApiRequests ipApiRequests;

    @Override
    public IpData getIpData(String ip) {
        final Call<IpData> ipData = ipApiRequests.getIpData(ip);
        try {
            final Response<IpData> response = ipData.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
