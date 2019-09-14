package nsu.manasyan.mergeSort.options;

import nsu.manasyan.mergeSort.exceptions.WrongArgumentException;
import org.apache.commons.cli.*;

public class CLI {
    private Options options = new Options();

    private String[] args;

    private static final int OUTFILE_INDEX = 0;
    private static final int MIN_FILENAMES_COUNT = 2;

    public CLI(String... args) {
        this.args = args;
        addOptions();
    }

    public MergeSortOptions parse() throws ParseException {

        MergeSortOptions mergeSortOptions = new MergeSortOptions();
        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);

            mergeSortOptions.setContentType(commandLine.hasOption("s") ? ContentType.STRING : ContentType.INTEGER);
            mergeSortOptions.setSortingOrder(commandLine.hasOption("d") ? SortingOrder.DESCENDING : SortingOrder.ASCENDING);

            var fileNames = commandLine.getArgList();

            if (fileNames.size() < MIN_FILENAMES_COUNT) {
                printUsage();
                throw new WrongArgumentException("Wrong filenames count. Must be not lesser than " + MIN_FILENAMES_COUNT);
            }

            mergeSortOptions.setOutFileName(fileNames.get(OUTFILE_INDEX));
            fileNames.remove(OUTFILE_INDEX);
            mergeSortOptions.setInFileNames(fileNames);
        }
        catch (ParseException ex){
            printUsage();
            throw ex;
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

    private void printUsage(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mergeSort", "",  options,   "After keys write output file and input file(s)", true);
    }
}
