package com.renanoliveira.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterSession {
	
	public void doFilter(ServletRequest request, ServletResponse response,  
	        FilterChain chain) throws IOException, ServletException {  
	      
	    HttpSession sessao = ((HttpServletRequest) request).getSession();  
	          
	    if(sessao.getAttribute("usuario") == null){  
	          
	        System.out.println("USUÁRIO NÃO AUTORIZADO");  
	          
	        ((HttpServletResponse) response).sendRedirect("/login.xhtml");  
	                      
	        return;  
	                  
	    }  
	    else{  
	        System.out.println(sessao.getAttribute("usuario"));  
	          
	        System.out.println("USUÁRIO LOGADO");         
	    }  
	      
	    chain.doFilter(request, response);  
	}  

}
