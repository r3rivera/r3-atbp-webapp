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

echo "## Displaying GIT Version..."
git --version | head -n 1
echo ""
echo "Git URL      :: ${GIT_URL}"
echo "Git Branch   :: ${GIT_BRANCH}"
echo "Build ID     :: ${BUILD_ID}"
echo "Build Number :: ${BUILD_NUMBER}"
echo "Git Branch   :: ${GIT_BRANCH}"
GIT_COMMIT_ID="$(git log | head -n 1 | cut -d '/' -f 2)"
BUILD_DATE="$(date +%m%d%Y)"
BUILD_TIME="$(date +%H%M%S)"
echo "Build Date   :: ${BUILD_DATE}"
echo "Build Time   :: ${BUILD_TIME}"
echo "Git Commit   :: ${GIT_COMMIT_ID}"
echo "############## COMPLETE: Build Of Your Web Application is SUCCESS! ##############"