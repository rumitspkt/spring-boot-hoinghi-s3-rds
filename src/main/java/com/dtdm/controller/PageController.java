package com.dtdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dtdm.service.PageService;

@Controller
public class PageController {
	
	@Autowired
	PageService pageService;
	
	@GetMapping("/about")
	public String showAbout(Model model) {
		model.addAttribute("page", pageService.findOne("About"));
		return "about";
	}
	
	@GetMapping("/event")
	public String showEvent(Model model) {
		model.addAttribute("page", pageService.findOne("Event"));
		return "event";
	}
	@GetMapping("/speakers")
	public String showSpeakers(Model model) {
		model.addAttribute("page", pageService.findOne("Speakers"));
		return "speakers";
	}
	@GetMapping("/schedule")
	public String showSchedule(Model model) {
		model.addAttribute("page", pageService.findOne("Schedule"));
		return "schedule";
	}
	@GetMapping("/sponsors")
	public String showSponsors(Model model) {
		model.addAttribute("page", pageService.findOne("Sponsors"));
		return "sponsors";
	}
	@GetMapping("/venue")
	public String showVenue(Model model) {
		model.addAttribute("page", pageService.findOne("Venue"));
		return "venue";
	}
}
