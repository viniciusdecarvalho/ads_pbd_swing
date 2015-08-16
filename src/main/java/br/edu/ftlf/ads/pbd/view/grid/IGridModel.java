package br.edu.ftlf.ads.pbd.view.grid;

import java.util.List;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public interface IGridModel<T> extends TableModel {

	/**
	 * 
	 * @param index
	 * @return retorna o objeto informado no parametro
	 */
	T getItem(int index);
	
	/**
	 * 
	 * @param index
	 * @return retorna uma lista com os os indices informados
	 */
	List<T> getItems(int[] indexs);
	
	/**
	 * 
	 * @return retorna a lista de objetos
	 */
	List<T> getAllItems();

	/**
	 * Adiciona um item a lista
	 * @param model
	 */
	void addItem(T model);

	/**
	 * Adicoina vários items a lista
	 * @param items
	 */
	void addItems(List<T> items);

	/**
	 * modifica um objeto em uma determinada posição
	 * @param index
	 * @param model
	 */
	void setItem(int index, T model);

	/**
	 * Modifica varios objetos 
	 * @param items
	 */
	void setItems(List<T> items);

	/** 
	 * Remove um objeto pelo indice
	 * @param index
	 */
	void removeItem(int index);

	/** 
	 * Remove varios items da lista
	 * @param index
	 */
	void removeItems(List<T> items);
	
	/** 
	 * Remove um objeto da lista
	 * @param index
	 */
	void removeItem(T model);

	/**
	 * Limpa a lista de items
	 */
	void clear();

	/**
	 * @return Colunas
	 */
	TableColumnModel getColumnModel();

	List<GridColumnDef> getColumns();

	Class<?> getBean();

	/**
	 * 
	 * @return Classe com as configurações das colunas
	 */
	void setBean(Class<?> beanClass);


}