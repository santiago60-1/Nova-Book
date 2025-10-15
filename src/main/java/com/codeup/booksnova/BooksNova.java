/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.codeup.booksnova;

import com.codeup.booksnova.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

/**
 *
 * @author Coder
 */
public class BooksNova {

    public static void main(String[] args) {
        System.out.println("🔍 Testing database connection...");

        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("✅ Connected!");
                System.out.println("   URL     : " + meta.getURL());
                System.out.println("   Driver  : " + meta.getDriverName());
                System.out.println("   User    : " + meta.getUserName());
            } else {
                System.err.println("❌ Connection is null or closed.");
            }
        } catch (Exception e) {
            System.err.println("❌ Could not connect to the database.");
            e.printStackTrace();
        }
    }
    
    
}
