package www.spring.com.market_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.spring.com.market_service.model.CryptoPrice;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {
}
