package edu.escuelaing.IETI.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@Getter
public class Design {
    @Id
    private String id;
    private String userId;
    private String name;
    private String url;
    private boolean isPublic;
    private String state;
    private boolean searchingCarpenter;
    private List<Quotation> quotations;
}
