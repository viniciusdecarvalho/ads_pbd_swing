package br.edu.ftlf.ads.pbd.view.grid.renderer;

public class CpfCnpjRenderer extends GridCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setValue(Object value) {
		String text = value == null ? "" : value.toString();
		try {
			String cpfCnpj = text.length() == 14 ? cnpj(text) : cpf(text);
			setText(cpfCnpj);
		} catch (Exception e) {
			e.printStackTrace();
			setText(text);
		}
	}

	private String cpf(String value) {
		return value.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
				"$1.$2.$3-$4");
	}

	private String cnpj(String value) {
		return value.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
				"$1.$2.$3/$4-$5");
	}
}
