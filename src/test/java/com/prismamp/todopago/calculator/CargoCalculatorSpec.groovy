package com.prismamp.todopago.calculator

import com.prismamp.todopago.exceptions.BusinessException
import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.CargoCuenta
import com.prismamp.todopago.model.CargoTransaccion
import com.prismamp.todopago.model.PromocionResponse
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

    def "Se calcula el monto de un Cargo con valores inválidos y se tira una BusinessException"() {

        when: "Se calcula el monto de un Cargo"
        calculator.calculateMonto(null, cargo, importe)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        cargo                         | importe | expectedException
        null                          | 100     | BusinessException
        new Cargo()                   | 100     | BusinessException
        new Cargo(valor: new Valor()) | null     | BusinessException
    }

    def "Se calcula el monto de un Cargo fijo y por porcentaje"() {

        given: "Un Cargo con Valor"
        Cargo cargo = new Cargo(valor: new Valor(valor: 100))

        when: "Se calcula el monto de un cargo fijo"
        CargoTransaccion cargoTransaccion = new CargoTransaccion(codigoAplicacion: "AP_FIJO")
        cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, 50)

        then: "El monto se obtiene directamente del Valor"
        cargoTransaccion.montoCalculado == 100

        when: "Se calcula el monto de un cargo por porcentaje"
        cargoTransaccion = new CargoTransaccion(codigoAplicacion: "AP_PORCENTAJE")
        cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, 50)

        then: "El monto se obtiene a través de la fórmula importe * (valor/100)"
        cargoTransaccion.montoCalculado == 50

        when: "Se calcula el monto de un cargo sin código de aplicación"
        cargoTransaccion = new CargoTransaccion(codigoAplicacion: null)
        cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, 50)

        then: "El monto es 0"
        cargoTransaccion.montoCalculado == 0
    }

    def "Se calcula el monto de un CargoTransaccion no de Tasa Directa con valores inválidos y se tira una BusinessException"() {

        when: "Se calcula el monto de un CargoTransaccion"
        calculator.calculateMonto(null, 50, promocion)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        promocion                     | expectedException
        null                          | BusinessException
        new PromocionResponse()       | BusinessException
    }

    def "Se calcula el monto de un CargoTransaccion no de Tasa Directa"() {

        given: "Una PromocionResponse válida"
        PromocionResponse promocion = new PromocionResponse(bonificacionCFVendedor: 50, tasaDirecta: 50)

        when: "Se calcula el monto con importe null"
        CargoTransaccion cargoTransaccion  = calculator.calculateMonto(null, null, promocion)

        then: "El monto se obtiene para un importe 0"
        cargoTransaccion.montoCalculado == 0

        when: "Se calcula el monto con importe 50"
        cargoTransaccion  = calculator.calculateMonto(null, 50, promocion)

        then: "El monto se obtiene para un importe 50"
        cargoTransaccion.montoCalculado == 12.5
    }

    def "Se calcula el monto de un CargoTransaccion de Tasa Directa"() {

        expect:
        CargoTransaccion cargo = calculator.calculateMontoTasaDirecta(null, a, b)
        result == cargo.montoCalculado

        where:
        a    | b    || result
        0    | 50   || 0
        null | 50   || 0
        50   | 0    || 0
        50   | null || 0
        50   | 50   || 25
    }

    def "Se calcula el valor aplicado con una promocion inválida y se tira una BusinessException"() {

        when: "Se calcula el valos aplicado"
        calculator.calculateValorAplicado(cargoTransaccion, promocion)

        then: "Se tira BusinessException"
        thrown(expectedException)

        where:
        cargoTransaccion       | promocion                                          | expectedException
        new CargoTransaccion() | new PromocionResponse(bonificacionCFVendedor: -1)  | BusinessException
        new CargoTransaccion() | new PromocionResponse(bonificacionCFVendedor: 101) | BusinessException
        new CargoTransaccion() | null                                               | BusinessException
    }

    def "Se calcula el valor aplicado para valores válidos y se obtiene un resultado válido"() {

        expect:
        CargoTransaccion cargo = calculator.calculateValorAplicado(a, b)
        result == cargo.valorAplicado

        where:
        a                      | b                                                  || result
        new CargoTransaccion() | new PromocionResponse(bonificacionCFVendedor: 100) || 0
        new CargoTransaccion() | new PromocionResponse(bonificacionCFVendedor: 50)  || 50
        new CargoTransaccion() | new PromocionResponse(bonificacionCFVendedor: 0)   || 100
    }
}