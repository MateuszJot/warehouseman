#!/bin/bash

# Skrypt nie testowany


if [ -z "$JAVA_HOME" ]; then
    echo "ERROR: JAVA_HOME is not set"
    exit 1
fi

echo "Detected java version:"
"$JAVA_HOME/bin/java" -version

if ! command -v mvn &> /dev/null
then
    echo "ERROR: mvn (maven) could not be found"
    exit 1
fi

echo "Detected maven version:"
mvn -v | grep -i "Apache Maven"

if ! command -v python &> /dev/null
then
    echo "ERROR: python could not be found"
    exit 1
fi

PYVER="$(python --version)"
if [[ "$PYVER" != *"Python 3"* ]]; then
    echo "ERROR: python version is not 3.x"
    exit 1
fi

echo "Detected python version:"
echo "$PYVER"


echo "Starting all services in background..."

echo "Starting Auth service..."
pushd "warehouseman-auth"
mvn spring-boot:run &
popd

echo "Starting Product service..."
pushd "warehouseman-product"
mvn spring-boot:run &
popd

echo "Starting Warehouse service..."
pushd "warehouseman-warehouse"
mvn spring-boot:run &
popd

echo "Starting Frontend service..."
pushd "warehouseman-frontend"
python -m http.server 8080 &
popd


echo "Press Ctrl+C to stop all services"
wait

echo "Stopped"