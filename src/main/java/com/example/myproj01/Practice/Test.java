package com.example.myproj01.Practice;

import com.example.myproj01.JClass.Functions;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        Connection con= Functions.getCon("mysales");
        try {
//            PreparedStatement pstmt = con.prepareStatement("select * from chess where playername like ?");
//            pstmt.setString(1,"%徐%");
//            ResultSet rs = pstmt.executeQuery();
//            System.out.println(Functions.RsToJson(rs));
            CallableStatement cstmt = con.prepareCall("{call demo0001(?)}");
            cstmt.setString(1,"志");
            ResultSet rs = cstmt.executeQuery();
            System.out.println(Functions.RsToJson(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
