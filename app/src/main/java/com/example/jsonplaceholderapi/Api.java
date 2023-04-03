package com.example.jsonplaceholderapi;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
    @GET("posts")
    Call<List<Model>> model(@Query("userId") int[] lala,

                            @Query("_sort") String sort,
                            @Query("_order") String desc
                            );


    @GET("posts")
    Call<List<Model>> model(@QueryMap Map<String,String> parameters);

    @GET("posts/{id}/comments")
   Call<List<Comments>> model2(@Path("id") int postid);
    @GET
    Call<List<Comments>> model2(@Url String url);
}
