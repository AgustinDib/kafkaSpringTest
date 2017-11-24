package com.prismamp.todopago.calculator

import com.prismamp.todopago.exceptions.BusinessException
import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.CargoCuenta
import com.prismamp.todopago.model.CargoTransaccion
import com.prismamp.todopago.model.ReglaBonificacion
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

    def "Se calcula el monto de un Cargo fijo y por porcentaje"() {

        given: "Un Cargo con Valor con Tipo sin código"
        Cargo cargo = new Cargo(valor: new Valor(valor: 300, tipo: new Tipo()))

        when: "Se calcula el monto de un cargo fijo"
        cargo.valor.tipo.codigo = "AP_FIJO"
        CargoTransaccion cargoTransaccion = calculator.calculateMonto(null, cargo, 200)

        then: "El monto se obtiene directamente del Valor"
        cargoTransaccion.montoCalculado == 300

        when: "Se calcula el monto de un cargo por porcentaje"
        cargo.valor.tipo.codigo = "AP_PORCENTAJE"
        cargoTransaccion = calculator.calculateMonto(null, cargo, 200)

        then: "El monto se obtiene a través de la fórmula importe * (valor/100)"
        cargoTransaccion.montoCalculado == 600
    }

    def "Se calcular un costo financiero para tasa no directa con valores negativos y se tira una BusinessException"() {

        when: "Se costo financiero"
        calculator.calculateCostoFinanciero(null, importe, tasa, bonificacion, null)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        importe | tasa  | bonificacion  | expectedException
        2       | 5     | -5            | BusinessException
        -2      | 5     | 5             | BusinessException
        2       | -5    | 5             | BusinessException
        -2      | -5    | -5            | BusinessException
    }

    def "Se calcula un costo financiero para tasa no directa con valores válidos y se obtiene un resultado válido"() {

        expect:
        CargoTransaccion cargo = calculator.calculateCostoFinanciero(null, a, b, c, null)
        result == cargo.montoCalculado

        where:
        a  | b  | c || result
        1  | 1  | 0 || 1
        50 | 50 | 0 || 2500
        1  | 50 | 0 || 50
        50 | 1  | 0 || 50
    }

    def "Se calcula un costo financiero para tasa directa con valores negativos y se tira una BusinessException"() {

        when: "Se calcula el costo financiero"
        calculator.calculateCostoFinanciero(null, importe, tasa, null)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        importe | tasa | expectedException
        -2      | 5    | BusinessException
        2       | -5   | BusinessException
        -2      | -5   | BusinessException
    }

    def "Se calcula un costo financiero para tasa directa con valores válidos y se obtiene un resultado válido"() {

        expect:
        CargoTransaccion cargo = calculator.calculateCostoFinanciero(null, a, b, null)
        result == cargo.montoCalculado

        where:
        a   | b   || result
        100 | 1   || 1
        1   | 100 || 1
        100 | 100 || 100
    }

    def "Se calcula el valor aplicado con una regla de bonificación inválida y se tira una BusinessException"() {

        when: "Se calcula el valos aplicado"
        calculator.calculateValorAplicado(cargoTransaccion, regla)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        cargoTransaccion       | regla                                              | expectedException
        new CargoTransaccion() | new ReglaBonificacion(bonificacionCFVendedor: 101) | BusinessException
        new CargoTransaccion() | new ReglaBonificacion(bonificacionCFVendedor: -1)  | BusinessException
        new CargoTransaccion() | null                                               | BusinessException
    }

    def "Se calcula el valor aplicado para valores válidos y se obtiene un resultado válido"() {

        expect:
        CargoTransaccion cargo = calculator.calculateValorAplicado(a, b)
        result == cargo.valorAplicado

        where:
        a                      | b                                                  || result
        new CargoTransaccion() | new ReglaBonificacion(bonificacionCFVendedor: 100) || 0
        new CargoTransaccion() | new ReglaBonificacion(bonificacionCFVendedor: 50)  || 50
        new CargoTransaccion() | new ReglaBonificacion(bonificacionCFVendedor: 0)   || 100
    }
}