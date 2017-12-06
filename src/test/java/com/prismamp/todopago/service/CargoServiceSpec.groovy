package com.prismamp.todopago.service

import com.prismamp.todopago.calculator.CargoCalculator
import com.prismamp.todopago.helper.EntityBuilder
import com.prismamp.todopago.model.CargoRequest
import com.prismamp.todopago.model.CargoTransaccion
import com.prismamp.todopago.model.PromocionResponse
import com.prismamp.todopago.model.ReglaBonificacion
import com.prismamp.todopago.model.TasaMedioPago
import com.prismamp.todopago.repository.CargoRepository
import spock.lang.Specification

import java.text.SimpleDateFormat

class CargoServiceSpec extends Specification {

    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss")

    ReglaBonificacion reglaTasaIndirecta = new ReglaBonificacion(
            tasaDirecta: false,
            tasaMedioPago: new TasaMedioPago(tasaDirecta: 10))

    PromocionResponse promocion = new PromocionResponse(
            bonificacionCFVendedor: 15,
            codigo: "PROMO_CTAS",
            idPromocion: 252)

    CargoRepository repository = Spy(CargoRepository) {
        findAll() >> EntityBuilder.createCargos()
        findIdCanalByNombre("EmbeddedForm") >> null
        findIdTipoByCodigo("BC_TX_PAGO") >> 37
        findIdTipoByCodigo("BC_TX_CUOTAS") >> 38
        findCargoCuenta(_, _, _) >> null
        findReglaBonificacion(5432) >> reglaTasaIndirecta
        findPromocion(25L, 5432) >> promocion
//        findIdTipoMedioPago(42) >> 1
//        findIdTipoMedioPago(43) >> 2
//        findCargoCuenta(_, _) >> null
//        findReglaBonificacion(2) >> reglaTasaIndirecta
//        findAcumuladorPromocionesMonto(_) >> 100
//        findVolumenReglaPromocionTasa(_) >> 50
//        findVolumenReglaPromocionBonificacion(_) >> 50
    }

    CargoCalculator calculator = new CargoCalculator()

    CargoService service = new CargoService(repository: repository, calculator: calculator)

    def "Calculo cargos para un request válido no de costo financiero"() {

        given: "Un request válido no de costo financiero"
        CargoRequest request = new CargoRequest(
                facilitiesPayments: 1,
                idCuenta: 30487,
                idMedioPago: 42,
                canal: "EmbeddedForm",
                importe: 55,
                idTransaccion: "001e28fe-ab5a-ebfa-125b-ca4f0900c86a",
                idPromotion: 5427,
                created: sdf.parse("2016-08-12 12:20:24"))

        when: "Se calculan los cargos"
        List<CargoTransaccion> cargosCalculados = service.calculateCargos(request)

        then: "Los cargos calculados son 1 y sus valores correctos"
        cargosCalculados.size() == 1
        cargosCalculados[0].montoCalculado.trunc(3) == 2.194
    }

    def "Calculo cargos para un request válido de costo financiero"() {

        given: "Un request válido de costo financiero"
        CargoRequest request = new CargoRequest(
                facilitiesPayments: 6,
                idCuenta: 5205,
                idMedioPago: 42,
                canal: "EmbeddedForm",
                importe: 3,
                idTransaccion: "000943b5-156a-7da7-91e6-2f042da1b741",
                idPromotion: 5432,
                created: sdf.parse("2015-12-28 05:13:16"))

        when: "Se calculan los cargos"
        List<CargoTransaccion> cargosCalculados = service.calculateCargos(request)

        then: "Los cargos calculados son 2 y sus valores correctos"
        cargosCalculados.size() == 2
        cargosCalculados[0].montoCalculado.trunc(3) == 0.147
        cargosCalculados[1].montoCalculado == null
    }
}
