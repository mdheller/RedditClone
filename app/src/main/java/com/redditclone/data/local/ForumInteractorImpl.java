package com.redditclone.data.local;

import android.app.Application;

import com.redditclone.BaseApplication;
import com.redditclone.data.model.Forum;

import java.util.ArrayList;

/**
 * @author Tosin Onikute.
 *
 * This is a Data Manager implementer class which contains methods, exposed for all the Forum related data handling operations
 * to decouple your class, thus making it cleaner and testable
 *
 */

public class ForumInteractorImpl implements ForumInteractor {

    private final Application application;

    public ForumInteractorImpl(Application application) {
        this.application = application;
    }

    public void addNewPost(Forum forum, ArrayList<Forum> forumList,String title, String desc){
        forum = new Forum();
        forum.setPostTitle(title);
        forum.setPostDesc(desc);
        forumList.add(forum);
        ((BaseApplication) application).setForum(forumList);
    }


    public void editPost(int position,String title, String desc){
        ((BaseApplication) application).getForum().get(position).setPostTitle(title);
        ((BaseApplication) application).getForum().get(position).setPostDesc(desc);
    }


}
