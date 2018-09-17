package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Cliente;

public abstract class PopulaAbs implements Popula {
	
	@Override
	public void inserir(Object obj) {
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
	}
	@Override
	public void atualiza(Object obj) {
	
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		em.close();
		
	}



	@Override
	public void buscar(Object obj) {
		EntityManager em = new JPAUtil().getEntityManger();
		//Query busca = em.createNativeQuery(query, Cliente.class);
		//obj = (Object) busca.getSingleResult();
		
		
	}



	@Override
	public void remover(Object obj, EntityManager em) {
		em.getTransaction().begin();
		em.remove(em.contains(obj) ? obj : em.merge(obj));
		em.getTransaction().commit();
		em.close();
		
	}
 
}
