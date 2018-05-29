package shopping;

import java.time.LocalDate;

public class Store {
    public static void main(String[] args) {
        Pair<String> ps = new Pair<>("Fred", "Jones");
        String s = ps.getLeft();
//        ps.setRight(LocalDate.now());
        ClothingPair<Shoe> shoes = new ClothingPair<>(
                new Shoe("Red", 45),
                new Shoe("Red", 45)
        );

        System.out.println("pair matches? " + shoes.matches());
    }
}
