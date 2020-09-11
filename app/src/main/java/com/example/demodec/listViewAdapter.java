package com.example.demodec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listViewAdapter extends RecyclerView.Adapter<listViewAdapter.listViewHolder> {

    private Context mContext;
    private ArrayList<listItem> mItemList;

    public listViewAdapter(Context context, ArrayList<listItem> itemList){
        mContext=context;
        mItemList=itemList;
    }



    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new listViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
    listItem currentItem = mItemList.get(position);
    String eventName = currentItem.getEventName();

    holder.mEvent.setText(eventName);}

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class listViewHolder extends RecyclerView.ViewHolder {
        public TextView mEvent;
        public listViewHolder(@NonNull View itemView) {

            super(itemView);
            mEvent = itemView.findViewById(R.id.text_event);
        }
    }
}
