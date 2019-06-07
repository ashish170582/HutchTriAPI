/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class KaraokeComment extends Root {

    private int totalComment;
    private List<KaraokeCommentData> commentList;
    public KaraokeComment() {        
        super(0, "Success");
    }
	public KaraokeComment(int totalComment,
			List<KaraokeCommentData> commentList) {
		super(0, "Success");
		this.totalComment = totalComment;
		this.commentList = commentList;
	}
	
	public KaraokeComment(int statusCode,String message,int totalComment,
			List<KaraokeCommentData> commentList) {
		super(statusCode, message);
		this.totalComment = totalComment;
		this.commentList = commentList;
	}
	
	public int getTotalComment() {
		return totalComment;
	}
	public void setTotalComment(int totalComment) {
		this.totalComment = totalComment;
	}

	
	public List<KaraokeCommentData> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<KaraokeCommentData> commentList) {
		this.commentList = commentList;
	}
	
	@Override
	public String toString() {
		return "KaraokeComment [totalComment=" + totalComment
				+ ", commentList=" + commentList + "]";
	}

}
