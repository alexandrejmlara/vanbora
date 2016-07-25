package com.gyngro.vanbora;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Alexandre Lara on 01/07/2016.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {

    protected View mView;
    protected Context mContext;

    public EventViewHolder(View eventItemView) {
        super(eventItemView);
        mView = eventItemView;
        mContext = itemView.getContext();
    }

    public void bindEvent( Event event ){
        TextView nameTextView = (TextView) mView.findViewById(R.id.eventNameTextView);

        nameTextView.setText(event.getMessage());
    }

}
