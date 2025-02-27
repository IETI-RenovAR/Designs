package org.adaschool.project;


import org.adaschool.project.controller.DesignController;
import org.adaschool.project.dto.DesignDTO;
import org.adaschool.project.model.Design;
import org.adaschool.project.service.DesignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DesignControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DesignService designService;

    @InjectMocks
    private DesignController designController;

    private Design design;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(designController).build();
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
