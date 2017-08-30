package net.tp.shoppingbacken.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.tp.shoppingbackend.dao.CategoryDAO;
import net.tp.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("net.tp.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	/*@Test
	public void TestAddCategory()
	{
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is description of Television!");
		category.setImageURL("CAT_1.png");
		
		assertEquals("Successfully added category into table", true, categoryDAO.add(category));
	}*/
	
	/*@Test
	public void TestGetCategory()
	{
		
		category = categoryDAO.get(2);
				
		assertEquals("Successfully fetched a category from the table", "Television", category.getName());
	}*/
	
	/*@Test
	public void TestUpdateCategory()
	{
		
		category = categoryDAO.get(2);
		category.setName("TV");		
		assertEquals("Successfully updated a category in the table", true, categoryDAO.update(category));
	}*/
	
	/*@Test
	public void TestDeleteCategory()
	{
		
		category = categoryDAO.get(2);
		
		assertEquals("Successfully deleted a category from the table", true, categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void TestListCategory()
	{
				
		assertEquals("Successfully fetched active list of categories from the table", 1, categoryDAO.list().size());
	}*/

	@Test
	public void TestCRUDCategory()
	{
		//add category
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is description of Laptop!");
		category.setImageURL("CAT_1.png");

		assertEquals("Successfully added category into table", true, categoryDAO.add(category));
		
		//AGAIN ADD SECOND RECORD
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is description of Television!");
		category.setImageURL("CAT_2.png");

		assertEquals("Successfully added category into table", true, categoryDAO.add(category));
		
		//get a single Category
		category = categoryDAO.get(2);
		
		assertEquals("Successfully fetched a category from the table", "Television", category.getName());
		
		//update a category
		category.setName("TV");		
		assertEquals("Successfully updated a category in the table", true, categoryDAO.update(category));
		
		//delete a category
		assertEquals("Successfully deleted a category from the table", true, categoryDAO.delete(category));
		
		//get list of active categories 
		assertEquals("Successfully fetched active list of categories from the table", 1, categoryDAO.list().size());
		
	}




}
