package com.mirtanvir.schoolapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mirtanvir.schoolapi.domain.School;
import com.mirtanvir.schoolapi.repositories.SchoolRepository;

@Controller
@RequestMapping("html/**")
@Transactional
public class HtmlController {

    @Autowired
    private SchoolRepository repo;

    // @RequestMapping(value = "", method = RequestMethod.GET)
    // @ResponseBody
    // private ModelAndView getAllSchools(Pageable page) {
    //
    // return new ModelAndView("WEB-INF/velocity/hello.vm", "schools",
    // repo.findAllSchool());
    // }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(Model uiModel) {

        List<School> contacts = repo.findAllSchool();
        return new ModelAndView("WEB-INF/velocity/hello.vm", "schools",
                contacts);
    }
}