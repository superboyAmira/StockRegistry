package ru.zakharenko.javaquerytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.models.Subject;
import ru.zakharenko.javaquerytool.services.SubjectService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	private final SubjectService service;

	@Autowired
	public SubjectController(SubjectService ser) {
		this.service = ser;
	}

	@GetMapping("/")
	public String getAllSubjects(Model model) {
		List<Subject> s = service.getAll();
		model.addAttribute("subjects", s);
		return "subject_view/subjects";
	}

	@GetMapping("/create")
	public String createStockForm(Model model) {
		model.addAttribute("subject", new Subject());
		return "subject_view/create-subject";
	}

	@PostMapping("/")
	public String createSubject(@ModelAttribute Subject entity) {
		entity.setId(UUID.randomUUID());
		service.create(entity);
		return "redirect:/subjects/";
	}

	@GetMapping("/edit/{id}")
	public String editStockForm(@PathVariable("id") UUID id, Model model) {
		Subject s = service.getById(id);
		if (s == null) {
			System.err.println("Subj not found:SubjController:47");
			return "redirect:/subjects/";
		}
		model.addAttribute("subject", s);
		return "subject_view/edit-subject";
	}

	@PostMapping("/update/{id}")
	public String updateSubject(@PathVariable("id") UUID id, @ModelAttribute Subject entity) {
		entity.setId(id);
		service.update(entity);
		return "redirect:/subjects/";
	}

	@GetMapping("/delete/{id}")
	public String deleteSubject(@PathVariable("id") UUID id) {
		service.delete(id);
		return "redirect:/subjects/";
	}
}
