package com.bikerental.backend.apirest.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikerental.backend.apirest.models.entity.Invoice;
import com.bikerental.backend.apirest.models.services.IClientService;

@RestController
@RequestMapping("/api")
public class InvoiceRestController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping("/invoices/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Invoice invoice = null;
		Map<String, Object> response = new HashMap<>();

		try {
			invoice = clientService.findInvoiceById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta  en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (invoice == null) {
			response.put("mensaje", "La factura ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}
	
	@PostMapping("/invoices")
	public ResponseEntity<?> create(@Valid @RequestBody Invoice invoice, BindingResult result) {
		Invoice invoiceNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			invoiceNew = clientService.saveInvoice(invoice);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "la factura ha sido creada con exito!");
		response.put("factura", invoiceNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clientService.deleteInvoiceById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la factura de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La factura fue eliminada con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
