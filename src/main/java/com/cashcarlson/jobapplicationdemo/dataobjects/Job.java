/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashcarlson.jobapplicationdemo.dataobjects;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 *
 * @author Cash Carlson
 */
public class Job {
    private int id;
    private boolean active;
    private LocalDate dateCreated;
    private String title;
    private String city;
    private String state;
    private boolean fullTime;
    private String department;
    private String experience;
    private String wageCategory;
    private double salary;

    public Job() {
        //Assumed Default Values. Not specified.
        setId(0);
        setActive(true);
        setDateCreated(LocalDate.now());
        setTitle("Default Title");
        setCity("Cedar Rapids");
        setState("Iowa");
        setFullTime(true);
        setDepartment("Default Department");
        setExperience("Entry Level");
        setWageCategory("Hourly");
        setSalary(15.00);
    }

    public Job(int id, boolean active, LocalDate dateCreated, String title, String city, String state, boolean fullTime, String department, String experience, String wageCategory, double salary) {
        setId(id);
        setActive(active);
        setDateCreated(dateCreated);
        setTitle(title);
        setCity(city);
        setState(state);
        setFullTime(fullTime);
        setDepartment(department);
        setExperience(experience);
        setWageCategory(wageCategory);
        setSalary(salary);
    }
    
    public Date newDateCreated(LocalDate date) {
        Date newDate = Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
        return newDate;
    }
    
    @Override
    public String toString() {
        String display;
        String status = (active) ? "Active" : "Inactive";
        display = "Title: " + title + ", Location: " + city + ", " 
                + state + ", Status: " + status;
        
        return display;
    }
    
    public int compareTo(Job job) {
        int compared = 0;
        
        if (this.newDateCreated(this.dateCreated).after(job.newDateCreated(job.dateCreated))) {
            //its set to negative because its more recent than the compared Job
            compared = -1;
        } else if (this.newDateCreated(this.dateCreated).before(job.newDateCreated(job.dateCreated))) {
            //its set to postive because its more older than the compared Job
            compared = 1;
        } else if (this.newDateCreated(this.dateCreated).equals(job.newDateCreated(job.dateCreated))) {
            //Only enter here if the date is strictly the same as the compared Job
            //use String compareTo to compare titles then set it to int
            compared = this.title.compareTo(job.title);
            //If the titles are EXACTLY the same then are the same job therefore should be set to 0
            //Any difference alphabetically will set this job before or after the compared.
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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the dateCreated
     */
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the fullTime
     */
    public boolean isFullTime() {
        return fullTime;
    }

    /**
     * @param fullTime the fullTime to set
     */
    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the experience
     */
    public String getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * @return the wageCategory
     */
    public String getWageCategory() {
        return wageCategory;
    }

    /**
     * @param wageCategory the wageCategory to set
     */
    public void setWageCategory(String wageCategory) {
        this.wageCategory = wageCategory;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
