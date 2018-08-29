package br.com.caelum.financas.util;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Preferencia;

public class PopulaCliente {
	EntityManager em = new JPAUtil().getEntityManger();

	public static void popula() {
		Preferencia preferencia = new Preferencia();
		Scanner scan = new Scanner(System.in);
		Cliente cliente = new Cliente();

		System.out.println("digite o nome: ");
		cliente.setNome(scan.next());

		System.out.println("digite o sobrenome: ");
		cliente.setSobrenome(scan.next());

		System.out.println("digite atricula: ");
		cliente.setMatricula(scan.next());

		cliente.setAtivo(true);

		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();

		System.out.println("Digite 0 para ADD preferencia Ou 1 para não");
		if (scan.nextInt() == 0) {
			preferencia.setIdCliente(cliente);
			preferencia.setPreferencia("CLIENTE PREFERENCIAL");

		} else {
			preferencia.setIdCliente(cliente);
			preferencia.setPreferencia("NENHUMA PREFENCIA");

		}

		EntityManager em1 = new JPAUtil().getEntityManger();
		em1.getTransaction().begin();
		em1.persist(preferencia);
		em1.getTransaction().commit();
		em1.close();

		scan.close();
	}

	public static void atualiza() {
		Cliente cliente = new Cliente();

		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Matricula para atualizar: ");
		String matricula = scan.next();
		Query busca = em.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class);
		cliente = (Cliente) busca.getSingleResult();

		System.out.println("digite o nome: ");
		cliente.setNome(scan.next());

		System.out.println("digite o sobrenome: ");
		cliente.setSobrenome(scan.next());

		System.out.println("digite matricula: ");
		cliente.setMatricula(scan.next());
		em.merge(cliente);
		em.getTransaction().commit();
		em.close();

	}

	public static void remover() {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Matricula para DELETAR: ");
		String matricula = scan.next();
		Query remover = em.createNativeQuery("Select * from cliente where matricula = " + matricula, Cliente.class);
		cliente = (Cliente) remover.getSingleResult();
		em.remove(cliente);
		em.getTransaction().commit();
		em.close();

	}

	public static List<Cliente> listar() {
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		Query consulta = em.createNativeQuery("Select * from cliente", Cliente.class);
		List<Cliente> lista = consulta.getResultList();
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
		cliente.setAtivo(false);
		em.merge(cliente);
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
		Cliente cliente = (Cliente) em.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class).getSingleResult();
		Preferencia preferencia = cliente.getPreferencia();
		System.out.println(preferencia.getPreferencia());
        em.getTransaction().commit();
		em.close();

	}

}
