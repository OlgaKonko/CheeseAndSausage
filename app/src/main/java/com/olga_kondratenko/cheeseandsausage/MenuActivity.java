package com.olga_kondratenko.cheeseandsausage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.olga_kondratenko.cheeseandsausage.env.Environment;
import com.olga_kondratenko.cheeseandsausage.ii.neiroii.education.EducationData;

import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.CvC;
import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.CvCE;
import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.PvC;
import static com.olga_kondratenko.cheeseandsausage.constants.GameModes.PvP;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view) {
        System.out.println("start game");
        Intent intent = new Intent(this, MainActivity.class);
        wins =0;
        loses =0;
        all =0;
        switch (view.getId()){
            case R.id.pvp_button:{
                Environment.gameMode = PvP;
                break;
            }
            case R.id.pvc_button:{
                Environment.gameMode = PvC;
                break;
            }
            case R.id.cvc_button:{
                Environment.gameMode = CvC;
                break;
            }
        }
        startActivity(intent);
       // finish();
    }

    public void educateII(View view) {
        Intent intent = new Intent(this, EducationActivity.class);
        if (view.getId()== R.id.new_education_button){
            EducationData.need_new_education = true;
            }
        Environment.gameMode = CvCE;
        wins =0;
        loses =0;
        all =0;
        startActivity(intent);
    }
}
