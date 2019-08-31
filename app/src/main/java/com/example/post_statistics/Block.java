package com.example.post_statistics;

import java.util.ArrayList;

public class Block {
    public static final int BLOCK_WITH_USERS = 1;
    public static final int BLOCK_WITHOUT_USERS = 0;


    private Long count;
    private String name;
    private ArrayList<User> users;



    public String getName() {
        return name;
    }

    public Long getCount() {
        return count;
    }



    public Block(Long count, String name, ArrayList<User> users) {
        this.count = count;
        this.name = name;
        this.users = users;
    }

    public int isBlockWithUsers () {
        if (users != null)
            return BLOCK_WITHOUT_USERS;
        else return BLOCK_WITH_USERS;
    }



    public static class User {
        Long id;
        String nickname;
        String url_small_avatar;

        public User(Long id, String nickname, String url_small_avatar) {
            this.id = id;
            this.nickname = nickname;
            this.url_small_avatar = url_small_avatar;
        }
    }

}
