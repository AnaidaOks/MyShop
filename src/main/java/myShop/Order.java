package myShop;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private int orderNumber;
    private String orderStatus;
    private double orderAmount;
    private ArrayList<OrderPosition> lOrderPosition;

    public Order(ArrayList<OrderPosition> positions, int orderNumber) {
        this.orderNumber = orderNumber;
        this.lOrderPosition = positions;
        this.orderStatus = "Заказ оформлен";
        for (OrderPosition orderPosition : positions) {
            this.orderAmount += orderPosition.getAmount();
        }
    }

    public ArrayList<OrderPosition> getOrderPositions() {
        return lOrderPosition;
    }

    public void addPositionToOrder(ArrayList<OrderPosition> positions) {
        positions.stream().map(position -> {
            orderAmount += position.getAmount();
            return orderAmount;
        });
        lOrderPosition.addAll(positions);
    }

    public void removePositionFromOrder(OrderPosition position) {
        orderAmount -= position.getAmount();
        lOrderPosition.remove(position);
        position.getProduct().removeRating();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderPosition getPosition(int indx) {
        return lOrderPosition.get(indx);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getOrderAmount() {
        return  orderAmount;
    }

    public void setOrderAmount(double amount) {
        orderAmount = amount;
    }

    public void setOrderStatus(String status) {
        orderStatus = status;
    }

    public void printOrder() {
        if (!lOrderPosition.isEmpty()) {
            System.out.println("Заказ № " + orderNumber + ", статус: " + orderStatus + ", сумма заказа: " + orderAmount);
            System.out.println(String.format("%-5.5s", "№") + String.format("%-20.20s", "Название") + " | " +
                    String.format("%-10.10s", "Произв.") + " | " +  String.format("%-10.10s", "Цена") +
                    String.format("%-10.10s", "Кол-во") + " | " + "Сумма");
            for (int i = 0; i < lOrderPosition.size(); i++) {
                System.out.println(String.format("%-5.5s", i + 1) +
                        String.format("%-20.20s", lOrderPosition.get(i).getProduct().getName()) +
                        " | " + String.format("%-10.10s", lOrderPosition.get(i).getProduct().getProducer()) +
                        " | " + String.format("%-10.10s", lOrderPosition.get(i).getProduct().getPrice()) +
                        " | " + String.format("%-10.10s", lOrderPosition.get(i).getCount()) +
                        " | " + String.format("%-10.10s", lOrderPosition.get(i).getAmount())
                );
            }
            System.out.println();
        } else {
            System.out.println("В заказ " + orderNumber + " не добавлены товары");
        }
    }

    public Order copyOrder(int orderNumber) {
        ArrayList<OrderPosition> orderPositions = new ArrayList<>();
        double amount = 0.0;
        for (OrderPosition orderPosition : this.getOrderPositions()) {
            OrderPosition copiedPosition = orderPosition.copyOrderPosition(orderPosition);
            orderPositions.add(orderPosition.copyOrderPosition(orderPosition));
            amount += copiedPosition.getAmount();
        }

        Order copyOrder = new Order(this.getOrderPositions(), orderNumber);
        copyOrder.setOrderAmount(amount);

        return copyOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }
}
