package com.example.post_statistics;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;



public interface StatisticsAPI {
    @POST("/v1/users/posts/get")
    Call<ResponseGetInfoAboutPost> getInfoAboutPost (@Header("Authorization") String token,
                                                     @Body Slug slug);

    @POST("/v1/users/posts/likers/all")
    Call<ResponseGetAllGroupPost> getAllLikersPost (@Header("Authorization") String token,
                                                    @Body IdPost idPost);

    @POST("/v1/users/posts/reposters/all")
    Call<ResponseGetAllGroupPost> getAllRepostrsPost (@Header("Authorization") String token,
                                                      @Body IdPost idPost);;

    @POST("/v1/users/posts/commentators/all")
    Call<ResponseGetAllGroupPost> getAllCommentatorsPost (@Header("Authorization") String token,
                                                          @Body IdPost idPost);

    @POST("/v1/users/posts/mentions/all")
    Call<ResponseGetAllGroupPost> getAllMentionsPost (@Header("Authorization") String token,
                                                      @Body IdPost idPost);


}
