package bitcamp.pms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class PMSClient {
  Socket socket;
  Scanner in;
  PrintStream out;
  static Scanner keyScan = new Scanner(System.in);
  
  public PMSClient(String server, int port) throws Exception {
    socket = new Socket(server, port);
    in = new Scanner(new BufferedInputStream(socket.getInputStream()));
    out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
  }

  public void close() {
    try {
      in.close();
    } catch (Exception e) {
    }
    try {
      out.close();
    } catch (Exception e) {
    }
    try {
      socket.close();
    } catch (Exception e) {
    }
  }

  public void execute() {
    
    String command = null;
    do {
//      if(sendCommand(keyScan).equals("quit")) {
//        break;
//      }
      command = sendCommand(keyScan);
      receiveMessage();
    } while(!command.equals("quit"));
    
  }

  private String sendCommand(Scanner keyScan) {
    System.out.print("명령? ");
    String command = keyScan.nextLine();
    out.println(command);
    out.flush();
    return command;
  }

  private void receiveMessage() {
    String message = null;
    do {
      message = in.nextLine();
      System.out.println(message);
    } while (message.equals(""));
  }

  public static void main(String[] args) {
    System.out.println("서버? ");
    String server = keyScan.nextLine();
    int port = 9999;
    try {
      System.out.println("포트번호? ");
      port =Integer.parseInt(keyScan.nextLine());
    } catch (Exception e) {
      System.out.println("포트번호 오류. default 9999.");
    }
    PMSClient client = null;
    try {
      client = new PMSClient(server, port);
      client.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      keyScan.close();
      client.close();
    }

  }


}
