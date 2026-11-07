@echo off
REM Stage 0 middleware for route A: PostgreSQL + Nacos + RocketMQ
REM Redis is NOT started here - a native redis-server already listens on 127.0.0.1:6379.
chcp 65001 >nul
cd /d "%~dp0..\.."

echo [1/2] starting postgres / nacos / rocketmq (first run pulls ~1GB images) ...
docker compose -f podman-compose.yml -f deploy/local/compose.override.yml up -d postgres nacos rocketmq-nameserver rocketmq-broker
if errorlevel 1 goto :fail

echo.
echo [2/2] waiting for health (up to 90s) ...
timeout /t 30 /nobreak >nul
docker compose -f podman-compose.yml ps
echo.
echo Nacos console : http://127.0.0.1:18080
echo Nacos server  : 127.0.0.1:8848
echo PostgreSQL    : 127.0.0.1:5432  (hannote/hannote)
echo RocketMQ      : 127.0.0.1:9876
echo.
echo Next: run init-db.cmd ONCE (empty volume only), then start-services.cmd
echo.
pause
goto :eof

:fail
echo.
echo FAILED. Check these in order:
echo   1. Docker Desktop is running  ^(docker info^)
echo   2. Ports 5432 / 8848 / 9876 / 10911 are free  ^(netstat -ano ^| findstr "5432"^)
echo   3. Image pull reached Docker Hub
echo.
pause
