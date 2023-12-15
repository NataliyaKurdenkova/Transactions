import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
//к большей).
//
//2. Вывести список неповторяющихся городов, в которых работают трейдеры.
//
//3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
//
//4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
//
//5. Выяснить, существует ли хоть один трейдер из Милана.
//
//6. Вывести суммы всех транзакций трейдеров из Кембриджа.
//
//7. Какова максимальная сумма среди всех транзакций?
//
//8. Найти транзакцию с минимальной суммой.
public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).");
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted((o1, o2) -> (o1.getValue() - o2.getValue()))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры.");
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.");
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .sorted((o1, o2) -> o1.getTrader().getName().compareTo(o2.getTrader().getName()))
                .map(t -> t.getTrader().getName())
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted((o1, o2) -> o1.compareTo(o2))
                .map(n -> (n + " "))
                .forEach(System.out::print);

        System.out.println();
        System.out.println();

        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана.");
        boolean isTraderForMilanilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        if (!isTraderForMilanilan) System.out.println("Не существует ни одного трейдера из Милана");
        else System.out.println("В списке присутствует трейдер из Милана");
        System.out.println();

        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа.");
        int cambridgeSum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(t -> t.getValue())
                .sum();

        System.out.println(cambridgeSum);
        System.out.println();

        System.out.println("7. Какова максимальная сумма среди всех транзакций?");
        int maxSumTransactions = transactions.stream()
                .mapToInt(t -> t.getValue())
                .max()
                .orElse(0);

        System.out.println(maxSumTransactions);
        System.out.println();

        System.out.println("8. Найти транзакцию с минимальной суммой.");
        int minTransaction = transactions.stream()
                .mapToInt(t -> t.getValue())
                .min().orElse(0);
        System.out.println(minTransaction);

    }
}