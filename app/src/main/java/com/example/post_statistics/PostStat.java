package com.example.post_statistics;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostStat {

    private static final String BASE_URL = "https://api.inrating.top";
    private static final String SLUG = "LeBxOWT5zSemiSvkuqBLXFjXlaA0bJlX";
    private static final String TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjJmNGU5ZDA1MzU3MDI3MmFlMGZhZTMzM2Y4ZTY4ZWVlMWNiMzc0NmM0Mjg5NzI0ZTExNzJjM2Q4ODYzNDNkNDkyY2ZjZjI4Njg0NzQ0MGEwIn0.eyJhdWQiOiIyIiwianRpIjoiMmY0ZTlkMDUzNTcwMjcyYWUwZmFlMzMzZjhlNjhlZWUxY2IzNzQ2YzQyODk3MjRlMTE3MmMzZDg4NjM0M2Q0OTJjZmNmMjg2ODQ3NDQwYTAiLCJpYXQiOjE1MzY4MzE4ODcsIm5iZiI6MTUzNjgzMTg4NywiZXhwIjoxNTY4MzY3ODg3LCJzdWIiOiIzOCIsInNjb3BlcyI6W119.dRitRnoqNFS3xUgtLdLiDjDVVe7ZFNrh24Qm2ML9m-V7kZpgQgajArYoS44kMa1dz_MHUhq3pqk8SnAYIsULgfrOvewTUzmH1C92-yL64Uqnv7lqWizldX2fbJ2IbB8khOCtQ-CCNA_fGY_zEBJXLsOqr4Z00tbZE6fa0PX4Mu0SsuUakLeygXbXnKOmFyZmLJZWoXKpbqiSBU239nrcyqJftBon8DL1BAUuFiadap-gpVSXj8h6BX-FsJx5cgPHFiijIalcEgzOq4VCMkwbQE8xbTsmmxkZUOnM7oKab5inzl8EV5iUgcExeSbHT6k_phOkA7XUaR6PhVoKrSQTPcfdijhME1IHfPVDPGO0vhd6hKszRrhjEPEpoothBoB8ss0lmuCFURdxFv17q97rfpDn1OfO_Y3wYuRW2lqFAnw7sLd92CHjfONwQKswLDzwE4hiQhB8iS_UEbuL_UamNOiCLfjNnVWbVc9BvoReEa8jG4coc0Kv9VNJVWh3D_hGf8dLRZBd1a7zB6-nSpKGf0eAzB0_rBXsyBepjudC-5EFDjloJOxy1Mdruoq6mQa_tFcO99JRteUSd0CXHZO-CN4Bp4xND9kstdutjBn2UWT5xhNq_QRBmBsBDAwp647dUCyQofutN9GUlu2LxmhL0ojydazdND_d9rHtY9t-ndw";

    private Context context;

    private StatisticsAPI statisticsAPI;
    private Retrofit retrofit;
    private Long id_post;
    private Long comments_count;
    private Long likes_count;
    private Long bookmarks_count;
    private Long reposts_count;
    private Long views_count;
    private Long mentions_count;

    private ArrayList<Block> mBlocks  = new ArrayList<Block>(6);

    private ArrayList<ResponseGetAllGroupPost.User> people = new ArrayList<>();

    private ArrayList<Block.User> likers = new ArrayList<>();
    private ArrayList<Block.User> commentators = new ArrayList<>();
    private ArrayList<Block.User> mentions = new ArrayList<>();
    private ArrayList<Block.User> reposts = new ArrayList<>();

    private ArrayList<Block.User> users = new ArrayList<>();

    public PostStat(Context context) {
        this.context = context;
    }

    public ArrayList<Block> getBlocks(){
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
                    id_post = response.body().id;
                    comments_count = response.body().comments_count;
                    likes_count = response.body().likes_count;
                    bookmarks_count = response.body().bookmarks_count;
                    reposts_count = response.body().reposts_count;
                    views_count = response.body().views_count;


                    //просмотры
                    mBlocks.add(new Block(views_count, context.getResources().getString(R.string.looks), null));

                    getLikersBlock();
                    getComentatorsBlock();
                    getMentionsBlock();
                    getRepostsBlock();
                    //закладки
                    mBlocks.add(new Block(bookmarks_count, context.getResources().getString(R.string.bookmarks), null));




                } else {
                    Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseGetInfoAboutPost> call, Throwable t) {
                Toast.makeText(context, "ошибка", Toast.LENGTH_LONG).show();
            }
        });



        return mBlocks;
    }

    private void getLikersBlock(){
        if ( likes_count!=0) {
            IdPost idPost = new IdPost(id_post);
            Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllLikersPost(TOKEN, idPost);
            call.enqueue(new Callback<ResponseGetAllGroupPost>() {
                @Override
                public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                    if (response.isSuccessful()) {

                        likers = gettinUsersFromResponse(response);
                        mBlocks.add(new Block(likes_count, context.getResources().getString(R.string.likes), likers));

                    } else {
                        Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
                }
            });
        }
        else  mBlocks.add(new Block(likes_count, context.getResources().getString(R.string.likes), null));
    }

    private void getComentatorsBlock(){
        if (comments_count!=0) {
            IdPost idPost = new IdPost(id_post);
            Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllCommentatorsPost(TOKEN, idPost);
            call.enqueue(new Callback<ResponseGetAllGroupPost>() {
                @Override
                public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                    if (response.isSuccessful()) {

                        commentators = gettinUsersFromResponse(response);
                        mBlocks.add(new Block(comments_count, context.getResources().getString(R.string.commentators), commentators));

                    } else {
                        Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
                }
            });
        }
        else  mBlocks.add(new Block(comments_count, context.getResources().getString(R.string.commentators), null));
    }

    private void getMentionsBlock(){

        IdPost idPost = new IdPost(id_post);
        Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllMentionsPost(TOKEN, idPost);
        call.enqueue(new Callback<ResponseGetAllGroupPost>() {
            @Override
            public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                if (response.isSuccessful()) {

                    mentions_count = response.body().meta.total;
                    if (mentions_count!=0) {
                        mentions = gettinUsersFromResponse(response);
                        mBlocks.add(new Block(mentions_count, context.getResources().getString(R.string.mentions), mentions));
                    }
                    else mBlocks.add(new Block(mentions_count, context.getResources().getString(R.string.mentions), null));

                } else {
                    Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
            }
        });



    }

    private void getRepostsBlock(){
        if (reposts_count!=0) {
            IdPost idPost = new IdPost(id_post);
            Call<ResponseGetAllGroupPost> call = statisticsAPI.getAllRepostrsPost(TOKEN, idPost);
            call.enqueue(new Callback<ResponseGetAllGroupPost>() {
                @Override
                public void onResponse(Call<ResponseGetAllGroupPost> call, Response<ResponseGetAllGroupPost> response) {
                    if (response.isSuccessful()) {

                        reposts = gettinUsersFromResponse(response);
                        mBlocks.add(new Block(reposts_count, context.getResources().getString(R.string.reposts), reposts));

                    } else {
                        Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetAllGroupPost> call, Throwable t) {
                }
            });
        }
        else  mBlocks.add(new Block(reposts_count, context.getResources().getString(R.string.reposts), null));

    }




    private ArrayList<Block.User> gettinUsersFromResponse (Response<ResponseGetAllGroupPost> response) {

        people = response.body().data;

        for (int i=0; i < response.body().meta.total; i++){

            Long id = people.get(i).id;
            String nickn = people.get(i).nickname;
            String avatar = people.get(i).avatar_image.url_small;

            users.add(new Block.User(id, nickn, avatar));
        }
        return users;

    }




}
