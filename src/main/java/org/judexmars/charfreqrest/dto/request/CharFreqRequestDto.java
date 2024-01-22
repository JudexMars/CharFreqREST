package org.judexmars.charfreqrest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CharFreqRequestDto(
        @NotBlank
        String line
) {
}
