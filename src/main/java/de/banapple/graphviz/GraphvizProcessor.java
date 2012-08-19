package de.banapple.graphviz;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Uses graphviz to render dot content to svg.
 * 
 * @author <a href="mailto:achim.abeling@freenet-ag.de">Achim Abeling</a>
 */
public class GraphvizProcessor
{
    private static Logger log = LoggerFactory.getLogger(GraphvizProcessor.class);

    private String dotCommand = "/usr/bin/dot";

    /**
     * Used to read output from a Process and log it back.
     */
    class Receiver implements Runnable
    {

        /**
         * stream to receive data from child
         */
        private final InputStream is;

        /**
         * method invoked when Receiver thread started. Reads data from child
         * and displays in on System.out.
         */
        public void run()
        {
            try {
                final BufferedReader br =
                        new BufferedReader(new InputStreamReader(is), 50 /*
                                                                          * keep
                                                                          * small
                                                                          * for
                                                                          * testing
                                                                          */);
                String line;
                while ((line = br.readLine()) != null) {
                    log.debug("process output: {}", line);
                }
                br.close();
                log.debug("stopped reading from the process");
            } catch (IOException e) {
                log.error("failed to receive output from process", e);
                throw new IllegalArgumentException(
                        "IOException receiving data from child process.");
            }

            log.debug("thread finished");
        }

        // --------------------------- CONSTRUCTORS ---------------------------

        /**
         * contructor
         * 
         * @param is
         *            stream to receive data from child
         */
        Receiver(InputStream is)
        {
            this.is = is;
        }
    }

    public String toSvg(String dot)
    {
        log.debug("create svg from dot");

        File dotFile = null;
        File svgFile = null;
        try {
            
            /* write the dot to a temporary file */
            dotFile = File.createTempFile("graphviz", ".dot");
            FileWriter writer = new FileWriter(dotFile); 
            IOUtils.write(dot, writer);
            writer.flush();
            writer.close();
            log.debug("dot written to {}", dotFile);

            /* prepare a temporary output file */
            svgFile = File.createTempFile("graphviz", ".svg");
            
            /* prepare the command */
            String command =
                    dotCommand
                    + " -Tsvg"                    
                    + " -o" + svgFile.getAbsolutePath()
                    + " -Gcharset=latin1 "
                    + dotFile.getAbsolutePath();
            log.debug("command: {}", command);
            
            ProcessBuilder pb = new ProcessBuilder(command.split(" "));
            pb.redirectErrorStream(true);
            Process process = pb.start();
            Thread receiverThread =
                    new Thread(new Receiver(process.getInputStream()),
                            "process output receiver");
            receiverThread.setDaemon(true);
            receiverThread.start();
            log.debug("started the archive process: {}", process);
        
            int processExit = process.waitFor();
            log.debug("process exited with {}", processExit);
            if ( processExit != 0 ) {
                throw new RuntimeException("failed to execute dot");
            }
            
            /* read the result from the temporary file */
            log.debug("read result from temporary file");
            String result = IOUtils.toString(new FileReader(svgFile));
            
            log.debug("successfully produced svg");
            
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (dotFile != null) {
                log.debug("delete temporary dot file: {}", 
                        dotFile.getAbsolutePath());
                dotFile.delete();
            }
            if (svgFile != null) {
                log.debug("delete temporary svg file: {}",
                        svgFile.getAbsolutePath());
                svgFile.delete();
            }
        }

    }

    public void setDotCommand(String dotCommand)
    {
        this.dotCommand = dotCommand;
    }
    
    public String getDotCommand()
    {
        return dotCommand;
    }

}

