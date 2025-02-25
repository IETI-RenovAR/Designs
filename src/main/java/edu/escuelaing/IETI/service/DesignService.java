package edu.escuelaing.IETI.service;

import edu.escuelaing.IETI.dto.DesignDTO;
import edu.escuelaing.IETI.dto.QuotationDTO;
import edu.escuelaing.IETI.exception.DesignNotFound;
import edu.escuelaing.IETI.exception.QuotationNotFound;
import edu.escuelaing.IETI.model.Design;
import edu.escuelaing.IETI.model.Quotation;
import edu.escuelaing.IETI.repository.DesignRepository;
import edu.escuelaing.IETI.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignService {

    private final DesignRepository designRepository;
    private final QuotationRepository quotationRepository;

    @Autowired
    public DesignService(DesignRepository designRepository, QuotationRepository quotationRepository) {
        this.designRepository = designRepository;
        this.quotationRepository = quotationRepository;
    }

    public List<Design> getAllPublicDesigns() {
        return designRepository.findByIsPublic(true);
    }

    public Design getDesignById(String id) throws Exception {
        Optional<Design> design = designRepository.findById(id);
        if(!design.isPresent()) throw new DesignNotFound(id);
        return design.get();
    }

    public Design saveDesign(DesignDTO designDTO) {
        Design newDesign = new Design(designDTO);
        return designRepository.save(newDesign);
    }

    public Design updateDesign(String id, DesignDTO designDTO) throws Exception {
        Design updateDesign = getDesignById(id);
        updateDesign.setUserId(designDTO.getUserId());
        updateDesign.setName(designDTO.getName());
        updateDesign.setUrl(designDTO.getUrl());
        updateDesign.setPublic(designDTO.isPublic());
        updateDesign.setState(designDTO.getState());
        updateDesign.setSearchingCarpenter(designDTO.isSearchingCarpenter());
        updateDesign.setQuotations(designDTO.getQuotations());
        return designRepository.save(updateDesign);
    }

    public void deleteDesign(String id) {
        designRepository.deleteById(id);
    }

    public List<Design> getDesignByUserId(String userId) {
        return designRepository.findByUserId(userId);
    }

    public List<Quotation> getAllQuotations(String id) throws Exception {
        Design design = getDesignById(id);
        return design.getQuotations();
    }

    public Quotation getQuotationById(String idQuotation) throws Exception {
        Optional<Quotation> quotation = quotationRepository.findById(idQuotation);
        if(!quotation.isPresent()) throw new QuotationNotFound(idQuotation);
        return quotation.get();
    }

    public Quotation saveQuotation(QuotationDTO quotationDTO, String designId) throws Exception {
        // Creo y guardo la nueva cotización
        Quotation newQuotation = new Quotation(quotationDTO);
        quotationRepository.save(newQuotation);
        Design design = getDesignById(designId);

        // Traigo la lista y la actualizo
        List<Quotation> quotations = design.getQuotations();
        quotations.add(newQuotation);
        design.setQuotations(quotations);

        // Guardo el diseño con el cambio en su lista de cotizaciones
        designRepository.save(design);

        return newQuotation;
    }

    public Quotation updateQuotation(String quotationId, QuotationDTO quotationDTO) throws Exception {
        Quotation updateQuotation = getQuotationById(quotationId);
        updateQuotation.setCarpenterId(quotationDTO.getCarpenterId());
        updateQuotation.setPrice(quotationDTO.getPrice());
        updateQuotation.setAccepted(quotationDTO.isAccepted());
        return quotationRepository.save(updateQuotation);
    }

    public void deleteQuotation(String quotationId) {
        quotationRepository.deleteById(quotationId);
    }
}
