package com.andersen.hoover.oleg.controller;

import com.andersen.hoover.oleg.dto.HooverTaskDto;
import com.andersen.hoover.oleg.dto.HooverTaskResultDto;
import com.andersen.hoover.oleg.dto.converter.HooverTaskDtoConverter;
import com.andersen.hoover.oleg.dto.converter.HooverTaskResultDtoConverter;
import com.andersen.hoover.oleg.entity.HooverTask;
import com.andersen.hoover.oleg.entity.HooverTaskResult;
import com.andersen.hoover.oleg.service.HooverTaskResultService;
import com.andersen.hoover.oleg.service.HooverTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doCleaningJob")
public class HooverController {
    private final HooverTaskService hooverTaskService;
    private final HooverTaskResultService hooverTaskResultService;
    private final HooverTaskDtoConverter hooverTaskDtoConverter;
    private final HooverTaskResultDtoConverter hooverTaskResultDtoConverter;

    @PostMapping
    public HooverTaskResultDto doCleaningJob(@Valid @RequestBody HooverTaskDto hooverTaskDto) {
        HooverTask hooverTask = hooverTaskService.save(hooverTaskDtoConverter.fromDto(hooverTaskDto));
        HooverTaskResult hooverTaskResult = hooverTaskResultService.doCleaning(hooverTask);
        hooverTaskResult.setHooverTask(hooverTask);
        hooverTaskResultService.save(hooverTaskResult);

        return hooverTaskResultDtoConverter.toDto(hooverTaskResult);
    }

}
