package com.example.post_statistics;

public interface PostStaticsticCallback {

    void onInfoAboutPost(ResponseGetInfoAboutPost response);

    void onAllLikersPost (ResponseGetAllGroupPost response);
    void onAllCommentatorsPost (ResponseGetAllGroupPost response);
    void onAllMentionsPost (ResponseGetAllGroupPost response);
    void onAllAllRepostrsPost (ResponseGetAllGroupPost response);

}
