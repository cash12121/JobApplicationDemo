/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo.dataaccess.csv;

import com.cashcarlson.jobapplicationdemo.dataaccess.DAOPattern;
import com.cashcarlson.jobapplicationdemo.dataaccess.JobDataException;
import com.cashcarlson.jobapplicationdemo.dataobjects.Job;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author Cash Carlson
 */
public class JobDAOCSV implements DAOPattern {
    private static SortedSet<Job> jobs;
    private String path = "";
    private static final DateTimeFormatter ISO_DATE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public JobDAOCSV(String path) {
        this.path = path;
    }
    
    public void readFromFile() throws JobDataException {
        try(Scanner in = new Scanner(new File(path))) {
            jobs = new TreeSet<>();
            int lineCount = 0;
            String line;
            String[] fields;
            
            int id;
            boolean active;
            LocalDate dateCreated;
            String title;
            String city;
            String state;
            boolean fullTime;
            String department;
            String experience;
            String wageCategory;
            double salary;
            
            //Check if empty
            if(in.hasNextLine()) {
                //skip first/header line
                in.nextLine();
            }
            
            //read data
            while(in.hasNextLine()) {
                lineCount++;
                line = in.nextLine();
                fields = line.split(",");
                
                // Parse ID
                try {
                    id = Integer.parseInt(fields[0]);
                } catch (NumberFormatException ex) {
                    throw new JobDataException(ex.getMessage() + "CSV Line " + 
                            (lineCount + 1));
                }
                
                // Parse Active
                try {
                    active = Boolean.getBoolean(fields[1]);
                } catch (Exception ex) {
                    throw new JobDataException(ex.getMessage() + "CSV Line " + 
                            (lineCount + 1));
                }
                
                // Parse Date Created
                try {
                    dateCreated = LocalDate.parse(fields[2], ISO_DATE);
                } catch (Exception ex) {
                    throw new JobDataException(ex.getMessage() + "CSV Line " + 
                            (lineCount + 1));
                }
                
                // Parse title, city, state.
                title = fields[3];
                city = fields[4];
                state = fields[5];
                
                try {
                    fullTime = Boolean.getBoolean(fields[6]);
                } catch (Exception ex) {
                    throw new JobDataException(ex.getMessage() + "CSV Line " + 
                            (lineCount + 1));
                }
                
                department = fields[7];
                experience = fields[8];
                wageCategory = fields[9];
                
                try {
                    salary = Double.parseDouble(fields[10]);
                } catch (Exception ex) {
                    throw new JobDataException(ex.getMessage() + "CSV Line " + 
                            (lineCount + 1));
                }
                
                //Create Object and Store it
                jobs.add(new Job(id, active, dateCreated, title, city, state, 
                        fullTime, department, experience, wageCategory, salary));
            }
        } catch (FileNotFoundException ex) {
            throw new JobDataException(ex);
        }
    }
    
    private void verifyJobList() throws JobDataException {
        if (null == jobs) {
            readFromFile();
        }
    }

    @Override
    public void createRecord(Job arg0) throws JobDataException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Job getJobById(String id) throws JobDataException {
        Job job = null;
        try {
            int conId = Integer.parseInt(id);
            
            verifyJobList();
            for (Job job1 : jobs) {
                if(job1.getId() == conId) {
                    job = job1;
                    break;
                }
            }
        } catch(Exception ex) {
            throw new JobDataException(ex);
        }
        return job;
    }
    
    @Override
    public SortedSet<Job> getAllJobs() throws JobDataException {
        verifyJobList();
        return jobs;
    }

    @Override
    public void UpdateRecord(Job arg0, Job arg1) throws JobDataException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void DeleteRecord(Job arg0) throws JobDataException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void DeleteRecord(String arg0) throws JobDataException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
