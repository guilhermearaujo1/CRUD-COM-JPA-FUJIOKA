package br.com.caelum.financas.util;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Preferencia;

public class ClienteService extends PopulaAbs {
	EntityManager em = new JPAUtil().getEntityManger();
	private static PopulaAbs abs = new PopulaAbs() {
	};
	
	QueryBuilder queryBuilder = new QueryBuilder();

	public static void popula() {
		Preferencia preferencia = new Preferencia();
		Scanner scan = new Scanner(System.in);
		Cliente cliente = new Cliente();

		instCliente(scan, cliente);

		abs.inserir(cliente);
		// PopuplaPreferencia.savePreferencia();
	}

	private static void instCliente(Scanner scan, Cliente cliente) {
		System.out.println("digite o nome: ");
		cliente.setNome(scan.next());

		System.out.println("digite o sobrenome: ");
		cliente.setSobrenome(scan.next());

		System.out.println("digite atricula: ");
		cliente.setMatricula(scan.next());

		cliente.setAtivo(true);

	}

	public static void atualiza() {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		
		QueryBuilder queryBuilder = new QueryBuilder();
		cliente = (Cliente) queryBuilder.buildQueryBusca().getSingleResult();

		instCliente(scan, cliente);
		abs.atualiza(cliente);
		

	}
	
	public static void remover() {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		
		
		
		QueryBuilder queryBuilder = new QueryBuilder();
		cliente = (Cliente) queryBuilder.buildQueryBusca().getSingleResult();
		System.out.println(cliente.getNome());
		abs.remover(cliente, em);
		

	}

	public static List<Cliente> listar() {
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		QueryBuilder queryBuilder = new QueryBuilder();
		
		
		List<Cliente> lista = queryBuilder.buildQueryLista().getResultList();
		for (Cliente c : lista) {
			System.out.println(c);
		}
		em.getTransaction().commit();
		em.close();
		return lista;

	}

	public static void ativar() {
		Cliente cliente = new Cliente();

		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Matricula para ATIVAR CLIENTE: ");
		String matricula = scan.next();
		Query busca = em.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class);
		cliente = (Cliente) busca.getSingleResult();
		cliente.setAtivo(true);
		em.merge(cliente);
		System.out.println("CLIENTE ATIVADO COM SUSCESSSO");
		em.getTransaction().commit();
		em.close();
	}

	public static void desativar() {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Matricula para DESATIVAR CLIENTE: ");
		String matricula = scan.next();
		Query busca = em.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class);
		cliente = (Cliente) busca.getSingleResult();
		// cliente.setAtivo(false);
		em.detach(cliente);
		// em.merge(cliente);
		System.out.println("CLIENTE DESATIVADO COM SUSCESSSO");
		em.getTransaction().commit();
		em.close();
	}

	public static void listarPreferencia() {

		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Matricula para atualizar: ");
		String matricula = scan.next();
		Cliente cliente = (Cliente) em
				.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class)
				.getSingleResult();
		Preferencia preferencia = cliente.getPreferencia();
		System.out.println(preferencia.getPreferencia());
		em.getTransaction().commit();
		em.close();

	}

	public static void refresh() {
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		Cliente cliente = em.getReference(Cliente.class, 1);
		System.out.println("info cliente antes");
		System.out.println(cliente);
		em.getTransaction().commit();
		try {
			Thread.sleep(30000);
			em.refresh(cliente);
			System.out.println(cliente);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.close();
	}



}
