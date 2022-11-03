package com.gdu.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.contact.domain.ContactVO;
import com.gdu.contact.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;

	// 목록 보기
	@GetMapping({"/", "cntc/list"})
	public String list(Model model) {
		model.addAttribute("contacts", contactService.findAllContacts());
		return "contact/list";
	}

	// 등록 화면
	@GetMapping("cntc/register")
	public String regi(ContactVO contact) {
		contactService.regiContact(contact);
		return "redirect:cntc/list";
	}
	
}
