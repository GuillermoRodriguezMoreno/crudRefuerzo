package org.iesvdm.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class Cliente {

	/*
	ALTER TABLE cliente
            ADD COLUMN email VARCHAR(255) NOT NULL
	* */
	
	private long id;
	@NotNull(message = "{msg.valid.notNull}")
	@NotEmpty(message = "{msg.valid.notEmpty}")
	@Size(max = 30, message = "{msg.valid.size30}")
	private String nombre;
	@NotNull(message = "{msg.valid.notNull}")
	@NotBlank(message = "{msg.valid.notBlank}")
	@NotEmpty(message = "{msg.valid.notEmpty}")
	@Size(max = 30, message = "{msg.valid.size30}")
	private String apellido1;
	private String apellido2;
	@NotNull(message = "{msg.valid.notNull}")
	@NotBlank(message = "{msg.valid.notBlank}")
	@NotEmpty(message = "{msg.valid.notEmpty}")
	@Size(max = 50, message = "{msg.valid.size30}")
	private String ciudad;
	@NotNull(message = "{msg.valid.notNull}")
	@Min(value = 100, message = "{msg.valid.minValue}")
	@Max(value = 1000, message = "{msg.valid.maxValue}")
	private int categoria;
	@NotNull(message = "{msg.valid.notNull}")
	@NotBlank(message = "{msg.valid.notBlank}")
	@NotEmpty(message = "{msg.valid.notEmpty}")
	@Email(message = "{msg.valid.email}")
	private String email;

	// Constructor
	public Cliente(){};
	
}
