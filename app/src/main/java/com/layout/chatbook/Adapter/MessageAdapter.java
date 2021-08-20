package com.layout.chatbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.layout.chatbook.Model.Message;
import com.layout.chatbook.R;
import com.layout.chatbook.databinding.ItemReciveBinding;
import com.layout.chatbook.databinding.ItemSendBinding;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {

    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;
    Context context;
    ArrayList<Message> messageArrayList;

    public MessageAdapter(Context context, ArrayList<Message> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_SENT) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_send, parent, false);
            return new sentViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recive, parent, false);
            return new receiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        Message message = messageArrayList.get(position);
        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderId())) {
            return ITEM_SENT;
        } else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Message message = messageArrayList.get(position);
        if (holder.getClass() == sentViewHolder.class) {
            sentViewHolder viewHolder = (sentViewHolder) holder;
            viewHolder.binding.messages.setText(message.getMessage());
        } else {
            receiverViewHolder viewHolder = (receiverViewHolder) holder;
            viewHolder.binding.messages.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public class sentViewHolder extends RecyclerView.ViewHolder {

        ItemSendBinding binding;

        public sentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSendBinding.bind(itemView);
        }
    }

    public class receiverViewHolder extends RecyclerView.ViewHolder {

        ItemReciveBinding binding;

        public receiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReciveBinding.bind(itemView);
        }
    }
}
