
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author MDA
 */
public final class User 
{
	
	private String username;
	private Timestamp joined;
	private String email;
	private String password;
	private final int uid;
	private Timestamp lastAccess;
	
    public User(int uid, String username, Timestamp joined, String email, String password, Timestamp lastAccess) 
    {
    	this.uid = uid;
    	this.username = username;
    	this.joined = joined;
    	this.email = email;
    	this.password = password;
    	this.lastAccess = lastAccess;
    }

	public String getUsername() {
		return username;
	}

	public Timestamp getJoined() {
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
	
	public Timestamp getLastAccess() {
		return lastAccess;
	}
}
