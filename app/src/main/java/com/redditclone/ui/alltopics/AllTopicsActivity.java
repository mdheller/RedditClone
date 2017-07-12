package com.redditclone.ui.alltopics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redditclone.BaseApplication;
import com.redditclone.R;
import com.redditclone.data.model.Forum;
import com.redditclone.di.component.ForumComponent;
import com.redditclone.ui.base.BaseActivity;
import com.redditclone.ui.post.PostActivity;
import com.redditclone.util.Logger;
import com.redditclone.util.ui.MaterialProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AllTopicsActivity extends BaseActivity implements AllTopicsView {

    private Logger logger = Logger.getLogger(getClass());
    private RelativeLayout mainLayout;
    private AllForumListAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private TextView noPostYet;
    private TextView topTopics;

    private ArrayList<Forum> listPost;


    @Override
    protected void setupActivity(ForumComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_topics);
        ((BaseApplication) getApplication()).getComponent().inject(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    // Initialize the view
    public void init() {
        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        topTopics = (TextView) findViewById(R.id.top_topics);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        noPostYet = (TextView) findViewById(R.id.no_post_yet);
        recyclerView = (RecyclerView) findViewById(R.id.forum_list_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.VISIBLE);

    }


    public void setAdapter(ArrayList<Forum> forumArrayList){
        progressBar.setVisibility(View.GONE);
        if(forumArrayList != null && forumArrayList.size() > 0) {
            topTopics.setText("All Topics");
            noPostYet.setVisibility(View.GONE);
            adapter = new AllForumListAdapter(getApplicationContext(), forumArrayList);
            recyclerView.setAdapter(adapter); // set adapter on recyclerview
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        listPost = ((BaseApplication) getApplication()).getForum();

        if(listPost != null) {
            /*
            Sort using Integer signsum function
             */
            Collections.sort(listPost, new Comparator<Forum>() {
                @Override
                public int compare(Forum f1,Forum f2) {
                    return Integer.signum(f2.getUpvotes() - f1.getUpvotes());
                }
            });

        }

        setAdapter(listPost);

    }


    public void launchPostActivity(View view){
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }


    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_forum_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
