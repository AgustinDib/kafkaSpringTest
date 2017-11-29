package com.prismamp.todopago.model;

import java.util.Date;

public class RangoCicloFacturacion {

	private Date desde;

	private Date hasta;

	public RangoCicloFacturacion(Date desde, Date hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
}
