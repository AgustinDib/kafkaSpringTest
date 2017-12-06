package com.prismamp.todopago.helper

import com.prismamp.todopago.model.Cargo
import com.prismamp.todopago.model.MedioPago
import com.prismamp.todopago.model.Tipo
import com.prismamp.todopago.model.TipoCargo
import com.prismamp.todopago.model.Valor

import java.text.SimpleDateFormat

class EntityBuilder {

    static List<Cargo> createCargos() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss")

        MedioPago medio1 = new MedioPago(id: 42)
        Valor valor1 = new Valor(inicioVigencia: sdf.parse("2015-08-12 12:20:24"), finVigencia: sdf.parse("2017-08-12 12:20:24"), valor: 3.99)
        Valor valor2 = new Valor(inicioVigencia: sdf.parse("2015-06-05 16:23:46"), finVigencia: sdf.parse("2016-08-09 23:59:59"), valor: 4.90)
        Valor valor3 = new Valor(inicioVigencia: sdf.parse("2015-07-09 12:44:59"))
        Tipo tipoPorcentaje = new Tipo(id: 44, codigo: "AP_PORCENTAJE")
        TipoCargo tipoComision = new TipoCargo(id: 1, codigo: "COMISION")
        TipoCargo tipoCostoFinanciero = new TipoCargo(id: 2, codigo: "COSTO_FIN_V")

        Cargo cargo1 = new Cargo(id: 31,
                idBaseCalculo: 37,
                idCuenta: 30487,
                medioPago: medio1,
                valor: valor1,
                tipoAplicacion: tipoPorcentaje,
                tipoCargo: tipoComision)

        Cargo cargo2 = new Cargo(id: 17,
                idBaseCalculo: 38,
                idCuenta: 5205,
                medioPago: medio1,
                valor: valor2,
                tipoAplicacion: tipoPorcentaje,
                tipoCargo: tipoComision)

        Cargo cargo3 = new Cargo(id: 26,
                idBaseCalculo: 38,
                idCuenta: 5205,
                medioPago: medio1,
                valor: valor3,
                tipoCargo: tipoCostoFinanciero)

        [cargo1, cargo2, cargo3]
    }
}
