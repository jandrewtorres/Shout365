/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MDA
 */
public final class Friend 
{
    private int ID;
    private int uID;
    private int fID;
    private String fName;
    private String uName;
    
    public Friend(int ID, int uID, int fID, String fName, String uName)
    {
        this.ID = ID;
        this.uID = uID;
        this.fID = fID;
        this.fName = fName;
        this.uName = uName;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public int getUserID()
    {
        return uID;
    }
    
    public int getFriendID()
    {
        return fID;
    }
}
