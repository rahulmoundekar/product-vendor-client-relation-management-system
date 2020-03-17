package com.vcr.app.controllers;

import java.time.LocalDateTime;
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

import com.vcr.app.document.Products;
import com.vcr.app.exceptions.CustomErrorResponse;
import com.vcr.app.exceptions.ResourceNotFoundException;
import com.vcr.app.helpers.TimeStampUtility;
import com.vcr.app.repo.impl.ProductRepository;

@RestController
public class ProductController {

	private String exception = "";

	@Autowired
	ProductRepository productRepository;

	@GetMapping(value = "/api/products")
	public Iterable<Products> product() {
		return productRepository.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "/api/products")
	public ResponseEntity<String> save(@RequestBody Products product) throws ResourceNotFoundException {

		product.setActive(false);
		product.setUpdate_timestamp(TimeStampUtility.currentMiliSecond());
		Products prod = productRepository.save(product);
		if (prod != null) {
			exception = "Proudct Saved Successfuly";
		} else {
			throw new ResourceNotFoundException("Product Not Saved..Please try later..!!");
		}
		return new ResponseEntity<String>(exception, HttpStatus.OK);
	}

	@GetMapping(value = "/api/products/{id}")
	public Products show(@PathVariable String id) throws ResourceNotFoundException {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :" + id));
	}

	@DeleteMapping(value = "/api/products/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) throws ResourceNotFoundException {
		Products product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :" + id));
		product.setActive(true);
		Products prod = productRepository.save(product);
		if (prod != null) {
			exception = "Proudct Deleted Successfuly";
		} else {
			throw new ResourceNotFoundException("Product Not Deleted..Please try later..!!");
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
