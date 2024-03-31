package myShop;

import java.util.HashMap;
import java.util.Map;

public class ShopClient {
    private String fio;
    private HashMap<String, Order> hmOrder;

    public ShopClient(String fio) {
        this.fio = fio;
        hmOrder = new HashMap<>();
    }

    public HashMap<String, Order> addOrder(Order order, String orderNum) {
        hmOrder.put(orderNum, order);
        return hmOrder;
    }

    public Order getOrder(String ordNum) {
        return hmOrder.get(ordNum);
    }

    public void returnOrder(String orderNumber, String status) {
        if (!hmOrder.containsKey(orderNumber)) {
            System.out.println("Внимание! У вас нет заказа с номером " + orderNumber);
        } else {
            Order order = hmOrder.get(orderNumber);
            if (order.getOrderStatus().equals(status)) {
                System.out.println("По заказу " + orderNumber + " уже оформлен возврат");
            } else {
                order.setOrderStatus(status);
                System.out.println("Оформлен возврат заказа " + orderNumber);
            }
        }
    }

    public void copyOrder(String orderNumber, String status, int newOrderNumber) {
        if (!hmOrder.containsKey(orderNumber)) {
            System.out.println("Внимание! У вас нет заказа с номером " + orderNumber);
        } else {
            Order order = hmOrder.get(orderNumber);
            Order copiedOrder = order.copyOrder(newOrderNumber);
            copiedOrder.setOrderStatus(status);
            hmOrder.put(String.valueOf(newOrderNumber), copiedOrder);
            System.out.println("Ваш заказа " + copiedOrder.getOrderNumber() + " добавлен");
        }
    }

    public void printOrder(String orderNum) {
        if (!hmOrder.containsKey(orderNum)) {
            System.out.println("Внимание! У вас нет заказа с номером " + orderNum);
        } else {
            hmOrder.get(orderNum).printOrder();
        }
    }

    public void printOrders() {
        if (hmOrder.isEmpty()) {
            System.out.println("У вас нет заказов");
        } else {
            for(Map.Entry<String, Order> entry : hmOrder.entrySet()) {
                entry.getValue().printOrder();
            }
        }
    }
}
