package kz.zhassulan.covidtestproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CovidApiMainTests {

    @Test
    @DisplayName("JUnit test for getPercent")
    public void testGetPercent() {
        String percent = CovidApiMain.getPercent(1, 2);
        Assertions.assertThat(percent).isEqualTo("50");
    }
}
