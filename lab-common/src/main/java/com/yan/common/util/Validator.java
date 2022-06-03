package com.yan.common.util;

import com.yan.common.element.StudyGroup;

public class Validator {

    private final int yLowerLimit = -969;

    public boolean checkStudyGroup(StudyGroup sg) {
        return sg.getName() != null && !sg.getName().equals("") && sg.getCoordinates() != null
                && sg.getCoordinates().getX() != null && checkY(sg.getCoordinates().getY())
                && checkPositive("studentsCount", sg.getStudentsCount().longValue())
                && checkPositive("shouldBeExpelled", sg.getShouldBeExpelled().longValue())
                && checkPositive("transferredStudents", sg.getTransferredStudents()) && (sg.getGroupAdmin() == null
                || sg.getGroupAdmin().getName() != null && !sg.getGroupAdmin().getName().equals("")
                && sg.getGroupAdmin().getNationality() != null && sg.getGroupAdmin().getLocation() != null
                && sg.getGroupAdmin().getLocation().getX() != null && sg.getGroupAdmin().getLocation().getY() != null);
    }

    public boolean checkY(Double y) {
        if (y == null) {
            return false;
        }
        if (y <= yLowerLimit) {
            System.out.println("!!! Y must be > -969 !!!");
        }
        return y > yLowerLimit;
    }

    public boolean checkPositive(String field, Long value) {
        if (value == null) {
            System.out.println("!!! " + field + " cannot be null !!!");
            return false;
        }
        if (value < 0) {
            System.out.println("!!! " + field + " must be positive number !!!");
        }
        return value > 0;
    }

}
