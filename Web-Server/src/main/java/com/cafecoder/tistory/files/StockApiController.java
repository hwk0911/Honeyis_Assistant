package com.cafecoder.tistory.files;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockApiController {
    private final StocksRepository stocksRepository;

    @PostMapping("/stock/{userId}/{amount}")
    public String addAmount(@PathVariable String userId, int amount) {
        Stocks stocks = this.stocksRepository.findByUserId(userId);
        Stocks updateStocks = new Stocks().builder()
                .userId(stocks.getUserId())
                .productName(stocks.getProductName())
                .color(stocks.getSize())
                .size(stocks.getSize())
                .amount(stocks.getAmount() + amount)
                .build();

        this.stocksRepository.delete(stocks);
        this.stocksRepository.save(updateStocks);

        return "redirect:/users/stock";
    }
}
