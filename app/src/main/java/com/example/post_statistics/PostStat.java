package com.example.post_statistics;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostStat {

    private static final String TAG = "PostStat";
    private static final String ERROR = "Ошибка ";
    private static final String BASE_URL = "https://api.inrating.top";
    private static final String SLUG = "LeBxOWT5zSemiSvkuqBLXFjXlaA0bJlX";
    private static final String TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjJmNGU5ZDA1MzU3MDI3MmFlMGZhZTMzM2Y4ZTY4ZWVlMWNiMzc0NmM0Mjg5NzI0ZTExNzJjM2Q4ODYzNDNkNDkyY2ZjZjI4Njg0NzQ0MGEwIn0.eyJhdWQiOiIyIiwianRpIjoiMmY0ZTlkMDUzNTcwMjcyYWUwZmFlMzMzZjhlNjhlZWUxY2IzNzQ2YzQyODk3MjRlMTE3MmMzZDg4NjM0M2Q0OTJjZmNmMjg2ODQ3NDQwYTAiLCJpYXQiOjE1MzY4MzE4ODcsIm5iZiI6MTUzNjgzMTg4NywiZXhwIjoxNTY4MzY3ODg3LCJzdWIiOiIzOCIsInNjb3BlcyI6W119.dRitRnoqNFS3xUgtLdLiDjDVVe7ZFNrh24Qm2ML9m-V7kZpgQgajArYoS44kMa1dz_MHUhq3pqk8SnAYIsULgfrOvewTUzmH1C92-yL64Uqnv7lqWizldX2fbJ2IbB8khOCtQ-CCNA_fGY_zEBJXLsOqr4Z00tbZE6fa0PX4Mu0SsuUakLeygXbXnKOmFyZmLJZWoXKpbqiSBU239nrcyqJftBon8DL1BAUuFiadap-gpVSXj8h6BX-FsJx5cgPHFiijIalcEgzOq4VCMkwbQE8xbTsmmxkZUOnM7oKab5inzl8EV5iUgcExeSbHT6k_phOkA7XUaR6PhVoKrSQTPcfdijhME1IHfPVDPGO0vhd6hKszRrhjEPEpoothBoB8ss0lmuCFURdxFv17q97rfpDn1OfO_Y3wYuRW2lqFAnw7sLd92CHjfONwQKswLDzwE4hiQhB8iS_UEbuL_UamNOiCLfjNnVWbVc9BvoReEa8jG4coc0Kv9VNJVWh3D_hGf8dLRZBd1a7zB6-nSpKGf0eAzB0_rBXsyBepjudC-5EFDjloJOxy1Mdruoq6mQa_tFcO99JRteUSd0CXHZO-CN4Bp4xND9kstdutjBn2UWT5xhNq_QRBmBsBDAwp647dUCyQofutN9GUlu2LxmhL0ojydazdND_d9rHtY9t-ndw";


    private PostStaticsticCallback callback;

    private StatisticsAPI statisticsAPI;
    private Retrofit retrofit;
    private Long idPost;


    public void getBlocks(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        statisticsAPI = retrofit.create(StatisticsAPI.class);
        Slug slug = new Slug(SLUG);
        Call<ResponseGetInfoAboutPost> call_infoAboutPost = statisticsAPI.getInfoAboutPost(TOKEN, slug);
        call_infoAboutPost.enqueue(new Callback<ResponseGetInfoAboutPost>() {
            @Override
            public void onResponse(Call<ResponseGetInfoAboutPost> call, Response<ResponseGetInfoAboutPost> response) {
                if (response.isSuccessful()) {
                    idPost = response.body().id;

                    callback.onInfoAboutPost(response.body());

                    getLikersBlock();
                    getCommentatorsBlock();
                    getMentionsBlock();
                    getRepostsBlock();

                } else {
                    Log.e(TAG, ERROR + response.code());
                }
            }
            @Override
            public void onFailure(Call<ResponseGetInfoAboutPost> call, Throwable t) {
                Log.e(TAG, ERROR);
            }
        });

    }

    private void getLikersBlock(){

            IdPost idPost = new IdPost(this.idPost);
            Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllLikersPost(TOKEN, idPost);
            call.enqueue(new Callback<ResponseGetAllGroupPost>() {
                @Override
                public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                    if (response.isSuccessful()) {

                        callback.onAllLikersPost(response.body());

                    } else {
                        Log.e(TAG, ERROR + response.code());
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
                }
            });
    }

    private void getCommentatorsBlock(){

            IdPost idPost = new IdPost(this.idPost);
            Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllCommentatorsPost(TOKEN, idPost);
            call.enqueue(new Callback<ResponseGetAllGroupPost>() {
                @Override
                public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                    if (response.isSuccessful()) {

                        callback.onAllCommentatorsPost(response.body());

                    } else {
                        Log.e(TAG, ERROR + response.code());
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
                }
            });
    }

    private void getMentionsBlock(){

        IdPost idPost = new IdPost(this.idPost);
        Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllMentionsPost(TOKEN, idPost);
        call.enqueue(new Callback<ResponseGetAllGroupPost>() {
            @Override
            public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                if (response.isSuccessful()) {

                    callback.onAllMentionsPost(response.body());

                } else {
                    Log.e(TAG, ERROR + response.code());
                }
            }
            @Override
            public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
            }
        });
    }

    private void getRepostsBlock(){

            IdPost idPost = new IdPost(this.idPost);
            Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllRepostrsPost(TOKEN, idPost);
            call.enqueue(new Callback<ResponseGetAllGroupPost>() {
                @Override
                public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                    if (response.isSuccessful()) {

                        callback.onAllAllRepostrsPost(response.body());

                    } else {
                        Log.e(TAG, ERROR + response.code());
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
                }
            });
    }


    public PostStat(PostStaticsticCallback callback) {
        this.callback = callback;
    }
}
