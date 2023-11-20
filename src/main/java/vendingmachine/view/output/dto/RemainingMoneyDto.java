package vendingmachine.view.output.dto;

public class RemainingMoneyDto {
    private final int amount;

    public RemainingMoneyDto(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%d원", amount);
    }
}