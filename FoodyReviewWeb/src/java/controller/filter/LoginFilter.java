/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Account;
import entity.Admin;

/**
 *
 * @author truon
 */
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        req.setCharacterEncoding("UTF-8");
        String reqURI = req.getRequestURI();
        if(!reqURI.endsWith("Home") && !reqURI.endsWith("/SignUp")&& !reqURI.endsWith("/Login")
                && !reqURI.endsWith("/LoginAdmin")&& !reqURI.endsWith("/LoginAdmin.jsp")
                && (((Account)req.getSession().getAttribute("user") == null)
                && ((Admin)req.getSession().getAttribute("admin") == null))
                ){
            resp.sendRedirect("Login");
        }else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
}
