import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CurrencyConverter {

    private static final String API_KEY = "01769b79f843ce3b41c8dc8a";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public Map<String, Double> getExchangeRates(String baseCurrency) throws IOException, ParseException {
        String url = BASE_URL + baseCurrency;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);
                JsonNode ratesNode = rootNode.path("conversion_rates");

                Map<String, Double> rates = new HashMap<>();
                Iterator<String> fieldNames = ratesNode.fieldNames();
                while (fieldNames.hasNext()) {
                    String currency = fieldNames.next();
                    rates.put(currency, ratesNode.get(currency).asDouble());
                }

                return rates;
            }
        }
    }

    public Double convertCurrency(String fromCurrency, String toCurrency, Double amount) throws IOException, ParseException {
        Map<String, Double> rates = getExchangeRates(fromCurrency);
        if (rates.containsKey(toCurrency)) {
            return amount * rates.get(toCurrency);
        } else {
            throw new RuntimeException("Currency not found");
        }
    }
}

