
package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public void process() throws IOException {
        List<File> subFiles = listFiles(rootPath);
        List<String> matchedLines = new ArrayList<String>();
        for (File file : subFiles){
            List<String> linesOfFile = readLines(file);
            for (String line : linesOfFile){
                if (containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        File rootDirectory = new File(rootDir);
        //Recursively iterate the root directory and store all files in the list
        File[] internalSubDir = rootDirectory.listFiles();
        List<File> fileList = new ArrayList<File>();
        for (File f : internalSubDir){
            if (f.isFile()){
                fileList.add(f);
            }else{
                fileList.addAll(listFiles(f.getAbsolutePath()));
            }
        }

        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> outputLines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            String line;
            while((line = br.readLine()) != null){
                outputLines.add(line);
            }
            br.close();
        }catch (FileNotFoundException fnfe){
            logger.error("file not found", fnfe);
        }catch (IOException ioe){
            logger.error("unable to open the file", ioe);
        }
        return  outputLines;
    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(List<String> Lines) throws IOException {
        FileWriter writer = new FileWriter(outFile);
        for(String str: Lines) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath=rootPath;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex=regex;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile=outFile;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
        }
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        } catch (Exception ex){
            javaGrepImp.logger.error(ex.getMessage(), ex);
        }

    }
}
