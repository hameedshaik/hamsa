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
import com.sgl.smartpra.master.app.exception.ServiceException;
import com.sgl.smartpra.master.app.service.ClientUserAreaService;
import com.sgl.smartpra.master.model.ClientUserArea;

@RestController
public class ClientUserAreaController {

	@Autowired
	private ClientUserAreaService clientUserAreaService;
	int parseClientUserAreaId = 0;
	private static final String CLIENTUSERDIDCHECK = "clientUserAreaId should be in digit";

	@GetMapping("/client-user-areas")
	public List<ClientUserArea> getClientListOfUserArea(
			@RequestParam(value = "userAreaCode", required = false) String userAreaCode,
			@RequestParam(value = "userAreaName", required = false) String userAreaName) {
		return clientUserAreaService.getClientListOfUserArea(userAreaCode, userAreaName);
	}

	@GetMapping("/client-user-areas/{userAreaId}")
	public ClientUserArea getClientUserAreaByUserAreaCode(@PathVariable(value = "userAreaId") String userAreaId) {
		try {
			parseClientUserAreaId=Integer.parseInt(userAreaId);
		}catch(NumberFormatException nfe) {
			throw new ServiceException(CLIENTUSERDIDCHECK);
		}
		return clientUserAreaService.getClientUserAreaByUserAreaCode(parseClientUserAreaId);
	}

	@PostMapping("/client-user-areas")
	public ClientUserArea createClientUserArea(@Validated(Create.class) @RequestBody ClientUserArea clientUserArea) {
		return clientUserAreaService.createClientUserArea(clientUserArea);
	}

	@PutMapping("/client-user-areas/{userAreaId}")
	public ClientUserArea updateClientUserArea(@PathVariable(value = "userAreaId") String userAreaId,
			@Validated(Update.class) @RequestBody ClientUserArea clientUserArea) {
		try {
			parseClientUserAreaId=Integer.parseInt(userAreaId);
		}catch(NumberFormatException nfe) {
			throw new ServiceException(CLIENTUSERDIDCHECK);
		}
		return clientUserAreaService.updateClientUserArea(parseClientUserAreaId, clientUserArea);
	}

	@PutMapping("/client-user-areas/{userAreaId}/deactivate")
	public void deactivateClientUserArea(@Valid @PathVariable(value = "userAreaId") String userAreaId,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		try {
			parseClientUserAreaId=Integer.parseInt(userAreaId);
		}catch(NumberFormatException nfe) {
			throw new ServiceException(CLIENTUSERDIDCHECK);
		}
		clientUserAreaService.deactivateClientUserArea(parseClientUserAreaId, lastUpdatedBy);

	}

	@PutMapping("/client-user-areas/{userAreaId}/activate")
	public void activateClientUserArea(@Valid @PathVariable(value = "userAreaId") String userAreaId,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		try {
			parseClientUserAreaId=Integer.parseInt(userAreaId);
		}catch(NumberFormatException nfe) {
			throw new ServiceException(CLIENTUSERDIDCHECK);
		}
		clientUserAreaService.activateClientUserArea(parseClientUserAreaId, lastUpdatedBy);

	}

}