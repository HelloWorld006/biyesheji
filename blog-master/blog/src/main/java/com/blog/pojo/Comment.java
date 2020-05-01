package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private boolean adminComment;  //是否为管理员评论

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;  //父评论id
    private String parentNickname;

    //回复评论
    //private List<Comment> replyComments = new ArrayList<>();

    //父评论
    private Comment parentComment;

    private Blog blog;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public Long getId() {
        return id;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public void setParentCommentId(long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
