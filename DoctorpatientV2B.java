/**
 * Author: Zetarius Hambrick
 */

   import java.util.*;

public class DoctorpatientV2B {
  


   // This is a global Scanner object named keyboard.
   static  Scanner keyboard = new Scanner(System.in);
   
   // Program specific constants
   static String MEDICINE_ONE = "Cipro";
   static String MEDICINE_TWO = "Amoxicillin";
   static String MEDICINE_THREE = "Penicillin";
   static String MEDICINE_FOUR = "Ciprofloxacin";
   static String MEDICINE_FIVE = "Vitamin C";
   static final int MAX_ROUNDS = 3;
   static final int MAX_HEALTH = 100;
   
//Main
 public static void main(String[] args) { 
    
     //Sets local variables for main
       String userName; 
       int userChoice = 0;
       int currentRound = 0;
       int diseaseWeakness = 0;
       String selectedMedicine;
       int patientHealthPoints = 100;
       Boolean successFlag = false;

     //Calls a module that displays a big banner that explains the idea of the game
       displayInstructionBanner();
       
     //Calls a function that asks user for their name and validates it
       userName = getString("Please enter your name.");
   
     //Calls a module displays list of medicines available to user and indicates their indicative values
       displayChoices(userName);
       
     //Plays rounds of the game where the user is asked for input, a weakness for the disease is determined,
     //the values are compared, and win/loss is determined using the boolean successFlag variable.  
       currentRound++;
       diseaseWeakness = getRandomNumber(1,5);
       userChoice = getInteger("Choose a medicine to prescribe for the patient.");
       successFlag = determineSuccess(diseaseWeakness, userChoice);
       selectedMedicine = determineMedicine(userChoice); 
       displayRoundText(selectedMedicine, successFlag, userName, patientHealthPoints, currentRound);
       if (successFlag) {
                displayWinScreen();
                System.exit(0);
     } else {
                patientHealthPoints = decreasePatientHealth(patientHealthPoints);
                currentRound++;
                diseaseWeakness = getRandomNumber(1,5);
                userChoice = getInteger("Choose a medicine to prescribe for the patient.");
                successFlag = determineSuccess(diseaseWeakness, userChoice);
                selectedMedicine = determineMedicine(userChoice); 
                displayRoundText(selectedMedicine, successFlag, userName, patientHealthPoints, currentRound);
            }   if (successFlag) {
                         displayWinScreen();
                         System.exit(0);
              } else {
                         patientHealthPoints = decreasePatientHealth(patientHealthPoints);
                         currentRound++;
                         diseaseWeakness = getRandomNumber(1,5);
                         userChoice = getInteger("Choose a medicine to prescribe for the patient.");
                         successFlag = determineSuccess(diseaseWeakness, userChoice);
                         selectedMedicine = determineMedicine(userChoice); 
                         displayRoundText(selectedMedicine, successFlag, userName, patientHealthPoints, currentRound);
                }        if (successFlag) {
                                  displayWinScreen();
                                  System.exit(0);
                      }  else {
                                  displayLoseScreen();
                                  System.exit(0);
       }
       
       
       
       
       
       
       
       
 } 
       
       
       
       
//////////////////////////////////////////////
         
//Module that displays a big banner that explains the idea of the game
  private static void displayInstructionBanner() {
     System.out.println("*******************************************************************************************");
     System.out.println("*                                    Beat the Disease!                                    *");
     System.out.println("* You are a doctor battling a disease in your patient.                                    *");
     System.out.println("* If you can guess the disease’s weakness, you will save the patient.                     *");
     System.out.println("* If you guess incorrectly 3 times the patient will die and your conduct will be reviewed.*");
     System.out.println("*******************************************************************************************");
     System.out.println("");
   }
         
//Displays list of medicines available to user and indicates their indicative values
  private static void displayChoices(String playerName) {
     System.out.println("Dr. " + playerName + ", we have several medicines to try to defeat the disease.");
     System.out.println("We will identify them by number:");
     System.out.println("1. Cipro");
     System.out.println("2. Amoxicillin");
     System.out.println("3. Penicillin");
     System.out.println("4. Ciprofloxacin");
     System.out.println("5. Vitamin C");
   }
   
   
//Gets input from user to determine chosen medicine and associates the value with medicine name
  private static String determineMedicine(int chosenMedicine) {
     String newValue;
     if (chosenMedicine == 1) {
      newValue = MEDICINE_ONE; 
   } else if (chosenMedicine == 2) {
        newValue = MEDICINE_TWO;
   } else if (chosenMedicine == 3) {
        newValue = MEDICINE_THREE;
   } else if (chosenMedicine == 4) {
        newValue = MEDICINE_FOUR;
   } else {
        newValue = MEDICINE_FIVE;
   }
     return newValue;
  }   

  //Displays information of results to user
       private static void displayRoundText(String chosenMedicine, Boolean hasUserWon,
                                       String playerAlias, int currentHP, int roundsSoFar) {
            System.out.println(chosenMedicine + ". Good choice.");
            System.out.println("It is time.  The patient takes the medicine...");
            if (hasUserWon) {
                System.out.println("Your prescription beat the disease! Well done.");
                System.out.println("Thank you for saving the patient, Dr. " + playerAlias);
          } else {
                currentHP = currentHP - (MAX_HEALTH / MAX_ROUNDS);
                if (MAX_ROUNDS - roundsSoFar == 0){ //This expression is chosen for cohesion and ease of debugging
                    System.out.println("GAME OVER, your patient has died");
              } else
                System.out.println("Your prescription did not affect the disease. Your patient’s health is at " + currentHP + "%" );
                System.out.println(MAX_ROUNDS - roundsSoFar + " more rounds to go!");
             }  
    }
       
  //Decreases patient's current health
      private static int decreasePatientHealth(int healthOfPatient) { 
             healthOfPatient = MAX_HEALTH - (MAX_HEALTH / MAX_ROUNDS);
      return healthOfPatient;
    }
       
  //Displays Win Screen
  private static void displayWinScreen() {
     System.out.println("******************************************************************************************");
     System.out.println("*                                         You win!!                                      *");
     System.out.println("******************************************************************************************");
     System.out.println("");
   }

    //Displays Lose Screen
  private static void displayLoseScreen() {
     System.out.println("******************************************************************************************");
     System.out.println("*                                         You lose..                                     *");
     System.out.println("******************************************************************************************");
     System.out.println("");
   }

    //Determines if win condition has been met
  private static boolean determineSuccess(int weaknessOfDisease, int choiceOfUser) {
        boolean didUserWin = false;
        if (weaknessOfDisease == choiceOfUser) {
          didUserWin = true;
        }
        return didUserWin;
   }

     
   ////////////// INPUT ROUTINE FUNCTIONS - DO NOT CHANGE ANYTHING BELOW HERE
   
   private static String getString(String msg) {
      System.out.println(msg);
      String newValue = keyboard.nextLine();  //gets a the entire line as a String

      // Replaces all spaces with null and checks if entered value is null
      if (newValue.replace(" ", "").equals("")) {
         System.err.println("Error: No input. Ending Program.");
         System.exit(-1);
      }
      return newValue;
   }
   
   private static int getInteger(String msg) {
      System.out.println(msg);
      if (!keyboard.hasNextInt()) {  // gets input from the user and asks if it is an integer
         System.err.println("Error: Invalid number. Ending Program");
         System.exit(-1);
      }
      int newValue = keyboard.nextInt();  // gets an integer
      return newValue;
   }
   
      private static boolean getYorN(String msg) {
      String newValue = "";
      keyboard.nextLine();           // this line is needed to flush out the input buffer
      newValue = getString(msg);     // gets a string from the String routine
      
      if  (newValue.compareToIgnoreCase("y")   == 0 
        || newValue.compareToIgnoreCase("yes") == 0) {
         return true;
      } 
      else {
         if  (newValue.compareToIgnoreCase("n")  == 0 
           || newValue.compareToIgnoreCase("no") == 0) {
            return false;
         }
         else {
            System.err.println("Error: Invalid value should be Y or N. Ending Program");
            System.exit(-1);
            return false;  // The Java compiler wants this even though it is never executed.
         }
      }
   }
   
   //Generates a random number from low to high, inclusive
   public static int getRandomNumber (int low, int high) {
      return (int)(Math.random() * ((high + 1) - low)) + low;
   }
   
   private static void closeScanner() {
      if(keyboard != null) {
         keyboard.close() ;  
      }
   }
   
  }
