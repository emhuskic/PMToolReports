package com.pmtool.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

import java.util.List;

import java.util.Arrays;

import java.text.SimpleDateFormat;
import com.pmtool.models.Report;
import com.pmtool.models.ResponseModel;
import com.pmtool.models.ReportDao;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by Emina on 21.03.2017..
 */
import java.time.format.DateTimeFormatter;
@Controller
public class ReportController {

    @RequestMapping("/create")
    @ResponseBody
    public String create(String name){
        String reportId="";
        try{

            Report report = new Report(name);
            reportDao.save(report);
            reportId=String.valueOf(report.getId());
        }
        catch(Exception ex){
            return "Error creating the report" + ex.toString();
        }
        return "Report succesfully created with id = "+reportId;

    }

    //@RequestMapping("/delete")
    //@ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Report> Delete(@PathVariable("id") Long id) {
   // public String delete(long id) {
    try{

        Report report = new Report(id);
        reportDao.delete(report);
        return new ResponseEntity(HttpStatus.OK);
    }
    catch (Exception ex){
        return new ResponseEntity(new ResponseModel(ex.toString()), HttpStatus.NOT_FOUND);
    }
}


    @RequestMapping("/get-by-name")
    @ResponseBody
    public String getByName(String name) {
        String reportId="";
        try {
            List<Report> reports = reportDao.findByName(name);
            Report report=reports.get(0);
            reportId=String.valueOf(report.getId());
        }
        catch (Exception ex) {
            return "Report not found: "+ex;
        }
        return "The report id is: "+reportId;
    }


    @RequestMapping("/update")
    @ResponseBody
    public String updateReport(long id, String name, Date date) {
        try {
            Report report = reportDao.findOne(id);
            report.setCreatedOn(date);
            report.setName(name);
            reportDao.save(report);
        }
        catch (Exception ex) {
            return "Error updating the report: " + ex.toString();
        }
        return "Report succesfully updated!";
    }

    @Autowired
    private ReportDao reportDao;
}

