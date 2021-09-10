package com.example.relays2;






import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<RelayState> getPostWithID(@Path("id") int id);

    @GET("/temp")
    public Call<List<RelayState>> getAllRelays();

    @GET("/posts")
    public Call<List<RelayState>> getPostOfUser(@Query("userId") int id);

    @POST("/postRelay")
    public Call<RelayState> postRelayState(@Body RelayState data);
}

