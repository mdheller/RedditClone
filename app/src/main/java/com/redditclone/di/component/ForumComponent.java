package com.redditclone.di.component;

import com.redditclone.di.module.ForumFetcherModule;
import com.redditclone.di.module.ForumModule;
import com.redditclone.di.scope.UserScope;
import com.redditclone.ui.list.ListForumActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */


@UserScope
@Component(dependencies = NetComponent.class, modules = {ForumModule.class, ForumFetcherModule.class})
public interface ForumComponent {

    void inject(ListForumActivity listForumActivity);

}

