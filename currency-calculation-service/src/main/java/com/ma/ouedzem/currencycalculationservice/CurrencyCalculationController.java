package com.ma.ouedzem.currencycalculationservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyCalculationController {

	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@GetMapping("/currency-calculation/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyCalculationBean retriveExchangeValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		// CurrencyCalculationBean exchangeValue = new CurrencyCalculationBean(1L, from,
		// to, BigDecimal.ONE,quantity, quantity );

		// exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
		ResponseEntity<CurrencyCalculationBean> currencyCalculationBean = new RestTemplate().getForEntity(url,
				CurrencyCalculationBean.class, uriVariables);
		CurrencyCalculationBean currencyCalculation = currencyCalculationBean.getBody();
		currencyCalculation.setQuantity(quantity);

		BigDecimal totalCalculationAmount = quantity.multiply(currencyCalculation.getConversionMultiple());
		currencyCalculation.setTotalCalculationAmount(totalCalculationAmount);

		return currencyCalculation;
	}
	
	@GetMapping("/currency-calculation-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyCalculationBean retriveExchangeValueFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyCalculationBean currencyCalculation = currencyExchangeServiceProxy.retriveExchangeValue(from, to);
		currencyCalculation.setQuantity(quantity);

		BigDecimal totalCalculationAmount = quantity.multiply(currencyCalculation.getConversionMultiple());
		currencyCalculation.setTotalCalculationAmount(totalCalculationAmount);

		return currencyCalculation;
	}


}
