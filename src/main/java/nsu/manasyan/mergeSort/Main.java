package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.arguments.CLI;
import nsu.manasyan.mergeSort.arguments.MergeSortOptions;

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test(){
        try {
//            MergeSortOptions options = new CLI("-i" , "-s","in/out.txt" ,"in/left.txt", "in/right.txt", "in/third.txt", "in/fourth.txt").parse();
            MergeSortOptions options = new CLI("-i" , "-d","in/out.txt" ,"in/left.txt", "in/right.txt").parse();
            SortingService service = new SortingService(options);
            service.start();
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
