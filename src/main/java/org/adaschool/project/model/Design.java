package org.adaschool.project.model;


import org.adaschool.project.dto.DesignDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "designs")
public class Design {
    @Id
    private String id;
    private String userId;
    private String name;
    private String url;
    private Boolean isPublic;
    private DesignState state;
    private Boolean searchingCarpenter;
    @DBRef
    private List<Quotation> quotations;

    public Design() {
    }

    public Design(String id, String name, String userId, String url, Boolean isPublic, DesignState state,
                  Boolean searchingCarpenter, List<Quotation> quotations) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.url = url;
        this.isPublic = isPublic;
        this.state = state;
        this.searchingCarpenter = searchingCarpenter;
        this.quotations = quotations;
    }

    // Constructor que puede ser usado por el cliente cada vez que crea un diseño
    public Design(String userId, String name, String url, Boolean isPublic) {
        this.id = null;
        this.userId = userId;
        this.name = name;
        this.url = url;
        this.isPublic = isPublic;
        this.state = DesignState.CREATED;
        this.searchingCarpenter = false;
        this.quotations = new ArrayList<>();
    }

    // Constructor a partir de DTO
    public Design(DesignDTO designDTO) {
        this.id = null;
        this.userId = designDTO.getUserId();
        this.name = designDTO.getName();
        this.url = designDTO.getUrl();
        this.isPublic = designDTO.getIsPublic();
        this.state = designDTO.getState();
        this.searchingCarpenter = designDTO.getSearchingCarpenter();
        this.quotations = designDTO.getQuotations();
    }
}
