package com.qualcomm.ftcrobotcontroller.opmodes;
// PushBotAuto

import com.qualcomm.robotcore.robocol.Telemetry;

import customfunctions.Timer;

/**
 * Hill attempt: turns to the right
 * @author Dunter Hevlin
 * @version 404
 */
public class PushBotAuto extends PushBotTelemetry implements Runnable{
    /**
     * Construct the class.
     *
     * The system calls this member when the class is instantiated.
     */
    int timer;
    private float x;
    private float y;
    private boolean isActiveFlag;

    public PushBotAuto () {
        // Initialize base classes.
        // All via self-construction.
        // Initialize class members.
        // All via self-construction.
    }
    public PushBotAuto(float x, float y){
        this.x = x;
        this.y = y;
        this.start();
    }
    // start
    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     * The system calls this member once when the OpMode is enabled.
     */
    @Override public void start () {
        // Call the PushBotHardware (super/base class) start method.
        super.start ();

        // Reset the motor encoders on the drive wheels.
        reset_drive_encoders ();

    } // start

    /**
     * Implement a state machine that controls the robot during auto-operation.
     * The state machine uses a class member and encoder input to transition
     * between states.
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop () {
       /* try{
            //new instantiation of the thread
            isActiveFlag = true;
            new PushBotAuto(1.0F, 1.0F);
            Thread.sleep(1000);
            isActiveFlag = false;//- breaks the loop of the thread -
            Thread.sleep(1);
            isActiveFlag = true;
            new PushBotAuto(0F, 1.0F);
            Thread.sleep(1000);
            isActiveFlag = false;//- breaks the loop of the thread -
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }*/
        //f, l, f, r, f,
        try {
            switch (v_state) {
                case 0:
                    reset_drive_encoders();
                    v_state++;

                case 1:
                    run_using_encoders();
                    try {
                        Thread.sleep(1000L);
                        telemetry.addData("2017", "Post Thread");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    update_telemetry(); // Update common telemetry
                    telemetry.addData("2016", "Post T/C");
                    set_drive_power(1.0f, 1.0f);
                    timer=2000;
                    v_state++;
                    break;
                case 2:
                    set_drive_power(1.0F, -1.0F);
                    timer=500;
                    v_state++;
                    break;
                case 3:
                    set_drive_power(1.0f, 1.0f);
                    timer=3000;
                    v_state++;
                    break;
                case 4:
                    set_drive_power(0f, 0f);
                default:
                    break;
            } // switch
            update_telemetry (); // Update common telemetry
            telemetry.addData ("18", "State: " + v_state);
                Thread.sleep(timer);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    } // loop

    //--------------------------------------------------------------------------
    //
    // v_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialized (0).  When the loop
     * starts, the state will change from initialize to state_1.  When state_1
     * actions are complete, the state will change to state_2.  This implements
     * a state machine for the loop method.
     */
    private int v_state = 0;

    @Override
    public void run() {
        while(isActiveFlag)
        set_drive_power(this.x, this.y);
    }
} // PushBotAuto
