package utilities;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Locale;

@Log4j2
public class AppiumServer extends  testBase{
    public testBase tb;
    public AppiumServer(testBase tb)
    {
        this.tb=tb;
    }
    public void Start() throws Exception {
        if(isPortAvailable(4723)){
            try {
                osServer();
                log.info("Server started from here!");
            }catch (Exception e){
                log.error("Could not Start appium Server");
                throw new Exception("Could not Start appium Server");
            }
        }else {
            log.info("Could not Start appium Server, Server already running! on port 4723");
            throw new Exception("Could not Start appium Server, Server already running! on port 4723");
        }
    }


    /**
     * this method starts the appium  server depending on OS.
     *
     * @throws IOException          Signals that an I/O exception of some sort has occurred
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */
    void osServer() throws InterruptedException, IOException {
        String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (os.indexOf("win") >= 0) {
            CommandLine command = new CommandLine("cmd");
            command.addArgument("/c");
            command.addArgument("C:/Program Files/nodejs/node.exe");
            command.addArgument("C:/Appium/node_modules/appium/bin/appium.js");
            command.addArgument("--address", false);
            command.addArgument("127.0.0.1");
            command.addArgument("--port", false);
            command.addArgument("4723");
            command.addArgument("--full-reset", false);

            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);
            Thread.sleep(5000);
        } else if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0)) {
            CommandLine command = new CommandLine("/Users/apple/.nvm/versions/node/v15.14.0/bin/node");
            command.addArgument("/usr/local/bin/appium", false);
            command.addArgument("--address", false);
            command.addArgument("127.0.0.1");
            command.addArgument("--port", false);
            command.addArgument("4723");
            command.addArgument("--full-reset", true);
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);
            Thread.sleep(5000);
        } else if (os.indexOf("nux") >= 0) {
            //Start the appium server
            log.info("ANDROID_HOME : ");
            System.getenv("ANDROID_HOME");
            CommandLine command = new CommandLine("/bin/bash");
            command.addArgument("-c");
            command.addArgument("~/.linuxbrew/bin/node");
            command.addArgument("~/.linuxbrew/lib/node_modules/appium/lib/appium.js", true);
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);
            Thread.sleep(5000); //Wait for appium server to start

        } else {
            log.info(os + "is not supported yet");
        }
    }

    public  boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(false);
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * this method stops the appium  server.
     *
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     * @throws ExecuteException An exception indicating that the executing a subprocesses failed.
     */
    public void stop() throws ExecuteException, IOException {
        String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (os.indexOf("win") >= 0) {
            CommandLine command = new CommandLine("cmd");
            command.addArgument("/c");
            command.addArgument("Taskkill /F /IM node.exe");

            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValue(1);
            executor.execute(command, resultHandler);
        } else if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0) || os.indexOf("nux") >= 0) {
            String[] command = {"/usr/bin/killall", "-9", "node"};
            Runtime.getRuntime().exec(command);
            log.info("Appium server stopped");
        } else if (os.contains("linux")) {
            // need to add it
        }
    }

}
