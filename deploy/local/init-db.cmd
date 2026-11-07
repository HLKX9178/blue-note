@echo off
REM One-time PostgreSQL init. Safe to run on an empty volume only.
REM Tables use CREATE TABLE IF NOT EXISTS, but init_data.sql does plain INSERTs,
REM so running this twice will hit unique-key errors.
chcp 65001 >nul
cd /d "%~dp0..\.."

echo initializing hannote schema + seed data ...
docker compose -f podman-compose.yml exec -T postgres psql -U hannote -d hannote -f /schema/init.sql
if errorlevel 1 goto :fail

echo.
echo done. verify:
docker compose -f podman-compose.yml exec -T postgres psql -U hannote -d hannote -c "\dt"
goto :eof

:fail
echo.
echo FAILED. Make sure the postgres container is running and healthy.
