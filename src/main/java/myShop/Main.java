package myShop;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Product[] shopProducts = new Product[]{
                new Product("Картофель", "Россия", 49.50),
                new Product("Огурцы", "Россия", 100.00),
                new Product("Помидоры", "Беларусь", 120.50),
                new Product("Перец болгарский", "Россия", 250.60),
                new Product("Морковь", "Беларусь", 50.55),
                new Product("Яблоки", "Россия", 125.00),
                new Product("Бананы", "Эквадор", 89.90),
                new Product("Хурма", "Абхазия", 249.50),
                new Product("Мандарины", "Абхазия", 100.00),
                new Product("Грецкий орех", "Россия", 1000.00),
                new Product("Миндаль", "Россия", 1500.00),
                new Product("Финики", "Россия", 1800.00)
        };

        int metaOrderNumber = 0;
        System.out.println("Добрый день.");
        System.out.println("Представьтесь пожалуйста");
        Scanner scanner = new Scanner(System.in);
        String clientFio = scanner.nextLine();
        ShopClient client = new ShopClient(clientFio);
        System.out.println(clientFio + ", добро пожаловать в наш магазин");

        ShopMenu shopMenu = new ShopMenu();

        String optionNum = "";
        boolean isContinueShoping = true;
        while (isContinueShoping) {

            shopMenu.printMainMenu();
            scanner = new Scanner(System.in);
            optionNum = scanner.nextLine();
            String ordNum = "";

            switch (optionNum) {
                case "1":
                    printProducts(shopProducts);
                    break;
                case "2":
                    printProducts(shopProducts);
                    ArrayList<OrderPosition> positions = addPositions(shopProducts);
                    Order myOrder = new Order(positions, ++metaOrderNumber);
                    client.addOrder(myOrder, String.valueOf(myOrder.getOrderNumber()));
                    System.out.println("Ваш заказ № " + myOrder.getOrderNumber() + " на сумму " +
                            myOrder.getOrderAmount() + " оформлен.");
                    break;
                case "3":
                    System.out.println("Введите номер заказа для редактирования:");
                    scanner = new Scanner(System.in);
                    ordNum = scanner.nextLine();
                    Order order = client.getOrder(ordNum);
                    if (order == null) {
                        System.out.println("У вас нет заказа с номером " + ordNum);
                        break;
                    }

                    if(order.getOrderStatus().equals(ShopMenu.orderStatusReturned)) {
                        System.out.println("По заказу " + ordNum + " уже оформлен возврат. Редактирование невозможно");
                        break;
                    }
                    editOrder(order, shopProducts, shopMenu);
                    System.out.println("Ваш заказ:");
                    order.printOrder();
                    break;
                case "4":
                    client.printOrders();
                    System.out.println("Введите номер заказа, который хотите вернуть: ");
                    ordNum = scanner.nextLine();
                    client.returnOrder(ordNum, ShopMenu.orderStatusReturned);
                    break;
                case "5":
                    client.printOrders();
                    System.out.println("Введите номер заказа, который хотите повторить: ");
                    ordNum = scanner.nextLine();
                    client.copyOrder(ordNum, ShopMenu.orderStatusAdded, ++metaOrderNumber);
                    break;
                case "6":
                    System.out.println("Введите номер заказа, который хотите посмотреть: ");
                    ordNum = scanner.nextLine();
                    client.printOrder(ordNum);
                    break;
                case "7":
                    client.printOrders();
                    break;
                case "8":
                    printProductRating(shopProducts);
                    System.out.println("finish case 8");
                case "9":
                    isContinueShoping = false;
                    break;
                default:
                    isContinueShoping = true;
                    break;
            }
        }

        System.out.println("Всего доброго!");
    }

    public static ArrayList<OrderPosition> addPositions(Product[] shopProducts) {
        ArrayList<OrderPosition> positions = new ArrayList<>();
        boolean isContinueAdd = true;
        while (isContinueAdd) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер товара: ");
            int prodNum = scanner.nextInt();

            while (prodNum > shopProducts.length) {
                System.out.println("Номер товара введен неверно. Попробуйте снова ввести номер товара:");
                prodNum = scanner.nextInt();
            }

            System.out.println("Введите количество: ");
            int prodCount = scanner.nextInt();

            OrderPosition ordPos = new OrderPosition();
            ordPos.setProduct(shopProducts[prodNum - 1]);
            ordPos.setCount(prodCount);
            ordPos.setAmount(prodCount * shopProducts[prodNum - 1].getPrice());
            positions.add(ordPos);

            shopProducts[prodNum - 1].setRating();

            System.out.println("Хотите еще добавить товар? 1 - да, 2 - нет");
            scanner = new Scanner(System.in);
            String answ = scanner.nextLine();
            isContinueAdd = "1".equals(answ);
        }
        return positions;
    }

    public static void editOrder(Order order, Product[] shopProducts, ShopMenu shopMenu) {
        order.printOrder();
        boolean isContinue = true;
        while (isContinue) {
            shopMenu.printPositionMenu();
            Scanner scanner = new Scanner(System.in);
            String posMenuNo = scanner.nextLine();
            switch (posMenuNo) {
                case "1":
                    printProducts(shopProducts);
                    ArrayList<OrderPosition> pos = addPositions(shopProducts);
                    System.out.println("finish adding positions");
                    order.addPositionToOrder(pos);
                    break;
                case "2":
                    order.printOrder();
                    Scanner sc = new Scanner(System.in);
                    boolean isContinueRemoving = true;
                    while (isContinueRemoving) {
                        System.out.println("Введите номер товара, который хотите удалить: ");
                        int prodNum = sc.nextInt();

                        while (prodNum > order.getOrderPositions().size()) {
                            System.out.println("Номер товара введен неверно. Попробуйте снова ввести номер товара:");
                            prodNum = sc.nextInt();
                        }
                        order.removePositionFromOrder(order.getOrderPositions().get(prodNum - 1));
                        System.out.println("Товар удален из заказа");
                        System.out.println("Хотите еще удалить товар? 1 - да, 2 - нет");
                        String answ = sc.nextLine();
                        isContinueRemoving = "1".equals(answ);
                    }
                    break;
                default:
                    isContinue = false;
                    break;
            }
        }
    }

    public static void printProducts(Product[] shopProducts) {
        System.out.println(String.format("%-5.5s", "№") + String.format("%-20.20s", "Название") + " | " +
                String.format("%-10.10s", "Произв.") + " | " + "Цена");
        for (int i = 0; i < shopProducts.length; i++) {
            System.out.println(String.format("%-5.5s", i + 1) +
                    String.format("%-20.20s", shopProducts[i].getName()) +
                    " | " + String.format("%-10.10s", shopProducts[i].getProducer()) +
                    " | " + shopProducts[i].getPrice());
        }
        System.out.println();
    }


    public static void printProductRating(Product[] shopProducts){
        System.out.println(String.format("%-5.5s", "№") + String.format("%-20.20s", "Название") + " | " +
                String.format("%-10.10s", "Рейтинг"));
        for (int i = 0; i < shopProducts.length; i++) {
            System.out.println(String.format("%-5.5s", i + 1) +
                    String.format("%-20.20s", shopProducts[i].getName()) +
                    " | " + String.format("%-10.10s", shopProducts[i].getRating()));
        }
        System.out.println();
    }
}