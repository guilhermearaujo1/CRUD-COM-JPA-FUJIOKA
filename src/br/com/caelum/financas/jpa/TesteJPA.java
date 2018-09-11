package br.com.caelum.financas.jpa;
import br.com.caelum.financas.util.*;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.util.JPAUtil;
import br.com.caelum.financas.util.ClienteService;

public class TesteJPA {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		Scanner scan = new Scanner(System.in);
		ClienteService popula = new ClienteService();
		
		
		
		System.out.println("1 - CRIAR");
		System.out.println("2 - ATUALIZAr");
		System.out.println("3 - DELETAR");
		System.out.println("4 - LISTAR");
		System.out.println("5 - ATIVAR");
		System.out.println("6 - DESATIVAR DETACH");
		System.out.println("7 - LISTAR PREFERENCIA");
		
		System.out.println("8 - BUSCAR CLIENTE POR ID USANDO EAGER");
		System.out.println("9 - BUSCAR CLIENTE POR I3D USANDO LAZY");
		System.out.println("10 - REFRESH CLIENTE");
		
		int input = scan.nextInt();
		
		switch (input) {
		case 1:
			ClienteService.popula();
			break;
		case 2:
			ClienteService.atualiza();
			break;
		case 3:
			ClienteService.remover();
			break;
		case 4:
			ClienteService.listar();
			break;
		case 5:
			ClienteService.ativar();
			break;
		case 6:
			ClienteService.desativar();
			break;
		case 7:
			ClienteService.listarPreferencia();
			break;
		case 8:
			ClienteService.BuscarClientePorIdEager();
			break;
		case 9:
			ClienteService.BuscarClientePorIdLazy();
			break;
		case 10:
			ClienteService.refresh();
			break;
		}
		
	}
}