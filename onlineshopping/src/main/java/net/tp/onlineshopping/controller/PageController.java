package net.tp.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.tp.onlineshopping.exception.ProductNotFoundException;
import net.tp.shoppingbackend.dao.CategoryDAO;
import net.tp.shoppingbackend.dao.ProductDAO;
import net.tp.shoppingbackend.dto.Category;
import net.tp.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	@RequestMapping(value={"/", "/home", "/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home!");
		
		logger.info("Inside PageController Index method- Info");
		logger.debug("Inside PageController index method- Debug");
		
		//  passing the list of category
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);
		
		return mv;
	}
	@RequestMapping(value="/about")
	public ModelAndView about()
	{
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contact()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	/*
	 * methods to load all the products And based on category
	 */
	@RequestMapping(value="/show/all/products")
	public ModelAndView showAllProducts()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		//  passing the list of category
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView mv = new ModelAndView("page");
		
		// categoryDAO to fetch single category
		Category category = null;
		category = categoryDAO.get(id);
		
		//passing category name
		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list());
		//passing single category object
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);

		return mv;
	}
	
	//show single product based on ID
	@RequestMapping(value = "show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		//-----------
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProduct", true);
		return mv;
	}
}
