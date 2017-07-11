package com.redditclone.ui.post;

import android.app.Application;

import com.redditclone.data.local.ForumInteractor;
import com.redditclone.data.model.Forum;
import com.redditclone.ui.base.BasePresenter;
import com.redditclone.util.Logger;

import java.util.ArrayList;

/**
 * @author Tosin Onikute.
 */

public class PostPresenter extends BasePresenter<PostView> {

    private final Application application;
    private Logger logger = Logger.getLogger(getClass());

    ForumInteractor forumInteractor;


    public PostPresenter(Application application, ForumInteractor forumInteractor) {
        this.application = application;
        this.forumInteractor = forumInteractor;
    }

    @Override
    public void attachView(PostView postView){
        super.attachView(postView);
    }

    @Override
    public void detachView(){
        super.detachView();
    }

    public void addNewPost(Forum forum, ArrayList<Forum> forumList, String title, String desc){
        forumInteractor.addNewPost(forum, forumList, title, desc);
        getMvpView().setFieldEmpty();
        getMvpView().successMsg();
    }




}
