/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LocationDAO;
import entity.Account;
import entity.Location;
import entity.Rate;
import entity.Type;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class LocationDetail extends HttpServlet {

    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "image";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id=request.getParameter("id");
        LocationDAO LoDB = new LocationDAO();
        Location x=LoDB.getLocation(Integer.parseInt(id));
        ArrayList<Rate> rateL=LoDB.getALLRate(Integer.parseInt(id));
        int dem1=0,dem2=0,dem3=0,dem4=0,dem5=0;
        for (Rate i : rateL) {
            if (i.getScore()==1){
                dem1++;
            }
            if (i.getScore()==2){
                dem2++;
            }
            if (i.getScore()==3){
                dem3++;
            }
            if (i.getScore()==4){
                dem4++;
            }
            if (i.getScore()==5){
                dem5++;
            }
            
        }
        request.setAttribute("dem1", dem1);
        request.setAttribute("dem2", dem2);
        request.setAttribute("dem3", dem3);
        request.setAttribute("dem4", dem4);
        request.setAttribute("dem5", dem5);
        request.setAttribute("local", x);
        request.setAttribute("rateL", rateL);
        request.getRequestDispatcher("Location.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account a = (Account) request.getSession().getAttribute("user");
        String score = request.getParameter("rate");
        String comment = request.getParameter("comment");
        String localID = request.getParameter("localID");
        String img = uploadFile(request);
        LocationDAO LoDB = new LocationDAO();
        LoDB.addRate(comment, Integer.parseInt(score), img, Integer.parseInt(localID), a.getId());
        response.sendRedirect("LocationDetail?id="+localID);
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

}
