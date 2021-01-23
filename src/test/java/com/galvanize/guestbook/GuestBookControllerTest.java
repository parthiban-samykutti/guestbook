package com.galvanize.guestbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.bean.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GuestBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Any visitor can post their name and a comment to the Guestbook.
     * All visitors can see a list of every entry in the Guestbook.
     *
     */



    @Test
    public void testPost_Comment_To_GuestBook() throws Exception {

        Post post = new Post();
        post.setComment("happy birthday");
        post.setName("parthiban");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/guestbook/comment")
                .content(new ObjectMapper().writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void test_Get_All_Posts() throws Exception {
        testPost_Comment_To_GuestBook();
        mockMvc.perform(get("/api/getallposts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("parthiban"))
                .andExpect(jsonPath("$[0].comment").value("happy birthday"));
    }
}
