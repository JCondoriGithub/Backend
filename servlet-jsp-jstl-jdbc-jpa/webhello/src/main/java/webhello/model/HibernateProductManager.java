package webhello.model;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

public class HibernateProductManager extends ProductManager {
	
	SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	private EntityManager openEM() throws SQLException {
		return sf.createEntityManager();
	}

	public void remove(int code) {

		EntityManager em = null;
		try {
			em = openEM();
			em.getTransaction().begin();
			em.remove(em.find(Product.class, code));
			em.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if(em != null)
				em.close();	// è consigliabile chiudere sempre l'EM dopo l'utilizzo
		}
	}

	@Override
	public Product add(String name, int price) throws DuplicatedProductException {
		
		EntityManager em = null;
		try {
			em = openEM();
			em.getTransaction().begin();
			Product p = new Product(null, name, price);
			em.persist(p);
			em.getTransaction().commit();
			return p;
		} catch (EntityExistsException ex) {
			throw new DuplicatedProductException(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null)
				em.close();
		}
	}
	
	@Override
	public Product add(String name, int price, Category cat) throws DuplicatedProductException {
		
		EntityManager em = null;
		try {
			em = openEM();
			em.getTransaction().begin();
			Product p = new Product(null, name, price);
			cat = cat == null ? cat : em.find(Category.class, cat.getCode());
			p.setCategory(cat);
			em.persist(p);
			em.getTransaction().commit();
			return p;
		} catch (EntityExistsException ex) {
			throw new DuplicatedProductException(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null)
				em.close();
		}
	}

	private static HibernateProductManager instance = new HibernateProductManager();

	public static HibernateProductManager getInstance() {
		return instance;
	}

	public Product getProduct(int id) {

			EntityManager em = null;
		try {	
			em = openEM();
			return em.find(Product.class, id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}

	}

	@Override
	public List<Product> getProducts() {
		
			EntityManager em = null;
		try {
			em = openEM();
			return (List<Product>) em.createQuery("select p from Product p").getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	@Override
	public Category getCategory(int code) {
		
		EntityManager em = null;
		try {
			em = openEM();
			return em.find(Category.class, code);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}

	/*public static void main (String[] args) throws SQLException {
		EntityManager em = null;
		em = openEM();
		Category c = new Category(5, "cose");
		Category c1 =em.find(Category.class, 3);
		System.out.println(c1);
	}*/
	
	@Override
	public List<Category> getCategories() {
		
		EntityManager em = null;
		try {
			em = openEM();
			return (List<Category>) em.createQuery("select c from Category c").getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
}
