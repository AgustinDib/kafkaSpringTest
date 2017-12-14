package com.prismamp.todopago.config;

import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.writer.GaugeWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.util.concurrent.TimeUnit;

//@Configuration
public class MetricsConfig {


    private static final Logger LOGGER = LoggerFactory.getLogger(GaugeWriter.class);

    @Bean
    @ExportMetricWriter
    GaugeWriter influxMetricsWriter(@Autowired InfluxDBTemplate<Point> influxDB) {


        return value -> {
            Point point = Point.measurement(value.getName()).time(value.getTimestamp().getTime(), TimeUnit.MILLISECONDS)
                    .addField("value", value.getValue()).build();
            influxDB.write(point);
            LOGGER.info("write(" + value.getName() + "): " + value.getValue());
        };
    }

    @Bean
    public MetricsEndpointMetricReader metricsEndpointMetricReader(final MetricsEndpoint metricsEndpoint) {
        return new MetricsEndpointMetricReader(metricsEndpoint);
    }
}
