#!/bin/bash

sudo docker stop designs
git pull
sudo docker rm designs
sudo docker build -t designs .
sudo docker run -d -p 8081:8081 --name designs designs


