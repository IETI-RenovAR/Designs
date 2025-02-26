package edu.escuelaing.IETI;

import edu.escuelaing.IETI.controller.DesignController;
import edu.escuelaing.IETI.dto.QuotationDTO;
import edu.escuelaing.IETI.model.Quotation;
import edu.escuelaing.IETI.service.DesignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignController.class)
class QuotationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DesignService designService;

    private Quotation quotation;

    @BeforeEach
    void setUp() {
        quotation = new Quotation("1", "carpenter1", "500.00", false);
    }

    @Test
    void getAllQuotations_ShouldReturnListOfQuotations() throws Exception {
        List<Quotation> quotations = Arrays.asList(quotation);
        when(designService.getAllQuotations("1")).thenReturn(quotations);

        mockMvc.perform(get("/v1/designs/1/quotations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].carpenterId").value("carpenter1"))
                .andExpect(jsonPath("$[0].price").value("500.00"));
    }

    @Test
    void findByQuotationId_ShouldReturnQuotation_WhenFound() throws Exception {
        when(designService.getQuotationById("1")).thenReturn(quotation);

        mockMvc.perform(get("/v1/designs/quotations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.carpenterId").value("carpenter1"))
                .andExpect(jsonPath("$.price").value("500.00"));
    }

    @Test
    void createQuotation_ShouldReturnCreatedQuotation() throws Exception {
        QuotationDTO quotationDTO = new QuotationDTO("carpenter1", "500.00");
        when(designService.saveQuotation(any(QuotationDTO.class), any(String.class))).thenReturn(quotation);

        mockMvc.perform(post("/v1/designs/1/quotations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"carpenterId\": \"carpenter1\", \"price\": \"500.00\" }"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.carpenterId").value("carpenter1"))
                .andExpect(jsonPath("$.price").value("500.00"));
    }

    @Test
    void updateQuotation_ShouldReturnUpdatedQuotation() throws Exception {
        Quotation updatedQuotation = new Quotation("1", "carpenter1", "600.00", true);
        when(designService.updateQuotation(Mockito.eq("1"), any(QuotationDTO.class))).thenReturn(updatedQuotation);

        mockMvc.perform(put("/v1/designs/quotations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"carpenterId\": \"carpenter1\", \"price\": \"600.00\", \"accepted\": true }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.carpenterId").value("carpenter1"))
                .andExpect(jsonPath("$.price").value("600.00"))
                .andExpect(jsonPath("$.accepted").value(true));
    }

    @Test
    void deleteQuotation_ShouldReturnOk() throws Exception {
        Mockito.doNothing().when(designService).deleteQuotation("1");

        mockMvc.perform(delete("/v1/designs/quotations/1"))
                .andExpect(status().isOk());
    }
}
