package customfunctions;

/**
 * Created by cad1 on 10/5/2016.
 */

public class rCoOrdinateGrid {
    public static int x=0;
    public static int y=0;
    public static Orientation orientation = Orientation.FRONT;

    public rCoOrdinateGrid(float LeftMotor, float RightMotor){
        if(LeftMotor > 0 && RightMotor > 0){
            switch(orientation){
                case FRONT:
                    y++;
                    break;
                case BACK:
                    y--;
                    break;
                case RIGHT:
                    x++;
                    break;
                case LEFT:
                    x--;
                    break;
            }
        }
        if(LeftMotor > 0 && RightMotor < 0){
            //will need to compensate for timing
            switch(orientation){
                case FRONT:
                    orientation = Orientation.RIGHT;
                    break;
                case BACK:
                    orientation = Orientation.LEFT;
                    break;
                case RIGHT:
                    orientation = Orientation.BACK;
                    break;
                case LEFT:
                    orientation = Orientation.FRONT;
                    break;
            }
        }

    }


}
