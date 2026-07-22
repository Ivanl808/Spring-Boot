package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import com.bezkoder.spring.jpa.postgresql.repository.TutorialRepository;

/**
 * Controlador REST encargado de gestionar las operaciones CRUD
 * de la entidad Tutorial.
 *
 * Permite realizar:
 * Consultar todos los tutoriales.
 * Consultar un tutorial por ID.
 * Crear nuevos tutoriales.
 * Actualizar tutoriales existentes.
 * Eliminar tutoriales.
 */
@CrossOrigin(origins = "http://localhost:8081")
//Permite solicitudes desde la aplicación frontend ubicada en localhost:8081

@RestController
//Indica que esta clase funciona como un controlador REST
//y devuelve respuestas en formato JSON

@RequestMapping("/api")
//Define la ruta base para todos los endpoints del controlador
public class TutorialController {

	/**
	 * Inyección automática del repositorio.
	 *
	 * El repositorio permite comunicarse con la base de datos
	 * mediante operaciones JPA como guardar, consultar y eliminar.
	 */
	@Autowired
	TutorialRepository tutorialRepository;

	/**
	 * Obtiene todos los tutoriales registrados.
	 *
	 * También permite filtrar por título utilizando el parámetro "title".
	 *
	 * Ejemplos:
	 * GET /api/tutorials
	 * GET /api/tutorials?title=Java
	 *
	 * @param title título utilizado para realizar la búsqueda
	 * @return lista de tutoriales encontrados
	 */
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			
			// Lista donde se almacenarán los tutoriales encontrados
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			// Si no se recibe título, obtiene todos los registros
			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			
			// Si recibe título, realiza una búsqueda filtrada
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			// Si la lista está vacía devuelve código 204
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			// Devuelve la lista encontrada con código 200
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			
			// Error interno del servidor
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Busca un tutorial mediante su identificador.
	 *
	 * Ejemplo:
	 * GET /api/tutorials/1
	 *
	 * @param id identificador del tutorial
	 * @return tutorial encontrado
	 */
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		
		// Busca el tutorial por su ID en la base de datos
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		// Verifica si existe información
		if (tutorialData.isPresent()) {
			
			// Devuelve el tutorial encontrado
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			
			// Si no existe devuelve error 404
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Crea un nuevo tutorial.
	 *
	 * Ejemplo:
	 * POST /api/tutorials
	 *
	 * Recibe la información mediante JSON.
	 *
	 * @param tutorial objeto recibido desde la petición
	 * @return tutorial creado
	 */
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			
			// Guarda un nuevo tutorial en la base de datos
			// Por defecto se crea como no publicado
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			
			// Devuelve el objeto creado con código 201
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			
			// Error interno del servidor
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Actualiza un tutorial existente.
	 *
	 * Ejemplo:
	 * PUT /api/tutorials/1
	 *
	 * @param id identificador del tutorial
	 * @param tutorial datos nuevos del tutorial
	 * @return tutorial actualizado
	 */
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		
		// Busca el tutorial que será actualizado
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			
			// Obtiene el objeto existente
			Tutorial _tutorial = tutorialData.get();
			
			// Actualiza sus valores
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			
			// Guarda los cambios en la base de datos
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			
			// Si no encuentra el registro devuelve 404
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Elimina un tutorial específico por ID.
	 *
	 * Ejemplo:
	 * DELETE /api/tutorials/1
	 *
	 * @param id identificador del tutorial
	 */
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			
			// Elimina el registro mediante el repositorio
			tutorialRepository.deleteById(id);
			
			// Código 204 indica eliminación exitosa
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Elimina todos los tutoriales almacenados.
	 *
	 * Ejemplo:
	 * DELETE /api/tutorials
	 */
	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			
			// Elimina todos los registros de la tabla
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Obtiene solamente los tutoriales publicados.
	 *
	 * Ejemplo:
	 * GET /api/tutorials/published
	 *
	 * @return lista de tutoriales publicados
	 */
	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			
			// Consulta tutoriales cuyo campo published sea true
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			// Si no existen tutoriales publicados
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			// Devuelve la lista encontrada
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
