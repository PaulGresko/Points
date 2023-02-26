package org.example;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
   private static SessionFactory sessionFactory = null;

    static {
        registry = new StandardServiceRegistryBuilder().configure().build();
        try{
            sessionFactory = (SessionFactory) new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void close(){
        if(sessionFactory != null){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

