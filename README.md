# Simulator

This is an attempt at writing a simple robot simulator that can run the code we run on the robot.  
The reason behind this is that we want to be able to see what we are doing earlier in the season, before we have a working robot.


## Instructions

Clone the project, import into intellij, and run 
```Bash
./gradlew run 
```

Right now this is a proof of concept, but eventually we would like to simulate a whole game.  It would also be nice to write tests 
for things like autonomous, so we know where we are ending up.


## Project Style

So this project is broken down into 3 separate sub-projects.  

**robot** - Contains code specific to the physical robot. Connecting to hardware.

**simulator** - Contains code specific to the simulated robot.  Physics and graphics.

**shared** - Contains code shared between the two, this should be where we focus most of our logic.

Every robot component used in shared should be wrapped in an interface.
 