package edu.escuelaing.IETI.controller;

import com.sun.org.apache.xpath.internal.operations.Quo;
import edu.escuelaing.IETI.dto.DesignDTO;
import edu.escuelaing.IETI.dto.QuotationDTO;
import edu.escuelaing.IETI.model.Design;
import edu.escuelaing.IETI.model.Quotation;
import edu.escuelaing.IETI.repository.DesignRepository;
import edu.escuelaing.IETI.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.crypto.Des;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/v1/design")
public class DesignController {

    private final DesignService designService;

    @Autowired
    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @GetMapping
    public ResponseEntity<List<Design>> getAllPublicsDesigns() {
        List<Design> designs = designService.getAllPublicsDesigns();
        return ResponseEntity.ok(designs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Design> findById(@PathVariable("id") String id) {
        Design design = designService.getDesignById(id);
        return ResponseEntity.ok(design);
    }

    @GetMapping("/user/{userId}")
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
        Design updatedDesign = designService.updateDesign(id, designDTO);
        return ResponseEntity.ok(updatedDesign);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesign(@PathVariable("id") String id) {
        designService.deleteDesign(id);
        return ResponseEntity.ok(null);
    }

    //////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{id}/quotation")
    public ResponseEntity<List<Quotation>> getAllQuotations(@PathVariable("id")  String id) {
        List<Quotation> quotations = designService.getAllQuotations(id);
        return ResponseEntity.ok(quotations);
    }

    @GetMapping("/{id}/quotation/{idQuotation}")
    public ResponseEntity<Quotation> findByQuotationsId(@PathVariable("id")  String id, @PathVariable("idQuotation") String idQuotation) {
        Quotation quotation = designService.getQuotationById(id,idQuotation);
        return ResponseEntity.ok(quotation);
    }

    @PostMapping("/{id}/quotation")
    public ResponseEntity<Quotation> createQuotation(@PathVariable("id")  String designId, @RequestBody QuotationDTO quotationDTO) {
        Quotation createdQuotation = designService.saveQuotation(quotationDTO, designId);
        URI createdQuotationUri = URI.create("/v1/design/" + designId + "/quotation/" + createdQuotation.getId());
        return ResponseEntity.created(createdQuotationUri).body(createdQuotation);
    }

    @PutMapping("/{id}/quotation/{idQuotation}")
    public ResponseEntity<Quotation> updateQuotation(@PathVariable("id") String designId, @PathVariable("idQuotation") String quotationId, @RequestBody DesignDTO designDTO) {
        Quotation updatedQuotation = designService.updateQuotation(designId, quotationId,designDTO);
        return ResponseEntity.ok(updatedQuotation);
    }

    @DeleteMapping("/{id}/quotation/{idQuotation}")
    public ResponseEntity<Void> deleteQuotation(@PathVariable("id") String designId, @PathVariable("idQuotation") String quotationId) {
        designService.deleteQuotation(designId, quotationId);
        return ResponseEntity.ok(null);
    }
}
