package org.example.service;

import java.util.List;

import org.example.persistence.Client;
import org.example.persistence.Contract;

public interface contractDao {

	void save(Contract t);

	public List<Contract> findAll();

	public Contract findByIdContract(Integer id);
	
	public List<Contract> findByIdClient(Integer idClient);
	
	public void asociarClienteContrato(Integer id, Client client);

	void updateEntity(Contract object, Client client);

	void delecteById(Integer id);

	void delecteAll();

}
