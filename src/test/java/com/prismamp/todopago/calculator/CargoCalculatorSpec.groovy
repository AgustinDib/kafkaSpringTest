package com.prismamp.todopago.calculator

import com.prismamp.todopago.exceptions.BusinessException
import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.CargoCuenta
import com.prismamp.todopago.model.CargoTransaccion
import com.prismamp.todopago.model.Tipo
import com.prismamp.todopago.model.TipoCargo
import com.prismamp.todopago.model.Valor
import spock.lang.Specification

class CargoCalculatorSpec extends Specification {

    CargoCalculator calculator = new CargoCalculator()

    def "Se quiere determinar si un Cargo con valores en null es costo financiero y se tira una BusinessException"() {

        when: "Se quiere determinar si es Costo Financiero"
        calculator.isCostoFinanciero(cargo)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        cargo                      | expectedException
        null                       | BusinessException
        new Cargo(tipoCargo: null) | BusinessException
    }

    def "Se quiere determinar si un Cargo válido es costo financiero"() {

        expect:
        x == calculator.isCostoFinanciero(cargo)

        where:
        cargo                                                       || x
        new Cargo(tipoCargo: new TipoCargo(codigo: "COSTO_FIN_V"))  || true
        new Cargo(tipoCargo: new TipoCargo(codigo: ""))             || false
    }

    def "Se calcula la vigencia de una relación con un Cargo o un CargoCuenta con valor null"() {

        when: "Se quiere calcular la vigencia de una relación"
        calculator.calculateRelacionVigente(cargoCuenta, cargo, null)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        cargoCuenta      | cargo       | expectedException
        null             | new Cargo() | BusinessException
        new CargoCuenta()| null        | BusinessException
    }

    def "Se calcula la vigencia de una relación con fechas vigentes y no vigentes"() {

        given: "Un Cargo con Valor"
        Cargo cargo = new Cargo(valor: new Valor(valor: 100, tipo: new Tipo(id: 666)))

        when: "Se calcula la relación para una fecha vigente"
        Date inicio = new Date(System.currentTimeMillis() - 3600 * 1000)
        Date fin = new Date(System.currentTimeMillis() + 3600 * 1000)
        CargoCuenta cargoCuenta = new CargoCuenta(valor: 50, idTipoAplicacion: 333, inicioVigencia: inicio, finVigencia: fin)

        CargoTransaccion cargoTransaccion = calculator.calculateRelacionVigente(cargoCuenta, cargo, null)

        then: "El Valor y el ID del tipo de aplicación se toman del CargoCuenta"
        cargoTransaccion.idTipoAplicacion == 333 && cargoTransaccion.valorAplicado == 50

        when: "Se calcula la relación para una fecha no vigente"
        inicio = new Date(System.currentTimeMillis() + 3600 * 1000)
        fin = new Date(System.currentTimeMillis() - 3600 * 1000)
        cargoCuenta = new CargoCuenta(valor: 50, idTipoAplicacion: 333, inicioVigencia: inicio, finVigencia: fin)

        cargoTransaccion = calculator.calculateRelacionVigente(cargoCuenta, cargo, null)

        then: "El Valor y el ID del tipo de aplicación se toman del Cargo"
        cargoTransaccion.idTipoAplicacion == 666 && cargoTransaccion.valorAplicado == 100
    }

    def "Se calcula el monto de un Cargo con valores null y se tira una BusinessException"() {

        when: "Se calcula el monto de un Cargo"
        calculator.calculateMonto(null, cargo, 0)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        cargo                                                     | expectedException
        null                                                      | BusinessException
        new Cargo(valor: null)                                    | BusinessException
        new Cargo(valor: new Valor(tipo: new Tipo(codigo: "XXX")))| BusinessException
    }
}