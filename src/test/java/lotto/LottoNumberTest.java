package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 30, 40, 45})
    void createLottoNumberTest(int number) {
        LottoNumber lottoNumber = LottoNumber.of(number);
        assertThat(lottoNumber).isNotNull();
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    void createInvalidLottoNumberTest(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(RuntimeException.class);
    }
}
