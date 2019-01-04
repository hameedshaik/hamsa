package com.sgl.smartpra.master.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgl.smartpra.common.model.Create;
import com.sgl.smartpra.common.model.Update;
import com.sgl.smartpra.master.app.service.RegionService;
import com.sgl.smartpra.master.model.Region;

@RestController
public class RegionController {

	@Autowired
	private RegionService regionService;;

	@GetMapping("/regions")
	public List<Region> getAllRegion(@RequestParam(value = "regionCode", required = false) String regionCode,
			@RequestParam(value = "regionName", required = false) String regionName,
			@RequestParam(value = "continentName", required = false) String continentName) {
		return regionService.getAllRegion(regionCode, regionName, continentName);

	}

	@GetMapping("/regions/{regionCode}")
	public Region getRegionByRegionCode(@PathVariable(value = "regionCode") String regionCode) {
		return regionService.findRegionByRegionCode(regionCode);
	}

	@PostMapping("/regions")
	public Region createRegion(@Validated(Create.class) @RequestBody Region region) {
		return regionService.createRegion(region);
	}

	@PutMapping("/regions/{regionCode}")
	public Region updateRegion(@PathVariable(value = "regionCode") String regionCode,
			@Validated(Update.class) @RequestBody Region region) {
		return regionService.updateRegion(regionCode, region);
	}

	@PutMapping("/regions/{regionCode}/deactivate")
	public void deactivateRegion(@Valid @PathVariable(value = "regionCode") String regionCode,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		regionService.deactivateRegion(regionCode, lastUpdatedBy);

	}

	@PutMapping("/regions/{regionCode}/activate")
	public void activateRegion(@Valid @PathVariable(value = "regionCode") String regionCode,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		regionService.activateRegion(regionCode, lastUpdatedBy);

	}

}