package com.redditclone.di.module;

import android.app.Application;

import com.redditclone.data.remote.ForumInteractor;
import com.redditclone.data.remote.ForumInteractorImpl;
import com.redditclone.ui.list.ListForumPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class ForumModule {

    private Application application;

    public ForumModule(Application application){
        this.application = application;
    }

    @Provides
    public ListForumPresenter getHelloPresenter(ForumInteractor forumInteractor){
        return new ListForumPresenter(application, forumInteractor);
    }


    @Provides
    ForumInteractor provideHelloFetcher() {
        return new ForumInteractorImpl( application );
    }



}
