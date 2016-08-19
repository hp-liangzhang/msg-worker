package com.marykay.community.repository;

import com.marykay.community.common.MyLogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by zhujohnny11 on 2016/8/18.
 */
public class MessageSqlHelper {

    private String dbConnectString;

    public String getDbConnectString(){
        return this.dbConnectString;
    }

    public  void setDbConnectString(String dbConnectString){
        this.dbConnectString = dbConnectString;
    }

    public void UpdateMessageStatus(String messageId){
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String dbConnectStr = String.format("current DbConnectStr=%s",this.getDbConnectString());
            MyLogManager.LogInfo(dbConnectStr);
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(this.getDbConnectString());
            // Create and execute an SQL statement that returns some data.
            String SQL = String.format("SELECT * FROM mkc_Message_MessageLog where MessageId='%s'", messageId);
            //String SQL = "SELECT * FROM mkc_Message_MessageLog where MessageId='"+messageId+"'";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            rs.last();
            int rowNo =  rs.getRow();
            if(rowNo>0){
                rs.updateString("Status","Completed");
                java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String s = format1.format(new Date());
                rs.updateString("UpdateDateTime", s);
                rs.updateRow();
                MyLogManager.LogInfo("the current Messageid="+messageId+" was updated successfully!");
            }
            else {
                MyLogManager.LogInfo("the current Messageid was not found in database");
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            MyLogManager.ErrorInfo(e.getMessage()+"||"+e.getStackTrace().toString()+"||"+e.getClass().getName());
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}
