package br.com.caelum.financas.util;

import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Preferencia;

public class PreferenciaService {
	
	public static void savePreferencia() {
		EntityManager em = new JPAUtil().getEntityManger();
		Preferencia preferencia = new Preferencia();
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("0 CLIENTE PREFERENCIAL -------- 1 NENHUMA PREFERENCIA");
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
		
	}
	
	

