package model;

import java.util.Calendar;

/**
 *
 * @author MDA
 */
public final class Review 
{
    public final int rid;
    public final int bid;
    public final int uid;
    public final int stars;
    public final int usefulness;
    public final Calendar date;
    
    public Review(int rid, int bid, int uid, int stars, int usefulness, 
            Calendar date)
    {
        this.rid = rid;
        this.bid = bid;
        this.uid = uid;
        this.stars = stars;
        this.usefulness = usefulness;
        this.date = date;
    }
}
