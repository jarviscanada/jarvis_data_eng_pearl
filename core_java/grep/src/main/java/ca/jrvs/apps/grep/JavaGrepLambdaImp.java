package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
        }
        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try{
            javaGrepLambdaImp.process();
        } catch (Exception ex){
            javaGrepLambdaImp.logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Implement using Lambda and stream API
     * @param rootDir
     * @return list of files under the rootDir
     */
    @Override
    public List<File> listFiles(String rootDir) {
        return Stream.of(new File(rootDir).listFiles())
                .filter(file -> file.isFile())
                .collect(Collectors.toList());
    }


    /**
     * Implement using Lambda and stream API
     * @param inputFile
     * @return list of lines read from the file
     */
    @Override
    public List<String> readLines(File inputFile) {
        try (Stream<String> stream = Files.lines(inputFile.toPath())) {
            return stream.collect(Collectors.toList());
        }catch (IOException ioe) {
            logger.error("Unable to open the File "+inputFile, ioe);
        }
        return null;
    }
}
