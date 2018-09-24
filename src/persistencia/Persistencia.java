package persistencia;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//import modelo.Division;


public class Persistencia {
    private SessionFactory sessionFactory;
    private Session session;
    
    public Persistencia() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        
    }
    
    public void insert(Object unObjeto) {
        Transaction tr = session.beginTransaction();
          session.save(unObjeto);
        tr.commit();
    }


    public void delete(Object unObjeto)
    {
    Transaction tr = session.beginTransaction();
    session.delete(unObjeto);
    tr.commit();
    }

    public void update(Object unObjeto){
         Transaction tr = session.beginTransaction();
         session.update(unObjeto);
       tr.commit();
    }
        
    public Object load(String clase,Serializable id) {
        Object obj = session.load(clase,id);
        return obj;
    }
   
    public List find(String sentencia) {
        List obj = session.createQuery(sentencia).list();   
        return obj;
    }
}