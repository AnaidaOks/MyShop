package myShop;

public class ShopMenu {
    public static final int mainMenuCount = 9;
    //public static final int positionMenuCount = 8;
    public static final String orderStatusAdded = "Заказ оформлен";
    public static final String orderStatusReturned = "Оформлен возврат";

    public void printMainMenu() {
        System.out.println("Выберите опцию и введите номер");
        System.out.println("1. Посмотреть список товаров");
        System.out.println("2. Добавить заказ");
        System.out.println("3. Редактировать заказ");
        System.out.println("4. Вернуть заказ");
        System.out.println("5. Повторить заказ");
        System.out.println("6. Посмотреть заказ");
        System.out.println("7. Посмотреть список заказов");
        System.out.println("8. Посмотреть рейтинг товаров");
        System.out.println("9. Завершить покупку и выйти");
    }

    public void printPositionMenu() {
        System.out.println("Выберите опцию и введите номер");
        System.out.println("1. Добавить товар");
        System.out.println("2. Удалить товар");
        System.out.println("3. Завершить редактирование");
    }
}
