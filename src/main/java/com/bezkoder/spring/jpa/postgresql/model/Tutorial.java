package com.bezkoder.spring.jpa.postgresql.model;

import jakarta.persistence.*;

/**
 * Entidad Tutorial.
 * 
 * Esta clase representa la tabla "tutorials" dentro de la base de datos.
 * Cada objeto Tutorial corresponde a un registro de la tabla.
 */
@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla
@Table(name = "tutorials") // Especifica el nombre de la tabla en la base de datos
public class Tutorial {

	/**
	 *Identificador único del tutorial.
	 *Se genera automáticamente mediante la estrategia definida por JPA.
	 */
	
  @Id // Indica que este atributo es la llave primaria de la tabla
  @GeneratedValue(strategy = GenerationType.AUTO) // Genera el valor del ID automáticamente
  private long id;

  /**
   * Título del tutorial.
   * 
   * Se almacena en la columna "title" de la tabla tutorials.
   */
  @Column(name = "title") // Relaciona el atributo con la columna title
  private String title;

  /**
   * Descripción del tutorial.
   * 
   * Guarda información adicional sobre el contenido del tutorial.
   */
  @Column(name = "description") // Relaciona el atributo con la columna description
  private String description;

  /**
   * Indica si el tutorial está publicado o no.
   * 
   * true  = publicado
   * false = no publicado
   */
  @Column(name = "published") // Relaciona el atributo con la columna published
  private boolean published;

  /**
   * Constructor vacío requerido por JPA.
   * 
   * Hibernate utiliza este constructor para crear objetos
   * cuando recupera información desde la base de datos.
   */
  public Tutorial() {

  }

  /**
   * Constructor utilizado para crear un nuevo tutorial
   * con información inicial.
   *
   * @param title título del tutorial
   * @param description descripción del tutorial
   * @param published estado de publicación
   */
  public Tutorial(String title, String description, boolean published) {
    
	// Asigna el título recibido al atributo de la clase
	this.title = title;
	// Asigna la descripción recibida al atributo de la clase
    this.description = description;
    // Asigna el estado de publicación recibido
    this.published = published;
  }

  /**
   * Obtiene el identificador del tutorial.
   *
   * @return id del tutorial
   */
  public long getId() {
    return id;
  }

  /**
   * Obtiene el título del tutorial.
   *
   * @return título del tutorial
   */
  public String getTitle() {
    return title;
  }

  /**
   * Modifica el título del tutorial.
   *
   * @param title nuevo título
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Obtiene la descripción del tutorial.
   *
   * @return descripción del tutorial
   */
  public String getDescription() {
    return description;
  }

  /**
   * Modifica la descripción del tutorial.
   *
   * @param description nueva descripción
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Consulta si el tutorial está publicado.
   *
   * @return true si está publicado, false si no
   */
  public boolean isPublished() {
    return published;
  }

  /**
   * Cambia el estado de publicación del tutorial.
   *
   * @param isPublished nuevo estado de publicación
   */
  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  /**
   * Representación en texto del objeto Tutorial.
   * 
   * Se utiliza principalmente para mostrar información
   * del objeto durante pruebas o depuración.
   *
   * @return información del tutorial en formato String
   */
  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  }
  
}