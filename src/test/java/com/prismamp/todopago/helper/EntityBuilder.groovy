package com.prismamp.todopago.helper

import com.prismamp.todopago.model.CanalAdhesion
import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.Tipo
import com.prismamp.todopago.model.TipoCargo
import com.prismamp.todopago.model.TipoMedioPago
import com.prismamp.todopago.model.Valor

import java.text.SimpleDateFormat

class EntityBuilder {

    static List<Cargo> createCargos() {

        TipoMedioPago credito = new TipoMedioPago(id: 1, codigo: "CREDITO", nombre: "Crédito", permiteAnulacion: false,
                permiteDevolucion: true, plazoDevolucion: 60, operaCuotas: true, permiteContracargo: false, permitidoBilletera: true)
        TipoMedioPago debito = new TipoMedioPago(id: 2, codigo: "DEBITO", nombre: "Débito", permiteAnulacion: false,
                permiteDevolucion: true, plazoDevolucion: 60, operaCuotas: true, permiteContracargo: false, permitidoBilletera: true)

        CanalAdhesion canal = new CanalAdhesion()

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss")

        Valor valor1 = new Valor(valor: 1.50, tipo: new Tipo(id: 43, codigo: "AP_FIJO"), inicioVigencia: sdf.parse("2011-10-10 10:10:10"), finVigencia: sdf.parse("2013-10-10 10:10:10"))
        Valor valor2 = new Valor(valor: 3.00, tipo: new Tipo(id: 43, codigo: "AP_FIJO"), inicioVigencia: sdf.parse("2014-10-10 10:10:10"), finVigencia: sdf.parse("2016-10-10 10:10:10"))
        Valor valor3 = new Valor(valor: 2.00, tipo: new Tipo(id: 44, codigo: "AP_PORCENTAJE"), inicioVigencia: sdf.parse("2011-10-10 10:10:10"), finVigencia: sdf.parse("2013-10-10 10:10:10"))
        Valor valor4 = new Valor(valor: 4.00, tipo: new Tipo(id: 44, codigo: "AP_PORCENTAJE"), inicioVigencia: sdf.parse("2014-10-10 10:10:10"), finVigencia: sdf.parse("2016-10-10 10:10:10"))

        TipoCargo tipoComision = new TipoCargo(id: 1, codigo: "COMISION")
        TipoCargo tipoCostoFinanciero = new TipoCargo(id: 2, codigo: "COSTO_FIN_V")

        Cargo cargo1 = new Cargo(tipoMedioPago: debito, idTipoCuenta: 37, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 1, valor: valor1, tipoCargo: tipoComision)
        Cargo cargo2 = new Cargo(tipoMedioPago: credito, idTipoCuenta: 37, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 1, valor: valor2, tipoCargo: tipoCostoFinanciero)
        Cargo cargo3 = new Cargo(tipoMedioPago: debito, idTipoCuenta: 37, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 1, valor: valor3, tipoCargo: tipoComision)
        Cargo cargo4 = new Cargo(tipoMedioPago: credito, idTipoCuenta: 37, idBaseCalculo: 37, canalAdhesion: canal, idCuenta: 1, valor: valor4, tipoCargo: tipoCostoFinanciero)

        [cargo1, cargo2, cargo3, cargo4]
    }
}
