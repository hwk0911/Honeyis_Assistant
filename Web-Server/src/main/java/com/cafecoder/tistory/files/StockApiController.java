package com.cafecoder.tistory.files;

import com.cafecoder.tistory.user.Users;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Controller
@RequiredArgsConstructor
public class StockApiController {
    private final StocksRepository stocksRepository;

    @RequestMapping(value = "/api/v1/addAmount", method = RequestMethod.POST)
    public String addAmount(@RequestParam("id") Long id, @RequestParam("inputAmount") String inputAmount) throws Exception {
        Stocks stocks = this.stocksRepository.findById(id).get();

        try {
            stocks.addAmount(Integer.parseInt(inputAmount));
            this.stocksRepository.save(stocks);
        }
        catch (NumberFormatException e) {
            e.getStackTrace();
        }

        return "redirect:/users/stock";
    }

    @RequestMapping(value = "/api/v1/addProduct", method = RequestMethod.POST)
    public String addProduct(@RequestParam("client") String client,
                             @RequestParam("productName") String productName,
                             @RequestParam("color") String color,
                             @RequestParam("size") String size,
                             @RequestParam("amount") String amount
    ) throws Exception {
        System.out.println(client);
        System.out.println(productName);
        System.out.println(color);
        System.out.println(size);
        System.out.println(amount);

        if(size.length() == 0) {
            size = "FREE";
        }

        StringTokenizer colorToken = new StringTokenizer(color, ",");
        StringTokenizer amountToken = new StringTokenizer(amount, ",");


        while(colorToken.hasMoreTokens()) {
            try {
                Stocks stock = new Stocks().builder()
                        .userId("hwk0911@gmail.com")
                        .client(client)
                        .productName(productName)
                        .color(colorToken.nextToken())
                        .size(size)
                        .amount(Integer.parseInt(amountToken.nextToken()))
                        .build();

                System.out.println(stock.toString());
                this.stocksRepository.save(stock);
            }
            catch (ConstraintViolationException e) {
                e.getStackTrace();
                continue;
            }
            catch (NumberFormatException e) {
                e.getStackTrace();
                continue;
            }
            catch (NullPointerException e) {
                e.getStackTrace();
                continue;
            }
        }

        return "redirect:/users/stock";
    }
}
