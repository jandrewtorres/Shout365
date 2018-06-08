/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import gui.SplashScreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author jtorres
 */
public class Client {
    
    public static Connection CONNECTION = null;
	
	
    private static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort,
            String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
        final JSch jsch = new JSch();
        Session session = jsch.getSession(strSshUser, strSshHost, 22);
        session.setPassword(strSshPassword);

        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();
        session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
    }
 
    /**
     * @param args the command line arguments
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException 
    {
        
        try {
            String strSshUser = "nmfrench"; // SSH loging username Whatever your user is
            String strSshPassword = "1997Ss77tg12!"; // SSH login password Whatever your password is 
            String strSshHost = "unix2.csc.calpoly.edu"; // hostname or ip or
                                                            // SSH server
            int nSshPort = 22; // remote SSH host port number
            String strRemoteHost = "ambari-head.csc.calpoly.edu"; // hostname or
                                                                    // ip of
                                                                    // your
                                                                    // database
                                                                    // server
            int nLocalPort = 3366; // local port number use to bind SSH tunnel
            int nRemotePort = 3306; // remote port number of your database
            String strDbUser = "shout"; // database loging username
            String strDbPassword = "shout"; // database login password

            Client.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort,
                    nRemotePort);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + nLocalPort +
                    "/shout?useLegacyDatetimeCode=false&serverTimezone=UTC", strDbUser,
                    strDbPassword);
            
            // this is probably a terrible way to do this but who cares
            CONNECTION = con; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.show();
    }
    
    public static Connection getConnection() {
    	// we could do a null check or something but let's assume we could connect
    	return CONNECTION;
    }
    
}