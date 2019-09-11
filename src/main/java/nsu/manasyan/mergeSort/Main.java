package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.arguments.CLI;

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test(){
        var options =  new CLI("-i" ,"in/out.txt" ,"in/left.txt", "in/right.txt", "in/third.txt", "in/fourth.txt").parse();
        SortingService service = new SortingService(options);
        service.start();
    }
}
