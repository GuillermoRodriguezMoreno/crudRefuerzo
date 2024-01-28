package org.iesvdm.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Comercial {

	private int id;
	@NotNull(message = "{msg.valid.notNull}")
	@NotBlank(message = "{msg.valid.notBlank}")
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
	@DecimalMin(value = "0.276", message = "{msg.valid.minValue}")
	@DecimalMax(value="0.946", message = "{msg.valid.maxValue}")
	private BigDecimal comision;

	// Constructor
	public Comercial(){};
}
