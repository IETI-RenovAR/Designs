package edu.escuelaing.IETI.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class DesignDTO {
    private String id;
    private String userId;
    private String name;
    private String url;
    private boolean isPublic;
    private String state;
    private boolean searchingCarpenter;

    public DesignDTO(String name, String userId, String id, String url, boolean isPublic, String state, boolean searchingCarpenter) {
        this.name = name;
        this.userId = userId;
        this.id = id;
        this.url = url;
        this.isPublic = isPublic;
        this.state = state;
        this.searchingCarpenter = searchingCarpenter;
    }
}
