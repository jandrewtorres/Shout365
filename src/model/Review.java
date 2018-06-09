/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

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
	private Timestamp date;
	private String comments;
    
    
    public Review(int rid, int bid, int uid, int stars, Timestamp date, String comments)
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

	public Timestamp getDate() {
		return date;
	}

	public String getComments() {
		return comments;
	}
    
    
}
