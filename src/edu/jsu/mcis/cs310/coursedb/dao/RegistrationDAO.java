package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
            
                int affectedRows = ps.executeUpdate();
            
                result = (affectedRows > 0);
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        final String QUERY_DELETE = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
    
    try {
        Connection conn = daoFactory.getConnection();
        
        if (conn != null && conn.isValid(0)) {
            
            ps = conn.prepareStatement(QUERY_DELETE);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            
            int affectedRows = ps.executeUpdate();
            
            result = (affectedRows > 0); 
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        final String QUERY_DELETE_ALL = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
    
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(QUERY_DELETE_ALL);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            
            int affectedRows = ps.executeUpdate();
            
            result = (affectedRows > 0);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        final String QUERY_LIST = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
        
        String JSONresult = "";
        JsonArray jsonArray = new JsonArray();
        
        
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(QUERY_LIST);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
            
                rs = ps.executeQuery();
                
                    while (rs.next()){
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.put("STU", String.valueOf(studentid));
                        jsonObject.put("TID", String.valueOf(termid));
                        jsonArray.add(jsonObject);
                    }                        
                    jsonArray.toString();
                        
           
                
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return JSONresult;
        
    }
    
}
