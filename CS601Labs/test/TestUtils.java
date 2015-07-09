import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

/**
 * Original author sjengle, modified by srollins.
 *
 */
public class TestUtils {

    // Project Configuration

	/** Configure this on your system if you want to have a longer timeout. */
    public static final int TIMEOUT = 60000;
    
	
    public static final String INPUT_DIR  = "input";
    public static final String OUTPUT_DIR = "output";
    public static final String RESULT_DIR = "results";

    /**
     * Checks line-by-line if two files are equal. If one file contains extra
     * blank lines at the end of the file, the two are still considered equal.
     *
     * @param path1 - path to first file to compare with
     * @param path2 - path to second file to compare with
     * @return positive value if two files are equal, negative value if not
     *
     * @throws IOException
     */
    public static int checkFiles(Path path1, Path path2) throws IOException {
        Charset charset = java.nio.charset.StandardCharsets.UTF_8;

        // used to output line mismatch
        int count = 0;

        try (
            BufferedReader reader1 =
                    Files.newBufferedReader(path1, charset);
            BufferedReader reader2 =
                    Files.newBufferedReader(path2, charset);
        ) {
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            while (true) {
                count++;

                // compare lines until we hit a null (i.e. end of file)
                if ((line1 != null) && (line2 != null)) {
                    // use consistent path separators
                    line1 = line1.replaceAll(Matcher.quoteReplacement(File.separator), "/");
                    line2 = line2.replaceAll(Matcher.quoteReplacement(File.separator), "/");

                    // remove trailing spaces
                    line1 = line1.trim();
                    line2 = line2.trim();

                    // check if lines are equal
                    if (!line1.equals(line2)) {
                        return -count;
                    }

                    // read next lines if we get this far
                    line1 = reader1.readLine();
                    line2 = reader2.readLine();
                }
                else {
                    // discard extra blank lines at end of reader1
                    while ((line1 != null) && line1.trim().isEmpty()) {
                        line1 = reader1.readLine();
                    }

                    // discard extra blank lines at end of reader2
                    while ((line2 != null) && line2.trim().isEmpty()) {
                        line2 = reader2.readLine();
                    }

                    if (line1 == line2) {
                        // only true if both are null, otherwise one file had
                        // extra non-empty lines
                        return count;
                    }
                    else {
                        // extra blank lines found in one file
                        return -count;
                    }
                }
            }
        }
    }
}