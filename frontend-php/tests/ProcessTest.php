<?php

use PHPUnit\Framework\TestCase;

class ProcessTest extends TestCase {
    public function testFormatDataCURL() {
        // Skenario: Memastikan struktur data pesanan tidak kosong
        $data_pesanan = [
            'ticketType' => 'VIP',
            'quantity' => 5
        ];

        // Pastikan array memiliki key 'quantity' dan isinya 5
        $this->assertArrayHasKey('quantity', $data_pesanan);
        $this->assertEquals(5, $data_pesanan['quantity']);
    }
}