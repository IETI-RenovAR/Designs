package org.adaschool.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuotationDTO {
    private String id;
    private String carpenterId;
    private String price;
    private boolean accepted;

    public QuotationDTO() {
    }

    public QuotationDTO(String id, String carpenterId, String price, boolean accepted) {
        this.id = id;
        this.carpenterId = carpenterId;
        this.price = price;
        this.accepted = false;
    }

    // Constructor que puede ser usado cada vez que se crea una cotización
    public QuotationDTO(String carpenterId, String price) {
        this.carpenterId = carpenterId;
        this.price = price;
        this.accepted = false;
    }
}
