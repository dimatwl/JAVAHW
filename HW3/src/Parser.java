import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<SyntaxTree> trees = null;

    public Integer Parse(String fileName) throws IOException {
        List<String> codeLines = readCode(fileName);
    }

    private List<String> readCode(String fileName) throws IOException{
        List<String> lines = new ArrayList<String>();

        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;

        while( ( line = reader.readLine() ) != null ) {
            lines.add(line.replaceAll("\\s",""));
        }

        return lines;
    }


}
