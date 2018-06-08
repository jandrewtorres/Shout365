package model;

import java.util.Calendar;

/**
 *
 * @author A. Chow
 */
public final class User 
{
    public final int uid;
    public final String name;
    public final Calendar date;
    
    public User(int uid, String name, Calendar date)
    {
        this.uid = uid;
        this.name = name;
        this.date = date;
    }
}
