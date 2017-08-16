package net.tp.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.tp.shoppingbackend.dao.CategoryDAO;
import net.tp.shoppingbackend.dto.Category;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value={"/", "/home", "/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home!");
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
		System.out.println(id);
		// categoryDAO to fetch single category
		Category category = null;
		category = categoryDAO.get(id);
		//passing category name
		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list());
		//passing single category object
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);
		System.out.println(category.getName());
		System.out.println(category.getId());
		return mv;
	}
	
}
