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
import com.sgl.smartpra.master.app.service.POSService;
import com.sgl.smartpra.master.model.POS;

@RestController
public class POSController {

	@Autowired
	private POSService posService;

	@GetMapping("/pos")
	public List<POS> getAllpos(@RequestParam(value = "posCode", required = false) String posCode,
			@RequestParam(value = "posName", required = false) String posName,
			@RequestParam(value = "account", required = false) String account) {
		return posService.getAllPOS(posCode, posName, account);
	}

	@GetMapping("/pos/{posCode}")
	public POS getPosByPosCode(@PathVariable(value = "posCode") String posCode) {
		return posService.findPOSByPOSCode(posCode);
	}

	@PostMapping("/pos")
	public POS createPos(@Validated(Create.class) @RequestBody POS pos) {
		return posService.createPOS(pos);
	}

	@PutMapping("/pos/{posCode}")
	public POS updatePos(@PathVariable(value = "posCode") String posCode,
			@Validated(Update.class) @RequestBody POS pos) {
		return posService.updatePOS(posCode, pos);
	}

	@PutMapping("/pos/{posCode}/deactivate")
	public void deactivatePOS(@Valid @PathVariable(value = "posCode") String posCode,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		posService.deactivatePOS(posCode, lastUpdatedBy);
	}

	@PutMapping("/pos/{posCode}/activate")
	public void activatePOS(@Valid @PathVariable(value = "posCode") String posCode,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		posService.activatePOS(posCode, lastUpdatedBy);
	}
}
