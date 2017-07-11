package com.redditclone.ui.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.redditclone.BaseApplication;
import com.redditclone.R;
import com.redditclone.di.component.ForumComponent;
import com.redditclone.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity implements DetailForumView {



    @Override
    protected void setupActivity(ForumComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);
        ((BaseApplication) getApplication()).getComponent().inject(this);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    // Initialize the view
    public void init() {

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
