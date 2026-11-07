@echo off
REM Route A backend services: id-generator -> count -> user -> auth -> gateway
REM Each service opens its own window; logs also go to .\logs\ (logback FILE appender).
chcp 65001 >nul
setlocal

set "JAVA_HOME=D:\DevTool\java\jdk-25.0.4.1+1"
set "PATH=%JAVA_HOME%\bin;%PATH%"
cd /d "%~dp0..\.."

if not exist "%JAVA_HOME%\bin\java.exe" (
  echo [ERROR] JAVA_HOME not found: %JAVA_HOME%
  echo Edit this script and point it to your JDK 25 install.
  pause
  exit /b 1
)

echo [1/5] bluenote-distributed-id-generator  :8084
start "bluenote-id" cmd /c java -Xms128m -Xmx256m -XX:MaxMetaspaceSize=256m -jar "bluenote-distributed-id-generator\bluenote-distributed-id-generator-biz\target\bluenote-distributed-id-generator-biz-0.0.1-SNAPSHOT.jar"
timeout /t 20 /nobreak >nul

echo [2/5] bluenote-count  :8087
start "bluenote-count" cmd /c java -Xms128m -Xmx256m -XX:MaxMetaspaceSize=256m -jar "bluenote-count\bluenote-count-biz\target\bluenote-count-biz-0.0.1-SNAPSHOT.jar"
timeout /t 18 /nobreak >nul

echo [3/5] bluenote-user  :8082
start "bluenote-user" cmd /c java -Xms192m -Xmx384m -XX:MaxMetaspaceSize=256m -jar "bluenote-user\bluenote-user-biz\target\bluenote-user-biz-0.0.1-SNAPSHOT.jar"
timeout /t 20 /nobreak >nul

echo [4/5] bluenote-auth  :8080
start "bluenote-auth" cmd /c java -Xms128m -Xmx256m -XX:MaxMetaspaceSize=256m -jar "bluenote-auth\target\bluenote-auth-0.0.1-SNAPSHOT.jar"
timeout /t 18 /nobreak >nul

echo [5/5] bluenote-gateway  :8000
start "bluenote-gateway" cmd /c java -Xms128m -Xmx256m -XX:MaxMetaspaceSize=256m -jar "bluenote-gateway\target\bluenote-gateway-0.0.1-SNAPSHOT.jar"

echo.
echo All five services launched. Wait ~20s, then check:
echo   http://127.0.0.1:18080  (Nacos console - 5 instances should be registered)
echo   http://127.0.0.1:8000   (gateway)
echo.
echo Smoke test (replace CODE with the one printed in logs\auth.*.log):
echo   curl -X POST http://127.0.0.1:8000/auth/verification/code/send -H "Content-Type: application/json" -d "{\"phone\":\"17628516882\"}"
echo   curl -X POST http://127.0.0.1:8000/auth/user/login -H "Content-Type: application/json" -d "{\"phone\":\"17628516882\",\"code\":\"CODE\",\"type\":1}"
