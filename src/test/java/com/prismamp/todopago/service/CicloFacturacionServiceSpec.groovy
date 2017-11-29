package com.prismamp.todopago.service

import com.prismamp.todopago.exceptions.BusinessException
import com.prismamp.todopago.model.CicloFacturacion
import com.prismamp.todopago.model.RangoCicloFacturacion
import com.prismamp.todopago.repository.CicloFacturacionRepository
import spock.lang.Specification

import java.time.LocalDate
import java.time.ZoneId

class CicloFacturacionServiceSpec extends Specification {

    CicloFacturacionRepository repository = Spy(CicloFacturacionRepository) {
        findAll() >> createCiclosFacturacion()
    }

    CicloFacturacionService service = new CicloFacturacionService(repository: repository)

    def "Se obtiene el Rango del Ciclo de Facturación con una fecha nula y se tira una BusinessException"() {

        when: "Se obtiene el Rango del Ciclo de Facturación"
        service.getRangoCicloFacturacion(creacion)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        creacion  | expectedException
        null      | BusinessException
    }

    def "Se obtiene el Rango del Ciclo de Facturación con fechas válidas"() {

        expect:
        RangoCicloFacturacion rango = service.getRangoCicloFacturacion(new GregorianCalendar(ano, mes, dia).time)

        LocalDate localDateDesde = rango.desde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        LocalDate localDateHasta = rango.hasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        desdeDia == localDateDesde.dayOfMonth
        desdeMes == localDateDesde.monthValue
        desdeAno == localDateDesde.year
        hastaDia == localDateHasta.dayOfMonth
        hastaMes == localDateHasta.monthValue
        hastaAno == localDateHasta.year

        where:
        ano  | mes  | dia || desdeDia || desdeMes || desdeAno || hastaDia || hastaMes || hastaAno
        2010 | 7    | 10  || 1        || 8        || 2010     ||  15      || 8        || 2010
        2010 | 7    | 20  || 16       || 8        || 2010     ||  31      || 8        || 2010
        2010 | 7    | 1   || 1        || 8        || 2010     ||  15      || 8        || 2010
        2010 | 7    | 15  || 1        || 8        || 2010     ||  15      || 8        || 2010
        2010 | 7    | 16  || 16       || 8        || 2010     ||  31      || 8        || 2010
        2010 | 7    | 31  || 16       || 8        || 2010     ||  31      || 8        || 2010
    }

    List<CicloFacturacion> createCiclosFacturacion() {
        CicloFacturacion ciclo1 = new CicloFacturacion(diaInicio: 1, diaTopeIncluido: 15)
        CicloFacturacion ciclo2 = new CicloFacturacion(diaInicio: 16, diaTopeIncluido: 31)

        [ciclo1, ciclo2]
    }
}
