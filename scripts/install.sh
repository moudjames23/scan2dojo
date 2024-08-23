#!/bin/bash

# Define variables
CLI_NAME="scan2dojo"
VERSION="v1.0.0"
OS=$(uname | tr '[:upper:]' '[:lower:]')

# Determine the download URL based on OS
if [ "$OS" == "darwin" ]; then
    DOWNLOAD_URL="https://github.com/moudjames23/scan2dojo/releases/download/$VERSION/$CLI_NAME-macos"
elif [ "$OS" == "linux" ]; then
    DOWNLOAD_URL="https://github.com/moudjames23/scan2dojo/releases/download/$VERSION/$CLI_NAME-linux"
else
    echo "Unsupported OS: $OS"
    exit 1
fi

# Download the binary
echo "Downloading $CLI_NAME from $DOWNLOAD_URL..."
curl -L "$DOWNLOAD_URL" -o "$CLI_NAME"

# Make the binary executable
chmod +x "$CLI_NAME"

# Move the binary to /usr/local/bin (or another directory in PATH)
sudo mv "$CLI_NAME" /usr/local/bin/

echo "$CLI_NAME installed successfully!"
