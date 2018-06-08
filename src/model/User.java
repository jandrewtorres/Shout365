
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author MDA
 */
public final class User 
{
	
	private String username;
	private Date joined;
	private String email;
	private String password;
	private final int uid;
    
    public User(int uid, String username, Date joined, String email, String password) 
    {
    	this.uid = uid;
    	this.username = username;
    	this.joined = joined;
    	this.email = email;
    	this.password = password;
    }

	public String getUsername() {
		return username;
	}

	public Date getJoined() {
		return joined;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getUid() {
		return uid;
	}
}
