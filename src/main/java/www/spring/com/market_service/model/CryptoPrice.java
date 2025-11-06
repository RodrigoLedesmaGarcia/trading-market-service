package www.spring.com.market_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "crypto_prices")
public class CryptoPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double priceUsd;
    private LocalDateTime timestamp;

    public CryptoPrice() {}

    public CryptoPrice(String name, double priceUsd) {
        this.name = name;
        this.priceUsd = priceUsd;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPriceUsd() { return priceUsd; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPriceUsd(double priceUsd) { this.priceUsd = priceUsd; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
