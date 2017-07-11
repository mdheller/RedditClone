package com.redditclone.data.model;

/**
 * @author Tosin Onikute.
 */

public class Forum implements java.io.Serializable {

    private int id;
    private String postTitle;
    private String postDesc;
    private int upvotes;
    private int downvotes;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getUpvotes(){
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes(){
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

}
