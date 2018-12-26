#!/bin/bash

docker build -t  hello1:v1  --build-arg servicename=Hello1 .
