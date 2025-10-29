package com.viewmanagementservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewmanagementservice.dto.Event;
import com.viewmanagementservice.dto.ResourceMetadataEvent;
import com.viewmanagementservice.model.EventType;
import com.viewmanagementservice.service.ViewManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ViewManagementController.class)
public class ViewManagementControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ViewManagementService viewManagementService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testProcessEventsWithInvalidEvent() throws Exception {
        List<Event> events = Collections.singletonList(new ResourceMetadataEvent("", EventType.RESOURCE_METADATA)); // Invalid because it's blank

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(events)))
                .andExpect(status().isBadRequest());
    }
}
