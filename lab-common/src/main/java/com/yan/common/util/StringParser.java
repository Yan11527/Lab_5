package com.yan.common.util;

import com.yan.common.element.Country;
import com.yan.common.element.FormOfEducation;

public class StringParser {

    public Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("!!! Value must be integer !!!");
            return null;
        }
    }

    public Double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("!!! Value must be double !!!");
            return null;
        }
    }

    public Long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            System.out.println("!!! Value must be long !!!");
            return null;
        }
    }

    public FormOfEducation parseFormOfEducation(String s) {
        try {
            return FormOfEducation.valueOf(s);
        } catch (IllegalArgumentException e) {
            System.out.println("!!! Value must be one of: DISTANCE_EDUCATION,\n"
                    + "    FULL_TIME_EDUCATION,\n" + "    EVENING_CLASSES !!!");
            return null;
        }
    }

    public Country parseCountry(String s) {
        try {
            return Country.valueOf(s);
        } catch (IllegalArgumentException e) {
            System.out.println("!!! Value must be on of: UNITED_KINGDOM,\n"
                    + "    CHINA,\n" + "    THAILAND,\n" + "    SOUTH_KOREA,\n" + "    JAPAN !!!");
            return null;
        }
    }

}
