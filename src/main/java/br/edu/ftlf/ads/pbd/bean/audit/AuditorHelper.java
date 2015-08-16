package br.edu.ftlf.ads.pbd.bean.audit;

import br.edu.ftlf.ads.pbd.bean.audit.Auditoria.Operacao;
import br.edu.ftlf.ads.pbd.bean.audit.InstanteAuditoria.Instante;


public class AuditorHelper {

	private AuditorHelper() {}
	
	private static Auditoria before(Operacao operacao) {
		return new Auditoria(Instante.BEFORE, operacao);
	}

	private static Auditoria after(Operacao operacao) {
		return new Auditoria(Instante.AFTER, operacao);
	}

	public static Auditoria beforeInsert() {
		return before(Operacao.INSERT);
	}

	public static Auditoria afterInsert() {
		return after(Operacao.INSERT);
	}

	public static Auditoria beforeUpdate() {
		return before(Operacao.UPDATE);
	}

	public static Auditoria afterUpdate() {
		return after(Operacao.UPDATE);
	}

	public static Auditoria beforeDelete() {
		return before(Operacao.DELETE);
	}

	public static Auditoria afterDelete() {
		return after(Operacao.DELETE);
	}
	
}
