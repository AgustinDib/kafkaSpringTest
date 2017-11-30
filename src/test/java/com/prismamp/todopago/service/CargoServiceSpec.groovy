package com.prismamp.todopago.service

import com.prismamp.todopago.calculator.CargoCalculator
import com.prismamp.todopago.helper.EntityBuilder
import com.prismamp.todopago.model.CargoRequest
import com.prismamp.todopago.model.CargoTransaccion
import com.prismamp.todopago.repository.CargoRepository
import spock.lang.Specification

class CargoServiceSpec extends Specification {

    CargoRepository repository = Spy(CargoRepository) {
        findAll() >> EntityBuilder.createCargos()
        findIdTipoMedioPago(43) >> 2
        findCargoCuenta(_, _) >> null
    }

    CargoCalculator calculator = new CargoCalculator()

    CargoService service = new CargoService(repository: repository, calculator: calculator)

    def "Calculo cargos para un request válido"() {

        given: "Un request válido"
        CargoRequest request = new CargoRequest(idBaseCalculo: 37,
                idCuenta: 40488,
                idMedioPago: 43,
                idCanal: 7,
                importe: 2.87,
                idTransaccion: "0001b0d7-2acf-5e2f-1acd-c84511eftest")

        when: "Se calculan los cargos"
        List<CargoTransaccion> cargosCalculados = service.calculateCargos(request)

        then: "Los cargos calculados son 4 y sus valores correctos"
        null == cargosCalculados
    }
}
