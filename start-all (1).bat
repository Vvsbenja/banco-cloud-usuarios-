@echo off
echo ============================================
echo   Levantando Banco Cloud (todo en un click)
echo ============================================

echo.
echo [1/6] Levantando Keycloak (Docker)...
cd /d "%~dp0keycloak"
docker compose up -d

echo.
echo [2/6] Levantando base de datos de la app (Docker)...
cd /d "%~dp0db"
docker compose up -d

echo.
echo Esperando 8 segundos a que las bases de datos inicien...
timeout /t 8 /nobreak >nul

echo.
echo [3/6] Levantando banco-cloud-usuarios...
start "usuarios" cmd /k "cd /d %~dp0banco-cloud-usuarios && mvnw.cmd spring-boot:run"

echo [4/6] Levantando banco-cloud-sucursales...
start "sucursales" cmd /k "cd /d %~dp0banco-cloud-sucursales && mvnw.cmd spring-boot:run"

echo [5/6] Levantando banco-cloud-cajeros...
start "cajeros" cmd /k "cd /d %~dp0banco-cloud-cajeros && mvnw.cmd spring-boot:run"

echo [6/6] Levantando banco-cloud-bff...
start "bff" cmd /k "cd /d %~dp0banco-cloud-bff && set KEYCLOAK_AUDIENCE=banco-cloud-frontend && mvnw.cmd spring-boot:run"

echo.
echo Levantando el frontend Angular...
if not exist "%~dp0banco-cloud-frontend\node_modules" (
    start "frontend" cmd /k "cd /d %~dp0banco-cloud-frontend && npm install && npm start"
) else (
    start "frontend" cmd /k "cd /d %~dp0banco-cloud-frontend && npm start"
)

echo.
echo ============================================
echo Listo. Se abrieron 5 ventanas nuevas.
echo Espera a que cada una diga "Started..." (los
echo Java) o "Local: http://localhost:4200" (Angular)
echo antes de probar en el navegador.
echo ============================================
pause
