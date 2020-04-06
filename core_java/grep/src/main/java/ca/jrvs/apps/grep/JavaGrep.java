package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

    /**
     * top-level workflow of searching a pattern against a root folder
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * traverse a given directory and return all files
     * @param rootDir input directory
     * @return list of files under the rootDir
     */
    List<File> listFiles (String rootDir);

    /**
     * Read a file from return all lines of the file
     * @param inputFile file to be read
     * @return list of lines read from the inputFile
     * @throws IllegalArgumentException if given inputFile is not a File
     */
    List<String> readLines (File inputFile);


    /**
     * check if a line contains a certain regex pattern
     * @param line input string
     * @return True/False depend on whether the string contains a regex pattern
     */
    boolean containsPattern (String line);


    /**
     * write list of lines to a file
     * @param Lines list of lines needed to be written
     * @throws IOException if writing failed
     */
    void writeToFile(List<String> Lines) throws IOException;


    /**
     * Getters and setters for rootDir, regex and outFile
     */
    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
}