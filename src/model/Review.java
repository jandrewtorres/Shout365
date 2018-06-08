/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author MDA
 */
public final class Review 
{
    private final int rID;
    private final int bID;
    private final int uID;
    private final int stars;
	private Date date;
	private String comments;
    
    
    public Review(int rid, int bid, int uid, int stars, Date date, String comments)
    {
        this.rID = rid;
        this.bID = bid;
        this.uID = uid;
        this.stars = stars;
        this.date = date;
        this.comments = comments;
    }
    
    public int getReviewID()
    {
        return rID;
    }
    
    public int getBusinessID()
    {
        return bID;   
    }
    
    public int getUserID()
    {
        return uID;
    }
    
    public int getStars()
    {
      return stars;   
    }

	public Date getDate() {
		return date;
	}

	public String getComments() {
		return comments;
	}
    
    
}
