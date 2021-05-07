/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo.dataaccess;

import com.cashcarlson.jobapplicationdemo.dataobjects.Job;
import java.util.ArrayList;
import java.util.SortedSet;

/**
 *
 * @author Cash Carlson
 */
public interface DAOPattern {
    void createRecord(Job record) throws JobDataException;
    Job getJobById(String orderNumber) throws JobDataException;
    SortedSet<Job> getAllJobs() throws JobDataException;
    void UpdateRecord(Job recordOld, Job recordNew) throws JobDataException;
    void DeleteRecord(Job record) throws JobDataException;
    void DeleteRecord(String orderNumber) throws JobDataException;
}
