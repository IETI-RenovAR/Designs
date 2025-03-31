#!/bin/bash

sudo docker stop designs
git pull
sudo docker rm designs
sudo docker build -t designs .
sudo docker run -d --name designs designs


