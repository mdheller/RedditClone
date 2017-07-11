package com.redditclone.di.module;

import android.app.Application;

import com.redditclone.data.local.ForumInteractor;
import com.redditclone.data.local.ForumInteractorImpl;
import com.redditclone.ui.post.PostPresenter;

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
    public PostPresenter getHelloPresenter(ForumInteractor forumInteractor){
        return new PostPresenter(application, forumInteractor);
    }


    @Provides
    ForumInteractor provideHelloFetcher() {
        return new ForumInteractorImpl( application );
    }



}
