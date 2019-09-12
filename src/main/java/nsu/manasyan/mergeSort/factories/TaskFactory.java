package nsu.manasyan.mergeSort.factories;

import nsu.manasyan.mergeSort.options.ContentType;
import nsu.manasyan.mergeSort.exceptions.WrongArgumentException;
import nsu.manasyan.mergeSort.util.FileManager;
import nsu.manasyan.mergeSort.ThreadTask;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TaskFactory {
    private static Map<ContentType, TaskCreator> creators;

    private static TaskFactory instance;

    private TaskFactory(){}

    static {
        creators = new HashMap<>();
        creators.put(ContentType.STRING, (m, c) -> new ThreadTask<String>(m,c, s->s));
        creators.put(ContentType.INTEGER, (m, c) -> new ThreadTask<Integer>(m,c, s -> (s == null) ? null : Integer.valueOf(s)));
    }

    public static TaskFactory getInstance(){
        if(instance == null)
            instance = new TaskFactory();

        return instance;
    }

    public ThreadTask<?> getThreadTask(ContentType key, FileManager manager, Comparator<? extends Comparable<?>> comparator){
        var taskCreator =  creators.get(key);
        if(taskCreator == null)
            throw new WrongArgumentException("Wrong content type");
        return taskCreator.create(manager,comparator);
    }
}