package com.vcr.app.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.vcr.app.exceptions.CustomErrorResponse;
import com.vcr.app.exceptions.ResourceNotFoundException;
import com.vcr.app.helpers.TimeStampUtility;
import com.vcr.app.repo.impl.ClientRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.vcr.app.document.Clients;

@RestController
@Api(value = "Client Management System", description = "Operations pertaining to Charges in Charges Management System")
public class ClientController {

	private String exception = "";

	@Autowired
	ClientRepository clientsRepository;

	@GetMapping(value = "/api/clients")
	@ApiOperation(value = "View a list of Active Client")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Clients"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public Iterable<Clients> Clients() {
		return clientsRepository.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "/api/clients")
	public ResponseEntity<String> save(@RequestBody Clients Clients) throws ResourceNotFoundException {

		Clients.setActive(false);
		Clients.setUpdateTimestamp(TimeStampUtility.currentMiliSecond());
		Clients prod = clientsRepository.save(Clients);
		if (prod != null) {
			exception = "Client Saved Successfuly";
		} else {
			throw new ResourceNotFoundException("Clients Not Saved..Please try later..!!");
		}
		return new ResponseEntity<String>(exception, HttpStatus.OK);
	}

	@GetMapping(value = "/api/clients/{id}")
	public Clients show(@PathVariable String id) throws ResourceNotFoundException {
		return clientsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Clients not found for this id :" + id));
	}

	@DeleteMapping(value = "/api/clients/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) throws ResourceNotFoundException {
		Clients Clients = clientsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :" + id));
		Clients.setActive(true);
		Clients prod = clientsRepository.save(Clients);
		if (prod != null) {
			exception = "Client Deleted Successfuly";
		} else {
			throw new ResourceNotFoundException("Client Not Deleted..Please try later..!!");
		}
		return new ResponseEntity<String>(exception, HttpStatus.OK);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

}
