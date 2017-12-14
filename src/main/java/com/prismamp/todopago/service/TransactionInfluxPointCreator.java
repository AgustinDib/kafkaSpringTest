package com.prismamp.todopago.service;

import java.util.concurrent.TimeUnit;

import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.model.Transaccion;

@Service
public class TransactionInfluxPointCreator {

    @Value("${metrics.transaccionProcessedMeasurement}")
    protected String transaccionProcessedMeasurement;

    public Point createPoint(Transaccion tx) {


          return  Point.measurement(transaccionProcessedMeasurement)
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("client_id", tx.getCuentaId().toString())
                    .tag("payment_method", tx.getIdTipoMedioPago().toString())
                    .tag("promotion", tx.getIdPromotion().toString())
                    .addField("transaction_id", tx.getId())
                    .addField("original_ammount", tx.getImporte()).build();


    }
}
