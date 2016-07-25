package com.gyngro.vanbora;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Alexandre Lara on 01/07/2016.
 */

public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected View mView;
    protected Context mContext;

    public GroupViewHolder( View groupItemView ){
        super(groupItemView);
        mView = groupItemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindGroup( Group group ){
        TextView nameTextView = (TextView) mView.findViewById(R.id.title);
        TextView nameTextView2 = (TextView)  mView.findViewById(R.id.title2);

        nameTextView.setText(group.getName());
        nameTextView2.setText(group.getShift());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Group> groups = new ArrayList<>();
        DatabaseReference groupsRef = FirebaseDatabase.getInstance()
                .getReference("groups");
        groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    groups.add(snapshot.getValue(Group.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, GroupMainActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("groups", groups);

                Log.v("E_VALUE", groups.get(itemPosition).getMembers().toString());

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
