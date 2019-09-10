package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.util.DataExtractor;
import nsu.manasyan.mergeSort.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Comparator;

public class ThreadTask <T> implements Runnable {

    private FileManager fileManager;
    private Comparator<T> comparator;
    private DataExtractor<T> extructor;

    public ThreadTask(FileManager fileManager, Comparator<T> comparator, DataExtractor<T> extructor) {
        this.fileManager = fileManager;
        this.comparator = comparator;
        this.extructor = extructor;
    }

    @Override
    public void run() {
        Pair<String> fileNames = fileManager.getFileNames();
        if(fileNames == null){
            return;
        }

        MergeSorter<T> mergeSorter = new MergeSorter<>(extructor);
        try {
            String resultFileName = mergeSorter.sort(getInputStream(fileNames.getFirst()), getInputStream(fileNames.getSecond()), comparator);
            fileManager.putFileName(resultFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private InputStream getInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }
}
