package store.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {


    @Test
    @DisplayName("Y를 입력하면 YES(Enum)를 찾는다")
    void find_YES() {
        //give
        String command = "Y";

        //when
        Command findCommand = Command.find(command);

        //then
        Assertions.assertThat(findCommand).isEqualTo(Command.YES);
    }

    @Test
    @DisplayName("N을 입력하면 NO(Enum)을 찾는다")
    void find_NO() {
        //give
        String command = "N";

        //when
        Command findCommand = Command.find(command);

        //then
        Assertions.assertThat(findCommand).isEqualTo(Command.NO);
    }

    @Test
    @DisplayName("유효하지 않은 커맨드 입력시 예외 발생")
    void find_Exception() {
        //give
        String command = "Exception";

        //when//then
        Assertions.assertThatThrownBy(() -> Command.find(command))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Yes인지 판단 시 YES(Enum)라면 True")
    void isYes_True() {
        //give
        Command command = Command.YES;

        //when//then
        Assertions.assertThat(command.isYes()).isTrue();
    }

    @Test
    @DisplayName("Yes인지 판단 시 NO(Enum)라면 False")
    void isYes_False() {
        //give
        Command command = Command.NO;

        //when//then
        Assertions.assertThat(command.isYes()).isFalse();
    }

}