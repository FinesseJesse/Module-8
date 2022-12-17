/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.exercise30_02;

/**
 *
 * @author jswai
 */
import java.util.*;
import java.util.stream.Collectors;

public class Exercise30_02 {
  public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> list = new ArrayList<>();
    
    System.out.print("Enter the integers between 1 and 100, enter zero when done: ");
    //attention use 0  to finish program
    
    // Read all numbers
    int number = input.nextInt(); // number read from a file
    while (number != 0) {
      if (number <= 100 && number >= 0)
        list.add(number); 
      
      number = input.nextInt(); 
    }

    list.stream().collect(Collectors.groupingBy(e -> e, 
      TreeMap::new, Collectors.counting())).forEach((k, v) ->
        System.out.println(k + " occurs " + v + (v == 1 ? " time " : " times")));
  }
}
