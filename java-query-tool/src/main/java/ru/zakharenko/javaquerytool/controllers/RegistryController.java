package ru.zakharenko.javaquerytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.javaquerytool.models.Issuer;
import ru.zakharenko.javaquerytool.models.Registry;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.services.RegistryService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/registries")
public class RegistryController {
	private final RegistryService service;

	@Autowired
	public RegistryController(RegistryService ser) {
		this.service = ser;
	}

	@GetMapping("/")
	public String getAllRegistries(Model model) {
		List<Registry> r = service.getAll();
		model.addAttribute("regisrtries", r);
		return "registry_view/registries";
	}

	@GetMapping("/{id}")
	public String getRegistryById(@PathVariable("id") UUID id, Model model) {
		Registry r = service.getById(id);
		model.addAttribute("registry", r);
		return "registry_view/registry-detail";
	}

	@GetMapping("/create")
	public String createRegistryForm(Model model) {
		model.addAttribute("registry", new Registry());
		return "registry_view/create-registry";
	}

	@PostMapping("/")
	public String createRegistry(@ModelAttribute Registry r) {
		r.setId(UUID.randomUUID());
		service.create(r);
		return "redirect:/registries/";
	}

	@GetMapping("/edit/{id}")
	public String editRegistryForm(@PathVariable("id") UUID id, Model model) {
		Registry r = service.getById(id);
		if (r == null) {
			System.err.println("Registry not found");
			return "redirect:/registries/";
		}
		model.addAttribute("registry", r);
		return "registry_view/edit-registry";
	}

	@PostMapping("/update/{id}")
	public String updateRegistry(@PathVariable("id") UUID id, @ModelAttribute Registry r) {
		Registry exR = service.getById(id);
		if (exR != null) {
			r.setId(id);
			service.update(r);
		}
		return "redirect:/registries/";
	}

	@GetMapping("/delete/{id}")
	public String deleteRegistry(@PathVariable("id") UUID id) {
		service.delete(id);
		return "redirect:/registries/";
	}
}
