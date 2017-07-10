package com.redditclone.di.module;

import android.app.Application;

import com.redditclone.data.remote.ForumInteractor;
import com.redditclone.data.remote.ForumInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    ForumInteractor provideDataManager(ForumInteractorImpl appDataManager) {
        return appDataManager;
    }
}