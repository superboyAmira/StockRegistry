package ru.zakharenko.javaquerytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.javaquerytool.models.Operation;
import ru.zakharenko.javaquerytool.services.OperationService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/operations")
public class OperationController {
	private final OperationService service;

	@Autowired
	public OperationController(OperationService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String getAllOperations(Model model) {
		List<Operation> ia = service.getAll();
		model.addAttribute("operations", ia);
		return "operations_view/operations";
	}

	@GetMapping("/{id}")
	public String getOperationById(@PathVariable("id") Long id, Model model) {
		Operation ia = service.getById(id);
		model.addAttribute("operation", ia);
		return "operations_view/detail-operation";
	}

	@GetMapping("/create")
	public String createOperationForm(Model model) {
		model.addAttribute("operation", new Operation());
		return "operations_view/create-operation";
	}

	@PostMapping("/")
	public String createOperation(@ModelAttribute Operation stock) {
		stock.setId(service.getMaxId() + 1);
		service.create(stock);
		return "redirect:/operations/";
	}

	@GetMapping("/edit/{id}")
	public String editOperationForm(@PathVariable("id") Long id, Model model) {
		Operation ia = service.getById(id);
		if (ia == null) {
			System.err.println("Operation not found:StockController:53");
			return "redirect:/operations/";
		}
		model.addAttribute("operation", ia);
		return "operations_view/edit-operation";
	}

	@PostMapping("/update/{id}")
	public String updateOperation(@PathVariable("id") Long id, @ModelAttribute Operation acc) {
		Operation exIa = service.getById(id);
		if (exIa != null) {
			acc.setId(id);
			service.update(acc);
		}
		return "redirect:/operations/";
	}

	@GetMapping("/delete/{id}")
	public String deleteOperation(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/operations/";
	}
}
