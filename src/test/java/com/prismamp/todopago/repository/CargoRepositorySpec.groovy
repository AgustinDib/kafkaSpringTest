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
        x == repository.findByDefault(a, b, c, sdf.parse("2016-08-12 12:20:24"), d).size()

        where:
        a     | b  | c    | d  || x
        30487 | 42 | null | 37 || 1
        30486 | 42 | null | 37 || 0
        30487 | 41 | null | 37 || 0
        30487 | 42 | null | 36 || 0
    }
}
