package org.example.presentation;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.example.constans.Constants;
import org.example.persistence.Client;
import org.example.persistence.Contract;
import org.example.service.clientDao;
import org.example.service.contractDao;
import org.example.service.impl.clientDaoImpl;
import org.example.service.impl.contractDaoImpl;

public class Presentation {

	private static Client client;
	private static Contract contract;

	private static Constants constants;
	private static contractDao serviceContract = new contractDaoImpl();
	private static clientDao serviceClient = new clientDaoImpl();

	// MÉTODO PARA MOSTRAR MENU, SOLO MUESTRA EL MENSAJE DEL MENU
	public static void Menu() {
		Utils.mostrarMensaje(constants.SC_MENU);
	}

	// MÉTODO PARA CONTROLAR LA OPCIÓN ELEGIDA
	public static boolean opcionMenu(int opcion) {

		switch (opcion) {

		case 1: // NUEVO CLIENTE
			addClient();
			break;
		case 2: // CONSULTAR TODOS LOS CIENTES
			getAllClient();
			break;
		case 3: // CONSULTAR POR ID
			getClientById();
			break;
		case 4: // CONSULTAR POR NOMBRE Y APELLIDOS
			getClientByNameAndSubnames();
			break;
		case 5: // ACTUALIZAR CLIENTE
			updateClient();
			break;
		case 6: // ELIMINAR POR ID
			deleteClientById();
			break;
		case 7: // ELIMINAR TODOS
			deleteAll();
			break;

		// ----------CONTRATOS----------//

		case 8: // CREAR CONTRATO
			saveContract();
			break;
		case 9: // ASOCIAR CONTRATO CON CLIENTE
			asociarClienteContrato();
			break;
		case 10: // OBTENER TODOS LOS CONTRATOS
			getAllContract();
			break;

		case 11: // OBTENER CONTRATO POR ID CONTRATO
			getContractById();
			break;

		case 12: // OBTENER CONTRATO POR ID CLIENTE
			getContractByIdClient();
			break;

		case 13: // ACTUALIZAR CONTRATO POR ID
			updateContractById();
			break;
		case 14: // ELIMINAR CONTRATO POR ID
			deleteContractById();
			break;
		case 15: // ELIMINAR TODOS LOS CONTRATOS
			deleteAllContract();
			break;

		case 0: // SALIR
			Utils.mostrarMensaje(constants.SC_DESPEDIDA); // SOLO SACA EL MENSAJE DE DESPEDIDA
			return false; // SI SALES EL FALSE SE ASIGNA AL BOLEANO QUE CONTROLA LA REPETICIÓN DEL MENÚ

		default:
			Utils.mostrarMensajeErr(constants.SC_OPCION_INCORRECTA);
			break;
		}
		return true; // SIEMPRE QUE NO ELIJAS 0 ESTARÁ REPITIENDO EL MENÚ
	}

	/**
	 * MÉTODO AGREGAR CLIENTE RECOGIENDO PARÁMETROS SOLICITADOS
	 */
	public static void addClient() {

		String nombreCliente = Utils.entradaTexto(constants.SC_NOMBRE_CLIENTE);
		String apellido1 = Utils.entradaTexto(constants.SC_APELLIDO1_CLIENTE);
		String apellido2 = Utils.entradaTexto(constants.SC_APELLIDO2_CLIENTE);
		String dni = null;

		do {
			dni = Utils.entradaTexto(constants.SC_DNI_CLIENTE);
		} while (dni.length() != 9);

		String añoVigente = null;
		String mesVigente = null;
		String diaVigente = null;

		String añoExpiracion = null;
		String mesExpiracion = null;
		String diaExpiracion = null;

		// REPETIRA EL BUCLE SI NO INTRODUCES VALORES APROPIADOS AL DATO QUE SE PIDE
		do {
			añoVigente = Utils.entradaTexto(constants.CC_AÑO_VIGENTE);
		} while (Integer.parseInt(añoVigente) < 1 && Integer.parseInt(añoVigente) > 1950);

		do {
			mesVigente = Utils.entradaTexto(constants.CC_MES_VIGENTE);
		} while (Integer.parseInt(mesVigente) < 1 || Integer.parseInt(mesVigente) > 12);

		do {
			diaVigente = Utils.entradaTexto(constants.CC_DIA_VIGENTE);
		} while (Integer.parseInt(diaVigente) < 1 || Integer.parseInt(diaVigente) > 31);

		String fechaVigente = añoVigente.concat("-").concat(mesVigente).concat("-").concat(diaVigente);
		LocalDate fechaVigenteL = LocalDate.parse(fechaVigente);

		do {
			añoExpiracion = Utils.entradaTexto(constants.CC_AÑO_EXPIRACION);
		} while (Integer.parseInt(añoExpiracion) < 1 && Integer.parseInt(añoExpiracion) > 1950);

		do {
			mesExpiracion = Utils.entradaTexto(constants.CC_MES_EXPIRACION);
		} while (Integer.parseInt(mesExpiracion) < 1 || Integer.parseInt(mesExpiracion) > 12);

		do {
			diaExpiracion = Utils.entradaTexto(constants.CC_DIA_EXPIRACION);
		} while (Integer.parseInt(diaExpiracion) < 1 || Integer.parseInt(diaExpiracion) > 31);

		String fechaExpiracion = añoExpiracion.concat("-").concat(mesExpiracion).concat("-").concat(diaExpiracion);
		LocalDate fechaExpiracionL = LocalDate.parse(fechaExpiracion);

		Double precio = Utils.entradaDouble(constants.CC_PRECIO_CONTRATO);

		client = new Client();

		client.setNombre(nombreCliente);
		client.setApellido1(apellido1);
		client.setApellido2(apellido2);
		client.setDni(dni);
		client.setContract(serviceContract.findAll());

		serviceClient.save(client);

		contract = new Contract();
		
		contract.setFechaVigencia(fechaExpiracionL);
		contract.setFechaCaducidad(fechaExpiracionL);
		contract.setPrecio(precio);
		contract.setClient(client);

		serviceContract.save(contract);

	}

	/**
	 * MÉTODO OBTENER LISTA DE CLIENTES
	 */
	public static void getAllClient() {

		List<Client> myListClient = serviceClient.findAll();

		if (myListClient.size() > 0) {
			myListClient.stream().forEach(System.out::println);
		} else {
			Utils.mostrarMensajeErr(constants.SC_NOT_FOUND_CLIENT);
		}
	}

	/**
	 * MÉTODO OBTENER CLIENTE POR ID
	 */
	public static void getClientById() {

		Integer idClient = Utils.entradaInt(constants.SC_ID_CLIENTE);

		client = serviceClient.findById(idClient);

		if (client != null) {
			Utils.mostrarMensaje(client.toString());
		} else {
			Utils.mostrarMensajeErr(constants.SC_FAILED_FIND_CLIENT);
		}

	}

	/**
	 * MÉTODO OBTENER CLIENTE POR NOMBRE Y APELLIDOS, SOLO DEVUELVE 1
	 */
	public static void getClientByNameAndSubnames() {

		String nombreCliente = Utils.entradaTexto(constants.SC_NOMBRE_CLIENTE);
		String apellido1 = Utils.entradaTexto(constants.SC_APELLIDO1_CLIENTE);
		String apellido2 = Utils.entradaTexto(constants.SC_APELLIDO2_CLIENTE);

		client = serviceClient.findByName_Apellido1_Apellido2(nombreCliente, apellido1, apellido2);

		if (client != null) {
			Utils.mostrarMensaje(client.toString());
		} else {
			Utils.mostrarMensajeErr(constants.SC_FAILED_FIND_CLIENT);
		}

	}

	/**
	 * MÉTODO ACTUALIZAR CLIENTE POR PARÁMETROS PÁSADOS, SE COMPRUEBA SI EL CLIENTE
	 * EXISTE ANTES DE SOLICITAR.
	 */
	public static void updateClient() {

		Integer idCliente = Utils.entradaInt(constants.SC_ID_CLIENTE);
		String nombreCliente = Utils.entradaTexto(constants.SC_NOMBRE_CLIENTE);
		String apellido1 = Utils.entradaTexto(constants.SC_APELLIDO1_CLIENTE);
		String apellido2 = Utils.entradaTexto(constants.SC_APELLIDO2_CLIENTE);
		String dni = null;
		do {
			dni = Utils.entradaTexto(constants.SC_DNI_CLIENTE);
		} while (dni.length() != 9);

		if (serviceClient.findById(idCliente) == null) {
			Utils.mostrarMensajeErr(constants.SC_FAILED_FIND_CLIENT);
		} else {
			client = new Client();

			client.setId(idCliente);
			client.setNombre(nombreCliente);
			client.setApellido1(apellido1);
			client.setApellido2(apellido2);
			client.setDni(dni);

			serviceClient.updateEntity(client);
		}

	}

	/**
	 * MÉTODO ELIMINAR POR ID
	 */
	public static void deleteClientById() {

		Integer id = Utils.entradaInt(constants.SC_ID_CLIENTE);

		serviceClient.delecteById(id);

	}

	/**
	 * MÉTODO ELIMINAR TODOS
	 */
	public static void deleteAll() {

		serviceClient.delecteAll();

	}

	// --------------MÉTODOS CONTRATO -------------//

	private static void saveContract() {

		String añoVigente = null;
		String mesVigente = null;
		String diaVigente = null;

		String añoExpiracion = null;
		String mesExpiracion = null;
		String diaExpiracion = null;

		// REPETIRA EL BUCLE SI NO INTRODUCES VALORES APROPIADOS AL DATO QUE SE PIDE
		do {
			añoVigente = Utils.entradaTexto(constants.CC_AÑO_VIGENTE);
		} while (Integer.parseInt(añoVigente) < 1 && Integer.parseInt(añoVigente) > 1950);

		do {
			mesVigente = Utils.entradaTexto(constants.CC_MES_VIGENTE);
		} while (Integer.parseInt(mesVigente) < 1 || Integer.parseInt(mesVigente) > 12);

		do {
			diaVigente = Utils.entradaTexto(constants.CC_DIA_VIGENTE);
		} while (Integer.parseInt(diaVigente) < 1 || Integer.parseInt(diaVigente) > 31);

		String fechaVigente = añoVigente.concat("-").concat(mesVigente).concat("-").concat(diaVigente);
		LocalDate fechaVigenteL = LocalDate.parse(fechaVigente);

		do {
			añoExpiracion = Utils.entradaTexto(constants.CC_AÑO_EXPIRACION);
		} while (Integer.parseInt(añoExpiracion) < 1 && Integer.parseInt(añoExpiracion) > 1950);

		do {
			mesExpiracion = Utils.entradaTexto(constants.CC_MES_EXPIRACION);
		} while (Integer.parseInt(mesExpiracion) < 1 || Integer.parseInt(mesExpiracion) > 12);

		do {
			diaExpiracion = Utils.entradaTexto(constants.CC_DIA_EXPIRACION);
		} while (Integer.parseInt(diaExpiracion) < 1 || Integer.parseInt(diaExpiracion) > 31);

		String fechaExpiracion = añoExpiracion.concat("-").concat(mesExpiracion).concat("-").concat(diaExpiracion);
		LocalDate fechaExpiracionL = LocalDate.parse(fechaExpiracion);

		Double precio = Utils.entradaDouble(constants.CC_PRECIO_CONTRATO);

		Contract contract = new Contract();

		contract.setFechaVigencia(fechaExpiracionL);
		contract.setFechaCaducidad(fechaExpiracionL);
		contract.setPrecio(precio);
		contract.setClient(client);

		serviceContract.save(contract);

	}

	private static void asociarClienteContrato() {
		Integer idContract = Utils.entradaInt(constants.CC_ID_CONTRATO);
		Client myClient = serviceClient.findById(Utils.entradaInt(constants.SC_ID_CLIENTE));

		serviceContract.asociarClienteContrato(idContract, myClient);

	}

	private static void getAllContract() {
		List<Contract> myListContract = serviceContract.findAll();

		if (myListContract.size() > 0) {
			myListContract.stream().forEach(System.out::println);
		} else {
			Utils.mostrarMensajeErr(constants.CC_NOT_FOUND_CONTRACT);
		}
	}

	private static void getContractById() {

		contract = serviceContract.findByIdContract(Utils.entradaInt(constants.CC_ID_CONTRATO));

		if (contract != null) {
			Utils.mostrarMensaje(contract.toString());
		} else {
			Utils.mostrarMensajeErr(constants.CC_NOT_FOUND_CONTRACT);
		}

	}

	private static void getContractByIdClient() {

		client = serviceClient.findById(Utils.entradaInt(constants.SC_ID_CLIENTE));
		List <Contract> myContract = null;
		if (client!= null) {
			
			myContract = serviceContract.findByIdClient(client);

		}

		if (myContract != null) {
			myContract.stream().forEach(System.out::println);
		} else {
			Utils.mostrarMensajeErr(constants.CC_NOT_FOUND_CONTRACT);
		}

	}

	private static void updateContractById() {
		Integer idContract = Utils.entradaInt(constants.CC_ID_CONTRATO);

		String añoVigente = null;
		String mesVigente = null;
		String diaVigente = null;

		String añoExpiracion = null;
		String mesExpiracion = null;
		String diaExpiracion = null;

		// REPETIRA EL BUCLE SI NO INTRODUCES VALORES APROPIADOS AL DATO QUE SE PIDE
		do {
			añoVigente = Utils.entradaTexto(constants.CC_AÑO_VIGENTE);
		} while (Integer.parseInt(añoVigente) < 1 && Integer.parseInt(añoVigente) > 1950);

		do {
			mesVigente = Utils.entradaTexto(constants.CC_MES_VIGENTE);
		} while (Integer.parseInt(mesVigente) < 1 || Integer.parseInt(mesVigente) > 12);

		do {
			diaVigente = Utils.entradaTexto(constants.CC_DIA_VIGENTE);
		} while (Integer.parseInt(diaVigente) < 1 || Integer.parseInt(diaVigente) > 31);

		String fechaVigente = añoVigente.concat("-").concat(mesVigente).concat("-").concat(diaVigente);
		LocalDate fechaVigenteL = LocalDate.parse(fechaVigente);

		do {
			añoExpiracion = Utils.entradaTexto(constants.CC_AÑO_EXPIRACION);
		} while (Integer.parseInt(añoExpiracion) < 1 && Integer.parseInt(añoExpiracion) > 1950);

		do {
			mesExpiracion = Utils.entradaTexto(constants.CC_MES_EXPIRACION);
		} while (Integer.parseInt(mesExpiracion) < 1 || Integer.parseInt(mesExpiracion) > 12);

		do {
			diaExpiracion = Utils.entradaTexto(constants.CC_DIA_EXPIRACION);
		} while (Integer.parseInt(diaExpiracion) < 1 || Integer.parseInt(diaExpiracion) > 31);

		String fechaExpiracion = añoExpiracion.concat("-").concat(mesExpiracion).concat("-").concat(diaExpiracion);
		LocalDate fechaExpiracionL = LocalDate.parse(fechaExpiracion);

		Double precio = Utils.entradaDouble(constants.CC_PRECIO_CONTRATO);

		Client myClient = serviceClient.findById(Utils.entradaInt(constants.SC_ID_CLIENTE));
		contract = new Contract();
		contract.setFechaVigencia(fechaExpiracionL);
		contract.setFechaCaducidad(fechaExpiracionL);
		contract.setPrecio(precio);
		contract.setId(idContract);
		serviceContract.updateEntity(contract, myClient);

	}

	private static void deleteAllContract() {
		serviceContract.delecteAll();
	}

	private static void deleteContractById() {
		Integer id = Utils.entradaInt(constants.CC_ID_CONTRATO);

		serviceContract.delecteById(id);
	}

}
