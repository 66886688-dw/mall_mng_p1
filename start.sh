#!/bin/bash
echo "===================================="
echo "商品后台管理系统启动脚本"
echo "===================================="
echo ""
echo "[1/2] 正在编译项目..."
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "编译失败，请检查Maven配置"
    exit 1
fi
echo ""
echo "[2/2] 正在启动应用..."
java -jar target/mall-mng-1.0.0.jar
