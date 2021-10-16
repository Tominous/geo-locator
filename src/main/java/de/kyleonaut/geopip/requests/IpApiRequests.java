package de.kyleonaut.geopip.requests;

import de.kyleonaut.geopip.entity.IpData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
public interface IpApiRequests {

    @GET("json/{ip}")
    Call<IpData> getIpData(@Path("ip") String ip);
}
