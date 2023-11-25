package com.ethan.apiproject.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionService {

    @Value("${currency.api.key}")
    private String apiKey;

    private final String apiUrl = "https://api.currencyapi.com/v3/latest?apikey=cur_live_nrIl31BQlFn4J9AXmbz2p6vMau5ZeuySaV9p7AjZ";

    private final RestTemplate restTemplate;

    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        String url = String.format("%s?amount=%s&from=%s&to=%s&apiKey=%s", apiUrl, amount, fromCurrency, toCurrency, apiKey);
        CurrencyConversionResult result = restTemplate.getForObject(url, CurrencyConversionResult.class);
        if (result != null) {
            return result.getConvertedAmount();
        } else {
            return amount;
        }
    }
}
