package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private Servo gripper = null;

    private Servo gripper2 = null;
    private boolean gripperOpen = false;

    private int liftLevel = 0;

    private DcMotor lifted = null;

    private int level0target = -30;

    private int level1target = -4000;

    private int level2target = -7750;

    private int level3target = -10500;

    private int auton = -6000;

    // private DigitalChannel armLimitSwitch = null;
   // private DigitalChannel armLowerLimit = null;



    public  Intake(HardwareMap h) {

        gripper = h.get(Servo.class, "gripper");
        gripper2 = h.get(Servo.class, "gripper2");

        lifted = h.get(DcMotor.class, "lifted");

        lifted.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lifted.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void removeLiftLimits() {
        lifted.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public String getMotorRunmode() {
        return lifted.getMode().toString();
    }

    public int getLiftLevel() {return  liftLevel; }

    public void setLiftLevel(int level) { liftLevel = level; }


    //Open and close the gripper
    public void openGripper() {
        gripper.setPosition(.25);
        gripper2.setPosition(.7);
        gripperOpen = true;
    }

    public void closeGripper() {
        gripper.setPosition(.7);
        gripper2.setPosition(.25);
        gripperOpen = false;
    }


    public void togleGripper() {
        if(gripperOpen) {
            closeGripper();
        } else {
            openGripper();
        }
        gripperOpen = !gripperOpen;
    }

    public void moveLift(double speed)  {
        if(speed > .2 || speed < -.2){
            lifted.setPower(speed);
        } else {
            lifted.setPower(0);
        }

    }
    public double getEncodedLift(){
        return lifted.getCurrentPosition();
    }

    public void liftToBottom() {
        lifted.setTargetPosition(level0target);
        setLiftLevel(0);
        lifted.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void liftLevel1() {
        lifted.setTargetPosition(level1target);
        setLiftLevel(1);
        lifted.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void liftLevel2() {
        lifted.setTargetPosition(level2target);
        setLiftLevel(2);
        lifted.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void liftLevel3() {
        lifted.setTargetPosition(level3target);
        setLiftLevel(3);
        lifted.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void AUTONlevel() {
        lifted.setTargetPosition(auton);
        setLiftLevel(auton);
        lifted.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public  boolean liftBusy() { return  lifted.isBusy(); }


    public int getLevel3Target(){
        return level3target;

    }

    public int getLevel2Target(){
        return level2target;

    }

    public int getLevel1Target(){
        return level1target;

    }

    public int getBottomTarget(){
        return level0target;

    }

    public int getauton(){
        return auton;

    }


}
