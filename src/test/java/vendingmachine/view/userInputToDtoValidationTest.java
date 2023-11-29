package vendingmachine.view;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.view.input.dto.InitialAmountDto;

public class userInputToDtoValidationTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "10"})
    void 자판기가_보유하고_있는_금액_정상_입력_테스트(String initialAmount) {
        assertThatCode(() -> InitialAmountDto.createInitialAmountDto(initialAmount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"백만원", "-1", "0"})
    void 자판기가_보유하고_있는_금액_예외_입력_테스트(String initialAmount) {
        assertThatCode(() -> InitialAmountDto.createInitialAmountDto(initialAmount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"백만원", "-1", "0"})
    void 추가_상품_정상_입력_테스트(String initialAmount) {
        assertThatCode(() -> InitialAmountDto.createInitialAmountDto(initialAmount))
                .doesNotThrowAnyException();
    }
}
