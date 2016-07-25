package com.gyngro.vanbora;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupsListActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;

    @BindView(android.R.id.content)
    View mRootView;

    @BindView(R.id.menuExit)
    MenuItem btnSignOut;

    @BindView(R.id.menuNewGroup)
    MenuItem btnAddNewGroup;


    private DatabaseReference mUsersRef;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    protected RecyclerView mRecyclerView;
    private MaterialViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.groupListToolbar);
        setSupportActionBar(toolbar);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.rView);
        mUsersRef = FirebaseDatabase.getInstance().getReference("groups");

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Group, GroupViewHolder>(
                Group.class,
                R.layout.layout_group_item,
                GroupViewHolder.class,
                mUsersRef
        ) {
            @Override
            protected void populateViewHolder(GroupViewHolder viewHolder, Group model, int position) {
                viewHolder.bindGroup(model);
            }
        };


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuNewGroup) {
            addNewGroup();
            return true;
        } else if(id == R.id.menuConfigurations){
            return true;
        } else if(id == R.id.menuExit){
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();


    }

    public static Intent createIntent(Context context){
        Intent in = new Intent();

        in.setClass(context, GroupsListActivity.class);

        return in;
    }

    @OnClick(R.id.menuNewGroup)
    public void addNewGroup(){
        Intent intent = new Intent(getApplicationContext(), NewGroupActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
    }

    @OnClick(R.id.menuExit)
    public void signOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(MainScreenActivity.createIntent(GroupsListActivity.this));
                            finish();
                        } else {
                            showSnackbar(R.string.sign_out_failed);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically

            }
        }
    }


    @MainThread
    private void showSnackbar(@StringRes int errorMessageRes) {
        Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
