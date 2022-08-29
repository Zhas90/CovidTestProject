package kz.zhassulan.covidtestproject;

import kz.zhassulan.covidtestproject.model.HistoricalCases;
import kz.zhassulan.covidtestproject.model.LiveCases;
import kz.zhassulan.covidtestproject.model.Vaccines;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class
 */
public class CovidApiMain {

    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) {
        System.out.print("Enter country name: ");
        String countryName;
        try (Scanner scanner = new Scanner(System.in)) {
            countryName = scanner.next();
        }

        CovidApiService covidApiService = new CovidApiService();

        LiveCases liveCases = covidApiService.getLiveCases(countryName);
        if (Objects.isNull(liveCases)) {
            throw new RuntimeException("Please enter a valid country name!");
        }
        Vaccines vaccines = covidApiService.getVaccines(countryName);
        HistoricalCases historicalCases = covidApiService.getHistoricalCases(countryName);
        Long lastAvailableConfirmed = historicalCases.getDates().entrySet().iterator().next().getValue();

        System.out.println("Confirmed: " + liveCases.getConfirmed());
        System.out.println("Recovered: " + liveCases.getRecovered());
        System.out.println("Deaths: " + liveCases.getDeaths());
        System.out.println("Vaccinated level: " + getPercent(vaccines.getVaccinated(), vaccines.getPopulation()) + "%");
        System.out.println("Partially vaccinated level: " + getPercent(vaccines.getPartiallyVaccinated(), vaccines.getPopulation()) + "%");
        System.out.println("New confirmed cases since last data available: " + (liveCases.getConfirmed() - lastAvailableConfirmed));
    }

    /**
     * Method for calculating and return percent as string
     * @param number1 first number
     * @param number2 second number
     * @return calculated percent
     */
    static String getPercent(long number1, long number2) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        float level = (float) number1 / number2;
        return df.format(level * 100);
    }
}
