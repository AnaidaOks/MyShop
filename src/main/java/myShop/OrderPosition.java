package myShop;

import java.util.Objects;

public class OrderPosition {
    private Product product;
    private int count;
    private double amount;

    public OrderPosition(){}

    public Product getProduct() {
        return  product;
    }

    public int getCount() {
        return count;
    }

    public double getAmount() {
        return amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public void printPosition() {
        if(product != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-20.20s", product.getName()));
            sb.append(String.format("%-10.10s", product.getProducer()));
            sb.append(String.format("%-5.5s", product.getPrice()));
            sb.append(String.format("%-5.5s", count));
            sb.append(String.format("%-5.5s", amount));
            System.out.println(sb);
        }
    }

    public OrderPosition copyOrderPosition(OrderPosition orderPosition) {
        OrderPosition orderPositionCopy = new OrderPosition();
        orderPositionCopy.setProduct(orderPosition.getProduct());
        orderPositionCopy.setCount(orderPosition.getCount());
        orderPositionCopy.setAmount(orderPosition.getAmount());
        return orderPositionCopy;
    }
}
