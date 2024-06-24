package ru.zakharenko.javaquerytool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.services.StockService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/stocks")
public class StockController {
	private final StockService service;

	@Autowired
	public StockController(StockService ser) {
		this.service = ser;
	}

	@GetMapping("/")
	public String getAllStocks(Model model) {
		List<Stock> stocks = service.getAll();
		model.addAttribute("stocks", stocks);
		return "stock_view/stocks";
	}

	@GetMapping("/{id}")
	public String getStockById(@PathVariable("id") UUID id, Model model) {
		Stock stock = service.getById(id);
		model.addAttribute("stock", stock);
		return "stock_view/stock-detail";
	}

	@GetMapping("/create")
	public String createStockForm(Model model) {
		model.addAttribute("stock", new Stock());
		return "stock_view/create-stock";
	}

	@PostMapping("/")
	public String createStock(@ModelAttribute Stock stock) {
		stock.setId(UUID.randomUUID());
		service.create(stock);
		return "redirect:/stocks/";
	}

	@GetMapping("/edit/{id}")
	public String editStockForm(@PathVariable("id") UUID id, Model model) {
		Stock stock = service.getById(id);
		if (stock == null) {
			System.err.println("Stock not found:StockController:53");
			return "redirect:/stocks/";
		}
		model.addAttribute("stock", stock);
		return "stock_view/edit-stock";
	}

	@PostMapping("/update/{id}")
	public String updateStock(@PathVariable("id") UUID id, @ModelAttribute Stock stock) {
		Stock existStock = service.getById(id);
		if (existStock != null) {
			stock.setId(id);
			stock.setRegistry(existStock.getRegistry());
			stock.setSubject(existStock.getSubject());
			stock.setIssuerAccount(existStock.getIssuerAccount());
			service.update(stock);
		}
		return "redirect:/stocks/";
	}

	@GetMapping("/delete/{id}")
	public String deleteStock(@PathVariable("id") UUID id) {
		service.delete(id);
		return "redirect:/stocks/";
	}
}
