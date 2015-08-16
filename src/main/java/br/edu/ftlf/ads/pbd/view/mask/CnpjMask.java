package br.edu.ftlf.ads.pbd.view.mask;

public class CnpjMask extends DefaultMask {

	private static final long serialVersionUID = -2565946096611124594L;
	public static final String MASK = "##.###.###/####-##";

	public CnpjMask() {
		super(MASK);
	}
}
