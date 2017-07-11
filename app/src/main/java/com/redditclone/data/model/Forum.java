package com.redditclone.data.model;

/**
 * @author Tosin Onikute.
 */

public class Forum implements java.io.Serializable {

    private String postTitle;
    private String postDesc;

    public String getPostTitle(){
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDesc(){
        return postDesc;
    }

    public void setPostDesc(String postDesc){
        this.postDesc = postDesc;
    }

}
