package org.example.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.example.persistence.Client;
import org.example.persistence.Contract;
import org.example.service.contractDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class contractDaoImpl implements contractDao {

	private static SessionFactory sessionFa;
	private static Session s;

	/**
	 * Construir la SessionFactory.
	 *
	 * @return
	 */
	private static SessionFactory buildSession() {

		sessionFa = new Configuration().configure().buildSessionFactory();
		return sessionFa;
	}

	@Override
	public void save(Contract t) {
		// TODO Auto-generated method stub
		s = buildSession().openSession();
		s.beginTransaction();
		
		s.save(t);
		
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public List<Contract> findAll() {
		// TODO Auto-generated method stub
		
		s = buildSession().openSession();

		CriteriaBuilder builder = s.getCriteriaBuilder();

		CriteriaQuery<Contract> criteriaQuery = builder.createQuery(Contract.class);

		criteriaQuery.from(Contract.class);

		List<Contract> contractList = s.createQuery(criteriaQuery).getResultList();

		s.close();

		return contractList;
		
	}

	@Override
	public Contract findByIdContract(Integer id) {
		s = buildSession().openSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Contract> criteriaQuery = builder.createQuery(Contract.class);
		Root<Contract> root = criteriaQuery.from(Contract.class);

		try {
			criteriaQuery.select(root);
			criteriaQuery.where(builder.equal(root.get("id"), id));

			Contract contract = s.createQuery(criteriaQuery).getSingleResult();

			s.close();
			return contract;
			
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * CONSULTA JOIN
	 */
	@Override
	public List<Contract> findByIdClient(Integer idClient) {
		
		
		s = buildSession().openSession();
		
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Contract> criteriaQuery = builder.createQuery(Contract.class);
		Root<Contract> root = criteriaQuery.from(Contract.class);
		Join<Contract, Client> cJoinC = root.join("client");

		try {
			
			Predicate pr1 = builder.equal(cJoinC.get("id"), idClient);
			
			criteriaQuery.select(root).where(builder.and(pr1));
			
			List<Contract> contract = s.createQuery(criteriaQuery).getResultList();
			
			s.close();
			return contract;
			
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ASOCIAR CONTRATO CREADO CON CLIENTE
	 */
	@Override
	public void asociarClienteContrato(Integer id, Client client) {
		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaUpdate<Contract> criteriaQuery = builder.createCriteriaUpdate(Contract.class);
		Root<Contract> root = criteriaQuery.from(Contract.class);

		criteriaQuery.set("client", client);
		criteriaQuery.where(builder.equal(root.get("id"), id));

		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();
	}
	
	@Override
	public void updateEntity(Contract object, Client client) {

		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaUpdate<Contract> criteriaQuery = builder.createCriteriaUpdate(Contract.class);
		Root<Contract> root = criteriaQuery.from(Contract.class);

		criteriaQuery.set("fechaCaducidad", object.getFechaCaducidad());
		criteriaQuery.set("fechaVigencia", object.getFechaVigencia());
		criteriaQuery.set("precio", object.getPrecio());
		criteriaQuery.set("client", client);
		criteriaQuery.where(builder.equal(root.get("id"), object.getId()));

		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();
		
	}

	@Override
	public void delecteById(Integer id) {
		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaDelete<Contract> criteriaQuery = builder.createCriteriaDelete(Contract.class);
		Root<Contract> root = criteriaQuery.from(Contract.class);

		criteriaQuery.where(builder.equal(root.get("id"), id));

		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();
		
	}

	@Override
	public void delecteAll() {
		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaDelete<Contract> criteriaQuery = builder.createCriteriaDelete(Contract.class);
		Root<Contract> root = criteriaQuery.from(Contract.class);

		criteriaQuery.from(Contract.class);
		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();		
	}



	
}
