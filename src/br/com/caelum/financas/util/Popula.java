package br.com.caelum.financas.util;

import javax.persistence.Query;

public interface Popula {
	public void inserir(Object obj);
	public void atualiza(Object obj);
	public void buscar(Object obj);
	public void remover(Object obj);
	
	
}
