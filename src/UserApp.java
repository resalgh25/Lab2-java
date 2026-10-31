import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class UserApp {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        try (Scanner reader = new Scanner(new File("Users.txt.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.trim().split("\\s+");

                if (parts.length != 2) {
                    System.out.println(line + " - Invalid line format");
                    continue;
                }

                try {
                    User user = new User(parts[0], parts[1]);
                    users.add(user);
                }
                catch (IllegalArgumentException e) {
                    System.out.println(line + " - " + e.getMessage());
                }
            }
            users.sort((u1, u2) ->
                    u1.toString().compareTo(u2.toString()));
            try (PrintWriter writer = new PrintWriter("out.txt")) {
                for (User user : users) {
                    System.out.println(user);
                    writer.println(user);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}