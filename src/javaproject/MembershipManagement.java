package javaproject;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    public int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0)
                    throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException ie) {
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. Please try again: ");
            }
        }
        return choice;
    }

    public void printClubOptions() {
        System.out.println("""
                Club Mercury
                Club Neptune
                Club Jupiter
                Multi Clubs""");
    }

    public int getChoice() {
        int choice;
        System.out.println("""
                WELCOME TO OZONE FITNESS CENTER
                ===============================
                1) Add Member
                2) Remove Member
                3) Display Member Information
                Please select an option (or Enter -1 quit)""");
        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> calc;
        System.out.print("Please enter your name: ");
        name = reader.nextLine();
        printClubOptions();
        System.out.print("Please enter ID club: ");
        club = getIntInput();
        while (club < 1 || club > 4) {
            System.out.println("ID club is invalid. Please enter the correct ID: ");
            club = getIntInput();
        }
        if (m.size() > 0) {
            memberID = m.getLast().getMemberID() + 1;
        } else memberID = 1;

        if (club != 4) {
            calc = (n) -> switch (n) {
                case 1 -> 900;
                case 2 -> 950;
                case 3 -> 1000;
                default -> -1;
            };
            fees = calc.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");
        } else {
            calc = (n) -> switch (n) {
                case 4 -> 1200;
                default -> -1;
            };
            fees = calc.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int memberID;
        System.out.print("Please enter ID member: ");
        memberID = getIntInput();
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("Member with ID " + memberID + " has deleted");
                return;
            }
        }
        System.out.println("Member with ID " + memberID + " is not found");
    }

    public void printMemberInfo(LinkedList<Member> m) {
        int memberID;
        String[] memberInfo;
        System.out.print("Please enter ID member: ");
        memberID = getIntInput();
        for (Member member : m) {
            if (member.getMemberID() == memberID) {
                memberInfo = member.toString().split(", ");
                System.out.println(Arrays.toString(memberInfo));
                return;
            }
        }
    }
}
