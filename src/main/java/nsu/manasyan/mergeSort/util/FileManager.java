package nsu.manasyan.mergeSort.util;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class FileManager {

    private ArrayBlockingQueue<String> fileNames;

    private int currentFilesCount;

    public FileManager(List<String> fileNames){
        this.fileNames = new ArrayBlockingQueue<>(fileNames.size());
        this.fileNames.addAll(fileNames);
        this.currentFilesCount = fileNames.size();
    }

    public boolean isFinished(){
        return currentFilesCount <= 1;
    }

    public synchronized Pair<String> getFileNames() {
        Pair<String> fileNamesToReturn = null;

        try {
            if(!isFinished()) {
                --currentFilesCount;
                fileNamesToReturn = new Pair<>(fileNames.take(), fileNames.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fileNamesToReturn;
    }

    public void putFileName(String fileName){
        fileNames.add(fileName);
    }
}
