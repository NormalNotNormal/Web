import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            Database database = new Database();

            System.out.println("\nДля получения списка команд введите \"help\",\"h\" или \"?\"");
            System.out.println("\nВведите команду:");

            String line;
            while (!(line = in.readLine()).equals("exit")) {
                try {
                    switch (line) {

                        case "out": {
                            database.out();
                            break;
                        }

                        case "get": {
                            System.out.println("Введите номер:");

                            try {
                                int ID = Integer.parseInt(in.readLine());
                                String result = database.get(ID);
                                if (result == null) {
                                    System.out.printf("Запись с номером %d не найдена\n", ID);
                                } else {
                                    System.out.println(result);
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case "add": {
                            int ID = 0;
                            String Name;
                            int Price = 0;
                            int BookPages = 0;

                            boolean check = false;

                            System.out.println("Введите ID:");
                            do {
                                try {
                                    ID = Integer.parseInt(in.readLine());
                                    check = true;
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                }
                            } while (!check);
                            System.out.println("Введите название книги :");
                            Name = in.readLine();
                            check = false;
                            System.out.println("Введите Цену Книги:");
                            do {
                                try {
                                    Price = Integer.parseInt(in.readLine());
                                    check = true;
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                }
                            } while (!check);
                            check = false;
                            System.out.println("Введите количество страниц:");
                            do {
                                try {
                                    BookPages = Integer.parseInt(in.readLine());
                                    check = true;
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                }
                            } while (!check);
                            try {
                                database.add(ID, Name, Price, BookPages);
                                System.out.println("Запись успешно добавлена");
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case "set": {
                            int ID = 0;

                            boolean check = false;

                            System.out.println("Введите номер:");
                            do {
                                try {
                                    ID = Integer.parseInt(in.readLine());
                                    database.get(ID);
                                    check = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Не удалось преобразовать в число. Попробуйте снова");
                                } catch (SQLException e) {
                                    System.out.printf("Запись с номером %d не найдена\n", ID);
                                }
                            } while (!check);
                            System.out.println("Введите поле для изменения" +
                                    " (ID, Name, Price, BookPages:");
                            String column = in.readLine();
                            System.out.println("Введите новое значение:");
                            String value = in.readLine();
                            try {
                                database.set(ID, column, value);
                                System.out.println("Новое значение успешно задано");
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }

                        case "rem": {
                            System.out.println("Введите ID:");

                            try {
                                int ID = Integer.parseInt(in.readLine());
                                String result = database.get(ID);

                                if (result == null) {
                                    System.out.printf("\nЗапись с номером %d не найдена\n", ID);
                                } else {
                                    database.remove(ID);
                                    System.out.printf("\nЗапись %s успешно удалена\n", result);
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        }


                        case "help": {
                            System.out.println(
                                    "\nadd - Добавить запись\n" +
                                            "set - Изменить запись\n" +
                                            "get - Вывести запись\n" +
                                            "rem - Удалить запись\n" +
                                            "out - Вывести все записи\n" +
                                            "help или ? - Вывести список команд\n" +
                                            "exit - Выход");//+
                        }

                        case "?": {
                            System.out.println(
                                    "\nadd - Добавить запись\n" +
                                            "set - Изменить запись\n" +
                                            "get - Вывести запись\n" +
                                            "rem - Удалить запись\n" +
                                            "out - Вывести все записи\n" +
                                            "help или ? - Вывести список команд\n" +
                                            "exit - Выход");
                            break;
                        }

                        case "h": {
                            System.out.println(
                                    "\nadd - Добавить запись\n" +
                                            "set - Изменить запись\n" +
                                            "get - Вывести запись\n" +
                                            "rem - Удалить запись\n" +
                                            "out - Вывести все записи\n" +
                                            "help или ? - Вывести список команд\n" +
                                            "exit - Выход");
                            break;
                        }

                        default: {
                            System.out.println("Команда не распознана, введите \"help\",\"h\" " +
                                    "или \"?\" для вывода списка команд");
                            break;
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("\nВведите команду:");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}