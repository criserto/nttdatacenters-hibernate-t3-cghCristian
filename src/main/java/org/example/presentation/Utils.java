package org.example.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.example.constans.Constants;

public class Utils {

	private static Constants constants;


	// MÉTODO PARA SACAR MENSAJE
	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	// MÉTODO PARA SACAR MENSAJE ERRONEO
	public static void mostrarMensajeErr(String mensaje) {
		System.err.println(mensaje);
	}

	// MÉTODO PARA SACAR MENSAJE Y LLAMADA AL MÉTODO DE RECOGIDA DE TEXTO
	public static String entradaTexto(String mensaje) {
		System.out.println(mensaje);
		return inLine();
	}

	// MÉTODO RECOGIDA DE TEXTO
	public static String inLine() {
		return new Scanner(System.in).nextLine();
	}

	// MÉTODO PARA SACAR MENSAJE Y LLAMADA AL MÉTODO DE RECOGIDA DE NÚMERO
	public static int entradaInt(String mensaje) {
		System.out.println(mensaje);
		return inInt();
	}

	// MÉTODO RECOGIDA DE NÚMERO
	public static int inInt() {
		try {
			return new Scanner(System.in).nextInt();
		} catch (InputMismatchException e) {
			System.out.println(constants.SC_NUM_INVALIDO);
			return inInt();
		}
	}
	
	// MÉTODO PARA SACAR MENSAJE Y LLAMADA AL MÉTODO DE RECOGIDA DE NÚMERO DOUBLE
	public static Double entradaDouble(String mensaje) {
		System.out.println(mensaje);
		return inDouble();
	}

	// MÉTODO RECOGIDA DE NÚMERO
	public static Double inDouble() {
		try {
			return new Scanner(System.in).nextDouble();
		} catch (InputMismatchException e) {
			System.out.println(constants.SC_NUM_INVALIDO);
			return inDouble();
		}
	}
}
