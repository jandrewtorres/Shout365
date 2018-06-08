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
public final class Category 
{
    private int cID;
    private int bID;
    private String Category;
    
    public Category(int cID, int bID, String Category)
    {
        this.cID = cID;
        this.bID = bID;
        this.Category = Category;
        
    }
    
    public int categoryID()
    {
        return cID;
    }
    
    public int businessID()
    {
        return bID;
    }
    
    public String getCategory()
    {
        return Category;
    }
    
    
}
