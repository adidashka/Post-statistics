package com.example.post_statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements PostStaticsticCallback {

    private TextView tBookmark;
    private TextView tVLooks;
    private TextView tVLikes;
    private TextView tComments;
    private TextView tMentions;
    private TextView tReposts;

    private RecyclerView recyclerViewLikes;
    private RecyclerView recyclerViewCommentators;
    private RecyclerView recyclerViewMentions;
    private RecyclerView recyclerViewReposts;

    public PostStat postStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tBookmark = findViewById(R.id.tBookmarks);
        tVLooks = findViewById(R.id.tLooks);

        tVLikes = findViewById(R.id.tLikes);
        recyclerViewLikes = findViewById(R.id.recyclerViewLikers);

        tComments = findViewById(R.id.tComment);
        recyclerViewCommentators = findViewById(R.id.recyclerViewCommentators);

        tMentions = findViewById(R.id.tMentions);
        recyclerViewMentions = findViewById(R.id.recyclerViewMentions);

        tReposts = findViewById(R.id.tReposts);
        recyclerViewReposts = findViewById(R.id.recyclerViewReposts);

        postStat = new PostStat(this);
        postStat.getBlocks();


    }


    public void onInfoAboutPost(ResponseGetInfoAboutPost response) {
        String bookmarksText = " " + getResources().getString(R.string.bookmarks) + " "+ response.bookmarks_count;
        tBookmark.setText(bookmarksText);
        String looksText = " " + getResources().getString(R.string.looks) + " "+ response.views_count;
        tVLooks.setText(looksText);
    }

    @Override
    public void onAllLikersPost(ResponseGetAllGroupPost response) {
        String s = " " + getResources().getString(R.string.likes) + " " + response.meta.total;
        tVLikes.setText(s);
        recyclerViewLikes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterL = new UsersBlockRecyclerView(this, response.data);
        recyclerViewLikes.setAdapter(adapterL);
    }

    @Override
    public void onAllCommentatorsPost(ResponseGetAllGroupPost response) {
        String s = " " + getResources().getString(R.string.commentators) + " "+ response.meta.total;
        tComments.setText(s);
        recyclerViewCommentators.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterC = new UsersBlockRecyclerView(this, response.data);
        recyclerViewCommentators.setAdapter(adapterC);
    }

    @Override
    public void onAllMentionsPost(ResponseGetAllGroupPost response) {
        String s = " " + getResources().getString(R.string.mentions) + " "+ response.meta.total;
        tMentions.setText(s);
        recyclerViewMentions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterM = new UsersBlockRecyclerView(this, response.data);
        recyclerViewMentions.setAdapter(adapterM);
    }

    @Override
    public void onAllAllRepostrsPost(ResponseGetAllGroupPost response) {
        String s = " " + getResources().getString(R.string.reposts) + " "+ response.meta.total;
        tReposts.setText(s);
        recyclerViewReposts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterR = new UsersBlockRecyclerView(this, response.data);
        recyclerViewReposts.setAdapter(adapterR);
    }
}