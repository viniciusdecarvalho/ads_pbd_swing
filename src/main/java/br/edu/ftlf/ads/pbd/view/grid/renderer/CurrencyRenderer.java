package br.edu.ftlf.ads.pbd.view.grid.renderer;
import java.math.BigDecimal;
import java.text.NumberFormat;
 
public class CurrencyRenderer extends GridCellRenderer {
 
	private static final long serialVersionUID = 1L;
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	@Override
	protected void setValue(Object value) {
		if (value != null) {
			BigDecimal currencyValue = new BigDecimal(value.toString());
			setText(currencyValue != null ? formatter.format(currencyValue)	: "");
		}
	}

}