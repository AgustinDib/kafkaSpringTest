package com.prismamp.todopago.repository

import com.prismamp.todopago.helper.EntityBuilder
import spock.lang.Specification

class CargoRepositorySpec extends Specification {

    CargoRepository repository = Spy(CargoRepository) {
        findAll() >> EntityBuilder.createCargos()
    }

    def "Búsqueda de Cargos por Base de Cálculo Transaccion"() {

        expect:
        x == repository.findByBaseCalculoTransaccion(a, b, c, d).size()

        where:
        a  | b     | c | d    || x
        37 | 40488 | 2 | 7    || 4
        37 | 40488 | 2 | null || 4
        37 | 40488 | 1 | null || 0
    }
}
