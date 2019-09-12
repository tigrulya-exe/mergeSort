package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.arguments.CLI;
import nsu.manasyan.mergeSort.arguments.MergeSortOptions;

public class Main {
    public static void main(String[] args) {
//        try{
//            MergeSortOptions options = new CLI(args).parse();
//            SortingService service = new SortingService(options);
//            service.start();
//        } catch (Exception ex) {
//            System.err.println(ex.getLocalizedMessage());
//        }
        test();
    }

    private static void test(){
        try {
            MergeSortOptions options = new CLI("-i" , "-a", "in/out.txt" ,"in/left.txt", "in/right.txt", "in/third.txt", "in/fourth.txt").parse();
            SortingService service = new SortingService(options);
            service.start();
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
