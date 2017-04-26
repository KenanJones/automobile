package edu.jalc.automobile.onlinebuilder.components;

import java.util.ArrayList;
import edu.jalc.automobile.onlinebuilder.components.engine.diese.*;
import edu.jalc.automobile.onlinebuilder.components.engine.economy.*;
import edu.jalc.automobile.onlinebuilder.components.engine.sport.*;
import edu.jalc.automobile.parts.*;

public class Driver{

   public static void main(String[] args){
      
      ArrayList<Suspension> order = new ArrayList<>();
      
      order.add(new TowingSuperDuty(new SuperShock(),new SuperSpring()));
      
      for(Suspension s : order){
         System.out.println(s.toString());
      }
      

   }
}