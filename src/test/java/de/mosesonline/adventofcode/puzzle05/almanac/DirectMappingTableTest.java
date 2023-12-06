package de.mosesonline.adventofcode.puzzle05.almanac;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectMappingTableTest {
    @ParameterizedTest
    @MethodSource("mappingParameters")
    void should_find_mapping(int input, int mappingOutput) {
        final var mapping = new DirectMappingTable("seed", "soil", new ArrayList<>(List.of(
                AlmanacMapEntry.from("50 98 2"),
                AlmanacMapEntry.from("52 50 48"))
        ));
        Seed mapped = mapping.map(new Seed(input));
        assertEquals(mappingOutput , mapped.valueFor("soil"));
    }

    static Stream<Arguments> mappingParameters(){
        return Stream.of(
                Arguments.arguments(79, 81),
                Arguments.arguments(50, 52),
                Arguments.arguments(97, 99),
                Arguments.arguments(98, 50),
                Arguments.arguments(99, 51)

        );
    }
}
