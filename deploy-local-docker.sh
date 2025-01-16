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
echo "Start Creating Docker Image"
chmod +x ./Dockerfile

GIT_COMMIT_LABEL="$(git log -1 --format=%h)"
BUILD_TIMESTAMP=$(date +%Y%m%d%H%M)
echo "COMMIT LABEL is ${GIT_COMMIT_LABEL} and BUILD_TIMESTAMP of ${BUILD_TIMESTAMP}"

DOCKER_TAG="r3app-atbp:${GIT_COMMIT_LABEL}-${BUILD_TIMESTAMP}"
RUNNING_CONTAINER="$(docker container ps -a | awk 'NR>1 {print $1, $2}' | grep 'r3app-atbp' | awk '{print $1}')"

echo "Stopping an existing docker container of ${RUNNING_CONTAINER}..."
DOCKER_CTR_STOP="$(docker container stop ${RUNNING_CONTAINER})"
echo "Stop Status :: ${DOCKER_CTR_STOP}"

echo "Removing stopped container..."
DOCKER_CTR_RM="$(docker container rm ${RUNNING_CONTAINER})"
echo "Remove Status :: ${DOCKER_CTR_RM}"


#DOCKER_CURR="$(docker image rm ${DOCKER_TAG})"
#echo "Removing existing docker images..."
#echo "Remove Status :: ${DOCKER_CURR}"



docker build -t ${DOCKER_TAG} --build-arg jar_name=${JAR_FILE} --no-cache -f ./Dockerfile .
echo "Done Creating Docker Image"
echo ""
echo ""
echo "Deploying a new docker container...."
DOCKER_UUID="$(docker container run -p 8082:8082 -d --env-file .env --name ${DOCKER_TAG} ${DOCKER_TAG})"
echo "New Docker ID deployed :: ${DOCKER_UUID}"