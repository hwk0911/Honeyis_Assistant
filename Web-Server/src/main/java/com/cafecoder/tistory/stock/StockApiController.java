package com.cafecoder.tistory.stock;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@Controller
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

        StringTokenizer colorToken = new StringTokenizer(color, ",");

        if(size.length() == 0) {
            for(int index = 0, to = colorToken.countTokens() ; index < to ; ++index) {
                size += "FREE,";
            }
        }

        StringTokenizer sizeToken = new StringTokenizer(size, ",");
        StringTokenizer amountToken = new StringTokenizer(amount, ",");

        if(colorToken.countTokens() > sizeToken.countTokens() || colorToken.countTokens() > amountToken.countTokens()) {
            return "redirect:/users/stock";
        }

        while(colorToken.hasMoreTokens()) {
            try {
                Stocks stock = new Stocks().builder()
                        .userId("hwk0911@gmail.com")
                        .client(client)
                        .productName(productName)
                        .color(colorToken.nextToken())
                        .size(sizeToken.nextToken())
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

    @RequestMapping(value = "/api/v1/sort", method = RequestMethod.GET)
    public String sort(@RequestParam("productName") List<String> productNames, @RequestParam("client") List<String> client, @RequestParam("option") List<HashMap<String, Integer>> option, Model model) {
        List<Order> orderList = new ArrayList<>();

        for(int index = 0, size = productNames.size() ; index < size ; ++index) {
            orderList.add(new Order(productNames.get(index), client.get(index), option.get(index)));
        }

        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getClient().compareTo(o2.getClient());
            }
        });

        model.addAttribute("orderList", orderList);

        return "orderList";
    }
}


/*
방식을 생각해봐야함.... List productName, List Color, List size, List amount ...
form 으로 받아서, .. 기준으로 끊어서 데이터 입력.
 */