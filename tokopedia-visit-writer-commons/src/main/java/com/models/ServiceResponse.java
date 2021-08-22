package com.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse implements Serializable {
    private static final Long serialVersionUID = 1L;

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Object data;
}
