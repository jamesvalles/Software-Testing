/*
 * Assignment: #1
 * Topic: Identifying Triangles
 * Author: James Valles
 */

package edu.depaul.se433.assignment1;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * This class takes a string as input, parses it as an array of integers and determines what sort of
 * triangle the array represent. It also throws an error when user's input cannot be interpreted as
 * triangle or for invalid inputs.
 */

public class IsTriangle {

  public static void main(String[] args) throws Error {

    do {
      Scanner input = new Scanner(System.in);
      System.out.print(
          "Please enter the length of the sides. Type 'exit' and press return to terminate program. \n");

      //Reading in user input as a string
      String inputString = input.nextLine();

      int a = 0;
      int b = 0;
      int c = 0;

      try {
        //if exit is typed, terminate program
        if (inputString.equals("exit")) {
          System.exit(0);
        }
        //splitting string by blank space, mapping, and parsing into array
        int[] sidesArray = Stream.of(inputString.split(" ")).mapToInt(Integer::parseInt).toArray();
        //if the array does not contain 3 lengths throw exception
        if ((sidesArray.length != 3)) {
          throw new Exception(sidesArray.length
              + " side(s) entered. Invalid input length. Must contain lengths of three sides. \n");
        } else {
          //save values as a, b, c
          a = sidesArray[0];
          b = sidesArray[1];
          c = sidesArray[2];

          //check if any sides have a length of 0.
          if (a == 0 || b == 0 || c == 0) {
            throw new Exception("Sides cannot have a length value of 0.");
          }

          //triangle inequality test, if fails print 'triangle not valid'
          if ((a >= b + c || c >= b + a || b >= a + c)) {
            System.out.println("Not a valid triangle. \n");
          } else {
            //check if triangle is an equilateral
            if (a == b && a == c) {
              System.out.println("Triangle is equilateral.\n");
            } else if (a == b && a != c || b == c && b != a || a == c && c != b) {
              //check if triangle is isosceles
              System.out.println("Triangle is isosceles.\n");
            } else {
              //otherwise, triangle is scalene
              System.out.println("Triangle is a scalene.\n");
            }
          }
        }
        //throw exception
      } catch (Exception e) {
        System.out.println("Error: " + e + "\n");
      }
    } while (true);
  }
}
