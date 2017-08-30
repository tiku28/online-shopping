package net.tp.shoppingbacken.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.tp.shoppingbackend.dao.ProductDAO;
import net.tp.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("net.tp.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
		
	}
	
/*	@Test
	public void TestCRUDProduct()
	{
		
		//create or add operation
		product = new Product();
		
		product.setName("Oppo selfie S53");
		product.setBrand("Oppo");
		product.setDescription("Here is few description of Oppo mobile phones");
		product.setUnitPrice(500);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something Went Worng while adding a product?", true, productDAO.add(product));
		
		//reading or get and update product
		
		product = productDAO.get(2);
		product.setName("Samsung Galexy S7");
		
		assertEquals("Something Went Worng while updating an existing product?", true, productDAO.update(product));
		
		//delete a product
		assertEquals("Something Went Worng while deleting an existing product?", true, productDAO.delete(product));
		
		//list
		assertEquals("Something Went Worng while fetching all existing product?", 6, productDAO.list().size());
			
	}*/
	
	@Test
	public void TestListActiveProducts()
	{
		assertEquals("Something Went Worng while fetching all active products?", 5, productDAO.listActiveProducts().size());
	}
	
	@Test
	public void TestListActiveProductsByCategory()
	{
		assertEquals("Something Went Worng while fetching all active products whose id is 3?", 3, productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something Went Worng while fetching all active products whose id is 1?", 2, productDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void TestLatestActiveProducts()
	{
		assertEquals("Something Went Worng while fetching latest active products?", 3, productDAO.getLatestActiveProducts(3).size());
	}
}

