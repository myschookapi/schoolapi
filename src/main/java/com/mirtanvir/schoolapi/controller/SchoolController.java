package com.mirtanvir.schoolapi.controller;

import java.util.List;
import java.util.Set;

import javax.transaction.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mirtanvir.schoolapi.domain.School;
import com.mirtanvir.schoolapi.domain.Students;
import com.mirtanvir.schoolapi.domain.Users;
import com.mirtanvir.schoolapi.repositories.SchoolRepository;

@Controller
@RequestMapping("/api**")
@Transactional
public class SchoolController {

	@Autowired
	private SchoolRepository repo;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	//private List<School> getAllSchools() {
		//return repo.findAllSchool();
		private Users yada() {	
		return repo.login(9L);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	private School getOneSchool(@PathVariable("id") long id) {
		System.out.println(id);
		return repo.findSchoolById(id);
	}

	
	
	/*
	@RequestMapping(value = "/zipcode/{zipCode}", method = RequestMethod.GET)
	@ResponseBody
	private Page<School> getSchoolsInAZipCode(
			@PathVariable("zipCode") String zipCode, Pageable page) {
		System.out.println("Searching for schools in zipcode" + zipCode);
		return repo.getByZip(zipCode, page);
	
	}
*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	private School createSchool(@RequestBody School school) throws RollbackException {
		School newSchool = null;
		newSchool = repo.saveSchool(school);
		return newSchool;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	private School updateSchool(@PathVariable("id") long id,
			@RequestBody School school) throws RollbackException {
		School existingSchool = repo.findSchoolById(id);
		if (existingSchool != null && existingSchool.getId() == school.getId()) {
			System.out.println("Saving the object with id" + id);
			return repo.saveSchool(school);
		}
		return null;
	}

	// ===========================================================

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	private void deleteSchool(@PathVariable("id") long id) {
		School existingSchool = repo.findSchoolById(id);
		if (existingSchool != null) {
			System.out.println("Deleting the object with id" + id);
			repo.deleteSchool(existingSchool);
		}

	}

}
