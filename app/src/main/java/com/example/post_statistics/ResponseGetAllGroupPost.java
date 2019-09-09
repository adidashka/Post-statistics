package com.example.post_statistics;

import java.util.ArrayList;

public class ResponseGetAllGroupPost {
    public ArrayList<User> data;
    public Meta meta;



    public class User {
        public Long id;
        public String nickname;
        public AvatarImage avatar_image;

        public String getNickname() {
            return nickname;
        }

        public String getAvatarImageUrl() {
            return avatar_image.url_medium_origin;
        }
    }

    public class AvatarImage {
        public String url_small;
        public String url_origin;
        public String url_medium_origin;
        public String url_small_origin;
    }

    public class Meta {
        public long total;
    }


}
