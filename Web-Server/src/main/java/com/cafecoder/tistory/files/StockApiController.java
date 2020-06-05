package com.cafecoder.tistory.files;

import com.cafecoder.tistory.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StockApiController {
    private final StocksRepository stocksRepository;

    @PostMapping("/api/v1/addAmount")
    public Long addAmount(@RequestBody Map<String, String> userData) {
        Long id = Long.parseLong(userData.get("id"));
        Integer addAmount = Integer.parseInt(userData.get("addAmount"));

        System.out.println(id);
        System.out.println(addAmount);

        Stocks stocks = this.stocksRepository.findById(id).get();

        stocks.addAmount(addAmount);
        this.stocksRepository.save(stocks);
        System.out.println(stocks.getAmount());

        return stocks.getId();
    }
}
