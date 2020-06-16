package com.cafecoder.tistory.stock;

import com.cafecoder.tistory.web.IndexController;
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
    private final ClientRepository clientRepository;

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
    public String sort(/*@RequestParam("productName") List<String> productNames, @RequestParam("client") List<String> client,*/ @RequestParam("option") List<Order> option) {
        List<Order> orderList = new ArrayList<>();

        System.out.println(option.get(0).toString());

        //for(int index = 0, size = productNames.size() ; index < size ; ++index) {
           //StringTokenizer stringTokenizer = new StringTokenizer(optionList.get(index), ", ");
        //}

        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getClient().compareTo(o2.getClient());
            }
        });

        //model.addAttribute("orderList", orderList);

        return "orderList";
    }

    @RequestMapping(value = "/api/v1/search/client", method = RequestMethod.GET)
    public void searchClient (@RequestParam("client") String client, Model model) {
        List<Client> clientList = this.clientRepository.findAll();

        List<Client> clientModel = new ArrayList<>();

        for(Client cli : clientList) {
            if(cli.getClient().contains(client) || client.contains(cli.getClient())) {
                clientModel.add(cli);
            }
        }

        model.addAttribute("clientList", clientModel);


    }
}