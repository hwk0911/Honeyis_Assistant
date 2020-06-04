package com.cafecoder.tistory.files;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockApiController {
    private final StocksRepository stocksRepository;

    /*
    @RequestMapping(value = "/addStock", method = RequestMethod.POST)
    public String addAmount(@RequestBody String productName) {
        System.out.println(productName);

        Stocks stocks = this.stocksRepository.findByUserIdAndProductNameAndColorAndSize(newStock.getUserId(), newStock.getProductName(), newStock.getColor(), newStock.getSize());
        Stocks updateStocks = new Stocks().builder()
                .userId(stocks.getUserId())
                .productName(stocks.getProductName())
                .color(stocks.getSize())
                .size(stocks.getSize())
                .amount(stocks.getAmount())
                .build();

        this.stocksRepository.delete(stocks);
        this.stocksRepository.save(updateStocks);

        return "redirect:/users/stock";
    }
    */

    @PostMapping("/api/v1/addAmount")
    public String addAmount(@RequestBody String[] data) {
        for(String d : data) {
            System.out.println(d);
        }

        return null;
    }
}
