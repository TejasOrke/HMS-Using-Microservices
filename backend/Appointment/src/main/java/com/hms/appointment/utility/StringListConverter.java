package com.hms.appointment.utility;

import java.util.List;

public class StringListConverter {
    public static String convertToString(List<String> list){
        if(list == null || list.isEmpty()) return "";
        return String.join(",", list);
    }

    public static List<String> convertToList(String str){
        if(str == null || str.isEmpty()) return List.of();
        return List.of(str.split(","));
    }
}
