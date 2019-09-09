package com.example.post_statistics;

import android.content.Context;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class UsersBlockRecyclerView extends RecyclerView.Adapter {
    private List<ResponseGetAllGroupPost.User> myUsers;
    private Context context;

    public UsersBlockRecyclerView(Context context, List<ResponseGetAllGroupPost.User> myUsers) {
        this.myUsers = myUsers;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int isBlockWithUsers) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_block, parent, false);
            return new UsersBlockViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResponseGetAllGroupPost.User mUser = myUsers.get(position);
        ((UsersBlockViewHolder) holder).bind(mUser);
    }

    @Override
    public int getItemCount() {
        return myUsers.size();
    }

    public class UsersBlockViewHolder extends RecyclerView.ViewHolder{

        public UsersBlockViewHolder(View itemView) {
            super(itemView);
        }

        public void bind (final ResponseGetAllGroupPost.User user){
            TextView tNickname =  itemView.findViewById(R.id.nickname);
            String nickname = user.getNickname();
            tNickname.setText(nickname);

            LinearLayout llImg = itemView.findViewById(R.id.llImg);

            llImg.getLayoutParams().width = getScreenWidht(context)/6;
            llImg.getLayoutParams().height = getScreenWidht(context)/6;

            ImageView avatar = itemView.findViewById(R.id.avatar);

            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();
            Picasso.with(context)
                    .load(user.getAvatarImageUrl())
                    .transform(transformation)
                    .into(avatar);
        }
    }

    public static int getScreenWidht (Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int screenWidth = size.x;
        return screenWidth;
    }

}
