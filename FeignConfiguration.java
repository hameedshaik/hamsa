package com.sgl.smartpra.master.app.configuration;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgl.smartpra.global.master.model.Carrier;
import com.sgl.smartpra.global.master.model.Country;
import com.sgl.smartpra.global.master.model.StandardArea;

@Configuration
public class FeignConfiguration {

	@FeignClient(value = "smartpra-global-master-app", path = "/global")
	public interface GlobalMasterFeignClient {

		@GetMapping("/countries/{countryCode}")
		public Country getCountryByCountryCode(@PathVariable(value = "countryCode") String countryCode);

		@GetMapping("/carriers")
		public List<Carrier> getAllCarrier(
				@RequestParam(value = "carrierCode", required = false) @Valid String carrierCode,
				@RequestParam(value = "carrierDesignatorCode", required = false)  String carrierDesignatorCode,
				@RequestParam(value = "carrierName1", required = false) String carrierName1,
				@RequestParam(value = "carrierName2", required = false) String carrierName2);
		
		@GetMapping("/airports/{airportCode}/validate")
		public boolean isValidAirportCodeOrCityCode(@PathVariable(value = "airportCode") String airportCode);
		

		@GetMapping("/standard-areas/{standardAreaCode}")
		public StandardArea getStandardAreaByStandardAreaCode(
				@PathVariable(value = "standardAreaCode") String standardAreaCode);
		

		
		@GetMapping("/airports/{stateCode}/isValid")
		public boolean isValidStateCode(@PathVariable(value = "stateCode") String stateCode);
		
	}

}
