package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignsPattern {

    private String fiveSigns;
    private String fourOpenSigns;
    private String fourClosedSigns;
    private String threeOpenSigns;
    private String threeClosedSigns;
    private String twoOpenSigns;
    private String twoClosedSigns;
    private String sixSignsWithHoles;
    private String fiveSignsWithHoles;
    private String fourOpenSignsWithHoles;
    private String fourCloseSignsWithHoles;
    private String threeOpenSignsWithHoles;

public SignsPattern(){
    HashMap<String, Integer> patterns = new HashMap<>();
    String playerSign = "[P|T]";
    String playerOrFreeSign = "[P|T|F]";
    String free = "F";
    String close = "[E|^|$]";
    String amound = "{%d}";

    fiveSigns = playerSign+String.format(amound, 5);

    fourOpenSigns = free+playerSign+String.format(amound, 4)+free;
    fourClosedSigns = "("+close+playerSign+String.format(amound, 4)+free+"|"+free+playerSign+String.format(amound, 4)+close+")";

    threeOpenSigns = free+playerSign+String.format(amound, 3)+free;
    threeClosedSigns = "("+close+playerSign+String.format(amound, 3)+free+"|"+free+playerSign+String.format(amound, 3)+close+")";

    twoOpenSigns = free+playerSign+String.format(amound, 2)+free;
    twoClosedSigns = "("+close+playerSign+String.format(amound, 2)+free+"|"+free+playerSign+String.format(amound, 2)+close+")";

    sixSignsWithHoles = playerOrFreeSign+String.format(amound, 6);

    fiveSignsWithHoles = playerOrFreeSign+String.format(amound, 5);

    fourOpenSignsWithHoles = free+playerOrFreeSign+String.format(amound, 4)+free;
    fourCloseSignsWithHoles = "("+close+playerOrFreeSign+String.format(amound, 4)+free+"|"+free+playerOrFreeSign+String.format(amound, 4)+close+")";

    threeOpenSignsWithHoles = free+playerOrFreeSign+String.format(amound, 3)+free;
}

public int countString(String s){
    int points =0;
    Pattern p = Pattern.compile(fiveSigns);
    Matcher m = p.matcher(s);


    return 0;
}


}
