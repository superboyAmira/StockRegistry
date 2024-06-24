package ru.zakharenko.javaquerytool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zakharenko.javaquerytool.models.Operation;
import ru.zakharenko.javaquerytool.models.Stock;
import ru.zakharenko.javaquerytool.services.OperationService;
import ru.zakharenko.javaquerytool.services.QueryService;
import ru.zakharenko.javaquerytool.services.StockService;

import java.util.List;

@Controller
@RequestMapping("/query/")
public class QueryController {
	private final QueryService service;

	@Autowired
	public QueryController(QueryService service) {
		this.service = service;
	}

	@GetMapping("/1table")
	public String execute1Table(Model model) {
		List<String> forms = service.getAllForms();
		model.addAttribute("forms", forms);
		return "query_view/1table";
	}
	@GetMapping("/2table")
	public String execute2Table(@RequestParam(value = "type", defaultValue = "Individual") String type,
	                            @RequestParam(value = "form", defaultValue = "Bond") String form,
	                            Model model) {
		List<Stock> stocks = service.getAllStockIndividual(type);
		List<Operation> operations = service.getAllBonds(form);
		model.addAttribute("stocks", stocks);
		model.addAttribute("operations", operations);
		model.addAttribute("defaultType", type);
		model.addAttribute("defaultForm", form);
		return "query_view/2table";
	}

	@GetMapping("/3table")
	public String execute3Table(@RequestParam(value = "subject_type", defaultValue = "Corporate") String subjectType,
	                            @RequestParam(value = "nominalVal", defaultValue = "1000") Long nominalVal,
	                            Model model) {
		List<Object[]> query1 = service.getAllInfoCorporate(subjectType);
		List<Object[]> query2 = service.getAllInfoStocksMoreThan1000(nominalVal);
		model.addAttribute("query1", query1);
		model.addAttribute("query2", query2);
		model.addAttribute("defaultSubjectType", subjectType);
		model.addAttribute("defaultNominalVal", nominalVal);
		return "query_view/3table";
	}
}
