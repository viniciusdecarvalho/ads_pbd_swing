package br.edu.ftlf.ads.pbd.bean.audit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ftlf.ads.pbd.bean.Bean;
import br.edu.ftlf.ads.pbd.bean.BeanImpl;
import br.edu.ftlf.ads.pbd.bean.Usuario;
import br.edu.ftlf.ads.pbd.bean.audit.InstanteAuditoria.Instante;
import br.edu.ftlf.ads.pbd.main.App;
import br.edu.ftlf.ads.pbd.spring.AutowireHelper;

public class AuditorListener {

	private Logger logger = LoggerFactory.getLogger(AuditorListener.class);
	
	@Autowired
	private EntityManagerFactory entityManagerFactory; 	
	
	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	private static Usuario getUsuario() {
		return App.getUserInfo().getUsuario();
	}
	
	@PrePersist
	public void prePersist(Bean<?> bean) {
		logger.debug("prePersist: " + bean);
	}

	private void init() {
		if (entityManagerFactory == null) {
			AutowireHelper.autowire(this);
		}
	}
	
	@PostPersist
	public void postPersist(BeanImpl<?> bean) {
		if (isAudit(bean)) {
			init();
			EntityManager manager = getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			try {
				transaction.begin();
				persist(getAuditable(AuditorHelper.afterInsert().getInstante(), bean), manager);
				transaction.commit();
			} catch (Exception e) {
				logger.debug("auditor falhou entidade: {}", e.getMessage());
				if (transaction != null && transaction.isActive())
					transaction.rollback();
			} 
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T extends Bean<?>> Auditable<T> getAuditable(InstanteAuditoria instanteAuditoria,Bean bean) {
		Class<? extends Auditable> auditClass = bean.getClass().getAnnotation(Audit.class).value();
		Auditable instantiate = BeanUtils.instantiate(auditClass);
		instantiate.setBean(bean);
		instantiate.setInstante(instanteAuditoria);
		return instantiate;
	}
	
	private static boolean isAudit(Bean<?> bean) {
		return bean.getClass().isAnnotationPresent(Audit.class);
	}

	private void persist(Auditable<?> auditable, EntityManager manager) {
		auditable.getInstante().getAuditoria().setUsuario(getUsuario());
		manager.persist(auditable.getInstante().getAuditoria());
		manager.persist(auditable.getInstante());
		manager.persist(auditable);		
	}

	@PreUpdate
	public void preUpdate(Bean<?> bean) {
		
	}

	@PostUpdate
	public void postUpdate(Bean<?> bean) {		
		if (isAudit(bean)) {
			init();
			Auditoria auditoria = AuditorHelper.afterUpdate();
			EntityManager manager = getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			try {
				transaction.begin();
				Bean<?> currentDbBean = manager.find(bean.getClass(), bean.getId());
				persist(getAuditable(new InstanteAuditoria(Instante.BEFORE, auditoria), currentDbBean), manager);
				persist(getAuditable(new InstanteAuditoria(Instante.AFTER, auditoria), bean), manager);
				transaction.commit();
			} catch (Exception e) {
				logger.debug("auditor falhou entidade: {}", e.getMessage());
				if (transaction != null && transaction.isActive())
					transaction.rollback();
			} 
		} 
	}

	@PreRemove
	public void preRemove(Bean<?> bean) {
		logger.debug("preRemove: " + bean);
	}

	@PostRemove
	public void postRemove(Bean<?> bean) {
		logger.debug("postRemove: " + bean);
		if (isAudit(bean))  {
			init();
			EntityManager manager = getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			try {
				transaction.begin();
				persist(getAuditable(AuditorHelper.afterDelete().getInstante(), bean), manager);
				transaction.commit();
			} catch (Exception e) {
				logger.debug("auditor falhou entidade: {}", e.getMessage());
				if (transaction != null && transaction.isActive())
					transaction.rollback();
			} 
		}
	}

	@PostLoad
	public void postLoad(Bean<?> bean) {
		logger.debug("postLoad: " + bean);
	}

}
