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
    private final String category;
    
    public Business(int bID, String Name, String Address, String City, String State, 
    String Zip, String category)
    {
        this.bID = bID;
        this.Name = Name;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Zip = Zip;     
        this.category = category;
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

	public int getbID() {
		return bID;
	}

	public String getZip() {
		return Zip;
	}

	public String getCategory() {
		return category;
	}
}
