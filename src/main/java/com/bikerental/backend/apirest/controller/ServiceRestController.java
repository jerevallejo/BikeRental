package com.bikerental.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikerental.backend.apirest.models.entity.Service;
import com.bikerental.backend.apirest.models.services.IServiceService;

@RestController
@RequestMapping("/api")
public class ServiceRestController {

	@Autowired
	private IServiceService serviceService;

	@GetMapping("/services")
	public List<Service> index() {
		return serviceService.findAll();
	}

	@GetMapping("/service/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Service service = null;
		Map<String, Object> response = new HashMap<>();

		try {
			service = serviceService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta  en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (service == null) {
			response.put("mensaje", "El servicio ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Service>(service, HttpStatus.OK);
	}

	@PostMapping("/service")
	public ResponseEntity<?> create(@Valid @RequestBody Service service, BindingResult result) {
		Service serviceNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			serviceNew = serviceService.save(service);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El servicio ha sido creado con exito!");
		response.put("service", serviceNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/service/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Service service, BindingResult result, @PathVariable Long id) {
		Service serviceActual = serviceService.findById(id);// current, prevailing
		Service serviceUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (serviceActual == null) {
			response.put("message", "Error: no se puede editar el servicio ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			serviceActual.setName(service.getName());
			serviceActual.setDuration(service.getDuration());
			serviceActual.setPrice(service.getPrice());

			serviceUpdated = serviceService.save(serviceActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el servicio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con exito!");
		response.put("servicio", serviceUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/service/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			serviceService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el servicio de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El servicio fue eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
