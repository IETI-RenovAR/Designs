package edu.escuelaing.IETI.dto;

import edu.escuelaing.IETI.model.DesignState;
import edu.escuelaing.IETI.model.Quotation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DesignDTO {
    private String id;
    private String userId;
    private String name;
    private String url;
    private boolean isPublic;
    private DesignState state;
    private boolean searchingCarpenter;
    private List<Quotation> quotations;

    public DesignDTO() {
    }

    public DesignDTO(String id, String userId, String name, String url, boolean isPublic, DesignState state,
                     boolean searchingCarpenter, List<Quotation> quotations) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.url = url;
        this.isPublic = isPublic;
        this.state = state;
        this.searchingCarpenter = searchingCarpenter;
        this.quotations = quotations;
    }

    // Constructor que puede ser usado por el cliente cada vez que crea un diseño
    public DesignDTO(String userId, String name, String url, boolean isPublic) {
        this.id = null;
        this.userId = userId;
        this.name = name;
        this.url = url;
        this.isPublic = isPublic;
        this.state = DesignState.CREATED;
        this.searchingCarpenter = false;
        this.quotations = new ArrayList<>();
    }
}
