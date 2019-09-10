package nsu.manasyan.mergeSort.factories;

import nsu.manasyan.mergeSort.util.FileManager;
import nsu.manasyan.mergeSort.ThreadTask;

import java.util.Comparator;

public interface TaskCreator<T> {
    ThreadTask<T> create(FileManager manager, Comparator<T> comparator);
}
