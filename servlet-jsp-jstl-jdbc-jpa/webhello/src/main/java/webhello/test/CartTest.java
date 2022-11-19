package webhello.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import webhello.model.Cart;
import webhello.model.Product;

class CartTest {

	static Cart c;
	Product p = new Product(1, "cintura", 10);
	Product p1 = new Product(2, "cassa", 30);

	@BeforeAll
	public static void before() {
		c = new Cart();
	}

	@AfterEach
	public void after() {
		for(Product p: c.getProducts()) {
			c.remove(p);
		}
	}
	
	@Test
	void getProductsTest() {		
		c.add(p, 1);
		c.add(p1, 1);
		List<Product> list = new ArrayList<Product>();
		list.add(p);
		list.add(p1);
		assertEquals(list, c.getProducts());
	}
	
	@Test
	void getQtyTest() {		
		c.add(p, 3);
		c.add(p1, 2);
		assertEquals(2, c.getQty(p1));
	}
	
	@Test
	void getTotPriceTest() {	
		c.add(p, 2);
		assertEquals(20, c.getTotPrice());
	}
	
	@Test
	void addTest() {
		c.add(p, 1);
		assertEquals(1, c.getQty(p));
	}
	
	@Test
	void removeTest() {
		c.add(p1, 3);
		c.remove(p1);
		assertEquals(0, c.getQty(p1));
	}
}
