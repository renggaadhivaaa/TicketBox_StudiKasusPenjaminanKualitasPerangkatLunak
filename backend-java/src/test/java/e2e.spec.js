const { test, expect } = require('@playwright/test');

test('Simulasi Alur Utuh Pengguna Membeli Tiket Box', async ({ page }) => {
    // 1. Robot membuka halaman utama web tiket (PHP)
    await page.goto('http://localhost:8000/index.php');

    // 2. Robot mengisi kuantitas tiket menjadi 5
    // (Sesuaikan atribut 'name' atau 'id' jika form di index.php kamu berbeda)
    await page.locator('input[name="quantity"]').fill('5');

    // 3. Robot memilih jenis tiket VIP
    await page.locator('select[name="ticketType"]').selectOption('VIP');

    // 4. Robot mencentang status member jika ada (opsional)
    const memberCheckbox = page.locator('input[name="isMember"]');
    if (await memberCheckbox.isVisible()) {
        await memberCheckbox.check();
    }

    // 5. Robot menekan tombol Submit / Bayar
    // (Bisa disesuaikan menggunakan text tombolnya, misal: 'Bayar' atau 'Pesan')
    await page.locator('button[type="submit"]').click();

    // 6. Robot memvalidasi apakah halaman struk menampilkan teks sukses dan total harga yang benar
    // Kita pastikan text respons dari sistem memuat total harga hasil kalkulasi Java (1.850.000)
    await expect(page.locator('body')).toContainText('Rp 1.850.000');
});