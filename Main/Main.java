package Main;

import Controller.Controller;
import Model.User;

import java.util.ArrayList;

public class Main {

    public Main(){
        Controller controller =  new Controller();
        ArrayList<User> users = controller.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("Quiz 2 PBO");
        new Main();
    }
}
