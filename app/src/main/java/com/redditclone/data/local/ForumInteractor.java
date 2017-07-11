package com.redditclone.data.local;

import com.redditclone.data.model.Forum;

import java.util.ArrayList;

/**
 * @author Tosin Onikute.
 *
 * ForumInteractor is an interface that is implemented by the ForumInteractorImpl Data Manager
 *
 */


public interface ForumInteractor {

    void addNewPost(Forum forum, ArrayList<Forum> forumList, String title, String desc);

}
