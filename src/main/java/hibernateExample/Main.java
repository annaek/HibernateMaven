package hibernateExample;

import hibernateExample.entity.Author;
import hibernateExample.utils.HibernateUtil;
import org.hibernate.Session;

public class Main {
   public static void main(String[] args){
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();

       Author author = new Author();
       author.setName("Anna");
       author.setLastName("Ek");
       author.setPhoneNumber("222-222-2222");

       session.save(author);

       session.getTransaction().commit();
       session.close();


   }
}
