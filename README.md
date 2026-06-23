# DuckDB Java SDK
A highly optimized read/write performance SDK to access in-memory DuckDB, with features like higher-order functions and Arrow Flight for sharing data via gRPC.

## Architecture Highlights
- Uses **DuckDB** embedded for fast OLAP capabilities.
- Uses **Apache Arrow Database Connectivity (ADBC)** / **Arrow Flight** to avoid serialization and serialization overheads between C++ / JVM.
- Features a lazily evaluated **DataFrame API** (similar to Spark / Pandas).

## Setup
The project uses Maven. Run the following to build:
```sh
mvn clean install
```
