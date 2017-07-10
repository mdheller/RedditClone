package com.redditclone.di.module;

import android.app.Application;

import dagger.Module;

/**
 * @author Tosin Onikute.
 */

@Module
public class ForumFetcherModule {

    private Application application;

    public ForumFetcherModule(Application application){
        this.application = application;
    }

}
