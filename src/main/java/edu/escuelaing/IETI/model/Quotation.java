package edu.escuelaing.IETI.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class Quotation {
    private String id;
    private String carpenterId;
    private String price;
    private String accepted;
}
