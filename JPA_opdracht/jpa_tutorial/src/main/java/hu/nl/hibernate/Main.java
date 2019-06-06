package hu.nl.hibernate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {
  private static SessionFactory factory;
  public static void main(String[] args) throws SQLException, ParseException {
      try {
        factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
      } catch (Throwable ex) {
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex);
      }
      Session session = factory.openSession();

      // Begin transactie
      Transaction t = session.beginTransaction();

      //Transactie opzetten.
      // Eerst maken we Student objecten:
      Student student1 = new Student(3, "Michele", "Onbekend");
      Student student2 = new Student(4, "Bart", "Onbekend");

      // Studenten een voor een saven
      session.save(student1);
      session.save(student2);
      // Haal student object op met ID 1
//      Student x = session.load(Student.class, 1);

      //ophalen dan de setters aanroepen om gegevens te wijzigen
//      x.setLastname("Nieuwe achternaam");
//      x.setFirstname("Micheleeee");

      // Haal object op, en stop hem in session.delete
//      session.delete(x);

//      session.save(x);

      //Log log = new Log(1,"Hibernate works!");

      t.commit();
      // Transactie eindigt hier
      System.out.println("successfully saved");    
      factory.close();  
      session.close();   
  }
}
