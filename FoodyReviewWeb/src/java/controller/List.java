/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LocationDAO;
import entity.Location;
import entity.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.HtmlHelper;

/**
 *
 * @author DELL
 */
public class List extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //request.setCharacterEncoding("urf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String s = "";
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            s += "&search=" + search;
            String min = request.getParameter("min");
            try{
                int t= Integer.parseInt(min);
            if ((min == null) || (min.equals(""))) {
                min = "0";
            }}catch(Exception e){
                min = "0";
            }
            s += "&min=" + min;
            String max = request.getParameter("max");
            if ((max == null) || (max.equals(""))) {
                max = "0";
            }
            s += "&max=" + max;
            String Tinput[] = request.getParameterValues("type");
            ArrayList<Integer> type = new ArrayList<>();
            if (Tinput != null) {
                for (int i = 0; i < Tinput.length; i++) {
                    s += "&type=" + Tinput[i];
                    type.add(Integer.parseInt(Tinput[i]));
                }
            }
            LocationDAO LoDB = new LocationDAO();
            int pagesize = 6;
            String raw_pageindex = request.getParameter("page");
            if (raw_pageindex == null) {
                raw_pageindex = "1";
            }
            int pageindex = Integer.parseInt(raw_pageindex);
            int count = LoDB.count(search, type, Integer.parseInt(min), Integer.parseInt(max));
            int pagecount = (count % pagesize == 0) ? count / pagesize : count / pagesize + 1;
            pagecount=2;
            //ArrayList<Location> LocalL = LoDB.getALLLocation();
            ArrayList<Location> LocalL = LoDB.searchALLLocation(pageindex, pagesize, search, type, Integer.parseInt(min), Integer.parseInt(max));
            ArrayList<Type> typeL = LoDB.getAllType();
            request.setAttribute("typeL", typeL);
            request.setAttribute("LocalL", LocalL);
            request.setAttribute("paging", HtmlHelper.pager(pageindex, pagecount, 10, s));
            request.getRequestDispatcher("List.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
