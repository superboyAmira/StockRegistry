package ru.zakharenko.javaquerytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.javaquerytool.models.Payment;
import ru.zakharenko.javaquerytool.services.PaymentService;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	private final PaymentService service;

	@Autowired
	public PaymentController(PaymentService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String getAllPayments(Model model) {
		List<Payment> ia = service.getAll();
		model.addAttribute("payments", ia);
		return "payment_view/payments";
	}

	@GetMapping("/{id}")
	public String getPaymentById(@PathVariable("id") Long id, Model model) {
		Payment ia = service.getById(id);
		model.addAttribute("payment", ia);
		return "payment_view/detail-payment";
	}

	@GetMapping("/create")
	public String createPaymentForm(Model model) {
		model.addAttribute("payment", new Payment());
		return "payment_view/create-payment";
	}

	@PostMapping("/")
	public String createPayment(@ModelAttribute Payment pay) {
		pay.setId(service.getMaxId() + 1);
		service.create(pay);
		return "redirect:/payments/";
	}

	@GetMapping("/edit/{id}")
	public String editPaymentForm(@PathVariable("id") Long id, Model model) {
		Payment ia = service.getById(id);
		if (ia == null) {
			System.err.println("Payment not found");
			return "redirect:/payments/";
		}
		model.addAttribute("payment", ia);
		return "payment_view/edit-payment";
	}

	@PostMapping("/update/{id}")
	public String updatePayment(@PathVariable("id") Long id, @ModelAttribute Payment acc) {
		Payment exIa = service.getById(id);
		if (exIa != null) {
			acc.setId(id);
			acc.setIssuer(exIa.getIssuer());
			acc.setStock(exIa.getStock());
			acc.setSubject(exIa.getSubject());
			service.update(acc);
		}
		return "redirect:/payments/";
	}

	@GetMapping("/delete/{id}")
	public String deletePayment(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/payments/";
	}
}
