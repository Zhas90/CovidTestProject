package kz.zhassulan.covidtestproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model for vaccines data
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vaccines {
    @JsonProperty("people_vaccinated")
    private long vaccinated;
    @JsonProperty("people_partially_vaccinated")
    private long partiallyVaccinated;
    private long population;
}
