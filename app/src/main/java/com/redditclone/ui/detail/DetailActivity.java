package com.redditclone.ui.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditclone.BaseApplication;
import com.redditclone.R;
import com.redditclone.data.model.Forum;
import com.redditclone.di.component.ForumComponent;
import com.redditclone.ui.base.BaseActivity;
import com.redditclone.util.ui.MaterialProgressBar;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity implements DetailForumView {


    private int position = 0;
    private ArrayList<Forum> listItem;
    private TextView title, description;
    private ImageView upvoteButton;
    private ImageView downvoteButton;
    private TextView upvoteText;
    private TextView downvoteText;

    @Override
    protected void setupActivity(ForumComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);
        ((BaseApplication) getApplication()).getComponent().inject(this);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
            listItem = (ArrayList<Forum>) getIntent().getSerializableExtra("mForum");
            if(listItem != null) {
                loadView();
            }
        }

    }

    // Initialize the view
    public void init() {
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        upvoteButton = (ImageView) findViewById(R.id.upvote_button);
        downvoteButton = (ImageView) findViewById(R.id.downvote_button);
        upvoteText = (TextView) findViewById(R.id.upvote_text);
        downvoteText = (TextView) findViewById(R.id.downvote_text);
    }

    public void loadView(){
        String ctitle = "";
        String cDesc = "";

        if(listItem.get(position).getPostTitle() != null) ctitle = listItem.get(position).getPostTitle();
        if(listItem.get(position).getPostDesc() != null) cDesc = listItem.get(position).getPostDesc();

        title.setText(ctitle);
        description.setText(cDesc);
        upvoteText.setText(String.valueOf(listItem.get(position).getUpvotes()));
        downvoteText.setText(String.valueOf(listItem.get(position).getDownvotes()));

        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(upvoteText.getText().toString());
                num = num + 1;
                upvoteText.setText(String.valueOf(num));
                ((BaseApplication) getApplication()).getForum().get(position).setUpvotes(num);
            }
        });


        downvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(downvoteText.getText().toString());
                num = num + 1;
                downvoteText.setText(String.valueOf(num));
                ((BaseApplication) getApplication()).getForum().get(position).setDownvotes(num);

            }
        });
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
