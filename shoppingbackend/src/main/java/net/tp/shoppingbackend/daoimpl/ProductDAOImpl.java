package net.tp.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tp.shoppingbackend.dao.ProductDAO;
import net.tp.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	//List of products
	@Override
	public List<Product> list() {
		try{
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// Get single product based on product Id
	@Override
	public Product get(int productId) {
		try{
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		}catch(Exception e)
		{
			e.printStackTrace();			
		}
		return null;
	}

	//Insert product in table
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

	//Update product in table
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	//Delete product from table Product
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	//List all the products 
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class).setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class).setParameter("active", true).setParameter("categoryId", Integer.valueOf(categoryId)).getResultList();
	
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
	
		return sessionFactory.getCurrentSession().createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class).setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
		
	}

}
