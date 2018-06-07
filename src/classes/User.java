/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Calendar;

/**
 *
 * @author MDA
 */
public final class User 
{
    private int uID;
    private String Name;
    
    //Again don't know DateTIME class in SQL
    private Calendar date;
    
    public User(int uID, String Name)
    {
        this.uID = uID;
        this.Name = Name;
    }
    
    public int getUserID()
    {
        return uID;
    }
    
    public String getName()
    {
        return Name;
    }
}
