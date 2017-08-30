package net.tp.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tp.shoppingbackend.dao.CategoryDAO;
import net.tp.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	//get all active category
	
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory) ;
		query.setParameter("active", true);
		return query.getResultList();
	}

	//get single category based on id
	
	public Category get(int id) {
				
			return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	//add category
	
	@Override
	public boolean add(Category category) {
		
		try{
			//add the category to database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
	}

	//update a single category
	
	@Override
	public boolean update(Category category) {
	
		try{
			//add the category to database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	//delete a category
	
	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		try{
			//add the category to database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}	

}
