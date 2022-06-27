package persistance;

import model.Zaposleni;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ZaposleniCRUD {
    public static boolean unosZaposlenog(Zaposleni zaposleni) {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(zaposleni);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return false;
        } finally {
            HibernateUtil.close();
        }
    }

    public static boolean brisanjeZaposlenog(int id) {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Zaposleni zaposleni = session.load(Zaposleni.class, id);
            session.delete(zaposleni);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return false;
        } finally {
            HibernateUtil.close();
        }
    }

    public static List<Zaposleni> prikazSvihZaposlenih() {

        List<Zaposleni> listaZaposlenih = new ArrayList<>();

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            listaZaposlenih = session.createQuery("SELECT a FROM model.Zaposleni a", Zaposleni.class).getResultList();
            tx.commit();

            return listaZaposlenih;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return listaZaposlenih;
        } finally {
            HibernateUtil.close();
        }
    }

    public static boolean izmenaZaposlenog(Zaposleni zaposleni) {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            session.update(zaposleni);
            tx.commit();
            return true;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return false;

        } finally {
            HibernateUtil.close();
        }
    }

    public static List<Zaposleni> prikazPoImenu(String zaposleni) {

        List<Zaposleni> listaZaposlenih = new ArrayList<>();

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            listaZaposlenih = session.createQuery("SELECT a FROM model.Zaposleni a WHERE ime = :zaposleni_ime", Zaposleni.class)
                    .setParameter("zaposleni_ime", zaposleni)
                    .getResultList();
            tx.commit();

            return listaZaposlenih;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return listaZaposlenih;

        } finally {
            HibernateUtil.close();
        }
    }

    public static Zaposleni pretragaPoId(int id) {

        Zaposleni zaposleni = new Zaposleni();
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            zaposleni = session.get(Zaposleni.class, id);
            tx.commit();

            return zaposleni;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
            return zaposleni;

        } finally {
            HibernateUtil.close();
        }
    }
}
