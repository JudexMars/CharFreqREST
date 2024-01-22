package org.judexmars.charfreqrest.dto.response;

import java.util.Map;

public record CharFreqResponseDto(
        Map<Character, Integer> data
) {
}
