package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.options.MergeSortOptions;
import nsu.manasyan.mergeSort.options.SortingOrder;
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

    private static final int TIMEOUT_SEC = 99;

    private static final String FILES_DIRECTORY_NAME = "tmpFiles";

    public SortingService(MergeSortOptions options) {
        this.options = options;
        this.fileManager = new FileManager(options.getInFileNames());
    }

    public void start() throws IOException {
        checkFilesDirectory();
        var threadTask = TaskFactory.getInstance().getThreadTask(options.getContentType(), fileManager, getComparator(options.getSortingOrder()));

        try {
            while (!fileManager.isFinished()) {
                executorService.submit(threadTask);
            }
            executorService.shutdown();
            executorService.awaitTermination(TIMEOUT_SEC, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUpTmpFiles();
    }

    private void checkFilesDirectory() throws IOException {
        if (!Files.exists(Path.of(FILES_DIRECTORY_NAME))) {
            Files.createDirectory(Path.of(FILES_DIRECTORY_NAME));
        }
    }

    private <T extends Comparable<T>> Comparator<T> getComparator(SortingOrder sortingOrder) {
        return sortingOrder == SortingOrder.ASCENDING ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }

    private void copyFile(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
    }

    private void cleanUpTmpFiles() throws IOException {
        copyFile(fileManager.getLastFileName(), options.getOutFileName());
        Files.walk(Path.of(FILES_DIRECTORY_NAME)).map(Path::toFile).forEach(File::delete);
        new File(FILES_DIRECTORY_NAME).delete();
    }
}