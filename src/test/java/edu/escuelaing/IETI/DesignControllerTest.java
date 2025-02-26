package edu.escuelaing.IETI;

import edu.escuelaing.IETI.controller.DesignController;
import edu.escuelaing.IETI.dto.DesignDTO;
import edu.escuelaing.IETI.model.Design;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignController.class)
class DesignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DesignService designService;

    private Design design;

    @BeforeEach
    void setUp() {
        design = new Design();
        design.setId("1");
        design.setName("Test Design");
    }

    @Test
    void getAllPublicDesigns_ShouldReturnListOfDesigns() throws Exception {
        List<Design> designs = Arrays.asList(design);
        when(designService.getAllPublicDesigns()).thenReturn(designs);

        mockMvc.perform(get("/v1/designs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Test Design"));
    }

    @Test
    void findById_ShouldReturnDesign_WhenFound() throws Exception {
        when(designService.getDesignById("1")).thenReturn(design);

        mockMvc.perform(get("/v1/designs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Design"));
    }

    @Test
    void findById_ShouldReturnNotFound_WhenDesignDoesNotExist() throws Exception {
        when(designService.getDesignById("99")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/v1/designs/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createDesign_ShouldReturnCreatedDesign() throws Exception {
        DesignDTO designDTO = new DesignDTO();
        when(designService.saveDesign(any(DesignDTO.class))).thenReturn(design);

        mockMvc.perform(post("/v1/designs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Test Design\" }"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Design"));
    }

    @Test
    void deleteDesign_ShouldReturnOk() throws Exception {
        Mockito.doNothing().when(designService).deleteDesign("1");

        mockMvc.perform(delete("/v1/designs/1"))
                .andExpect(status().isOk());
    }
}

