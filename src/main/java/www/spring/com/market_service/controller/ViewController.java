package www.spring.com.market_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import www.spring.com.market_service.model.CryptoPrice;
import www.spring.com.market_service.repository.CryptoPriceRepository;

import java.util.List;

@Controller
public class ViewController {

    private final CryptoPriceRepository repository;

    public ViewController(CryptoPriceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        List<CryptoPrice> prices = repository.findAll();
        model.addAttribute("prices", prices);
        return "chart";
    }
}
