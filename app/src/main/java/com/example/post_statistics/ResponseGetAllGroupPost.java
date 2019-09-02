package com.example.post_statistics;

import java.util.ArrayList;

public class ResponseGetAllGroupPost {
    public ArrayList<User> data;
    public Meta meta;



    public class User {
        public Long id;
        public String nickname;
        public AvatarImage avatar_image;
    }

    public class AvatarImage {
        public String url_small;
    }

    public class Meta {
        public long total;
    }
}
