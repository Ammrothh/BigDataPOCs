package com.example.duckdb;

import org.apache.arrow.flight.FlightServer;
import org.apache.arrow.flight.Location;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;

import java.io.IOException;

/**
 * A wrapper to start an Apache Arrow Flight server to share our DataFrames over gRPC.
 */
public class FlightServerWrapper implements AutoCloseable {

    private final BufferAllocator allocator;
    private final FlightServer server;

    public FlightServerWrapper(Location location) {
        this.allocator = new RootAllocator();
        // Here we would bind our DuckDBFlightProducer to handle requests.
        // For the skeleton, we initialize an empty producer.
        this.server = FlightServer.builder(allocator, location, new org.apache.arrow.flight.NoOpFlightProducer())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Flight server started on " + server.getLocation());
    }

    public void awaitTermination() throws InterruptedException {
        server.awaitTermination();
    }

    @Override
    public void close() throws Exception {
        server.close();
        allocator.close();
    }
}
