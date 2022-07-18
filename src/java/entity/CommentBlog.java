/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class CommentBlog {
    private int commentID;
    private int userID;
    private int blogID;
    private String userName;
    private String commentDetail;
    private Date dateComment;

    public CommentBlog() {
    }

    public CommentBlog(int commentID, int userID, int blogID, String userName, String commentDetail, Date dateComment) {
        this.commentID = commentID;
        this.userID = userID;
        this.blogID = blogID;
        this.userName = userName;
        this.commentDetail = commentDetail;
        this.dateComment = dateComment;
    }

    public CommentBlog(int userID, int blogID, String userName, String commentDetail, Date dateComment) {
        this.userID = userID;
        this.blogID = blogID;
        this.userName = userName;
        this.commentDetail = commentDetail;
        this.dateComment = dateComment;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    @Override
    public String toString() {
        return "CommentBlog{" + "commentID=" + commentID + ", userID=" + userID + ", blogID=" + blogID + ", userName=" + userName + ", commentDetail=" + commentDetail + ", dateComment=" + dateComment + '}';
    }
    
    
    
    
}
