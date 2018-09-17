package br.com.caelum.financas.util;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Cliente;

public class QueryBuilder {
	public Query buildQueryBusca() {
		EntityManager em = new JPAUtil().getEntityManger();
		String matricula = null;
		System.out.println("Busca: \n [1]-Id \n [2]-Matricula");
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
		String parametro;
		if(scan.nextInt() == 1) {
			parametro = "id";
			
		}else {
			matricula = scan1.nextLine();
			parametro = "matricula";
		}
		Query busca = em.createNativeQuery("Select * from cliente where "+ parametro + " = " + matricula , Cliente.class);
		return busca;
	}
	
	public Query buildQueryLista() {
		EntityManager em = new JPAUtil().getEntityManger();
		Query consulta = em.createNativeQuery("Select * from cliente", Cliente.class);
		return consulta;
	}
}
