package br.edu.ftlf.ads.pbd.bean;

import java.awt.Color;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ftlf.ads.pbd.bean.Enums.MovimentoSituacao;
import br.edu.ftlf.ads.pbd.bean.audit.Audit;
import br.edu.ftlf.ads.pbd.bean.audit.AuditorListener;
import br.edu.ftlf.ads.pbd.bean.audit.MovimentoAudit;
import br.edu.ftlf.ads.pbd.view.grid.renderer.GridCellRenderer;

/**
 * The persistent class for the tbmovimentos database table.
 * 
 */
@Entity
@Table(name = "TbMovimentos")
@EntityListeners(AuditorListener.class)
@Audit(MovimentoAudit.class)
public class Movimento extends BeanImpl<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	public class SituacaoRenderer extends GridCellRenderer {

		private static final long serialVersionUID = 410553732163065291L;

		@Override
		protected void setValue(Object value) {
			if (value != null) {
				switch ((MovimentoSituacao)value) {
				case PAGO:
					setBackground(Color.GREEN);
					break;
				case CANCELADO:
					setBackground(Color.RED);
					break;
				default:
					setBackground(Color.WHITE);
					break;
				}
				setText(value.toString());
			}
		}
	}

	@Enumerated(EnumType.STRING)
	private MovimentoSituacao situacao;

	private BigDecimal valor;

	@OneToMany(mappedBy = "movimento")
	private List<Despesa> despesas;

	@ManyToOne
	@JoinColumn(name = "caixaId")
	private Caixa caixa;

	@ManyToOne
	@JoinColumn(name = "centroCustoId")
	private CentroCusto centroCusto;

	@OneToMany(mappedBy = "movimento")
	private List<Pagamento> pagamentos;

	@OneToMany(mappedBy = "movimento")
	private List<Receita> receitas;

	@ManyToOne
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	public Movimento() {
	}

	public MovimentoSituacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(MovimentoSituacao situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<Despesa> getDespesas() {
		return this.despesas;
	}

	public void setDespesas(List<Despesa> tbdespesas) {
		this.despesas = tbdespesas;
	}

	public Caixa getCaixa() {
		return this.caixa;
	}

	public void setCaixa(Caixa tbcaixa) {
		this.caixa = tbcaixa;
	}

	public CentroCusto getCentroCusto() {
		return this.centroCusto;
	}

	public void setCentroCusto(CentroCusto tbcentrocusto) {
		this.centroCusto = tbcentrocusto;
	}

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> tbpagamentos) {
		this.pagamentos = tbpagamentos;
	}

	public List<Receita> getReceitas() {
		return this.receitas;
	}

	public void setReceitas(List<Receita> tbreceitas) {
		this.receitas = tbreceitas;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario tbusuario) {
		this.usuario = tbusuario;
	}

}