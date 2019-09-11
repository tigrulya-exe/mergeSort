package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.arguments.MergeSortOptions;
import nsu.manasyan.mergeSort.arguments.SortingOrder;
import nsu.manasyan.mergeSort.factories.TaskFactory;
import nsu.manasyan.mergeSort.util.FileManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SortingService {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private FileManager fileManager;

    private MergeSortOptions options;

    private static final int TIMEOUT = 10;

    public SortingService(MergeSortOptions options) {
        this.options = options;
        this.fileManager = new FileManager(options.getInFileNames());
    }

    public void start() {
        var threadTask = TaskFactory.getInstance().getThreadTask(options.getContentType(), fileManager, getComparator(options.getSortingOrder()));

        while (!fileManager.isFinished()) {
            executorService.submit(threadTask);
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
            copyFile(fileManager.getLastFileName(), options.getOutFileName());
            Files.walk(Path.of("tmpFiles")).map(Path::toFile).forEach(File::delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T extends Comparable<T>> Comparator<T> getComparator(SortingOrder sortingOrder) {
        return sortingOrder == SortingOrder.ASCENDING ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }

    private void copyFile(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
    }
}