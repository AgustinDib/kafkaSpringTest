package com.prismamp.todopago.repository

import com.prismamp.todopago.helper.EntityBuilder
import spock.lang.Specification

import java.text.SimpleDateFormat

class CargoRepositorySpec extends Specification {

    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss")

    CargoRepository repository = Spy(CargoRepository) {
        findAll() >> EntityBuilder.createCargos()
    }

    def "Búsqueda de Cargos por Base de Cálculo Transaccion"() {

        expect:
        x == repository.findByBaseCalculoTransaccion(a, b, c, d, sdf.parse("2012-10-10 10:10:10")).size()

        where:
        a  | b | c | d || x
        37 | 1 | 2 | 7 || 2
        37 | 1 | 1 | 7 || 0
        36 | 1 | 1 | 7 || 0
    }
}
