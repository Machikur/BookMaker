package com.app.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageWrapper {

    @JsonProperty("pageable")
    private PageDetails pageDetails;
    private List<MatchDto> content;
    private int totalPages;

    public boolean hasPrevious() {
        return pageDetails.getPageNumber() > 0;
    }

    public boolean hasNext() {
        return pageDetails.getPageNumber() < totalPages - 1;
    }

    public int getNumberOfPage(){
        return pageDetails.getPageNumber();
    }
}
