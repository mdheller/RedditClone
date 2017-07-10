package com.redditclone;

import android.app.Application;

import com.redditclone.di.component.DaggerForumComponent;
import com.redditclone.di.component.DaggerNetComponent;
import com.redditclone.di.component.ForumComponent;
import com.redditclone.di.component.NetComponent;
import com.redditclone.di.module.AppModule;
import com.redditclone.di.module.ForumFetcherModule;
import com.redditclone.di.module.ForumModule;


/**
 * @author Tosin Onikute.
 */

public class BaseApplication extends Application {

    public ForumComponent component;
    private NetComponent mNetComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .build();

        component = DaggerForumComponent
                .builder()
                .netComponent(mNetComponent)
                .forumModule(new ForumModule(this))
                .forumFetcherModule(new ForumFetcherModule(this))
                .build();

    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public ForumComponent getComponent() {
        return component;
    }

}
