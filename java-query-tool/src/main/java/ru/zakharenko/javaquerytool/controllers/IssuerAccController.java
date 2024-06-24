package ru.zakharenko.javaquerytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import ru.zakharenko.javaquerytool.models.IssuerAcc;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.services.IssuerAccService;

@Controller
@RequestMapping("/issuer_accounts")
public class IssuerAccController {
	private final IssuerAccService service;

	@Autowired
	public IssuerAccController(IssuerAccService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String getAllAccounts(Model model) {
		List<IssuerAcc> ia = service.getAll();
		model.addAttribute("issuer_accounts", ia);
		return "issuer_account_view/issuer_accounts";
	}

	@GetMapping("/{id}")
	public String getAccountById(@PathVariable("id") Long id, Model model) {
		IssuerAcc ia = service.getById(id);
		model.addAttribute("issuer_account", ia);
		return "issuer_account_view/issuer_account-detail";
	}

	@GetMapping("/create")
	public String createAccountForm(Model model) {
		model.addAttribute("issuer_account", new IssuerAcc());
		return "issuer_account_view/create-issuer_account";
	}

	@PostMapping("/")
	public String createAccount(@ModelAttribute IssuerAcc stock) {
		stock.setId(service.getMaxId() + 1);
		service.create(stock);
		return "redirect:/issuer_accounts/";
	}

	@GetMapping("/edit/{id}")
	public String editAccountForm(@PathVariable("id") Long id, Model model) {
		IssuerAcc ia = service.getById(id);
		if (ia == null) {
			System.err.println("Account not found:StockController:53");
			return "redirect:/issuer_accounts/";
		}
		model.addAttribute("issuer_account", ia);
		return "issuer_account_view/edit-issuer_account";
	}

	@PostMapping("/update/{id}")
	public String updateAccount(@PathVariable("id") Long id, @ModelAttribute IssuerAcc acc) {
		IssuerAcc exIa = service.getById(id);
		if (exIa != null) {
			acc.setId(id);
			acc.setIssuer(exIa.getIssuer());
			service.update(acc);
		}
		return "redirect:/issuer_accounts/";
	}

	@GetMapping("/delete/{id}")
	public String deleteAccount(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/issuer_accounts/";
	}
}
