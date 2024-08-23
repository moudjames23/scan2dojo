@echo off

:: Define variables
set CLI_NAME=scan2dojo
set VERSION=v1.0.0
set DOWNLOAD_URL=https://github.com/moudjames23/scan2dojo/releases/download/%VERSION%/%CLI_NAME%-windows.exe

:: Download the binary
echo Downloading %CLI_NAME% from %DOWNLOAD_URL%...
powershell -Command "Invoke-WebRequest -Uri %DOWNLOAD_URL% -OutFile %CLI_NAME%.exe"

:: Move the binary to a directory in PATH
move %CLI_NAME%.exe C:\Windows\System32\

echo %CLI_NAME% installed successfully!
