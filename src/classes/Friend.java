/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author MDA
 */
public final class Friend 
{
    private int ID;
    private int uID;
    private int fID;
    
    public Friend(int ID, int uID, int fID)
    {
        this.ID = ID;
        this.uID = uID;
        this.fID = fID;
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
