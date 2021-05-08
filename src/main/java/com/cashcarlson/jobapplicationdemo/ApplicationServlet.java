/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo;

import com.cashcarlson.jobapplicationdemo.dataobjects.Application;
import com.cashcarlson.jobapplicationdemo.dataobjects.Attachment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Cash Carlson
 */
public class ApplicationServlet extends HttpServlet {
    
    SortedSet<Application> applications = new TreeSet<Application>();

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
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        
        // Get Action
        String action = request.getParameter("action");
        if(action != null && !action.equals("")) {
            //Get Resume
            if(action.equals("download")) {
                downloadAttachment(request, response);
            } 
            // Deactivate Application
            else if (action.equals("deactivate")) {
                String deactiveId = request.getParameter("appId");
                if(deactiveId != null && !deactiveId.equals("")) {
                    try {
                        int deactId = Integer.parseInt(deactiveId);
                        for(Application app : applications) {
                            if(app.getId() == deactId) {
                                app.setActive(false);
                                response.sendRedirect("applications");
                                return;
                            }
                        }
                    } catch (NumberFormatException ex) {
                        ex.getMessage();
                    }
                }
            }
        }
        
        
        // View Application
        
        String appId = request.getParameter("id");
        if(appId != null && !appId.equals("")) {
            try {
                int id = Integer.parseInt(appId);
                for(Application app : applications) {
                    if (app.getId() == id) {
                        request.setAttribute("application", app);
                        request.getRequestDispatcher("/WEB-INF/jsp/view/application.jsp").forward(request, response);
                    }
                }
            } catch (NumberFormatException ex) {
                ex.getMessage();
            }
        }
       
        // View Application List
        
        int page = 1;
        int jobsPerPage = 4;
        int begin = 0;
        int end = 0;
        int maxPages = applications.size() / jobsPerPage;
        if(applications.size() % jobsPerPage != 0) {
            maxPages++;
        }
        
        String pageStr = request.getParameter("page");
        if(pageStr != null && !pageStr.equals("")) {
            try {
                page = Integer.parseInt(pageStr);
                if (page < 1) {
                    page = 1;
                } else if (page > maxPages) {
                    page = maxPages;
                }
            } catch(NumberFormatException ex)  {
                page = 1;
            }
        }
        
        begin = (page - 1) * jobsPerPage;
        end = begin + jobsPerPage - 1;
        request.setAttribute("applications", applications);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/WEB-INF/jsp/view/applicationList.jsp").forward(request, response);
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
        //Appplication Info
        int id = applications.size() + 1;
        int jobId;
        String jobTitle = request.getParameter("jobTitle");
        
        //Parse Job ID
        try {
            jobId = Integer.parseInt(request.getParameter("jobId"));
        } catch (Exception ex) {
            throw ex;
        }
        
        Instant dateTimeSubmitted = Instant.now();

        //User info
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Attachment resumeUpload = null;
        double desiredSalary;
        LocalDate earliestStartDate;
        
        //Parse Attachment
        Part file = request.getPart("resume");
        if (file != null && file.getSize() > 0) {
            Attachment attachment = processAttachment(file);
            if (attachment != null) {
                resumeUpload = attachment;
            }
        }
        
        //Parse Salary
        try {
            desiredSalary = Double.parseDouble(request.getParameter("desiredSalary"));
        } catch (Exception ex) {
            throw ex;
        }
        
        //Parse LocalDate
        try {
            earliestStartDate = LocalDate.parse(request.getParameter("earliestStartDate"));
        } catch (Exception ex) {
            throw ex;
        }
        
        Application application = new Application(id, jobId, dateTimeSubmitted, 
            firstName, lastName, email, phone, resumeUpload, desiredSalary, 
            earliestStartDate, jobTitle);
        
        applications.add(application);
        response.sendRedirect("jobs");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold
    
    private Attachment processAttachment(Part file) throws IOException {
        Attachment attachment = new Attachment();
        try (InputStream inputStream = file.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            attachment.setName(file.getSubmittedFileName());
            attachment.setContents(outputStream.toByteArray());
        }
        return attachment;
    }
    
    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appId = request.getParameter("appId");
        Application app = new Application();
        for (Application app1 : applications) {
            try {
                int id = Integer.parseInt(appId);
                if(app1.getId() == id) {
                    app = app1;
                }
            } catch (NumberFormatException ex) {
                ex.getMessage();
            }
        }
        
        
        Attachment attachment = app.getResumeUpload();
        if (attachment == null) {
            response.sendRedirect("application?id=" + appId);
            return;
        }
        
        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        try (ServletOutputStream stream = response.getOutputStream()) {
            stream.write(attachment.getContents());
        }
    }

}
