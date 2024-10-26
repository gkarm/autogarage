package nl.novi.autogarage.controller;
import nl.novi.autogarage.dto.KlantDto;
import nl.novi.autogarage.model.Klant;
import nl.novi.autogarage.service.KlantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class KlantControllerIntegratieTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KlantService klantService;

    @Test
    void getKlantById_Found() throws Exception {
        Klant klant = new Klant();
        klant.setId(1L);
        klant.setFirstName("Koffi");
        klant.setLastName("Gombo");
        klant.setDob(LocalDate.of(1990, 1, 1));
        klant.setPhone("123456789");

        when(klantService.getKlantById(1L)).thenReturn(klant);

        mockMvc.perform(get("/klanten/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Koffi"))
                .andExpect(jsonPath("$.lastName").value("Gombo"))
                .andExpect(jsonPath("$.dob").value("1990-01-01"))
                .andExpect(jsonPath("$.phone").value("123456789"));
    }

    @Test
    void getKlantById_NotFound() throws Exception {
        when(klantService.getKlantById(1L)).thenReturn(null);

        mockMvc.perform(get("/klanten/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllKlanten() throws Exception {
        Klant klant = new Klant();
        klant.setId(1L);
        klant.setFirstName("Koffi");
        klant.setLastName("Gombo");

        List<Klant> klanten = Collections.singletonList(klant);
        when(klantService.getAllKlanten()).thenReturn(klanten);

        mockMvc.perform(get("/klanten"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("Koffi"))
                .andExpect(jsonPath("$[0].lastName").value("Gombo"));
    }

    @Test
    void createKlant() throws Exception {
        KlantDto klantDto = new KlantDto(null, "Koffi", "Gombo", LocalDate.of(1990, 1, 1), "123456789");
        KlantDto createdKlantDto = new KlantDto(1L, "Koffi", "Gombo", LocalDate.of(1990, 1, 1), "123456789");

        when(klantService.createKlant(any(KlantDto.class))).thenReturn(createdKlantDto);

        mockMvc.perform(post("/klanten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Koffi\", \"lastName\":\"Gombo\", \"dob\":\"1990-01-01\", \"phone\":\"123456789\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Koffi"))
                .andExpect(jsonPath("$.lastName").value("Gombo"))
                .andExpect(jsonPath("$.dob").value("1990-01-01"))
                .andExpect(jsonPath("$.phone").value("123456789"));
    }

    @Test
    void updateKlant() throws Exception {
        Klant klant = new Klant();
        klant.setId(1L);
        klant.setFirstName("Kwassi");
        klant.setLastName("Gombo");

        when(klantService.updateKlant(anyLong(), any(Klant.class))).thenReturn(klant);

        mockMvc.perform(put("/klanten/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Kwassi\", \"lastName\":\"Gombo\", \"dob\":\"1990-01-01\", \"phone\":\"123456789\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Kwassi"))
                .andExpect(jsonPath("$.lastName").value("Gombo"));
    }

    @Test
    void deleteKlant() throws Exception {
        doNothing().when(klantService).deleteKlant(1L);

        mockMvc.perform(delete("/klanten/1"))
                .andExpect(status().isNoContent());
    }
}
