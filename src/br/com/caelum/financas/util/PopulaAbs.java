package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

		
	}



	@Override
	public void remover(Object obj) {
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
		em.close();
		
	}

}
