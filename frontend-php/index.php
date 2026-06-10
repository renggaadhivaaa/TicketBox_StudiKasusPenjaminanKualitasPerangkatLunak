<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>TicketBox Web</title>
    <script src="assets/script.js"></script>
</head>
<body>
    <h1>Pemesanan TicketBox</h1>
    <form action="process.php" method="POST" onsubmit="return validateOrderForm()">
        <label for="ticketType">Jenis Tiket:</label>
        <select name="ticketType" id="ticketType">
            <option value="VIP">VIP</option>
            <option value="REGULAR">REGULAR</option>
        </select>
        <br><br>

        <label for="basePrice">Harga Dasar:</label>
        <input type="number" name="basePrice" id="basePrice" value="500000" readonly>
        <br><br>

        <label for="quantity">Kuantitas:</label>
        <input type="number" name="quantity" id="quantity">
        <br><br>

        <label for="isMember">Status Member:</label>
        <select name="isMember" id="isMember">
            <option value="true">Ya</option>
            <option value="false">Tidak</option>
        </select>
        <br><br>

        <label for="address">Alamat Pengiriman Fisik:</label>
        <textarea name="address" id="address"></textarea>
        <br><br>

        <button type="submit">Beli Tiket</button>
    </form>
</body>
</html>