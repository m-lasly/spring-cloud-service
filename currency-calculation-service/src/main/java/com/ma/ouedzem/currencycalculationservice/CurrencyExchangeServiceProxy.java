package com.ma.ouedzem.currencycalculationservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-service", url = "localhost:8000")
//@FeignClient(name = "currency-service")
@FeignClient(name = "eureka-zuul-server")
@RibbonClient(name = "currency-service")

public interface CurrencyExchangeServiceProxy {
//	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyCalculationBean retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
