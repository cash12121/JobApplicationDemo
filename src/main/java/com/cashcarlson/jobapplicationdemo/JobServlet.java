/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo;

import com.cashcarlson.jobapplicationdemo.dataaccess.DAOPattern;
import com.cashcarlson.jobapplicationdemo.dataaccess.csv.JobDAOCSV;
import com.cashcarlson.jobapplicationdemo.dataobjects.Job;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cash Carlson
 */
public class JobServlet extends HttpServlet {
    private static final String FILE_NAME = "job-data.csv";
    private static final String FILE_PATH = "WEB-INF/csv/";
    
    SortedSet<Job> jobs;
    
    
    private void getJobs(DAOPattern dao) {
        try {
            //ArrayList<Job> temp = dao.getAllJobs();
            jobs = dao.getAllJobs();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

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
        DAOPattern dao = 
            new JobDAOCSV(getServletContext().getRealPath(FILE_PATH + FILE_NAME));
        getJobs(dao);
        
        String jobId = request.getParameter("id");
        if(jobId != null && !jobId.equals("")) {
            try {
                Job job = dao.getJobById(jobId);
                if(job != null) {
                    request.setAttribute("job", job);
                    request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
                }
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        
        int page = 1;
        int jobsPerPage = 4;
        int begin = 0;
        int end = 0;
        int maxPages = jobs.size() / jobsPerPage;
        if(jobs.size() % jobsPerPage != 0) {
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
        request.setAttribute("jobs", jobs);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp").forward(request, response);
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
