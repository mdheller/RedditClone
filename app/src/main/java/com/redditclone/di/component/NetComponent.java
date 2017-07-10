package com.redditclone.di.component;

import com.redditclone.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * @author Tosin Onikute.
 */

@Singleton
@Component(modules={AppModule.class})
public interface NetComponent {

    // downstream components need these exposed

}
