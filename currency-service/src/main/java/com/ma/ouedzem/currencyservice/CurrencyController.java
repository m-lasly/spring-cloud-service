package com.ma.ouedzem.currencyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
	@Autowired
	Environment environment;
	@Autowired
	CurrencyRepository currencyRepository;
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue=currencyRepository.findByFromAndTo(from, to);

		exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
		return exchangeValue;

	}
}
