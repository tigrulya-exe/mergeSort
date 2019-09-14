package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.util.DataExtractor;
import nsu.manasyan.mergeSort.util.FileManager;
import nsu.manasyan.mergeSort.util.Pair;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class ThreadTask <T> implements Runnable {

    private FileManager fileManager;
    private Comparator<T> comparator;
    private DataExtractor<T> extractor;

    public ThreadTask(FileManager fileManager, Comparator<T> comparator, DataExtractor<T> extractor) {
        this.fileManager = fileManager;
        this.comparator = comparator;
        this.extractor = extractor;
    }

    @Override
    public void run() {
        Pair<String> fileNames = fileManager.getFileNames();

        MergeSorter<T> mergeSorter = new MergeSorter<>(extractor,comparator);
        boolean isFirstCorrect = Files.exists(Path.of(fileNames.getFirst()));

        try(FileInputStream firstFileStream = new FileInputStream(fileNames.getFirst());
            FileInputStream secondFileStream = new FileInputStream(fileNames.getSecond())){

            String resultFileName = mergeSorter.sort(firstFileStream, secondFileStream);
            fileManager.putFileName(resultFileName);

        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            fileManager.putFileName(isFirstCorrect ? fileNames.getFirst() : fileNames.getSecond());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
