package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.util.DataExtractor;
import nsu.manasyan.mergeSort.util.FileManager;
import nsu.manasyan.mergeSort.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        if(fileNames == null){
            return;
        }

        MergeSorter<T> mergeSorter = new MergeSorter<>(extractor);
        try {
            String resultFileName = mergeSorter.sort(new FileInputStream(fileNames.getFirst()),
                    new FileInputStream(fileNames.getSecond()), comparator);
            fileManager.putFileName(resultFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
