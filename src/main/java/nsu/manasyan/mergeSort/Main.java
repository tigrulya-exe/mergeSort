package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.options.CLI;
import nsu.manasyan.mergeSort.options.MergeSortOptions;

public class Main {
    public static void main(String[] args) {
        try{
            MergeSortOptions options = new CLI(args).parse();
            SortingService service = new SortingService(options);
            service.start();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
