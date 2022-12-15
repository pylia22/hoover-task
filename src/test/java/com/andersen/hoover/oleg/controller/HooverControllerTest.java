package com.andersen.hoover.oleg.controller;

import com.andersen.hoover.oleg.HooverTaskApplication;
import com.andersen.hoover.oleg.dto.HooverTaskDto;
import com.andersen.hoover.oleg.dto.HooverTaskResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = HooverTaskApplication.class)
@AutoConfigureMockMvc
class HooverControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnErrorMessageWhenRoomLengthZero() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(0, 5, 3, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid hoover position. Room length must be greater than 0")));
    }

    @Test
    void shouldReturnErrorMessageWhenRoomLengthNull() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(null, 5, 3, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid hoover position. Room length must not be null")));
    }

    @Test
    void shouldReturnErrorMessageWhenRoomWidthZero() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, 0, 3, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid hoover position. Room width must be greater than 0")));
    }

    @Test
    void shouldReturnErrorMessageWhenRoomWidthNull() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, null, 3, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid hoover position. Room width must not be null")));
    }

    @Test
    void shouldReturnErrorMessageWhenHooverXAxisZero() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, 5, 0, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Hoover X Axis coordinates must be greater than 0")));
    }

    @Test
    void shouldReturnErrorMessageWhenHooverXAxisNull() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, 5, null, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Hoover X Axis coordinates must not be null. Invalid hoover position")));
    }

    @Test
    void shouldReturnErrorMessageWhenHooverYAxisZero() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, 5, 3, 0);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Hoover Y Axis coordinates must be greater than 0")));
    }

    @Test
    void shouldReturnErrorMessageWhenHooverYAxisNull() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, 5, 3, null);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Hoover Y Axis coordinates must not be null. Invalid hoover position")));
    }

    @Test
    void shouldReturnIsOkStatusCodeAndHooverTaskResult() throws Exception {
        HooverTaskDto hooverTaskDto = new HooverTaskDto(5, 5, 3, 3);

        mvc.perform(post("/api/v1/doCleaningJob")
                        .content(objectMapper.writeValueAsString(hooverTaskDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.directionHistory", is(any(String.class))))
                .andExpect(jsonPath("$.cleanCounter", is(any(Integer.TYPE))));
    }
}