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
public final class Business 
{
    private final int bID;
    private final String Name;
    private final String Address;
    private final String City;
    private final String State;
    private final String Zip;
    private final float Latitude;
    private final float Longitude;
    
    public Business(int bID, String Name, String Address, String City, String State, 
    String Zip, float Latitude, float Longitude)
    {
        this.bID = bID;
        this.Name = Name;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
        this.Latitude = Latitude;
        this.Longitude = Longitude;          
    }
    
    public int getBusinessID()
    {
        return bID;    
    }
    
    public String getName()
    {
        return Name;
    }
    
    public String getAddress()
    {
        return Address;
    }
    
    public String getCity()
    {
        return City;
    }
    
    public String getState()
    {
        return State;
    }    
    
    public String getZIP()
    {
        return Zip;
    }
    
    public float getLat()
    {
        return Latitude;
    }
    
    public float getLong()
    {
        return Longitude;
    }
}
