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
		EntityManager em = new JPAUtil().getEntityManger();
		
		
		System.out.println("Matricula para atualizar: ");
		String matricula = scan.next();
		Query busca = em.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class);
		cliente = (Cliente) busca.getSingleResult();

		instCliente(scan, cliente);
		abs.atualiza(cliente);
		

	}

	public static void BuscarClientePorIdEager() {

		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Id para pesquisar: ");
		Scanner scan = new Scanner(System.in);
		Integer id = new Integer(scan.next());
		Cliente cliente = em.find(Cliente.class, id);
		System.out.println(cliente);
		em.getTransaction().commit();
		em.close();
		scan.close();

	}

	public static void BuscarClientePorIdLazy() {
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		System.out.println("Id para pesquisar: ");
		Scanner scan = new Scanner(System.in);
		Integer id = new Integer(scan.next());
		Cliente cliente = em.getReference(Cliente.class, id);
		System.out.println(cliente);
		em.getTransaction().commit();
		em.close();
	}

	public static void remover() {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManger();
		
		
		
		System.out.println("Matricula para DELETAR: ");
		String matricula = scan.next();
		Query busca = em.createNativeQuery("Select * from cliente where matricula =" + matricula, Cliente.class);
		cliente = (Cliente) busca.getSingleResult();
		abs.remover(cliente, em);
		

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

	@Override
	public void inserir(Object obj) {
		// TODO Auto-generated method stub
		EntityManager em = new JPAUtil().getEntityManger();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();

	}



}
