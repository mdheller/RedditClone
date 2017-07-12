package com.redditclone.ui.post;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.redditclone.BaseApplication;
import com.redditclone.R;
import com.redditclone.data.model.Forum;
import com.redditclone.di.component.ForumComponent;
import com.redditclone.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class PostActivity extends BaseActivity implements PostView {

    @Inject
    PostPresenter presenter;

    private EditText title, description;
    private Button submitButton;
    private Forum forum;
    private ArrayList<Forum> listPost;

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
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                forum = new Forum();
                presenter.addNewPost(forum, listPost, title.getText().toString(), description.getText().toString());

            }
        });

    }

    public void setFieldEmpty(){
        title.setText("");
        description.setText("");
    }

    public void successMsg(){
        Toast.makeText(getApplicationContext(), "Post Created Sucessfully", Toast.LENGTH_SHORT).show();
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
