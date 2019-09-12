package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.util.DataExtractor;
import java.io.*;
import java.util.Comparator;

public class  MergeSorter <T>{
    private static int fileId = 0;
    private boolean isLeftSuitable = true, isRightSuitable = true;
    private T leftValue, rightValue;

    private DataExtractor<T> extractor;
    private Comparator<T> comparator;

    public MergeSorter( DataExtractor<T> extractor, Comparator<T> comparator){
        this.extractor = extractor;
        this.comparator = comparator;
    }

    public String sort(InputStream left, InputStream right){
        String resultFileName = "tmpFiles/tmp" + fileId++ + ".txt";

        try(BufferedReader leftReader = new BufferedReader(new InputStreamReader(left));
            BufferedReader rightReader = new BufferedReader(new InputStreamReader(right));
            PrintWriter writer = new PrintWriter(new FileWriter(resultFileName), true)){

            while((leftValue = getLeftValue(leftReader)) != null && (rightValue = getRightValue(rightReader)) != null){
                writer.println((isLeftSuitable = comparator.compare(leftValue , rightValue ) < 0) ? leftValue : rightValue);
                isRightSuitable = !isLeftSuitable;
            }

            for( ;leftValue != null; leftValue = getValue(leftReader,true,leftValue)){
                writer.println(leftValue);
            }

            for(;rightValue != null; rightValue = getValue(rightReader,true, rightValue)){
                writer.println(rightValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFileName;
    }

    private T getLeftValue(BufferedReader leftReader) throws IOException {
        return getValue(leftReader,isLeftSuitable, leftValue);
    }

    private T getRightValue(BufferedReader rightReader) throws IOException {
        return getValue(rightReader,isRightSuitable,rightValue);
    }

    private T getValue(BufferedReader reader, Boolean condition, T value) throws IOException {
        T newValue;
        while(condition){
            newValue = extractor.get(reader.readLine());
            if(value == null || newValue == null || comparator.compare(value, newValue) <= 0 ){
                return newValue;
            }
        }

        return value;
    }
}
