@echo off
echo ====================================
echo 商品后台管理系统启动脚本
echo ====================================
echo.
echo [1/2] 正在编译项目...
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo 编译失败，请检查Maven配置
    pause
    exit /b 1
)
echo.
echo [2/2] 正在启动应用...
java -jar target/mall-mng-1.0.0.jar
pause
