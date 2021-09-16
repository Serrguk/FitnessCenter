package javaproject;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Member mem = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            lineRead = reader.readLine();
            while (lineRead != null) {
                splitLine = lineRead.split(", ");
                if (splitLine[0].equals("S")) {
                    mem = new SingleClubMember('S',
                            Integer.parseInt(splitLine[1]),
                            splitLine[2],
                            Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4]));
                } else if (splitLine[0].equals("M")) {
                    mem = new MultiClubMember('M',
                            Integer.parseInt(splitLine[1]),
                            splitLine[2],
                            Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4]));
                }
                members.add(mem);
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void appendFile(String mem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))) {
            writer.write(mem + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void overwriteFile(LinkedList<Member> m) {
        String s;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))) {
            for (Member member : m) {
                s = member.toString();
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            File f = new File("members.csv");
            File tf = new File("members.temp");
            System.out.println(f.delete());
            System.out.println(tf.renameTo(f));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
