package com.cafecoder.tistory.files;

import com.cafecoder.tistory.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    public String addProduct(@RequestParam("id") List<Long> id, @RequestParam("inputAmount") List<String> productName) throws Exception {
        System.out.println(id.get(1));
        System.out.println(productName.get(1));

        return "redirect:/users/stock";
    }
}
