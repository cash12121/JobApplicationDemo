/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo.dataobjects;

import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author Cash Carlson
 */
public class Application implements Comparable<Application> {
    //Appplication Info
    private int id;
    private int jobId;
    private Instant dateTimeSubmitted;
    
    //User info
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Attachment resumeUpload;
    private double desiredSalary;
    private LocalDate earliestStartDate;
    
    //Error Variables
    private String firstNameError; 
    private String lastNameError;
    private String emailError; 
    private String phoneError; 
    private String resumeError; 
    private String salaryError; 
    private String startDateError;
    
    public Application() {
        setId(0);
        setJobId(0);
        setDateTimeSubmitted(Instant.now());
        setFirstName("First");
        setLastName("Last");
        setEmail("Email@Email.com");
        setPhone("555-555-5555");
        setResumeUpload(new Attachment());
        setDesiredSalary(15.00);
        setEarliestStartDate(LocalDate.now());
    }

    public Application(int id, int jobId, Instant dateTimeSubmitted, 
            String firstName, String lastName, String email, String phone, 
            Attachment resumeUpload, double desiredSalary, 
            LocalDate earliestStartDate) {
        setId(id);
        setJobId(jobId);
        setDateTimeSubmitted(dateTimeSubmitted);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
        setResumeUpload(resumeUpload);
        setDesiredSalary(desiredSalary);
        setEarliestStartDate(earliestStartDate);
    }
    
    @Override
    public String toString() {
        return "Job ID: " + getJobId() + " First Name: " + getFirstName() 
                + " Last Name: " + getLastName() + " Email: " + getEmail();
    }
    
    @Override
    public int compareTo(Application otherApp) {
        int compared = 0;
        
        if (this.dateTimeSubmitted.isAfter(otherApp.getDateTimeSubmitted())) {
            compared = -1;
        } else if (this.dateTimeSubmitted.isBefore(otherApp.getDateTimeSubmitted())) {
            compared = 1;
        }
        
        return compared;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the jobId
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the dateTimeSubmitted
     */
    public Instant getDateTimeSubmitted() {
        return dateTimeSubmitted;
    }

    /**
     * @param dateTimeSubmitted the dateTimeSubmitted to set
     */
    public void setDateTimeSubmitted(Instant dateTimeSubmitted) {
        this.dateTimeSubmitted = dateTimeSubmitted;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the resumeUpload
     */
    public Attachment getResumeUpload() {
        return resumeUpload;
    }

    /**
     * @param resumeUpload the resumeUpload to set
     */
    public void setResumeUpload(Attachment resumeUpload) {
        this.resumeUpload = resumeUpload;
    }

    /**
     * @return the desiredSalary
     */
    public double getDesiredSalary() {
        return desiredSalary;
    }

    /**
     * @param desiredSalary the desiredSalary to set
     */
    public void setDesiredSalary(double desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    /**
     * @return the earliestStartDate
     */
    public LocalDate getEarliestStartDate() {
        return earliestStartDate;
    }

    /**
     * @param earliestStartDate the earliestStartDate to set
     */
    public void setEarliestStartDate(LocalDate earliestStartDate) {
        this.earliestStartDate = earliestStartDate;
    }

    /**
     * @return the firstNameError
     */
    public String getFirstNameError() {
        return firstNameError;
    }

    /**
     * @param firstNameError the firstNameError to set
     */
    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    /**
     * @return the lastNameError
     */
    public String getLastNameError() {
        return lastNameError;
    }

    /**
     * @param lastNameError the lastNameError to set
     */
    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    /**
     * @return the emailError
     */
    public String getEmailError() {
        return emailError;
    }

    /**
     * @param emailError the emailError to set
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    /**
     * @return the phoneError
     */
    public String getPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    /**
     * @return the resumeError
     */
    public String getResumeError() {
        return resumeError;
    }

    /**
     * @param resumeError the resumeError to set
     */
    public void setResumeError(String resumeError) {
        this.resumeError = resumeError;
    }

    /**
     * @return the salaryError
     */
    public String getSalaryError() {
        return salaryError;
    }

    /**
     * @param salaryError the salaryError to set
     */
    public void setSalaryError(String salaryError) {
        this.salaryError = salaryError;
    }

    /**
     * @return the startDateError
     */
    public String getStartDateError() {
        return startDateError;
    }

    /**
     * @param startDateError the startDateError to set
     */
    public void setStartDateError(String startDateError) {
        this.startDateError = startDateError;
    }
    
    
}
