package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberGeneratorTest {

    @Test
    void generateNumbers() {
        NumberGenerator generator = new NumberGenerator() {
            @Override
            protected List<Integer> shuffleNumbers(List<Integer> candidateNumbers) {
                return candidateNumbers;
            }
        };

        List<Integer> result = generator.generateNumbers(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 6);
        assertThat(result.size()).isEqualTo(6);
        assertThat(result).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }
}
