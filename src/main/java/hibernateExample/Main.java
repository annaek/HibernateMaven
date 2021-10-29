package hibernateExample;

import hibernateExample.entity.Author;
import hibernateExample.entity.Post;
import hibernateExample.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       // createAuthor();

        //addPost();
        printPostData();
    }

    public static void createAuthor() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        try {

            Query query = session.createQuery("from Author where name=:name");

            Author user = (Author) query.setParameter("name", firstName).uniqueResult();


            if (user != null) {
                System.out.println("Username " + user.getName() + " is already taken.");
            } else {

                Author person = new Author();
                person.setName(firstName);
                person.setLastName(lastName);
                person.setPhoneNumber(phoneNumber);
                session.save(person);
            }

        } finally {
            session.close();
        }
    }

    public static void addPost() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Author id: ");
        String id = scanner.nextLine();
        Long authorId = Long.parseLong(id);

        try {
            Query query = session.createQuery("from Author where id=:id");
            Author user = (Author) query.setParameter("id", authorId).uniqueResult();
            if (user == null) {
                System.out.println("Username does not exist");
            } else {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                System.out.print("Enter content: ");
                String content = scanner.nextLine();

                Post post = new Post();
                post.setAuthor(user);
                post.setTitle(title);
                post.setContent(content);
                session.save(post);

            }

        }finally{
            session.close();
        }

    }

    public static void printPostData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Post id: ");
        String id = scanner.nextLine();
        Long authorId = Long.parseLong(id);

        try{
            Query query = session.createQuery("from Post where id = :id");
            Post post = (Post) query.setParameter("id", authorId).uniqueResult();
            if (post == null) {
                System.out.println("Post does not exist");
            } else{
                System.out.println("Author: "+ post.getAuthor().getName());
                System.out.println("Title: " + post.getTitle());
                System.out.println("Content: " + post.getContent());
            }

        }finally {
            session.close();
        }


    }


}

//todo read from user postId and then read content and title and update
//one to many  - how to create list of posts in Author
