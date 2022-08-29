package kz.zhassulan.covidtestproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.zhassulan.covidtestproject.model.GeneralModel;
import kz.zhassulan.covidtestproject.model.HistoricalCases;
import kz.zhassulan.covidtestproject.model.LiveCases;
import kz.zhassulan.covidtestproject.model.Vaccines;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for getting data from public API
 */
public class CovidApiService {

    private final static String LIVE_CASES_URL = "https://covid-api.mmediagroup.fr/v1/cases";
    private final static String VACCINES_URL = "https://covid-api.mmediagroup.fr/v1/vaccines";
    private final static String HISTORY_URL = "https://covid-api.mmediagroup.fr/v1/history?status=confirmed";

    private ObjectMapper objectMapper;

    /**
     * Method for getting live cases by country name
     * @param countryName country name
     * @return live cases data
     */
    public LiveCases getLiveCases(String countryName) {
        String responseBody = getResponseBody(countryName, LIVE_CASES_URL);

        GeneralModel<LiveCases> liveCases;
        try {
            liveCases = getObjectMapper().readValue(responseBody, new TypeReference<>() {});
            return liveCases.getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for getting vaccines data by country name
     * @param countryName country name
     * @return vaccines data
     */
    public Vaccines getVaccines(String countryName) {
        String responseBody = getResponseBody(countryName, VACCINES_URL);

        GeneralModel<Vaccines> vaccines;
        try {
            vaccines = getObjectMapper().readValue(responseBody, new TypeReference<>() {});
            return vaccines.getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for getting historical cases data
     * @param countryName country name
     * @return historical cases data
     */
    public HistoricalCases getHistoricalCases(String countryName) {
        String responseBody = getResponseBody(countryName, HISTORY_URL);

        GeneralModel<HistoricalCases> historicalCases;
        try {
            historicalCases = getObjectMapper().readValue(responseBody, new TypeReference<>() {});
            return historicalCases.getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    private String getResponseBody(String countryName, String baseUrl) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("country", countryName);
        String url = urlBuilder.build().toString();
        
        return sendRequest(url);
    }

    private String sendRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RuntimeException("Response code is: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectMapper getObjectMapper() {
        if (Objects.nonNull(objectMapper)) {
            return objectMapper;
        }
        objectMapper = new ObjectMapper();
        return objectMapper;
    }
}
