package com.prismamp.todopago.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.exception.Messages;
import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.CicloFacturacion;
import com.prismamp.todopago.model.RangoCicloFacturacion;
import com.prismamp.todopago.repository.CicloFacturacionRepository;

@Service
public class CicloFacturacionService {

	@Autowired
	CicloFacturacionRepository repository;

	/**
	 * Devuelve el Rango de Ciclo de Facturación correspondiente al mes y año de la
	 * transacción, basándose en los Ciclos de Facturación existentes.
	 * 
	 * @param creacion.
	 * @return RangoCicloFacturacion.
	 */
	public RangoCicloFacturacion getRangoCicloFacturacion(Date creacion) {
		if (null == creacion) {
			throw new BusinessException(Messages.NO_FECHA_CREACION);
		}
		LocalDate localDate = creacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int dia = localDate.getDayOfMonth();
		int mes = localDate.getMonthValue();
		int ano = localDate.getYear();

		List<CicloFacturacion> ciclos = repository.findAll();
		CicloFacturacion ciclo = ciclos.stream()
				.filter(a -> dia >= a.getDiaInicio() && dia <= a.getDiaTopeIncluido())
				.findFirst()
				.orElseThrow(() -> new BusinessException(Messages.NO_CICLO_FACTURACION));

		Date desde = new GregorianCalendar(ano, mes - 1, ciclo.getDiaInicio()).getTime();
		Date hasta = new GregorianCalendar(ano, mes - 1, ciclo.getDiaTopeIncluido()).getTime();

		return new RangoCicloFacturacion(desde, hasta);
	}
}
