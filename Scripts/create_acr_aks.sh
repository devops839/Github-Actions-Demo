#!/bin/bash

# Variables
RESOURCE_GROUP="ci-cd-rg"
LOCATION="eastus"
VNET_NAME="ci-cd-vnet"
ACR_NAME="ci-cd-acr"         # Name for Azure Container Registry
AKS_NAME="ci-cd-aks"         # Name for Azure Kubernetes Service Cluster
AKS_SUBNET_NAME="aks-subnet" # Subnet for AKS

# Step 1: Create Resource Group
echo "Creating Resource Group..."
az group create --name $RESOURCE_GROUP --location $LOCATION

# Step 2: Create VNet and Subnet for AKS
echo "Creating VNet and Subnet for AKS..."
az network vnet create --resource-group $RESOURCE_GROUP --name $VNET_NAME --subnet-name $AKS_SUBNET_NAME

# Step 3: Create Azure Container Registry (ACR)
echo "Creating Azure Container Registry (ACR)..."
az acr create --resource-group $RESOURCE_GROUP --name $ACR_NAME --sku Basic --location $LOCATION

# Step 4: Create Azure Kubernetes Service (AKS)
echo "Creating Azure Kubernetes Service (AKS)..."
az aks create --resource-group $RESOURCE_GROUP --name $AKS_NAME --node-count 3 --enable-addons monitoring --generate-ssh-keys --vnet-subnet-id "/subscriptions/$(az account show --query id -o tsv)/resourceGroups/$RESOURCE_GROUP/providers/Microsoft.Network/virtualNetworks/$VNET_NAME/subnets/$AKS_SUBNET_NAME"

# Step 5: Connect to AKS Cluster
echo "Connecting to AKS Cluster..."
az aks get-credentials --resource-group $RESOURCE_GROUP --name $AKS_NAME

echo "ACR and AKS creation completed!"
