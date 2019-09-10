package nsu.manasyan.mergeSort;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test(){
        SortingService service = new SortingService(List.of("left.txt", "right.txt", "third.txt", "fourth.txt"));
        service.start("a", "i");
    }
}
