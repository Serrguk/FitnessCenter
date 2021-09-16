package javaproject;

import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();
        int choice = mm.getChoice();

        while (choice != -1) {
            switch (choice) {
                case 1 -> mm.addMembers(members);
                case 2 -> mm.removeMember(members);
                case 3 -> mm.printMemberInfo(members);
                default -> System.out.println("\nINVALID INPUT. PLEASE TRY AGAIN OR ENTER -1 FOR ENDED.\n");
            }
            choice = mm.getChoice();
        }
    }
}
