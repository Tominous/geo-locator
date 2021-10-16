package de.kyleonaut.geopip.provider;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.inject.Provider;

/**
 * @author kyleonaut
 * @version 1.0.0
 * created at 16.10.2021
 */
public class RetrofitProvider implements Provider<Retrofit> {

    @Override
    public Retrofit get() {
        return new Retrofit.Builder()
                .baseUrl("http://ip-api.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
