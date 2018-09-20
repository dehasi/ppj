package edu.coursera.distributed;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A basic and very limited implementation of a file server that responds to GET
 * requests from HTTP clients.
 */
public final class FileServer {
    /**
     * Main entrypoint for the basic file server.
     *
     * @param socket Provided socket to accept connections on.
     * @param fs     A proxy filesystem to serve files from. See the PCDPFilesystem
     *               class for more detailed documentation of its usage.
     * @param ncores The number of cores that are available to your
     *               multi-threaded file server. Using this argument is entirely
     *               optional. You are free to use this information to change
     *               how you create your threads, or ignore it.
     * @throws IOException If an I/O error is detected on the server. This
     *                     should be a fatal error, your file server
     *                     implementation is not expected to ever throw
     *                     IOExceptions during normal operation.
     */


    public void run(final ServerSocket socket, final PCDPFilesystem fs,
                    final int ncores) throws IOException {
        /*
         * Enter a spin loop for handling client requests to the provided
         * ServerSocket object.
         */
        while (true) {


            Socket accept = socket.accept();

            Thread thread = new Thread(

                    () -> {

                        try( BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                             BufferedWriter wtiter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()))
                        ) {

                            String line = reader.readLine();
                            String[] request = line.split(" ");

                            String path = request[1];
                            PCDPPath pcdpPath = new PCDPPath(path);
                            String file = fs.readFile(pcdpPath);

                            if (file != null) {
                                wtiter.append("HTTP/1.0 200 OK\r\n");
                                wtiter.append("Server: FileServer\r\n");
                                wtiter.append("\r\n");
                                wtiter.append(file);
                                wtiter.flush();
                            } else {
                                wtiter.append("HTTP/1.0 404 Not Found\r\n");
                                wtiter.append("Server: FileServer\r\n");
                                wtiter.append("\r\n");
                            }
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                        finally {

                        }
                    });

            thread.start();

        }
    }
}
