package com.prismamp.todopago.repository

import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.TipoMedioPago
import spock.lang.Specification

class CargoRepositorySpec extends Specification {

    CargoRepository repository = Spy(CargoRepository) {
        findAll() >> createCargos()
    }

    def "Búsqueda de Cargos por Base de Cálculo Transaccion"() {

        expect:
        x == repository.findByBaseCalculoTransaccion(a, b, c).size()

        where:
        a  | b | c  || x
        27 | 1 | 37 || 1
        29 | 1 | 37 || 0
        29 | 4 | 38 || 1
    }

    List<Cargo> createCargos() {

        TipoMedioPago credito = new TipoMedioPago(id: 1, codigo: "CREDITO", nombre: "Crédito", permiteAnulacion: false,
                permiteDevolucion: true, plazoDevolucion: 60, operaCuotas: true, permiteContracargo: false, permitidoBilletera: true)
        TipoMedioPago debito = new TipoMedioPago(id: 2, codigo: "DEBITO", nombre: "Débito", permiteAnulacion: false,
                permiteDevolucion: true, plazoDevolucion: 60, operaCuotas: true, permiteContracargo: false, permitidoBilletera: true)
        TipoMedioPago efectivo = new TipoMedioPago(id: 3, codigo: "CREDITO", nombre: "Crédito", permiteAnulacion: false,
                permiteDevolucion: false, operaCuotas: false, permiteContracargo: false, permitidoBilletera: false)
        TipoMedioPago recargable = new TipoMedioPago(id: 4, codigo: "CREDITO", nombre: "Crédito", permiteAnulacion: false,
                permiteDevolucion: false, operaCuotas: false, permiteContracargo: false, permitidoBilletera: false)
        TipoMedioPago regalo = new TipoMedioPago(id: 5, codigo: "CREDITO", nombre: "Crédito", permiteAnulacion: false,
                permiteDevolucion: false, operaCuotas: false, permiteContracargo: false, permitidoBilletera: false)

        Cargo cargo1 = new Cargo(id: 1, tipoMedioPago: credito, idTipoCuenta: 27, idBaseCalculo: 37)
        Cargo cargo7 = new Cargo(id: 7, tipoMedioPago: debito, idTipoCuenta: 27, idBaseCalculo: 37)
        Cargo cargo22 = new Cargo(id: 22, tipoMedioPago: efectivo, idTipoCuenta: 27, idBaseCalculo: 37)
        Cargo cargo28 = new Cargo(id: 28, tipoMedioPago: recargable, idTipoCuenta: 29, idBaseCalculo: 38)
        Cargo cargo138 = new Cargo(id: 138, tipoMedioPago: regalo, idTipoCuenta: 27, idBaseCalculo: 37)

        [cargo1, cargo7, cargo22, cargo28, cargo138]
    }
}
