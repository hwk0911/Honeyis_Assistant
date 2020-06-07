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
    public String addAmount(@RequestParam("id") Long id, @RequestParam("inputAmount") Integer inputAmount) throws Exception{
        Stocks stocks = this.stocksRepository.findById(id).get();

        stocks.addAmount(inputAmount);
        this.stocksRepository.save(stocks);

        return "redirect:/users/stock";
    }
}
