#!/bin/bash

# Step 1: Update the system
echo "Updating system packages..."
sudo apt update && sudo apt upgrade -y

# Step 2: Install Java & Maven
echo "Installing Java and Maven..."
sudo apt install -y openjdk-17-jdk maven

# Step 3: Install Docker
echo "Installing Docker..."
curl -fsSL https://get.docker.com | sudo bash
sudo usermod -aG docker $USER
newgrp docker

# Step 4: Install Trivy
echo "Installing Trivy..."
wget https://github.com/aquasecurity/trivy/releases/latest/download/trivy_0.50.1_Linux-64bit.deb
sudo dpkg -i trivy_0.50.1_Linux-64bit.deb

# Step 5: Install kubectl
echo "Installing kubectl..."
curl -LO "https://dl.k8s.io/release/$(curl -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl && sudo mv kubectl /usr/local/bin/

# Step 6: Install Azure CLI
echo "Installing Azure CLI..."
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash

# Step 7: Install SonarQube (via Docker)
echo "Installing SonarQube..."
docker run -d --name sonarqube -p 9000:9000 sonarqube:community

echo "Tool installation completed!"
