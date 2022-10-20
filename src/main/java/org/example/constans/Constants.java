package org.example.constans;

public class Constants {

	//MENÚ
	public static final String SC_NUM_INVALIDO = "No has introducido un número valido";
	public static final String SC_MENU = "1. Nuevo cliente" + 
										"\n2. Obtener todos los clientes" + 
										"\n3. Obtener cliente por ID" + 
										"\n4. Obtener cliente por nombre y apellidos" + 
										"\n5. Actualizar cliente" + 
										"\n6. Eliminar cliente por ID" + 
										"\n7. Eliminar todos los clientes" + 
										"\n----------------------------------" +
										"\n8. Crear contrato nuevo" +
										"\n9. Asociar contrato nuevo con cliente" +
										"\n10. Obtener todos los contratos y clientes relacionados" +
										"\n11. Obtener contrato por ID" +
										"\n12. Obtener contrato por ID Cliente" +
										"\n13. Actualizar contrato por ID" +
										"\n14. Eliminar contrato por ID" +
										"\n15. Eliminar todos los contratos" +
										
										"\n0. Salir";

	public static final String SC_OPCION_INCORRECTA = "\nAtención, opción incorrecta\n";
	public static final String SC_FAILED = "\nAtención, has introducido un valor incorrecto o duplicado\n";
	
	// STRINGS MENSAJES PARA CLIENTES
	public static final String SC_FAILED_FIND_CLIENT = "\nAtención, no se ha localizado cliente con los datos facilitados\n";
	public static final String SC_NOT_FOUND_CLIENT = "\nNo hay clientes en la base de datos\n";
	public static final String SC_ID_CLIENTE = "Introduce el ID del cliente:";
	public static final String SC_NOMBRE_CLIENTE = "Introduce el nombre del cliente:";
	public static final String SC_APELLIDO1_CLIENTE = "Introduce el primer apellido del cliente:";
	public static final String SC_APELLIDO2_CLIENTE = "Introduce el segundo apellido del cliente:";
	public static final String SC_DNI_CLIENTE = "Introduce el DNI del cliente:";
	public static final String SC_DNI_REPETIDO = "\nIntroduciste un DNI repetido\n";
	public static final String SC_DESPEDIDA = "Adios";
	
	
	//STRINGS PARA CONTRATOS
	public static final String CC_AÑO_VIGENTE = "Introduce el año vigente del contrato";
	public static final String CC_MES_VIGENTE = "Introduce el mes vigente del contrato";
	public static final String CC_DIA_VIGENTE = "Introduce el día vigente del contrato";
	public static final String CC_AÑO_EXPIRACION = "Introduce el año de expiracion del contrato";
	public static final String CC_MES_EXPIRACION = "Introduce el mes de expiracion del contrato";
	public static final String CC_DIA_EXPIRACION = "Introduce el día de expriación del contrato";
	public static final String CC_PRECIO_CONTRATO = "Introduce el precio del contrato";
	public static final String CC_ID_CONTRATO = "Introduce el ID del contrato";
	public static final String CC_NOT_FOUND_CONTRACT = "\nNo se ha encontrado ningún contrato registrado\n";
}
