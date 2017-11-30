package com.prismamp.todopago.helper

import com.prismamp.todopago.model.CanalAdhesion
import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.Tipo
import com.prismamp.todopago.model.TipoCargo
import com.prismamp.todopago.model.TipoMedioPago
import com.prismamp.todopago.model.Valor

class EntityBuilder {

    static List<Cargo> createCargos() {

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

        CanalAdhesion canal = new CanalAdhesion()

        Valor valor9 = new Valor(valor: 0.00, tipo: new Tipo(id: 44, codigo: "AP_PORCENTAJE"))
        Valor valor21 = new Valor(valor: 4.90, tipo: new Tipo(id: 44, codigo: "AP_PORCENTAJE"))
        Valor valor36 = new Valor(valor: 3.99, tipo: new Tipo(id: 44, codigo: "AP_PORCENTAJE"))
        Valor valor54 = new Valor(valor: 1.50, tipo: new Tipo(id: 44, codigo: "AP_PORCENTAJE"))

        TipoCargo tipoComision = new TipoCargo(id: 1, codigo: "COMISION")

        Cargo cargo7 = new Cargo(id: 7, tipoMedioPago: credito, idTipoCuenta: 27, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 1)
        Cargo cargo9 = new Cargo(id: 9, tipoMedioPago: debito, idTipoCuenta: 29, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 40488, valor: valor9, tipoCargo: tipoComision)
        Cargo cargo21 = new Cargo(id: 21, tipoMedioPago: debito, idTipoCuenta: 29, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 40488, valor: valor21, tipoCargo: tipoComision)
        Cargo cargo22 = new Cargo(id: 22, tipoMedioPago: efectivo, idTipoCuenta: 27, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 2)
        Cargo cargo28 = new Cargo(id: 28, tipoMedioPago: recargable, idTipoCuenta: 27, idBaseCalculo: 38, canalAdhesion: canal, idCuenta: 3)
        Cargo cargo36 = new Cargo(id: 36, tipoMedioPago: debito, idTipoCuenta: 29, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 40488, valor: valor36, tipoCargo: tipoComision)
        Cargo cargo54 = new Cargo(id: 54, tipoMedioPago: debito, idTipoCuenta: 29, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 40488, valor: valor54, tipoCargo: tipoComision)
        Cargo cargo138 = new Cargo(id: 138, tipoMedioPago: regalo, idTipoCuenta: 27, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 4)

        [cargo7, cargo9, cargo21, cargo22, cargo28, cargo36, cargo54, cargo138]
    }
}
