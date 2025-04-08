@echo off
REM Définition des variables
set APP_NAME=ETU003335
set SRC_DIR=src\main\java
set WEB_DIR=src\main\webapp
set BUILD_DIR=build
set LIB_DIR=lib
set TOMCAT_WEBAPPS=C:\xampp\tomcat\webapps
set SERVLET_API_JAR=%LIB_DIR%\jakarta.servlet-api-6.0.0.jar

REM Nettoyage et création du répertoire temporaire
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
mkdir %BUILD_DIR%\WEB-INF\classes

REM Compilation des fichiers Java avec le JAR des Servlets
dir /s /b %SRC_DIR%\*.java > sources.txt
javac -cp %SERVLET_API_JAR% -d %BUILD_DIR%\WEB-INF\classes @sources.txt

REM Copier les fichiers web (web.xml, JSP, etc.)
xcopy %WEB_DIR% %BUILD_DIR% /E /I /Y

REM Générer le fichier .war dans le dossier build
cd %BUILD_DIR% || exit /b
jar -cvf %APP_NAME%.war *
move %APP_NAME%.war %TOMCAT_WEBAPPS%