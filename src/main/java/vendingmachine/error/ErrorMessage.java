package vendingmachine.error;

public class ErrorMessage {
    public static final String ERROR_HEADER = "[ERROR]";
    public static final String WHITE_SPACE = " ";
    public static final String RETRY = "다시 입력해 주세요.";

    public static String getMessage(String message) {
        return ERROR_HEADER + WHITE_SPACE
                + message + WHITE_SPACE
                + RETRY;
    }
}