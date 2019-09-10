package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.Exceptions.WrongArgumentException;
import nsu.manasyan.mergeSort.factories.TaskFactory;
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
//        var extractor = getDataExctractor(contentType);

//        while (!fileManager.isFinished()){
//            executorService.submit(new ThreadTask<>(fileManager,comparator, extractor));
//        }

        var threadTask = TaskFactory.getInstance().getFileHandler(contentType, fileManager,getComparator(sortingMode));
        while (!fileManager.isFinished()){
            executorService.submit(threadTask);
        }
        executorService.shutdown();
    }

    private <T extends Comparable<T>> Comparator<T> getComparator(String sortingMode){
        switch (sortingMode) {
            case "a":
                return Comparator.naturalOrder();
            case "d":
                return Comparator.reverseOrder();
            default:
                throw new WrongArgumentException("Wrong sort mode");
        }
    }
//
//    private <T> DataExtractor<T> getDataExctractor(String contentType){
//        switch (contentType) {
//            case "s":
//                return s -> (T)s;
//            case "i":
//                return i -> (T)Integer.valueOf(i);
//            default:
//                throw new WrongArgumentException("Wrong sort mode");
//        }
//    }
}