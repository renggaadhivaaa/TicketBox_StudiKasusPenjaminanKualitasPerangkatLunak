import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class TicketApp {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/order", new OrderHandler());
        server.setExecutor(null);
        server.start();
    }

    public static class OrderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStream is = t.getRequestBody();
            String body = new String(is.readAllBytes());

            int quantity = extractInt(body, "quantity");
            String ticketType = extractString(body, "ticketType");
            boolean isMember = body.contains("\"isMember\":true");
            int basePrice = extractInt(body, "basePrice");

            double total = calculateTotal(basePrice, quantity, ticketType, isMember);

            String response = "{\"status\":\"success\", \"total\":" + total + "}";

            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // SEBELUMNYA PRIVATE, SEKARANG DIUBAH MENJADI PUBLIC
        public double calculateTotal(int basePrice, int quantity, String ticketType, boolean isMember) {
            double total = basePrice * quantity;

            if (quantity >= 5) {
                if (ticketType.equals("VIP")) {
                    total = total * 0.8;
                } else {
                    total = total * 0.85;
                }
            } else if (quantity >= 3) {
                if (ticketType.equals("REGULAR")) {
                    total = total * 0.9;
                }
            }

            if (isMember) {
                if (total > 1000000) {
                    total = total - 150000;
                } else if (total > 500000) {
                    total = total - 50000;
                }
            }

            return total;
        }

        private int extractInt(String json, String key) {
            String pattern = "\"" + key + "\":";
            int idx = json.indexOf(pattern);
            if (idx == -1) return 0;
            int start = idx + pattern.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            String value = json.substring(start, end).trim();
            return Integer.parseInt(value);
        }

        private String extractString(String json, String key) {
            String pattern = "\"" + key + "\":\"";
            int idx = json.indexOf(pattern);
            if (idx == -1) return "";
            int start = idx + pattern.length();
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        }
    }
}