package model;

/**
 *
 * @author MDA
 */
public final class Business 
{
    public final int bID;
    public final String name;
    public final String address;
    public final String city;
    public final String state;
    public final String zip;
    public final float latitude;
    public final float longitude;
    
    public Business(int bID, String name, String address, String city, String state, 
    String zip, float latitude, float longitude)
    {
        this.bID = bID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;          
    }
}
