package com.karlaru.seccrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karlaru.seccrm.contact.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnIndex() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/index.html")
                                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnContactsList() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/contacts")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("Karl Aru"));
    }

    @Test
    public void shouldReturnContactsBySearch() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/contacts/search/karl")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("Karl Aru"));
    }

    @Test
    public void shouldAddContact() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/contacts/")
                        .content(new ObjectMapper().writeValueAsString(
                                        new Contact("Kelli", "klx", "53341212")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("Kelli"));
    }
}
