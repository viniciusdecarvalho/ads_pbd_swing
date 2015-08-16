package br.edu.ftlf.ads.pbd.bean;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Transient;

public interface Bean<ID extends Serializable> extends Cloneable {

	ID getId();
	void setId(ID id);
	
	Timestamp getUltimaAtualizacao();
	void setUltimaAtualizacao(Timestamp ultimaAtualizacao);	
	
	@Transient
	public boolean isNew();
	Object clone();
	
	void addPropertyChangeListener(PropertyChangeListener listener);
	void removePropertyChangeListener(PropertyChangeListener listener);
	void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
	void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
	
}