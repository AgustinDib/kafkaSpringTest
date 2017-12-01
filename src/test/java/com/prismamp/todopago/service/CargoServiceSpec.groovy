package com.prismamp.todopago.service

import com.prismamp.todopago.calculator.CargoCalculator
import com.prismamp.todopago.helper.EntityBuilder
import com.prismamp.todopago.model.CargoRequest
import com.prismamp.todopago.model.CargoTransaccion
import com.prismamp.todopago.model.ReglaBonificacion
import com.prismamp.todopago.model.TasaMedioPago
import com.prismamp.todopago.repository.CargoRepository
import spock.lang.Specification

import java.text.SimpleDateFormat

class CargoServiceSpec extends Specification {

    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss")

    ReglaBonificacion reglaTasaDirecta = new ReglaBonificacion(
            tasaDirecta: true,
            bonificacionCFVendedor: 10,
            tasaDirectaIngresada: 10,
            tasaMedioPago: new TasaMedioPago(tasaDirecta: 100))

    ReglaBonificacion reglaTasaIndirecta = new ReglaBonificacion(
            tasaDirecta: false,
            bonificacionCFVendedor: 10,
            tasaDirectaIngresada: 10,
            tasaMedioPago: new TasaMedioPago(tasaDirecta: 100))

    CargoRepository repository = Spy(CargoRepository) {
        findAll() >> EntityBuilder.createCargos()
        findIdTipoMedioPago(42) >> 1
        findIdTipoMedioPago(43) >> 2
        findCargoCuenta(_, _) >> null
        findReglaBonificacion(1) >> reglaTasaDirecta
        findReglaBonificacion(2) >> reglaTasaIndirecta
        findAcumuladorPromocionesMonto(_) >> 100
        findVolumenReglaPromocionTasa(_) >> 50
        findVolumenReglaPromocionBonificacion(_) >> 50
    }

    CargoCalculator calculator = new CargoCalculator()

    CargoService service = new CargoService(repository: repository, calculator: calculator)

    def "Calculo cargos para un request válido con cargos no de costo financiero y valores de tipo comisión y fijo"() {

        given: "Un request válido con cargos no de costo financiero y valores de tipo comisión y fijo"
        CargoRequest request = new CargoRequest(
                idBaseCalculo: 37,
                idCuenta: 1,
                idMedioPago: 43,
                idCanal: 7,
                importe: 3.00,
                idTransaccion: "0001b0d7-2acf-5e2f-1acd-c84511eftest",
                created: sdf.parse("2012-10-10 10:10:10"))

        when: "Se calculan los cargos"
        List<CargoTransaccion> cargosCalculados = service.calculateCargos(request)

        then: "Los cargos calculados son 2 y sus valores correctos"
        cargosCalculados.size() == 2
        cargosCalculados[0].valorAplicado.trunc(2) == 1.5
        cargosCalculados[0].montoCalculado.trunc(2) == 1.5
        cargosCalculados[1].valorAplicado.trunc(2) == 2
        cargosCalculados[1].montoCalculado.trunc(2) == 0.06
    }

    def "Calculo cargos para un request válido con cargos de costo financiero y regla de bonificación con tasa directa"() {

        given: "Un request válido con cargos de costo financiero y valores de tipo comisión y fijo"
        CargoRequest request = new CargoRequest(
                idBaseCalculo: 37,
                idCuenta: 1,
                idMedioPago: 42,
                idCanal: 7,
                importe: 3.00,
                idTransaccion: "0001b0d7-2acf-5e2f-1acd-c84511eftest",
                created: sdf.parse("2015-10-10 10:10:10"),
                idPromotion: 1)

        when: "Se calculan los cargos"
        List<CargoTransaccion> cargosCalculados = service.calculateCargos(request)

        then: "Los cargos calculados son 2 y sus valores correctos"
        cargosCalculados.size() == 2
        cargosCalculados[0].valorAplicado.trunc(2) == 90
        cargosCalculados[0].montoCalculado.trunc(2) == 100.3
        cargosCalculados[1].valorAplicado.trunc(2) == 90
        cargosCalculados[1].montoCalculado.trunc(2) == 100.3
    }

    def "Calculo cargos para un request válido con cargos de costo financiero y regla de bonificación con tasa indirecta"() {

        given: "Un request válido con cargos de costo financiero y regla de bonificación con tasa indirecta"
        CargoRequest request = new CargoRequest(
                idBaseCalculo: 37,
                idCuenta: 1,
                idMedioPago: 42,
                idCanal: 7,
                importe: 3.00,
                idTransaccion: "0001b0d7-2acf-5e2f-1acd-c84511eftest",
                created: sdf.parse("2015-10-10 10:10:10"),
                idPromotion: 2)

        when: "Se calculan los cargos"
        List<CargoTransaccion> cargosCalculados = service.calculateCargos(request)

        then: "Los cargos calculados son 2 y sus valores correctos"
        cargosCalculados.size() == 2
        cargosCalculados[0].valorAplicado.trunc(2) == 90
        cargosCalculados[0].montoCalculado.trunc(2) == 370
        cargosCalculados[1].valorAplicado.trunc(2) == 90
        cargosCalculados[1].montoCalculado.trunc(2) == 370
    }
}
