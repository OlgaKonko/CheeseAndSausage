package com.olga_kondratenko.cheeseandsausage.ii.neiroii.neiron.constants;


import static com.olga_kondratenko.cheeseandsausage.constants.FieldConstants.FIELD_SIZE;

public class NeironConstants {
    public final static int FIRST_LAYER_NEIRONS_COUNT = FIELD_SIZE*FIELD_SIZE;
    public final static int SECOND_LAYER_NEIRONS_COUNT = FIELD_SIZE;
    public final static int LAST_LAYER_NEIRONS_COUNT = FIELD_SIZE*FIELD_SIZE;

    public final static float EMPTY_SIGN = 0.75f;
    public final static float MY_SIGN = 0.5f;
    public final static float ENEMY_SIGN = 0.25f;
}
