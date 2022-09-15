package com.example.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.NewsForm;
import com.example.app.service.MemberService;
import com.example.app.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewsService service;
	@Autowired
	MemberService memberService;
	
	@InitBinder
	public void initBinderForm(WebDataBinder binder) {
	var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	dateFormat.setLenient(false);
	binder.registerCustomEditor(Date.class,
	new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping
	public String list(Model model) throws Exception{
		model.addAttribute("newsList", service.getNewsList());
		return "news/list";
	}
	
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("news", service.getNewsById(id));
		return "news/detail";
	}
	
	@GetMapping("/add")
	public String addGet(Model model) throws Exception{
		model.addAttribute("typeList", memberService.getMemberTypeList());
		model.addAttribute("newsForm", new NewsForm());
		return "news/save";
	}
	
	@PostMapping("add")
	public String addPost(
			HttpSession session, @Valid NewsForm newsForm, Model model, Errors errors, RedirectAttributes ra) throws Exception{
		if (errors.hasErrors()) {
			model.addAttribute("typeList", memberService.getMemberTypeList());
			return "news/save";
		}
		
		newsForm.setAuthor((String)session.getAttribute("loginId"));
		service.addNews(newsForm);
		ra.addFlashAttribute("statusMessage", "お知らせを追加しました。");
		return "redirect:/news";
	}
}
