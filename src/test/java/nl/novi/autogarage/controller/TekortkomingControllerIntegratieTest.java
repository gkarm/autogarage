package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.TekortkomingDto;
import nl.novi.autogarage.model.Tekortkoming;
import nl.novi.autogarage.service.TekortkomingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class TekortkomingControllerIntegratieTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TekortkomingService tekortkomingService;

    @Test
    void getTekortkomingById_Found() throws Exception {
        Tekortkoming tekortkoming = new Tekortkoming();
        tekortkoming.setId(1L);
        tekortkoming.setBeschrijving("Test Beschrijving");
        tekortkoming.setOplossing("Test Oplossing");

        when(tekortkomingService.getTekortkomingById(1L)).thenReturn(tekortkoming);

        mockMvc.perform(get("/tekortkomingen/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.beschrijving").value("Test Beschrijving"))
                .andExpect(jsonPath("$.oplossing").value("Test Oplossing"));
    }

    @Test
    public void testGetTekortkomingById_NotFound() throws Exception {
        when(tekortkomingService.getTekortkomingById(1L)).thenReturn(null);

        mockMvc.perform(get("/tekortkomingen/1"))
                .andExpect(status().isNotFound());
    }



    @Test
    public void testUpdateTekortkoming() throws Exception {
        TekortkomingDto dto = new TekortkomingDto(null, "Updated Beschrijving", "Updated Oplossing", 1L);
        Tekortkoming updatedTekortkoming = new Tekortkoming();
        updatedTekortkoming.setId(1L);
        updatedTekortkoming.setBeschrijving("Updated Beschrijving");
        updatedTekortkoming.setOplossing("Updated Oplossing");

        when(tekortkomingService.updateTekortkoming(anyLong(), any(TekortkomingDto.class))).thenReturn(updatedTekortkoming);

        mockMvc.perform(put("/tekortkomingen/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"beschrijving\":\"Updated Beschrijving\", \"oplossing\":\"Updated Oplossing\", \"autoId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.beschrijving").value("Updated Beschrijving"))
                .andExpect(jsonPath("$.oplossing").value("Updated Oplossing"));
    }

    @Test
    void deleteTekortkoming() throws Exception {
        doNothing().when(tekortkomingService).deleteTekortkoming(1L);

        mockMvc.perform(delete("/tekortkomingen/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void addTekortkomingToAuto() throws Exception {
        TekortkomingDto dto = new TekortkomingDto(null, "Test Beschrijving", "Test Oplossing", 1L);
        Tekortkoming createdTekortkoming = new Tekortkoming();
        createdTekortkoming.setId(1L);
        createdTekortkoming.setBeschrijving("Test Beschrijving");
        createdTekortkoming.setOplossing("Test Oplossing");

        when(tekortkomingService.addTekortkomingToAuto(any(TekortkomingDto.class))).thenReturn(createdTekortkoming);

        mockMvc.perform(post("/tekortkomingen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"beschrijving\":\"Test Beschrijving\", \"oplossing\":\"Test Oplossing\", \"autoId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.beschrijving").value("Test Beschrijving"))
                .andExpect(jsonPath("$.oplossing").value("Test Oplossing"));
    }
}