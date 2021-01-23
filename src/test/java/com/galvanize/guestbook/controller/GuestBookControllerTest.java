package com.galvanize.guestbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.model.VisitorEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GuestBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Any visitor can post their name and a comment to the Guestbook.
     */
    @Test
    public void testAddNewEntry() throws Exception {

        VisitorEntry visitorEntry = VisitorEntry.builder()
                .comment("happy birthday")
                .name("parthiban")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/guestbook/entry")
                .content(new ObjectMapper().writeValueAsString(visitorEntry))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    /**
     * All visitors can see a list of every entry in the Guestbook.
     *
     * @throws Exception
     */
    @Test
    public void testFindAllEntries() throws Exception {
        mockMvc.perform(get("/api/guestbook/entry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Parthiban"))
                .andExpect(jsonPath("$[0].comment").value("happy birthday"));
    }
}
