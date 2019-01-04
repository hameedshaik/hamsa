package com.sgl.smartpra.master.app.controller;

import java.sql.Timestamp;
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
import com.sgl.smartpra.master.app.service.SystemParameterService;
import com.sgl.smartpra.master.model.SystemParameter;

@RestController
public class SystemParameterController {

	@Autowired
	private SystemParameterService systemParameterService;

	@GetMapping("/system-parameters")
	public List<SystemParameter> getAllSystemParameter(
			@RequestParam(value = "groupName", required = false) String groupName,
			@RequestParam(value = "moduleName", required = false) String moduleName,
			@RequestParam(value = "parameterName", required = false) String parameterName) {
		return systemParameterService.getAllSystemParameters(groupName, moduleName, parameterName);
	}

	@GetMapping("/system-parameters/{parameterId}")
	public SystemParameter getSystemParameterByparameterId(@PathVariable(value = "parameterId") Integer parameterId) {
		return systemParameterService.findSystemParameterByParameterId(parameterId);
	}
	
	//not mentions is Documents  just keeping this api for safer side
	@GetMapping("/system-parameters-with-date/{parameterName}")
	public List<SystemParameter> getSystemParameterByparameterName(@PathVariable(value = "parameterName")String parameterName)  {
		
		return systemParameterService.findSystemParameterByParameterName(parameterName);
	}
	
	


	@PostMapping("/system-parameters")
	public SystemParameter createSystemParameter(
			@Validated(Create.class) @RequestBody SystemParameter systemParameter) {	
		return systemParameterService.createSystemParameter(systemParameter);
	}

	@PutMapping("/system-parameters/{parameterId}")
	public SystemParameter updateSystemParameter(@Valid @PathVariable(value = "parameterId") Integer parameterId,
			@Validated(Update.class) @RequestBody SystemParameter systemParameter) {
		return systemParameterService.updateSystemParameter(parameterId, systemParameter);
	}
	
	

	@PutMapping("/system-parameters/{parameterId}/deactivate")
	public void deactivateSystemParameter(@Valid @PathVariable(value = "parameterId") Integer parameterId,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		systemParameterService.deactivateSystemParameter(parameterId, lastUpdatedBy);
	}

	@PutMapping("/system-parameters/{parameterId}/activate")
	public void activateSystemParameter(@Valid @PathVariable(value = "parameterId") Integer parameterId,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy) {
		systemParameterService.activateSystemParameter(parameterId, lastUpdatedBy);
	}
}
