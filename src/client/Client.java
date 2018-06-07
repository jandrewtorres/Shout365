/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jtorres
 */
public class Client extends Application {
    
    
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
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        try {
            String strSshUser = ""; // SSH loging username Whatever your user is
            String strSshPassword = ""; // SSH login password Whatever your password is 
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
            
            Statement stmt = con.createStatement();
            String sql;
            sql = "SELECT * from business";
            // Is a set of tuples
            ResultSet rs = stmt.executeQuery(sql);
            
            // rs.next to advance to the next tuple ( each row returned) in the set.
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("bid");
                String name = rs.getString("name");
                String city = rs.getString("city");

                //Display values
                System.out.println("ID: " + id);
                System.out.println("name: " + name);
                System.out.println("city :" + city);

             }
            
            con.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
        System.out.println("LOL");
        launch(args);
    }
    
}