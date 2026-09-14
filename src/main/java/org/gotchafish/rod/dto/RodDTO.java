package org.gotchafish.rod.dto;

public class RodDTO {
    private Long rodId;
    private String rodName;
    private int catchProbability;
    private int price;

    private int quantity;

    public RodDTO(Long rodId, String rodName, int catchProbability, int price) {
        this.rodId = rodId;
        this.rodName = rodName;
        this.catchProbability = catchProbability;
        this.price = price;
    }

    public RodDTO(Long rodId, String rodName, int catchProbability, int price, int quantity) {
        this(rodId, rodName, catchProbability, price);
        this.quantity = quantity;
    }

    public Long getRodId() { return rodId; }

    public String getRodName() { return rodName; }

    public int getCatchProbability() { return catchProbability; }

    public int getPrice() { return price; }

    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "RodDTO{" +
                "rodId=" + rodId +
                ", rodName='" + rodName + '\'' +
                ", catchProbability=" + catchProbability +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}