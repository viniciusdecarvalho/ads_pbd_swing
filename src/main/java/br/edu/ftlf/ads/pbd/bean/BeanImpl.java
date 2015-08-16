package br.edu.ftlf.ads.pbd.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Marcus Vinicius de Carvalho Silva
 * 
 * @param <ID>
 *            Tipo da Chave Primaria
 */
@MappedSuperclass
public abstract class BeanImpl<ID extends Serializable> extends
		AbstractPersistable<ID> implements Cloneable, Serializable, Bean<ID> {

	private static final long serialVersionUID = 1L;
	private transient PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public void setId(ID id) {
		super.setId(id);
		firePropertyChange("id", getId(), id);
	}

	@Version
	@Basic(fetch = FetchType.LAZY)
	@Column(insertable = false, updatable = false)
	private Timestamp ultimaAtualizacao;

	@Override
	public Timestamp getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	protected BeanImpl() {
	}

	@Override
	public void setUltimaAtualizacao(Timestamp ultimaAtualizacao) {
		firePropertyChange("ultimaAtualizacao", this.ultimaAtualizacao,
				this.ultimaAtualizacao = ultimaAtualizacao);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		BeanImpl<ID> other = (BeanImpl<ID>) obj;
		return getId().equals(other.getId());
	}
	
	@Override
	public Object clone() {
		@SuppressWarnings("unchecked")
		Bean<ID> bean = BeanUtils.instantiate(getClass());
		try {
			BeanUtils.copyProperties(this, bean);
		} catch (BeansException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
		return bean;
	}
}