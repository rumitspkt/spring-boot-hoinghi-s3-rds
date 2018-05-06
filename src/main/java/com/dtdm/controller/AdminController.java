package com.dtdm.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.AmazonServiceException;
import com.dtdm.model.Admin;
import com.dtdm.model.FileAWS;
import com.dtdm.model.Page;
import com.dtdm.model.Post;
import com.dtdm.service.AdminService;
import com.dtdm.service.AmazonUploadService;
import com.dtdm.service.FileAWSService;
import com.dtdm.service.PageService;
import com.dtdm.service.PostService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	PageService pageService;

	@Autowired
	PostService postService;

	@Autowired
	AmazonUploadService amazonService;
	
	@Autowired
	FileAWSService fileAWSService;

	@GetMapping("/login")
	public String showLogIn(Model model) {
		return "login";
	}

	@GetMapping("/signup")
	public String showSignUp(Model model) {
		return "signup";
	}

	@GetMapping("/admin")
	public String showAdmin(Model model) {
		// check cookie
		// Get username in Cookie
		return "admin";
	}

	@PostMapping("/authenticate") // vd /admin/rum thi ko nhan Bootstrap ?????
	public String checkLogIn(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes redirect) {
		// authenticate
		Admin admin = adminService.findByUsername(username);
		if (admin == null || !admin.getPassword().equals(password)) {
			redirect.addFlashAttribute("failed", "Wrong username or password!!");
			return "redirect:/login";
		}
		// add Cookies
		redirect.addFlashAttribute("admin", admin);
		return "redirect:/admin";
	}

	@GetMapping("/formPage")
	public String showFormPage(Model model) {
		model.addAttribute("post", new Post());
		return "formPage";
	}

	@GetMapping("/formPost")
	public String showFormPost(Model model) {
		model.addAttribute("post", new Post());
		return "formPost";
	}

	@PostMapping("/formPostSave")
	public String saveFormPost(@Valid Post post, BindingResult result, RedirectAttributes redirect, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			return "formPost";
		}
		postService.save(post);
		FileAWS fileAWSCurrent = fileAWSService.findOne(post.getId());
		//upload file
		if(!file.isEmpty()) {
			
			if(fileAWSCurrent != null) {
				FileAWS fileAWS = fileAWSService.findOne(post.getId());
				amazonService.deleteFile(fileAWS.getName());
				fileAWSService.delete(fileAWS.getId());
				
			}
			List<String> listString = amazonService.uploadFile(file);
			if(listString != null) {
				FileAWS fileAWS = new FileAWS(post.getId(), listString.get(0), listString.get(1));
				fileAWSService.save(fileAWS);
			}
		}
		
		System.err.println(file.getOriginalFilename());
		redirect.addFlashAttribute("success", "Saved post successfully!");
		redirect.addFlashAttribute("admin", new Admin(1, "rum", "123"));
		return "redirect:/admin";
	}

	@GetMapping("/post/{id}/edit")
	public String editPost(@PathVariable int id, Model model) {
		model.addAttribute("fileAWS", fileAWSService.findOne(id));
		model.addAttribute("post", postService.findOne(id));
		return "formPost";
	}

	@GetMapping("/post/{id}/delete")
	public String deletePost(@PathVariable int id, RedirectAttributes redirect) {
		postService.delete(id);
		FileAWS fileAWS = fileAWSService.findOne(id);
		if(fileAWS != null) {
			amazonService.deleteFile(fileAWS.getName());
			fileAWSService.delete(fileAWS.getId());
		}		
		redirect.addFlashAttribute("admin", new Admin(1, "rum", "123"));
		redirect.addFlashAttribute("success", "Deleted post successfully!");
		return "redirect:/admin";
	}

	@GetMapping("/listPost")
	public String showListPost(Model model) {
		model.addAttribute("posts", postService.findAll());
		return "list";
	}

	@PostMapping("/createPost")
	public String createPost(@Valid Post post, BindingResult result, RedirectAttributes redirect) {
		// authenticate
		return "admin";
	}

	// code tao lao
	@GetMapping("/formPageEvent")
	public String showFormPageEvent(Model model) {
		model.addAttribute("page", pageService.findOne("Event"));
		return "formPage";
	}

	@GetMapping("/formPageAbout")
	public String showFormPageAbout(Model model) {
		model.addAttribute("page", pageService.findOne("About"));
		return "formPage";
	}
	
	@GetMapping("/formPageSpeakers")
	public String showFormPageSpeakers(Model model) {
		model.addAttribute("page", pageService.findOne("Speakers"));
		return "formPage";
	}
	
	@GetMapping("/formPageSchedule")
	public String showFormPageSchedule(Model model) {
		model.addAttribute("page", pageService.findOne("Schedule"));
		return "formPage";
	}
	
	@GetMapping("/formPageVenue")
	public String showFormPageVenue(Model model) {
		model.addAttribute("page", pageService.findOne("Venue"));
		return "formPage";
	}
	
	@GetMapping("/formPageSponsors")
	public String showFormPageSponsors(Model model) {
		model.addAttribute("page", pageService.findOne("Sponsors"));
		return "formPage";
	}

	@PostMapping("/formPageSave")
	public String savePage(RedirectAttributes redirect, @RequestParam("header") String header,
			@RequestParam("title") String title, @RequestParam("content") String content) {
		pageService.save(new Page(header, title, content));
		redirect.addFlashAttribute("success", "Update page completely!!");
		redirect.addFlashAttribute("admin", new Admin(1, "rum", "123"));
		return "redirect:/admin";
	}

	// test
	@PostMapping("/test")
	public String test(@RequestParam("content") String content) {
		System.out.println(content);
		return "signup";
	}

//	// Google Drive
//	@PostMapping("/upload")
//	public String upload(@RequestParam("file") MultipartFile file, Model model) {
//		if(file.isEmpty()) {
//			System.err.println("no file");
//			return "signup";
//		}
//		java.io.File tmpFile = new java.io.File(System.getProperty("java.io.tmpdir")
//				+ System.getProperty("file.separator") + file.getOriginalFilename());
//
//		try {
//			file.transferTo(tmpFile);
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.err.println(tmpFile.getAbsolutePath());
//
//		// File file = new File("C:\\Users\\RumIT\\Desktop\\wp1855879.jpg");
//		com.FileAWS.api.services.drive.model.File file2 = driveService.upLoadFile(tmpFile.getName(),
//				tmpFile.getAbsolutePath(), file.getContentType());
//
//		return "signup";
//	}
//
//	@GetMapping("/uploadForm")
//	public String uploadShow(Model model) {
//		return "uploadForm";
//	}

}
