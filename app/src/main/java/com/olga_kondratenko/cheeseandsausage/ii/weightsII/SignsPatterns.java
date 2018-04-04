package com.olga_kondratenko.cheeseandsausage.ii.weightsII;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignsPatterns {
    static final HashMap<String, Integer> patterns = init();

public static HashMap<String, Integer> init(){
    HashMap<String, Integer> patterns = new HashMap<>();
    int max_points = 100000;
    int pointsAdd = 500;
    int points4 = 10000;

    int points41 = 4500;
    int points42 = 2000;
    int points3 = 3000;
    int points31 = 1000;
    int points32 = 500;

    //xxxxx
    patterns.put("PPPPT", max_points);
    patterns.put("PPPTP", max_points);
    patterns.put("PPTPP", max_points);
    patterns.put("PTPPP", max_points);
    patterns.put("TPPPP", max_points);

    //x_xxxx
    patterns.put("PFPPPT", pointsAdd);
    patterns.put("PFPPTP", pointsAdd);
    patterns.put("PFPTPP", pointsAdd);
    patterns.put("PFTPPP", pointsAdd);
    patterns.put("TFPPPP", pointsAdd);

    //xx_xxx
    patterns.put("PPFPPT", pointsAdd);
    patterns.put("PPFPTP", pointsAdd);
    patterns.put("PPFTPP", pointsAdd);
    patterns.put("PTFPPP", pointsAdd);
    patterns.put("TPFPPP", pointsAdd);

    //xxx_xx
    patterns.put("PPPFPT", pointsAdd);
    patterns.put("PPPFTP", pointsAdd);
    patterns.put("PPTFPP", pointsAdd);
    patterns.put("PTPFPP", pointsAdd);
    patterns.put("TPPFPP", pointsAdd);

    //xxxx_x
    patterns.put("PPPPFT", pointsAdd);
    patterns.put("PPPTFP", pointsAdd);
    patterns.put("PPTPFP", pointsAdd);
    patterns.put("PTPPFP", pointsAdd);
    patterns.put("TPPPFP", pointsAdd);

    //xxxx
    patterns.put("FPPPTF", points4);
    patterns.put("FPPTPF", points4);
    patterns.put("FPTPPF", points4);
    patterns.put("FTPPPF", points4);

    //xxxx.
    patterns.put("FPPPT", points41);
    patterns.put("FPPTP", points41);
    patterns.put("FPTPP", points41);
    patterns.put("FTPPP", points41);

    //.xxxx
    patterns.put("PPPTF", points41);
    patterns.put("PPTPF", points41);
    patterns.put("PTPPF", points41);
    patterns.put("TPPPF", points41);

    //x_xxx
    patterns.put("FPFPPTF", points42);
    patterns.put("FPFPTPF", points42);
    patterns.put("FPFTPPF", points42);
    patterns.put("FTFPPPF", points42);

    //xx_xx
    patterns.put("FPPFPTF", points42);
    patterns.put("FPPFTPF", points42);
    patterns.put("FPTFPPF", points42);
    patterns.put("FTPFPPF", points42);

    //xxx_x
    patterns.put("FPPPFTF", points42);
    patterns.put("FPPTFPF", points42);
    patterns.put("FPTPFPF", points42);
    patterns.put("FTPPFPF", points42);


    //xxx
    patterns.put("FPPTF", points3);
    patterns.put("FPTPF", points3);
    patterns.put("FTPPF", points3);

    //xxx.
    patterns.put("FPPT", points31);
    patterns.put("FPTP", points31);
    patterns.put("FTPP", points31);

    //.xxx
    patterns.put("PPTF", points31);
    patterns.put("PTPF", points31);
    patterns.put("TPPF", points31);

    //x_xx
    patterns.put("FPFPTF", points32);
    patterns.put("FPFTPF", points32);
    patterns.put("FTFPPF", points32);

    //xx_x
    patterns.put("FPPFTF", points32);
    patterns.put("FPTFPF", points32);
    patterns.put("FTPFPF", points32);

    //x__xx
    patterns.put("FPFFPTF", points32);
    patterns.put("FPFFTPF", points32);
    patterns.put("FTFFPPF", points32);

    //xx__x
    patterns.put("FPPFFTF", points32);
    patterns.put("FPTFFPF", points32);
    patterns.put("FTPFFPF", points32);

    //x_x_x
    patterns.put("FPFPFTF", pointsAdd);
    patterns.put("FPFTFPF", pointsAdd);
    patterns.put("FTFPFPF", pointsAdd);

    //xx
    patterns.put("FPTF", pointsAdd);
    patterns.put("FTPF", pointsAdd);

    return  patterns;
}




public static int countString(String s){
    int points =0;

    for (Map.Entry<String, Integer> entry : patterns.entrySet()) {
        if(s.contains(entry.getKey())){
          //  System.out.println(s+" contains "+entry.getKey());
            points+=entry.getValue();
        }
        else{
          //  System.out.println(s+" isn't contains"+entry.getKey());
        }
    }

    return points;
}


}
