package com.srm.provasrm.model;

import java.math.BigDecimal;

public enum Risco {

	A(BigDecimal.ZERO), B(BigDecimal.TEN), C(BigDecimal.valueOf(20.0));

	private BigDecimal taxaJuros;

	Risco(BigDecimal taxaJuros) {
		this.setTaxaJuros(taxaJuros);
	}


	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

}
