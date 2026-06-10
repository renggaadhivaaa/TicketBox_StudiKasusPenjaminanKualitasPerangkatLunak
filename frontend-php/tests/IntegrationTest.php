<?php
use PHPUnit\Framework\TestCase;

class IntegrationTest extends TestCase {
    public function testPHPtoJavaIntegration() {
        $url = 'http://localhost:8080/api/order';
        
        $payload = json_encode([
            'basePrice' => 500000,
            'quantity' => 5,
            'ticketType' => 'VIP',
            'isMember' => true
        ]);

        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
        curl_setopt($ch, CURLOPT_HTTPHEADER, ['Content-Type: application/json']);
        
        $response = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);

        // 1. UJI STATUS: Harus 200 OK
        $this->assertEquals(200, $httpCode, "Koneksi ke API Java Gagal!");

        // 2. UJI DATA RESPON: Memastikan struktur JSON dari Java sesuai
        $responseData = json_decode($response, true);
        $this->assertArrayHasKey('status', $responseData);
        $this->assertEquals('success', $responseData['status']);
        $this->assertEquals(1850000, $responseData['total']);
    }
}