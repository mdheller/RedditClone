package com.redditclone.ui.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.redditclone.BaseApplication;
import com.redditclone.R;
import com.redditclone.data.model.Forum;
import com.redditclone.di.component.ForumComponent;
import com.redditclone.ui.base.BaseActivity;
import com.redditclone.ui.list.ListForumActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class PostActivity extends BaseActivity implements PostView {

    @Inject
    PostPresenter presenter;

    private EditText title, description;
    private LinearLayout mainLayout;
    private Button submitButton;
    private Forum forum;
    private ArrayList<Forum> listPost;
    private Snackbar msg;

    @Override
    protected void setupActivity(ForumComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_post);
        ((BaseApplication) getApplication()).getComponent().inject(this);
        presenter.attachView(this);


        listPost = ((BaseApplication) getApplication()).getForum();
        if(listPost == null){
            listPost = new ArrayList<Forum>();
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    // Initialize the view
    public void init() {
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validation()) {
                    forum = new Forum();
                    presenter.addNewPost(forum, listPost, title.getText().toString(), description.getText().toString());
                }

            }
        });

    }


    public void successMsg(){
        Toast.makeText(getApplicationContext(), "Topic Created Sucessfully", Toast.LENGTH_SHORT).show();
        openListActivity();
    }

    public void openListActivity() {
        Intent intent = new Intent(this, ListForumActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


    public boolean validation(){
        if(title.getText().toString().equals("")){
            snackMsg("Please enter a title");
        } else if(description.getText().toString().equals("")){
            snackMsg("Please enter a description");
        } else {
            return true;
        }
        return false;
    }

    public void snackMsg(String message) {
        msg = Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT);
        TextView snackbarText = (TextView) msg.getView().findViewById(android.support.design.R.id.snackbar_text);
        snackbarText.setTextColor(getApplicationContext().getResources().getColor(android.R.color.white));
        msg.show();
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
