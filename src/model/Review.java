/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

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
    private final int usefulness;
    
    // Not sure how DateTime is done inside of mySQL So not touching it yet.
    public Calendar date;
    
    public Review(int rid, int bid, int uid, int stars, int usefulness)
    {
        this.rID = rid;
        this.bID = bid;
        this.uID = uid;
        this.stars = stars;
        this.usefulness = usefulness;
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
    
    public int getUsefulness()
    {
        return usefulness;
    }
    
    
}
