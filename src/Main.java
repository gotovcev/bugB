
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Main {
    private static SessionFactory dbSessions;

    private static void startWorkWithDB() {
        dbSessions = new AnnotationConfiguration()
                .configure("/resources/hibernate.cfg.xml")
                .addAnnotatedClass(MyTable.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        Main caller = new Main();
        //System.out.print(setNewLine("13", "login",  "password", "firstName", "lastName",  "m",  "13.56.12",  "123123123"));
        System.out.print(checkUser("gopaul92", "qwerty"));

        dbSessions.close();
    }

    public static String checkUser(String login, String password) {
        startWorkWithDB();
        Session session = dbSessions.openSession();
            try {
                List usersList = session.createQuery("FROM MyTable").list();
            for (Iterator iterator = usersList.iterator(); iterator.hasNext(); ) {
                MyTable counter = (MyTable) iterator.next();

                if (counter.getLogin().equals(login) && counter.getPassword().equals(password)){
                    return "Yo Yo my bro " + counter.getFirst_name() + " " + counter.getLast_name();
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return "I`m sorry nigga. I never meant to hurt you. I never meant make you cry. Please, try again.";
    }



   private static String setNewLine(String id, String login, String password, String firstName,
                                   String lastName, String gender, String birthDate, String phoneNumber) // INSERT INTO table_name VALUES (...);
    {
        startWorkWithDB();
        Session session = dbSessions.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            MyTable newFm = new MyTable();
            newFm.setId(UUID.randomUUID().toString());
            newFm.setLogin(login);
            newFm.setPassword(password);
            newFm.setLast_name(lastName);
            newFm.setFirst_name(firstName);
            newFm.setGender(gender);
            newFm.setBirthDate(birthDate);
            newFm.setPhoneNumber(phoneNumber);
            session.save(newFm);
            tx.commit();
            System.out.println("Inserting has been done!");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return "Complite";
    }

}
