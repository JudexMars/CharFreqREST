package org.judexmars.charfreqrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class CharFreqServiceTest {

    private final CharFreqService charFreqService;

    public CharFreqServiceTest() {
        charFreqService = new CharFreqService();
    }

    @Test
    @DisplayName("Вычисление частоты для пустой строки")
    public void evalBlankString() {
        // given
        var line = "";

        // when
        var map = charFreqService.evaluate(line);

        // then
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Вычисление частоты для простой строки")
    public void evalSimpleLine() {
        // given
        var line = "abbcccddddAAAAA";

        // when
        var map = charFreqService.evaluate(line);

        // then
        assertEquals(5, map.size());
        assertEquals(1, map.get('a'));
        assertEquals(2, map.get('b'));
        assertEquals(3, map.get('c'));
        assertEquals(4, map.get('d'));
        assertEquals(5, map.get('A'));
    }

    @Test
    @DisplayName("Вычисление частоты для максимально длинной строки")
    public void evalLongestPossibleLine() {
        // given
        var sb = new StringBuilder();
        var rand = new Random();
        for (int i = 0; i < 20_000_000; i++) {
            sb.append((char) rand.nextInt(65, 90));
        }
        var line = sb.toString();

        String filePath = "file.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(line);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        // when
        var map1 = charFreqService.evaluate(line);
        var map2 = charFreqService.evaluate(line);
        var map3 = charFreqService.evaluate(line);

        // then
        assertEquals(map1, map2);
        assertEquals(map2, map3);

    }

}
