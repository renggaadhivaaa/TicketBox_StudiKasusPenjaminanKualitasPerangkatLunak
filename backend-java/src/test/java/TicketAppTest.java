import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

public class TicketAppTest {

    // Sesuaikan nama file dengan yang ada di foldermu (product.json atau products.json)
    private final String PRODUCT_ASLI = "data/product.json";
    private final String PRODUCT_CADANGAN = "data/product_backup.json";

    @BeforeEach
    public void setUp() {
        // PERSIAPAN: Menyalin file stok penuh sebelum tes berjalan
        try {
            Files.copy(Paths.get(PRODUCT_CADANGAN), Paths.get(PRODUCT_ASLI), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Setup Sukses: Stok produk direset ke kondisi awal.");
        } catch (IOException e) {
            System.out.println("Setup Gagal: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // PEMBERSIHAN: Menghapus file stok yang sudah berkurang akibat tes
        try {
            Files.deleteIfExists(Paths.get(PRODUCT_ASLI));
            System.out.println("TearDown Sukses: File produk kotor dihapus.");
        } catch (IOException e) {
            System.out.println("TearDown Gagal: " + e.getMessage());
        }
    }

    @Test
    public void testHitungDiskonVIPMember() {
        TicketApp.OrderHandler handler = new TicketApp.OrderHandler();
        double hasil = handler.calculateTotal(500000, 5, "VIP", true); 
        assertEquals(1850000.0, hasil, "Diskon VIP Member gagal dihitung!");
    }

    @Test
    public void testHitungDiskonRegulerNonMember() {
        TicketApp.OrderHandler handler = new TicketApp.OrderHandler();
        double hasil = handler.calculateTotal(100000, 3, "REGULAR", false); 
        assertEquals(270000.0, hasil, "Diskon REGULAR gagal dihitung!");
    }

    @Test
    public void testHitungTanpaDiskon() {
        TicketApp.OrderHandler handler = new TicketApp.OrderHandler();
        double hasil = handler.calculateTotal(100000, 2, "VIP", false); 
        assertEquals(200000.0, hasil, "Perhitungan tanpa diskon gagal!");
    }
}