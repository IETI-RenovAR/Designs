package edu.escuelaing.IETI.service;

import edu.escuelaing.IETI.dto.DesignDTO;
import edu.escuelaing.IETI.dto.QuotationDTO;
import edu.escuelaing.IETI.model.Design;
import edu.escuelaing.IETI.model.Quotation;
import edu.escuelaing.IETI.repository.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DesignService {

    private final DesignRepository designRepository;

    @Autowired
    public DesignService(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    public List<Design> getAllPublicsDesigns() {
        return null;
    }

    public Design getDesignById(String id) {
        return null;
    }

    public Design saveDesign(DesignDTO designDTO) {
        return null;
    }

    public Design updateDesign(String id, DesignDTO designDTO) {
        return null;
    }

    public void deleteDesign(String id) {
    }

    public List<Design> getDesignByUserId(String userId) {
        return null;
    }

    public List<Quotation> getAllQuotations(String id) {
        return null;
    }

    public Quotation getQuotationById(String id, String idQuotation) {
        return null;
    }

    public Quotation saveQuotation(QuotationDTO quotationDTO, String designId) {
        return null;
    }

    public Quotation updateQuotation(String designId, String quotationId, DesignDTO designDTO) {
        return null;
    }

    public void deleteQuotation(String designId, String quotationId) {
    }
}
