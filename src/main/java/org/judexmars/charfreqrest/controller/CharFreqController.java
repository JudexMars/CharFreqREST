package org.judexmars.charfreqrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.judexmars.charfreqrest.dto.request.CharFreqRequestDto;
import org.judexmars.charfreqrest.dto.response.CharFreqResponseDto;
import org.judexmars.charfreqrest.service.CharFreqService;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Char frequency", description = "Работа со строкой")
@RequestMapping("/freq")
@RequiredArgsConstructor
@Slf4j
public class CharFreqController {

    private final CharFreqService charFreqService;

    @PostMapping
    @Operation(description = "Вычисление частоты каждого символа в строке")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Частота вычислена"),
            @ApiResponse(responseCode = "400", description = "Неверный формат данных", content =
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public CharFreqResponseDto evaluate(@RequestBody @Valid CharFreqRequestDto requestDto) {

        var result = charFreqService.evaluate(requestDto.line());
        return new CharFreqResponseDto(result);
    }
}
