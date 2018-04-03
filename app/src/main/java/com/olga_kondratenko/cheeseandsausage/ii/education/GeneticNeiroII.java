package com.olga_kondratenko.cheeseandsausage.ii.education;


import com.olga_kondratenko.cheeseandsausage.constants.Sign;
import com.olga_kondratenko.cheeseandsausage.game.game_data.Moves;
import com.olga_kondratenko.cheeseandsausage.ii.II;
import com.olga_kondratenko.cheeseandsausage.ii.NeiroII;
import com.olga_kondratenko.cheeseandsausage.ii.RandomII;
import com.olga_kondratenko.cheeseandsausage.model.Coordinates;
import com.olga_kondratenko.cheeseandsausage.model.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.olga_kondratenko.cheeseandsausage.constants.EducationConstants.EDUCATION_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.EducationConstants.LIVED_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.EducationConstants.MUTATION_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.EducationConstants.POPULATION_SIZE;
import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_MAX_MOVES;
import static com.olga_kondratenko.cheeseandsausage.constants.GameConstants.DROW;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.CIRCLE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.FREE;
import static com.olga_kondratenko.cheeseandsausage.constants.Sign.KROSS;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Moves.currentMoves;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.all;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.loses;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins;
import static com.olga_kondratenko.cheeseandsausage.game.game_data.Statistic.wins_per;

public class GeneticNeiroII {
    public ArrayList<NeiroII> firstPopulation;
   // public ArrayList<NeiroII> secondPopulation;
    RandomII randomII;
    Field field;

    public GeneticNeiroII(Field field){
        this.field = field;
        //initPopulation(firstPopulation, Sign.CIRCLE, "FirstPopulation");
        //initPopulation(secondPopulation, Sign.KROSS, "SecondPopulation");
        initFirstPopulation();
        randomII = new RandomII(field,Sign.KROSS);
       // initSecondPopulation();
    }

    public void saveWinners(){
        firstPopulation.get(0).save();
      //  secondPopulation.get(0).save();
    }

    public void check(){
        Moves.educationWinnerEffect=0;
        loses=0;
        wins=0;
        all=0;
        System.out.println("start generation check ");
        for (int firstIndex =0; firstIndex<POPULATION_SIZE; firstIndex++ ){
            for (int secondIndex =0; secondIndex<EDUCATION_SIZE; secondIndex++ ){
                play(firstPopulation.get(firstIndex), randomII);
            }
        }
        wins_per = (wins*100)/all;
        System.out.println("checheg, w "+(((float)wins)/all)*100+"%");
        reproduse(firstPopulation);
      //  reproduse(secondPopulation);
    }

    /*public void check(){
        Moves.educationWinnerEffect=0;
        System.out.println("start generation check ");
        for (int firstIndex =0; firstIndex<POPULATION_SIZE; firstIndex++ ){
            for (int secondIndex =0; secondIndex<POPULATION_SIZE; secondIndex++ ){
                play(firstPopulation.get(firstIndex), secondPopulation.get(secondIndex));
            }
        }
        System.out.println("all algorithms checked");
        reproduse(firstPopulation);
        reproduse(secondPopulation);
    }*/

    private boolean firstPlayerTurn;
    boolean gameContinue;
    Sign winner;

    public void play(NeiroII firstPlayer, RandomII randomII){//,NeiroII secondPlayer){
      gameContinue = true;
      firstPlayerTurn = new Random().nextBoolean();
       currentMoves = 0;
       while (gameContinue){
           if (firstPlayerTurn)
           makeMove(firstPlayer);
           else
               makeMove(randomII);
       }

       field.cleanField();
       if (winner == CIRCLE){
           firstPlayer.effect += FIELD_MAX_MOVES - (currentMoves/2);
           System.out.print("+"+(FIELD_MAX_MOVES - (currentMoves/2)));
           wins++;
          // secondPlayer.effect -= FIELD_MAX_MOVES - currentMoves;
       }
        if (winner == KROSS){
            loses++;
            firstPlayer.effect -= FIELD_MAX_MOVES - (currentMoves/2);
            System.out.print("-"+(FIELD_MAX_MOVES - (currentMoves/2)));
           // secondPlayer.effect += FIELD_MAX_MOVES - currentMoves;
        }
        if (winner == FREE){
            firstPlayer.effect -= currentMoves/2;
            System.out.print("-"+currentMoves/2);
        //    secondPlayer.effect -= currentMoves/2;
        }
        all++;
    }

    public void makeMove(II ii){
        ii.makeMove();
        currentMoves++;
        if (field.checkGameEnding()) {
            winner = field.getWinnerSign();
            gameContinue =false;
        }
        firstPlayerTurn = !firstPlayerTurn;
    }

    public void reproduse(ArrayList<NeiroII> population){
      //  System.out.println("reproduse population");
        Collections.sort(population);
        Moves.educationWinnerEffect=population.get(0).effect;
        for (int index = 0; index<POPULATION_SIZE; index++){
            System.out.print(population.get(index).effect+" ");
            if (index>=LIVED_SIZE){
              //  System.out.print("replased "+index +" ");
            population.set(index, new NeiroII(population.get(index-LIVED_SIZE), population.get(new Random().nextInt(LIVED_SIZE))));
            }
            population.get(index).effect=0;
        }
        System.out.println();
        for (int index = 0; index<MUTATION_SIZE; index++){
            int mutateIndex = new Random().nextInt(POPULATION_SIZE);
           // System.out.print("mutate "+ mutateIndex +" ");
            population.get(mutateIndex).mutate();
        }
    }

    private void initFirstPopulation(){
        System.out.println("init first population");
        firstPopulation = new ArrayList<>(POPULATION_SIZE);
        for (int index =0; index<POPULATION_SIZE; index++ ){
            firstPopulation.add(new NeiroII(field, Sign.CIRCLE, "FirstPopulation"+index, EducationData.need_new_education));
        }
        EducationData.need_new_education = false;
    }
  /*  private void initSecondPopulation(){
        System.out.println("init second population");
        secondPopulation = new ArrayList<>(POPULATION_SIZE);
        for (int index =0; index<POPULATION_SIZE; index++ ){
            secondPopulation.add(new NeiroII(field,  Sign.KROSS, "SecondPopulation", true));
        }
    }*/
}
