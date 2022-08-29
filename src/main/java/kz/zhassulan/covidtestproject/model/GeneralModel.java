package kz.zhassulan.covidtestproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

/**
 * General model for deserialization
 * @param <T> specific object
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralModel<T> {
    public T All;
}
