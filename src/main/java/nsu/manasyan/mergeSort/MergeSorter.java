package nsu.manasyan.mergeSort;

import nsu.manasyan.mergeSort.util.DataExtractor;
import java.io.*;
import java.util.Comparator;

public class  MergeSorter <T>{
    private static int fileId = 0;
    private boolean isLeftSuitable = true, isRightSuitable = true;
    private T leftValue, rightValue;
    private DataExtractor<T> extractor;

    public MergeSorter( DataExtractor<T> extractor){
        this.extractor = extractor;
    }

    public String sort(InputStream left, InputStream right, Comparator<T> comparator){
        String resultFileName = "tmp" + fileId++ + ".txt";

        try(BufferedReader leftReader = new BufferedReader(new InputStreamReader(left));
            BufferedReader rightReader = new BufferedReader(new InputStreamReader(right));
            PrintWriter writer = new PrintWriter(new FileWriter(resultFileName), true)){

            while((leftValue = getLeftValue(leftReader)) != null && (rightValue = getRightValue(rightReader)) != null){
                writer.println((isLeftSuitable = comparator.compare(leftValue , rightValue ) < 0) ? leftValue : rightValue);
                isRightSuitable = !isLeftSuitable;
            }

            for( ;leftValue != null; leftValue = extractor.get(leftReader.readLine())){
                writer.println(leftValue);
            }

            for(;rightValue != null; rightValue = extractor.get(rightReader.readLine())){
                writer.println(rightValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFileName;
    }

    private T getLeftValue(BufferedReader leftReader) throws IOException {
        return isLeftSuitable ? extractor.get(leftReader.readLine()) : leftValue;
    }

    private T getRightValue(BufferedReader rightReader) throws IOException {
        return isRightSuitable ? extractor.get(rightReader.readLine()) : rightValue;
    }
}
