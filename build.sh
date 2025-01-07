#!/bin/bash
echo "############## START: Processing Build Of Your Web Application! ##############"
set -e

usage() {
  echo "Usage: $0 [clean] [package|install]"
  echo "Options:"
  echo "    clean      : Cleans the project before building."
  echo "    compile    : Compiles the projects."
  echo "    package    : Builds the project and packages it."
  echo "    install    : Builds and Installs the project in the maven repository."
}

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
  echo "Error: Maven is not installed OR not found!"
  exit 1
fi

# Checks if we have some args provided
if [ "$#" -eq 0]; then
  usage
fi

SCRIPT_DIR=$(dirname "$0")
cd "$SCRIPT_DIR" || exit

if [ "$1" == "clean" ]; then
  echo "Cleaning Project..."
  mvn clean
fi

if [ "$1" == "compile" ]; then
  echo "Compiling Project..."
  mvn compile
fi

if [ "$1" == "package" ]; then
  echo "Packaging Project..."
  mvn package
elif [ "$1" == "install" ]; then
  echo "Installing Project..."
  mvn install
else
  usage
fi
echo "############## COMPLETE: Build Of Your Web Application is SUCCESS! ##############"