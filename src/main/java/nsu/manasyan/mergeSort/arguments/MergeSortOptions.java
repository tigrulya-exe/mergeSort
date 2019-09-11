package nsu.manasyan.mergeSort.arguments;

import java.util.List;

public class MergeSortOptions {
    private String outFileName;
    private List<String> inFileNames;

    private SortingOrder sortingOrder;
    private ContentType contentType;

    public List<String> getInFileNames() {
        return inFileNames;
    }

    public void setInFileNames(List<String> inFileNames) {
        this.inFileNames = inFileNames;
    }

    public SortingOrder getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(SortingOrder sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }
}
