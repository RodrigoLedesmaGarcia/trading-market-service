package www.spring.com.market_service.controller;

import org.springframework.web.bind.annotation.*;
import www.spring.com.market_service.model.CryptoPrice;
import www.spring.com.market_service.service.MarketService;

import java.util.List;


@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/prices")
    public List<CryptoPrice> getPrices() {
        return marketService.getCurrentPrices();
    }

    @GetMapping("/history")
    public List<CryptoPrice> getHistory() {
        return marketService.getHistory();
    }
}
