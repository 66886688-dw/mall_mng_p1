$baseUrl = "http://localhost:8080/api"

Write-Host "=== 1. 查询所有商品 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 2. 新增商品: iPhone 15 ==="
$body1 = '{"name":"iPhone 15","image":"iphone15.jpg","price":5999.00,"spec":"128GB 黑色","stock":100}'
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products" -Method POST -Body $body1 -ContentType "application/json" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 3. 新增商品: MacBook Pro ==="
$body2 = '{"name":"MacBook Pro","image":"macbook.jpg","price":14999.00,"spec":"16英寸 M3芯片","stock":50}'
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products" -Method POST -Body $body2 -ContentType "application/json" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 4. 查询所有商品 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 5. 查询ID=1的商品 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products/1" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 6. 商品1上架 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products/1/on-shelf" -Method PUT -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 7. 按状态查询上架商品 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products/status/ON_SHELF" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 8. 商品1下架 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products/1/off-shelf" -Method PUT -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 9. 删除商品2 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products/2" -Method DELETE -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 10. 最终查询所有商品 ==="
try {
    $r = Invoke-WebRequest -Uri "$baseUrl/products" -UseBasicParsing
    Write-Host "成功: $($r.Content)"
} catch { Write-Host "失败: $($_.Exception.Message)" }

Write-Host ""
Write-Host "=== 测试完成 ==="
