package edu.escuelaing.IETI.controller;

import edu.escuelaing.IETI.dto.DesignDTO;
import edu.escuelaing.IETI.dto.QuotationDTO;
import edu.escuelaing.IETI.model.Design;
import edu.escuelaing.IETI.model.Quotation;
import edu.escuelaing.IETI.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/v1/designs")
public class DesignController {

    private final DesignService designService;

    @Autowired
    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @GetMapping
    public ResponseEntity<List<Design>> getAllPublicDesigns() {
        List<Design> designs = designService.getAllPublicDesigns();
        return ResponseEntity.ok(designs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Design> findById(@PathVariable("id") String id) {
        try {
            Design design = designService.getDesignById(id);
            return ResponseEntity.ok(design);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Design>> findByUserId(@PathVariable("userId") String userId) {
        List<Design> designs = designService.getDesignByUserId(userId);
        return ResponseEntity.ok(designs);
    }

    @PostMapping
    public ResponseEntity<Design> createDesign(@RequestBody DesignDTO designDTO) {
        Design createdDesign = designService.saveDesign(designDTO);
        URI createdDesignUri = URI.create("/v1/design/" + createdDesign.getId());
        return ResponseEntity.created(createdDesignUri).body(createdDesign);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Design> updateDesign(@PathVariable("id") String id, @RequestBody DesignDTO designDTO) {
        try {
            Design updatedDesign = designService.updateDesign(id, designDTO);
            return ResponseEntity.ok(updatedDesign);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesign(@PathVariable("id") String id) {
        designService.deleteDesign(id);
        return ResponseEntity.ok(null);
    }

    // Cotizaciones de los diseños

    @GetMapping("/{id}/quotations")
    public ResponseEntity<List<Quotation>> getAllQuotations(@PathVariable("id")  String id) {
        try {
            List<Quotation> quotations = designService.getAllQuotations(id);
            return ResponseEntity.ok(quotations);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/quotations/{idQuotation}")
    public ResponseEntity<Quotation> findByQuotationId(@PathVariable("idQuotation") String idQuotation) {
        try {
            Quotation quotation = designService.getQuotationById(idQuotation);
            return ResponseEntity.ok(quotation);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/quotations")
    public ResponseEntity<Quotation> createQuotation(@PathVariable("id") String designId,
                                                     @RequestBody QuotationDTO quotationDTO) {
        try {
            Quotation createdQuotation = designService.saveQuotation(quotationDTO, designId);
            URI createdQuotationUri = URI.create("/v1/design/" + designId + "/quotation/" + createdQuotation.getId());
            return ResponseEntity.created(createdQuotationUri).body(createdQuotation);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/quotations/{idQuotation}")
    public ResponseEntity<Quotation> updateQuotation(@PathVariable("idQuotation") String quotationId,
                                                     @RequestBody QuotationDTO quotationDTO) {
        try {
            Quotation updatedQuotation = designService.updateQuotation(quotationId, quotationDTO);
            return ResponseEntity.ok(updatedQuotation);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/quotations/{idQuotation}")
    public ResponseEntity<Void> deleteQuotation(@PathVariable("idQuotation") String quotationId) {
        designService.deleteQuotation(quotationId);
        return ResponseEntity.ok(null);
    }
}
