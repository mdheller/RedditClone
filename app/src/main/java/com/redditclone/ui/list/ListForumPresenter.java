package com.redditclone.ui.list;

import android.app.Application;

import com.redditclone.data.remote.ForumInteractor;
import com.redditclone.ui.base.BasePresenter;
import com.redditclone.util.Logger;

/**
 * @author Tosin Onikute.
 */

public class ListForumPresenter extends BasePresenter<ListForumView> {

    private final Application application;
    private Logger logger = Logger.getLogger(getClass());

    ForumInteractor forumInteractor;


    public ListForumPresenter(Application application, ForumInteractor forumInteractor) {
        this.application = application;
        this.forumInteractor = forumInteractor;
    }

    @Override
    public void attachView(ListForumView listForumView){
        super.attachView(listForumView);
    }

    @Override
    public void detachView(){
        super.detachView();
    }




}
