package net.kzn.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException()
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","the page is not constructed.");
		mv.addObject("errorDescription","the page you are looking for is not available.");
		mv.addObject("title","404 Error Page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException()
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","product not available");
		mv.addObject("errorDescription","the product you are looking for is not available right now.");
		mv.addObject("title","Product Unavailable");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e)
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","Contact Your Administrator");
		
//		only for ducbugging your application
		StringWriter s=new StringWriter();
		PrintWriter pw=new PrintWriter(s);
		e.printStackTrace(pw);
		
		mv.addObject("errorDescription",""+s.toString());
		mv.addObject("title","Error!"); 
		return mv;
	}

}
