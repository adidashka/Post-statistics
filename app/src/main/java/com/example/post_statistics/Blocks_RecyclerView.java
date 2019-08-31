package com.example.post_statistics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Blocks_RecyclerView extends RecyclerView.Adapter {
    private List<Block> myBlocks;

    public Blocks_RecyclerView(Context context, List<Block> myBlocks) {
        this.myBlocks = myBlocks;
    }

    @Override
    public int getItemViewType(int position) {
        return myBlocks.get(position).isBlockWithUsers();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int isBlockWithUsers) {
        if (isBlockWithUsers == Block.BLOCK_WITH_USERS){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.block_with_users, parent, false);
            return new Block_with_users_ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.block_without_users, parent, false);
            return new Block_without_users_ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Block mBlock = myBlocks.get(position);
            ((Block_without_users_ViewHolder) holder).bind(mBlock);
            ((Block_with_users_ViewHolder) holder).bind(mBlock);
    }

    @Override
    public int getItemCount() {
        return myBlocks.size();
    }

    public class Block_with_users_ViewHolder extends RecyclerView.ViewHolder{

        public Block_with_users_ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind (final Block block){
            TextView tV_block_name_and_count = (TextView) itemView.findViewById(R.id.block_name_and_count_with_users);

            String blockNameAndCount = block.getName() + " " + block.getCount();

            tV_block_name_and_count.setText(blockNameAndCount);
        }
    }

    public class Block_without_users_ViewHolder extends RecyclerView.ViewHolder{

        public Block_without_users_ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind (final Block block){
            TextView tV_block_name_and_count = (TextView) itemView.findViewById(R.id.block_name_and_count_without_users);

            String blockNameAndCount = block.getName() + " " + block.getCount();

            tV_block_name_and_count.setText(blockNameAndCount);
        }
    }
}
