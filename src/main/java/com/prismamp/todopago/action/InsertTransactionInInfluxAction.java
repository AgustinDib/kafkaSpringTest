package com.prismamp.todopago.action;

import java.util.UUID;
import java.util.function.Function;

import com.prismamp.todopago.InfluxDBService;
import com.prismamp.todopago.model.Transaccion;
import com.prismamp.todopago.service.TransactionInfluxPointCreator;

public class InsertTransactionInInfluxAction extends ActionVoid {

    @Override
    public Class[] getParamClasses() {
        return new Class[]{Transaccion.class, InfluxDBService.class, TransactionInfluxPointCreator.class};
    }

    @Override
    public Function<Param[], Void> getLambda() {
        return params -> {

            Transaccion transaccion = (Transaccion) params[0].getValue();
            InfluxDBService influxDBService = (InfluxDBService) params[1].getValue();
            TransactionInfluxPointCreator pointCreator = (TransactionInfluxPointCreator) params[2].getValue();


            if (transaccion != null) {
                influxDBService.addPoint(
                        () -> {
                            LOGGER.info("Enviando transaccion fallida a influxdb {}", transaccion != null ? transaccion.getId() : UUID.randomUUID().toString());
                            return pointCreator.createPoint(transaccion);
                        },

                        () -> "Ocurrio un error al intentar persistir la transaccion procesada con id [" + (transaccion != null ? transaccion.getId() : UUID.randomUUID().toString()) + "] en influxdb "
                );
                LOGGER.info("Fin persistencia en influx");
            } else {
                LOGGER.info("Ocurrio un error inesperado. Transaction es NULL.");
            }

            return null;
        };
    }

    public InsertTransactionInInfluxAction() {
        super();
    }

}
