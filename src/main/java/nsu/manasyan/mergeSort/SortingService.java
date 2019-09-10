package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.util.DataExtractor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortingService {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private FileManager fileManager;

    public SortingService(List<String> fileNames){
        this.fileManager = new FileManager(fileNames);
    }

    public void start(String sortingMode, String contentType){
        // tmp solution
        var sortingOrder = "a".equals(sortingMode) ? Comparator.naturalOrder() : Comparator.reverseOrder();
        DataExtractor extractor = "s".equals(contentType) ? s -> s : i -> i == null ? null : Integer.valueOf(i);

        while (!fileManager.isFinished()){
            executorService.submit(new ThreadTask<>(fileManager,sortingOrder, extractor));
        }

        executorService.shutdown();
    }

}
