package com.redditclone.data.remote;

import android.app.Application;

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
}
