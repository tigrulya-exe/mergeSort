package nsu.manasyan.mergeSort.arguments;

import org.apache.commons.cli.*;

public class CLI {
    private Options options = new Options();

    private String[] args;

    private static final int OUTFILE_INDEX = 0;

    public CLI(String... args) {
        this.args = args;
        addOptions();
    }

    public MergeSortOptions parse(){
        MergeSortOptions mergeSortOptions = new MergeSortOptions();

        try{
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);

            mergeSortOptions.setContentType(commandLine.hasOption("s") ? ContentType.STRING : ContentType.INTEGER);
            mergeSortOptions.setSortingOrder(commandLine.hasOption("d") ? SortingOrder.DESCENDING : SortingOrder.ASCENDING);

            var fileNames =  commandLine.getArgList();

            mergeSortOptions.setOutFileName(fileNames.get(OUTFILE_INDEX));
            fileNames.remove(OUTFILE_INDEX);
            mergeSortOptions.setInFileNames(fileNames);

        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage());
        }

        return mergeSortOptions;
    }

    private void addOptions(){
        OptionGroup contentType = new OptionGroup();
        contentType.addOption(new Option("s", false,"string content"));
        contentType.addOption(new Option("i", false,"int content"));
        contentType.setRequired(true);

        options.addOptionGroup(contentType);

        OptionGroup sortingOrder = new OptionGroup();
        sortingOrder.addOption(new Option("a", false,"ascending sort order"));
        sortingOrder.addOption(new Option("d", false, "descending sort order"));
        sortingOrder.setRequired(false);

        options.addOptionGroup(sortingOrder);
    }
}
