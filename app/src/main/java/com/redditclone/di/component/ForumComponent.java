package com.redditclone.di.component;

import com.redditclone.di.module.ForumFetcherModule;
import com.redditclone.di.module.ForumModule;
import com.redditclone.di.scope.UserScope;
import com.redditclone.ui.detail.DetailActivity;
import com.redditclone.ui.edit.EditPostActivity;
import com.redditclone.ui.list.ListForumActivity;
import com.redditclone.ui.post.PostActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */


@UserScope
@Component(dependencies = NetComponent.class, modules = {ForumModule.class, ForumFetcherModule.class})
public interface ForumComponent {

    void inject(ListForumActivity listForumActivity);
    void inject(PostActivity postActivity);
    void inject(DetailActivity detailActivity);
    void inject(EditPostActivity editPostActivity);

}

