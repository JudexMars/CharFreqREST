package org.judexmars.charfreqrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.judexmars.charfreqrest.dto.request.CharFreqRequestDto;
import org.judexmars.charfreqrest.service.CharFreqService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CharFreqController.class)
public class CharFreqControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharFreqService charFreqService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void evaluateShouldReturnMap() throws Exception {

        var line = "AaAbbccc";

        when(charFreqService.evaluate(line)).thenReturn(Map.of(
                'c', 3,
                'b', 2,
                'A', 2,
                'a', 1
        ));

        var requestDto = new CharFreqRequestDto(line);
        var requestDtoJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/freq")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isMap());
    }
}
