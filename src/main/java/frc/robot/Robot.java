package frc.robot;

import java.util.EnumSet;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEvent;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringSubscriber;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  NetworkTableInstance defaultNetworkTable;
  final StringSubscriber stringSubscriber;

  public Robot() {

    defaultNetworkTable = NetworkTableInstance.getDefault();
    NetworkTable datatable = defaultNetworkTable.getTable("datatable");

    stringSubscriber = datatable.getStringTopic("myStringValue").subscribe("not set", PubSubOptions.keepDuplicates(true), PubSubOptions.sendAll(true));

    // Set up a listener for changes to the value
    defaultNetworkTable.addListener(stringSubscriber, 
      EnumSet.of(NetworkTableEvent.Kind.kValueAll),
      event -> {
        String stringValue = stringSubscriber.get();
        System.out.println("Event - Client said: " + stringValue);
      });
  }

  String lastStringValue = "not set";

  @Override
  public void robotPeriodic() {

    // if( lastStringValue != stringSubscriber.get() ) {
    //   lastStringValue = stringSubscriber.get();
    //   System.out.println("Polled - Client said: " + lastStringValue + " " + System.currentTimeMillis());
    // }

  }
}
