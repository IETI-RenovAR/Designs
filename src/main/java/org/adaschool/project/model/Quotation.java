package org.adaschool.project.model;

import org.adaschool.project.dto.QuotationDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "quotations")
public class Quotation {
    @Id
    private String id;
    private String carpenterId;
    private String price;
    private boolean accepted;

    public Quotation() {
    }

    public Quotation(String id, String carpenterId, String price, boolean accepted) {
        this.id = id;
        this.carpenterId = carpenterId;
        this.price = price;
        this.accepted = accepted;
    }

    // Constructor que puede ser usado cada vez que se crea una cotización
    public Quotation(String carpenterId, String price) {
        this.id = null;
        this.carpenterId = carpenterId;
        this.price = price;
        this.accepted = false;
    }

    // Constructor a partir de DTO
    public Quotation(QuotationDTO quotationDTO) {
        this.id = null;
        this.carpenterId = quotationDTO.getCarpenterId();
        this.price = quotationDTO.getPrice();
        this.accepted = quotationDTO.isAccepted();
    }
}
