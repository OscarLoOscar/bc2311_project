package com.vtxlab.project.bc_product_data.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuoteResponseDTO {
    @JsonProperty("Current_Price")
    private Double c;
    @JsonProperty("Change")
    private Double d;
    @JsonProperty("Percent_Change")
    private Double dp;
    @JsonProperty("High_Price_Of_The_Day")
    private Double h;
    @JsonProperty("Low_Price_Of_The_Day")
    private Double l;
    @JsonProperty("Open_Price_Of_The_Day")
    private Double o;
    @JsonProperty("Previous_Close_Price")
    private Double pc;
    @JsonProperty("TimeStamp")
    private Long t;
}
