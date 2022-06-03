package com.yan.common.util;

import com.yan.common.element.Country;
import com.yan.common.element.FormOfEducation;
import com.yan.common.element.Location;
import com.yan.common.element.Person;

import java.util.Locale;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner = new Scanner(System.in);
    private final StringParser parser = new StringParser();
    private final Validator validator = new Validator();

    private String input(String prompt, boolean nullable) {
        while (true) {
            System.out.print(prompt + " -> ");
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (nullable) {
                    if ("".equals(s)) {
                        return null;
                    } else {
                        return s;
                    }
                }
                if (!"".equals(s)) {
                    return s;
                }
            }
        }
    }

    public String readName() {
        return input("name", false);
    }

    public Integer readX() {
        Integer x = null;
        while (x == null) {
            x = parser.parseInt(input("coordinate X", false));
        }
        return x;
    }

    public double readY() {
        Double y = null;
        while (y == null) {
            y = parser.parseDouble(input("coordinate Y (Y > -969)", false));
            if (y != null && !validator.checkY(y)) {
                y = null;
            }
        }
        return y;
    }

    public Integer readStudentsCount() {
        Integer sc = null;
        while (sc == null) {
            sc = parser.parseInt(input("studentsCount ( > 0)", false));
            if (sc != null && !validator.checkPositive("studentsCount", sc.longValue())) {
                sc = null;
            }
        }
        return sc;
    }

    public Integer readShouldBeExpelled() {
        Integer sbe = null;
        while (sbe == null) {
            sbe = parser.parseInt(input("shouldBeExpelled ( > 0)", false));
            if (sbe != null && !validator.checkPositive("shouldBeExpelled", sbe.longValue())) {
                sbe = null;
            }
        }
        return sbe;
    }

    public Long readTransferredStudents() {
        Long ts = null;
        while (ts == null) {
            ts = parser.parseLong(input("transferredStudents ( > 0)", false));
            if (ts != null && !validator.checkPositive("transferredStudents", ts)) {
                ts = null;
            }
        }
        return ts;
    }

    public FormOfEducation readFormOfEducation() {
        FormOfEducation foe = null;
        while (foe == null) {
            String s = input("formOfEducation (DISTANCE_EDUCATION,\n"
                    + "    FULL_TIME_EDUCATION,\n" + "    EVENING_CLASSES or null)", true);
            if (s == null) {
                return null;
            }
            foe = parser.parseFormOfEducation(s);
        }
        return foe;
    }

    public Person readPerson() {
        String ans = null;
        while (ans == null) {
            ans = input("Do you want to enter groupAdmin? yes/no", false);
            if (!ans.toUpperCase(Locale.ROOT).equals("YES") && !ans.toUpperCase(Locale.ROOT).equals("NO")) {
                ans = null;
            }
        }
        if (ans.toUpperCase(Locale.ROOT).equals("NO")) {
            return null;
        }
        String name = readName();
        String passID = input("passportID (may be null)", true);
        Country c = readCountry();
        Location l = new Location(readLocationX(), readLocationY(),
                input("location name (may be null)", true));
        return new Person(name, passID, c, l);
    }

    private Country readCountry() {
        Country c = null;
        while (c == null) {
            c = parser.parseCountry(input("nationality (UNITED_KINGDOM,\n"
                    + "    CHINA,\n" + "    THAILAND,\n" + "    SOUTH_KOREA,\n" + "    JAPAN)", false));
        }
        return c;
    }

    public Long readLocationX() {
        Long x = null;
        while (x == null) {
            x = parser.parseLong(input("location X", false));
        }
        return x;
    }

    public Double readLocationY() {
        Double y = null;
        while (y == null) {
            y = parser.parseDouble(input("location Y", false));
        }
        return y;
    }

}
