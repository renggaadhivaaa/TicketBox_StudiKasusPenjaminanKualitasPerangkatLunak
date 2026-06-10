<?php
$ticketType = $_POST['ticketType'];
$basePrice = (int)$_POST['basePrice'];
$quantity = (int)$_POST['quantity'];
$isMember = $_POST['isMember'] === 'true' ? true : false;
$address = $_POST['address'];

$payload = json_encode([
    "ticketType" => $ticketType,
    "basePrice" => $basePrice,
    "quantity" => $quantity,
    "isMember" => $isMember
]);

$ch = curl_init('http://localhost:8080/api/order');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
curl_setopt($ch, CURLOPT_HTTPHEADER, [
    'Content-Type: application/json',
    'Content-Length: ' . strlen($payload)
]);

$response = curl_exec($ch);
curl_close($ch);

$responseData = json_decode($response, true);
?>
<!DOCTYPE html>
<html>
<head>
    <title>Hasil Pemesanan</title>
</head>
<body>
    <h2>Status Pemesanan: <?php echo $responseData['status']; ?></h2>
    <h3>Total Harga: Rp <?php echo number_format($responseData['total'], 0, ',', '.'); ?></h3>
    <div style="border:1px solid #000; padding:10px; margin-top:20px;">
        <p>Tiket akan dikirimkan ke alamat berikut:</p>
        <p><?php echo $address; ?></p>
    </div>
    <br>
    <a href="index.php">Kembali</a>
</body>
</html>