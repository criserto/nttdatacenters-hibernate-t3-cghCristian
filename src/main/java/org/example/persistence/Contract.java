package org.example.persistence;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CONTRACT", schema = "client")
public class Contract {
//	identificador
//	numérico, nombre, primer apellido, segundo apellido, y número de documento de identidad (único, no nulo y
//	hasta 9 caracteres

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "FECHA_VIGENCIA")
	private LocalDate fechaVigencia;

	@Column(name = "FECHA_CADUCIDAD")
	private LocalDate fechaCaducidad;

	@Column(name = "PRECIO")
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name = "CONTRATO_CLIENTE_ID", nullable = true)
	private Client client;
	

	public Contract() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	@Override
	public String toString() {
		return "Contract [id=" + id + ", fechaVigencia=" + fechaVigencia + ", fechaCaducidad=" + fechaCaducidad
				+ ", precio=" + precio + ", client=" + client + "]";
	}



}
