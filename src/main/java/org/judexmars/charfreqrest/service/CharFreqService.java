package org.judexmars.charfreqrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharFreqService {

    public Map<Character, Integer> evaluate(String line) {

        var map = line
                .chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingByConcurrent(c -> c, Collectors.summingInt(c -> 1)));
        return map
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
