package com.example.post_statistics;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowingBlock {

    public void showLooks(Context context, Long viewsCount){
        TextView tVLooks = ((Activity)context).findViewById(R.id.tLooks);
        String s = " " + context.getResources().getString(R.string.looks) + " "+ viewsCount;
        tVLooks.setText(s);
    }

    public void showLikes(Context context, Long likesCount, ArrayList<ResponseGetAllGroupPost.User> likers){
        TextView tVLikes = ((Activity)context).findViewById(R.id.tLikes);
        String s = " " + context.getResources().getString(R.string.likes) + " "+ likesCount;
        tVLikes.setText(s);

        RecyclerView recyclerViewLikes = ((Activity)context).findViewById(R.id.recyclerViewLikers);
        recyclerViewLikes.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterL = new UsersBlockRecyclerView(context, likers);
        recyclerViewLikes.setAdapter(adapterL);
    }

    public void showComments(Context context, Long commentsCount, ArrayList<ResponseGetAllGroupPost.User> commentators){
        TextView tComments = ((Activity)context).findViewById(R.id.tComment);
        String s = " " + context.getResources().getString(R.string.commentators) + " "+ commentsCount;
        tComments.setText(s);

        RecyclerView recyclerViewCommentators = ((Activity)context).findViewById(R.id.recyclerViewCommentators);
        recyclerViewCommentators.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterC = new UsersBlockRecyclerView(context, commentators);
        recyclerViewCommentators.setAdapter(adapterC);

    }

    public void showMentions(Context context, Long mentionsCount, ArrayList<ResponseGetAllGroupPost.User> mentions){
        TextView tMentions = ((Activity)context).findViewById(R.id.tMentions);
        String s = " " + context.getResources().getString(R.string.mentions) + " "+ mentionsCount;
        tMentions.setText(s);

        RecyclerView recyclerViewCommentators = ((Activity)context).findViewById(R.id.recyclerViewMentions);
        recyclerViewCommentators.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterC = new UsersBlockRecyclerView(context, mentions);
        recyclerViewCommentators.setAdapter(adapterC);

    }

    public void showReposts(Context context, Long repostsCount, ArrayList<ResponseGetAllGroupPost.User> reposts){
        TextView tReposts = ((Activity)context).findViewById(R.id.tReposts);
        String s = " " + context.getResources().getString(R.string.reposts) + " "+ repostsCount;
        tReposts.setText(s);

        RecyclerView recyclerViewCommentators = ((Activity)context).findViewById(R.id.recyclerViewReposts);
        recyclerViewCommentators.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        UsersBlockRecyclerView adapterR = new UsersBlockRecyclerView(context, reposts);
        recyclerViewCommentators.setAdapter(adapterR);
    }

    public void showBookmarks(Context context,Long bookmarksCount ){

        TextView tBookmark = ((Activity)context).findViewById(R.id.tBookmarks);
        String s = " " + context.getResources().getString(R.string.bookmarks) + " "+ bookmarksCount;
        tBookmark.setText(s);
    }

}
