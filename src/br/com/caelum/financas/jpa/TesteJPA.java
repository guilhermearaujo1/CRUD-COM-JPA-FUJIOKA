package br.com.caelum.financas.jpa;
import br.com.caelum.financas.util.*;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.util.JPAUtil;
import br.com.caelum.financas.util.PopulaCliente;

public class TesteJPA {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		PopulaCliente popula = new PopulaCliente();
		
		
		
		System.out.println("1 - CRIAR");
		System.out.println("2 - ATUALIZAr");
		System.out.println("3 - DELETAR");
		System.out.println("4 - LISTAR");
		System.out.println("5 - ATIVAR");
		System.out.println("6 - DESATIVAR");
		System.out.println("7 - LISTAR PREFERENCIA");
		
		System.out.println("8 - BUSCAR CLIENTE POR ID USANDO EAGER");
		System.out.println("9 - BUSCAR CLIENTE POR ID USANDO LAZY");
		
		int input = scan.nextInt();
		
		switch (input) {
		case 1:
			PopulaCliente.popula();
			break;
		case 2:
			PopulaCliente.atualiza();
			break;
		case 3:
			PopulaCliente.remover();
			break;
		case 4:
			PopulaCliente.listar();
			break;
		case 5:
			PopulaCliente.ativar();
			break;
		case 6:
			PopulaCliente.desativar();
			break;
		case 7:
			PopulaCliente.listarPreferencia();
			break;
		case 8:
			PopulaCliente.BuscarClientePorIdEager();
			break;
		case 9:
			PopulaCliente.BuscarClientePorIdLazy();
			break;
		
		}
		
	}
}