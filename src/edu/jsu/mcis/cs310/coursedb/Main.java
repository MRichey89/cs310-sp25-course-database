package edu.jsu.mcis.cs310.coursedb;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import edu.jsu.mcis.cs310.coursedb.dao.*;

public class Main {
    
    private static final String USERNAME = "nobody@jsu.edu";
    
    public static void main(String[] args) {
        
        // Create DAO Objects
        
        DAOFactory daoFactory = new DAOFactory("coursedb");
        
        RegistrationDAO registrationDao = daoFactory.getRegistrationDAO();
        SectionDAO sectionDao = daoFactory.getSectionDAO();
        
        int studentid = daoFactory.getStudentDAO().find(USERNAME);
        
        // Test Connection
        
        if ( !daoFactory.isClosed() ) {
            
            System.out.println("Connected Successfully!");
            
        }
        
        String s7 = "[{\"termid\":\"1\",\"scheduletypeid\":\"LEC\",\"instructor\":\"Arup Kumar Ghosh\",\"num\":\"230\",\"start\":\"11:15:00\",\"days\":\"MWF\",\"section\":\"001\",\"end\":\"12:15:00\",\"where\":\"Ayers Hall 357\",\"crn\":\"10520\",\"subjectid\":\"CS\"},{\"termid\":\"1\",\"scheduletypeid\":\"LEC\",\"instructor\":\"Keith Bradley Foster\",\"num\":\"230\",\"start\":\"09:15:00\",\"days\":\"TR\",\"section\":\"002\",\"end\":\"10:45:00\",\"where\":\"Ayers Hall 357\",\"crn\":\"10521\",\"subjectid\":\"CS\"},{\"termid\":\"1\",\"scheduletypeid\":\"LEC\",\"instructor\":\"Scarlet Jadzia Maddox\",\"num\":\"230\",\"start\":\"13:45:00\",\"days\":\"MWF\",\"section\":\"003\",\"end\":\"14:45:00\",\"where\":\"Ayers Hall 355\",\"crn\":\"10522\",\"subjectid\":\"CS\"},{\"termid\":\"1\",\"scheduletypeid\":\"LEC\",\"instructor\":\"Scarlet Jadzia Maddox\",\"num\":\"230\",\"start\":\"12:45:00\",\"days\":\"TR\",\"section\":\"004\",\"end\":\"14:15:00\",\"where\":\"Ayers Hall 359\",\"crn\":\"10523\",\"subjectid\":\"CS\"},{\"termid\":\"1\",\"scheduletypeid\":\"ONL\",\"instructor\":\"Keith Bradley Foster\",\"num\":\"230\",\"start\":\"00:00:00\",\"days\":\"\",\"section\":\"005\",\"end\":\"00:00:00\",\"where\":\"Online\",\"crn\":\"10524\",\"subjectid\":\"CS\"}]";
        System.err.println(Jsoner.prettyPrint(s7));
        
        String result = sectionDao.find(1, "CS", "230");
        try {
            JsonArray t2 = (JsonArray)Jsoner.deserialize(result);
            System.out.println("Records: " + t2.size());
        }
        catch (Exception e) { e.printStackTrace(); }

        System.err.println(Jsoner.prettyPrint(result));
        //System.err.println(result);
        
    }
    
}