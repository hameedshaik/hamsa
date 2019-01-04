package com.sgl.smartpra.master.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgl.smartpra.common.model.Create;
import com.sgl.smartpra.common.model.Update;
import com.sgl.smartpra.master.app.service.RegionCountryService;
import com.sgl.smartpra.master.model.RegionCountry;

@RestController
public class RegionCountryController {

	@Autowired
	private RegionCountryService regionCountryService;

	@GetMapping("regions/{regionCode}/details")
	public List<RegionCountry> getAllRegionCountries(@PathVariable(value = "regionCode") String regionCode) {
		return regionCountryService.getAllRegionCountry(regionCode);
	}

	@GetMapping("regions/{regionCode}/details/{regionCountryId}")
	public RegionCountry getRegionCountryByRegionCountryId(@PathVariable(value = "regionCode") String regionCode,
			@PathVariable(value = "regionCountryId") Integer regionCountryId) {
		return regionCountryService.getRegionCountryByRegionCountryCode(regionCode, regionCountryId);

	}

	@PostMapping("regions/{regionCode}/details")
	public RegionCountry createRegionCountry(@PathVariable(value = "regionCode") String regionCode,
			@Validated(Create.class) @RequestBody RegionCountry regionCountry) {
		return regionCountryService.createRegionCountry(regionCode,  regionCountry);
	}

	@PutMapping("regions/{regionCode}/details/{regionCountryId}")
	public RegionCountry updateRegionCountry(@PathVariable(value = "regionCode") String regionCode, @PathVariable(value = "regionCountryId") Integer regionCountryId,
			@Validated(Update.class) @RequestBody RegionCountry regionCountry) {
		return regionCountryService.updateRegionCountry(regionCode, regionCountryId, regionCountry);

	}

	@DeleteMapping("regions/{regionCode}/details/{regionCountryId}")
	public void deleteRegionCountry(@PathVariable(value = "regionCode") String regionCode,
			@PathVariable(value = "regionCountryId") Integer regionCountryId) {
		regionCountryService.deleteRegionCountryByRegionCountryId(regionCountryId);
	}

}
