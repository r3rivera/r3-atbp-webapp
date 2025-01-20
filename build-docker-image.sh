#!/usr/bin/env bash
echo ""
echo ""
JAR_FILE="$(find ./target/*.jar | head -n 1 | cut -d '/' -f 3)"
echo ""
echo ""
echo "Current Docker Version"
docker --version
echo ""
echo "Current Directory is $(pwd)"
echo "Current User is $USER"
echo ""
echo "Start Creating Docker Image for ${JAR_FILE}"
chmod +x ./Dockerfile
GIT_COMMIT_LABEL="$(git log -1 --format=%h)"
BUILD_TIMESTAMP=$(date +%Y%m%d%H%M)
echo "COMMIT LABEL is ${GIT_COMMIT_LABEL} and BUILD_TIMESTAMP of ${BUILD_TIMESTAMP}"
DOCKER_TAG="r3app-atbp:${GIT_COMMIT_LABEL}-${BUILD_TIMESTAMP}"
echo "USING DOCKER IMAGE with ${DOCKER_TAG} TAG"
docker build -t ${DOCKER_TAG} --build-arg jar_name=${JAR_FILE} --no-cache -f ./Dockerfile .
echo "DONE Creating Docker Image"
