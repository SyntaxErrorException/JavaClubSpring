package com.example.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Admin;
import com.example.app.service.AdminService;

@Controller
public class HomeController {
	@Autowired
	AdminService service;
	
	@GetMapping
	public String showHome(Model model) {
		model.addAttribute("admin", new Admin());
		return "home";
	}
	
	@PostMapping
	public String login(@Valid Admin admin, Errors errors, HttpSession session) throws Exception {
		//エラーが出たらホームへ
		if (errors.hasErrors()) {
			return "home";
		}
		//ユーザが入力したIDとパスワードがDBと一致しなかったらホームへ
		if (!service.isCorrectIdAndPass(admin.getLoginId(), admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_pass");
			return "home";
		}
		//DBと一致したらセッションにログインIDを格納してコンテキストルートへ
		session.setAttribute("loginId", admin.getLoginId());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//セッションを破棄してトップページへ
		session.invalidate();
		return "redirect:/";
	}
}
