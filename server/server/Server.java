package server;

//import java.io.IOException;

public interface Server {
    //void start(ClientHandler clientHandler) throws IOException;
    void start(ClientHandler clientHandler);
    void stop();
}
