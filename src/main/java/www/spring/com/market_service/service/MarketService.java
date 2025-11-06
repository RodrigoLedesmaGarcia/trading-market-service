package www.spring.com.market_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import www.spring.com.market_service.model.CryptoPrice;
import www.spring.com.market_service.repository.CryptoPriceRepository;

import java.util.*;

@Service
public class MarketService {

    private final CryptoPriceRepository repository;

    private final String API_URL =
            "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";

    public MarketService(CryptoPriceRepository repository) {
        this.repository = repository;
    }


    public List<CryptoPrice> getHistory() {
        return repository.findAll();
    }

    // 🔹 Método para obtener precios en cualquier momento (usado por la API)
    public List<CryptoPrice> getCurrentPrices() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(API_URL, Map.class);
        Map<String, Map<String, Object>> data = response.getBody();

        List<CryptoPrice> prices = new ArrayList<>();
        for (String key : data.keySet()) {
            Object usdValue = data.get(key).get("usd");
            Double priceValue = ((Number) usdValue).doubleValue(); // convierte Integer o Double correctamente

            CryptoPrice price = new CryptoPrice(key, priceValue);
            repository.save(price);
            prices.add(price);
        }
        return prices;
    }

    //  Guardar precios automáticamente cada 5 minutos
    @Scheduled(fixedRate = 300000) // 300000 ms = 5 minutos
    public void autoSavePrices() {
        System.out.println("⏱ Guardando precios automáticamente...");
        getCurrentPrices(); // reutiliza la lógica de arriba
    }

}
