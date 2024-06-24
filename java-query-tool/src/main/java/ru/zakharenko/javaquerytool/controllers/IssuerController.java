package ru.zakharenko.javaquerytool.controllers;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.zakharenko.javaquerytool.models.Issuer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.services.IssuerService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/issuers")
public class IssuerController {
	private final IssuerService issuerService;

	@Autowired
	public IssuerController(IssuerService issuerService) {
		this.issuerService = issuerService;
	}

	@GetMapping("/")
	public String getAllIssuers(Model model) {
		List<Issuer> issuers = issuerService.getAll();
		model.addAttribute("issuers", issuers);
		return "issuer_view/issuers";
	}

	@GetMapping("/{id}")
	public String getIssuerById(@PathVariable("id") UUID id, Model model) {
		Issuer i = issuerService.getById(id);
		model.addAttribute("issuer", i);
		return "issuer_view/issuer-detail";
	}

	@GetMapping("/create")
	public String createStockForm(Model model) {
		model.addAttribute("issuer", new Issuer());
		return "issuer_view/create-issuer";
	}

	@PostMapping("/")
	public String createIssuer(@ModelAttribute Issuer i) {
		i.setId(UUID.randomUUID());
		issuerService.create(i);
		return "redirect:/issuers/";
	}

	@GetMapping("/edit/{id}")
	public String editStockForm(@PathVariable("id") UUID id, Model model) {
		Issuer i = issuerService.getById(id);
		if (i == null) {
			System.err.println("Issuer not found:IssuerController:57");
			return "redirect:/issuers/";
		}
		model.addAttribute("issuer", i);
		return "issuer_view/edit-issuer";
	}

	@PostMapping("/update/{id}")
	public String updateStock(@PathVariable("id") UUID id, @ModelAttribute Issuer i) {
		Issuer exI = issuerService.getById(id);
		if (exI != null) {
			i.setId(id);
			issuerService.update(i);
		}
		return "redirect:/issuers/";
	}

	@GetMapping("/delete/{id}")
	public String deleteStock(@PathVariable("id") UUID id) {
		issuerService.delete(id);
		return "redirect:/issuers/";
	}
}
