package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController{

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(@RequestParam(value = "searchType") String column,
                         @RequestParam(value = "searchTerm") String value,
                         Model model) {

        ArrayList<HashMap<String, String>> jobs;

        if (column.equals("all")) {
            jobs = JobData.findByValue(value);
        } else {
            jobs = JobData.findByColumnAndValue(column, value);
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchTerm", value);

        return "search";
    }

}
