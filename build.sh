#!/bin/sh
git pull
cd frontend
mvn clean install -Dmaven.test.skip=true
cd ../backend
mvn clean install -Dmaven.test.skip=true
