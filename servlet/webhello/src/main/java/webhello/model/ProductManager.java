package webhello.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class ProductManager {

	//private List<Product> products = new ArrayList<>();
	
	private static ProductManager instance = new ProductManager();	// quando si esegue il progetto, subito si genera un'istanza della classe "ProductManager"
	public static ProductManager getInstance() {	// metodo che restituisce l'istanza generata all'avvio del progetto
		return instance;
	}
	
	private static Connection conn;
	
	public static void getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce?user=root&password=PuchiPuchi25!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connessione aperta");
	}
	
	public ProductManager() throws SQLException {
		//List<Product> products = new ArrayList<>();
		/*products.add(new Product(1, "scarpe", 80));
		products.add(new Product(2, "giubotto", 120));*/
		Product p = new Product(3, "pantaloni", 60);
		
		PreparedStatement stmt = null;
		Resultset rs = null;
				
		//for(Product p: products) {
			
			try {
				stmt = conn.prepareStatement("INSERT INTO ecommerce.products(name, price) VALUES(?, ?)");
				stmt.setString(1, p.getName());
				stmt.setInt(2, p.getPrice());
				stmt.executeUpdate();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		//}
	}
	
	public List<Product> getProducts() {
		return List.copyOf(products);
	}
	
	public Product getProduct(int id) {
		for(Product p: products)
			if(p.getCodeID() == id) 
				return p;
			return null;
	}
	
	public void addProduct(String name, int price) throws DuplicatedProductException {
		int lastID = 0;
		for(Product p: products) {
			if(p.getCodeID() > lastID)
				lastID = p.getCodeID();
			if(p.getName().equals(name))
				throw new DuplicatedProductException(name);
		}
		products.add(new Product(lastID+1, name, price));
	}
	
	public void remProduct(int id) {
		products.remove(getProduct(id));
	}
	
	public static class DuplicatedProductException extends Exception {
		private String name;
		
		public DuplicatedProductException(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
}
