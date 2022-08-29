package kz.zhassulan.covidtestproject;

import kz.zhassulan.covidtestproject.model.HistoricalCases;
import kz.zhassulan.covidtestproject.model.LiveCases;
import kz.zhassulan.covidtestproject.model.Vaccines;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CovidApiServiceTests {

    private CovidApiService covidApiService;
    private String countryName;

    @BeforeEach
    public void setup() {
        covidApiService = new CovidApiService();
        countryName = "Kazakhstan";
    }

    @Test
    @DisplayName("JUnit test for getting live cases by country name")
    public void testGetLiveCases() {
        LiveCases liveCases = covidApiService.getLiveCases(countryName);
        Assertions.assertThat(liveCases).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for getting vaccines by country name")
    public void testGetVaccines() {
        Vaccines vaccines = covidApiService.getVaccines(countryName);
        Assertions.assertThat(vaccines).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for getting historical cases data by country name")
    public void testGetHistoricalCases() {
        HistoricalCases historicalCases = covidApiService.getHistoricalCases(countryName);
        Assertions.assertThat(historicalCases).isNotNull();
    }
}
