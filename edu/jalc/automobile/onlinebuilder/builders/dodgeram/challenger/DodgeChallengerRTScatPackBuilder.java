package edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger;

import edu.jalc.automobile.onlinebuilder.builders.dodgeram.DodgeRamBuilderInterface;
import edu.jalc.automobile.Automobile;
import edu.jalc.automobile.common.utils.prompter.*;

import edu.jalc.automobile.parts.engine.SportEngine;
import edu.jalc.automobile.onlinebuilder.components.engine.specs.*;
import edu.jalc.automobile.onlinebuilder.components.engine.EngineAssembly;
import edu.jalc.automobile.onlinebuilder.components.engine.sport.SportEngineAssembly;
import edu.jalc.automobile.onlinebuilder.components.engine.sport.NaturallyAspiratedSportEngine;
import edu.jalc.automobile.parts.induction.NaturallyAspiratedInduction;
import edu.jalc.automobile.parts.exhaust.PerformanceExhaust;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.engine.HEMISportEngine;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.engine.HemiMdsSportEngine;

import edu.jalc.automobile.parts.body.*;
import edu.jalc.automobile.parts.body.Paint;
import edu.jalc.automobile.parts.body.seat.Seat;
import edu.jalc.automobile.parts.body.seat.LeatherSeat;
import edu.jalc.automobile.onlinebuilder.components.body.Body;
import edu.jalc.automobile.onlinebuilder.components.body.car.CoupeBody;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.paint.*;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.seat.HighPerformanceSeat;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.seat.ClothBucketSeat;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.seat.SuedeNappaLeatherHighPerformanceSeat;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.color.Black;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.color.Red;

import edu.jalc.automobile.parts.suspension.*;
import edu.jalc.automobile.onlinebuilder.components.suspension.Suspension;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.suspension.SportSuspension;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.wheel.AluminumForgedWheel;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.tire.*;
import edu.jalc.automobile.onlinebuilder.builders.dodgeram.challenger.parts.brake.*;

import edu.jalc.automobile.onlinebuilder.components.driveline.DriveLine;
import edu.jalc.automobile.onlinebuilder.components.driveline.sport.SportRWD;
import edu.jalc.automobile.parts.driveline.*;

public class DodgeChallengerRTScatPackBuilder implements DodgeRamBuilderInterface{

  private SportEngineAssembly engine;
  private Body body;
  private Suspension suspension;
  private DriveLine driveline;

  public static void main(String[] args){
    Automobile RTScatPack = new DodgeChallengerRTScatPackBuilder().askForPowerTrain().askForColorAndInterior().askForOptions().askForPackages().build();
    System.out.println(RTScatPack);
  }

  public DodgeRamBuilderInterface askForPowerTrain(){
    TerminalPrompterBuilder powertrainPromptBuilder = TerminalPrompterBuilder.newBuilder();
    SportEngine hemiEngine = new HEMISportEngine(6.4,new HorsePower(485,5200),new Torque(475,4200),8);
    SportEngineAssembly hemi_Engine = new NaturallyAspiratedSportEngine(hemiEngine, new PerformanceExhaust(), new NaturallyAspiratedInduction());
    SportEngine hemiMdsSportEngine = new HemiMdsSportEngine(6.4,new HorsePower(375,5200),new Torque(410,4200),8);
    SportEngineAssembly hemi_Mds_Engine = new NaturallyAspiratedSportEngine(hemiMdsSportEngine, new PerformanceExhaust(), new NaturallyAspiratedInduction());
    powertrainPromptBuilder.addType("Powertrain");
    powertrainPromptBuilder.addOption(hemi_Engine);
    powertrainPromptBuilder.addOption(hemi_Mds_Engine);
    int choice;
    try{
      choice = powertrainPromptBuilder.sort().build().ask();
    }
    catch(Exception except){
      choice = 0;
    }
    this.engine = (SportEngineAssembly)powertrainPromptBuilder.getOptions().get(choice -1);
    return this;
  }



  public DodgeRamBuilderInterface askForColorAndInterior(){
    TerminalPrompterBuilder paintPromptBuilder = TerminalPrompterBuilder.newBuilder();
    paintPromptBuilder.addType("Paint");
    paintPromptBuilder.addOption(new ContusionBluePaint());
    paintPromptBuilder.addOption(new GoMangoPaint());
    paintPromptBuilder.addOption(new GreenGoPaint());
    paintPromptBuilder.addOption(new MaximumSteelPaint());
    paintPromptBuilder.addOption(new OctaneRedPaint());
    paintPromptBuilder.addOption(new PitchBlackPaint());
    paintPromptBuilder.addOption(new YellowJacketPaint());
    int choice;
    try{
      choice = paintPromptBuilder.sort().build().ask();
    }
    catch(Exception except){
      choice = 0;
    }
    Paint paint = (Paint)paintPromptBuilder.getOptions().get(choice - 1);


    TerminalPrompterBuilder cabinPromptBuilder = TerminalPrompterBuilder.newBuilder();
    HighPerformanceSeat blackSuedeSeat = new HighPerformanceSeat(new Black(),"Suede/Nappa Performance Seat");
    HighPerformanceSeat redSuedeSeat = new HighPerformanceSeat(new Red(),"Suede/Nappa Performance Seat");
    ClothBucketSeat clothSeat = new ClothBucketSeat("Black (With Logo)");
    LuxuryCabin blackSuede_Seat = new LuxuryCabin(blackSuedeSeat);
    LuxuryCabin redSuede_Seat = new LuxuryCabin(redSuedeSeat);
    StandardCabin cloth_Seat = new StandardCabin(clothSeat);
    cabinPromptBuilder.addType("Seat");
    cabinPromptBuilder.addOption(blackSuede_Seat);
    cabinPromptBuilder.addOption(redSuede_Seat);
    cabinPromptBuilder.addOption(cloth_Seat);
    int cabinChoice;
    try{
      cabinChoice = cabinPromptBuilder.sort().build().ask();
    }
    catch(Exception except){
      cabinChoice = 0;
    }

    Cabin cabin = (Cabin)cabinPromptBuilder.getOptions().get(cabinChoice - 1);

    TerminalPrompterBuilder graphicPromptBuilder = TerminalPrompterBuilder.newBuilder();
    graphicPromptBuilder.addType("Graphic");
    graphicPromptBuilder.addOption(new Graphic("'Challenger' Body Stripe"));
    graphicPromptBuilder.addOption(new Graphic("No Stripe"));
    int graphicChoice;
    try{
      graphicChoice = graphicPromptBuilder.sort().build().ask();
    }
    catch(Exception except){
      graphicChoice = 0;
    }
    Graphic graphic = (Graphic)graphicPromptBuilder.getOptions().get(graphicChoice - 1);
    this.body = new CoupeBody(new Quarterpanels(paint,graphic),new EngineCompartment(new Hood(paint,graphic)),cabin,new StandardTrunk(5));

    return this;
    }

    public DodgeRamBuilderInterface askForOptions(){

      TerminalPrompterBuilder wheelPromptBuilder = TerminalPrompterBuilder.newBuilder();
      wheelPromptBuilder.addType("Wheels");
      wheelPromptBuilder.addOption(new AluminumForgedWheel(20,9,"Hyper Black II"));
      wheelPromptBuilder.addOption(new AluminumForgedWheel(20,9.5,"Low Gloss Black"));
      int choice;
      try{
        choice = wheelPromptBuilder.sort().build().ask();
      }
      catch(Exception except){
        choice = 0;
      }
      ForgedWheel wheel = (ForgedWheel)wheelPromptBuilder.getOptions().get(choice - 1);

      TerminalPrompterBuilder tirePromptBuilder = TerminalPrompterBuilder.newBuilder();
      tirePromptBuilder.addType("Tires");
      tirePromptBuilder.addOption(new AllSeasonPerformanceTire(20,245,"245/45ZR20"));
      tirePromptBuilder.addOption(new AllSeasonPerformanceTire(20,275,"275/40ZR20"));
      tirePromptBuilder.addOption(new ThreeSeasonPerformanceTire(20,255,"255/45ZR20"));
      tirePromptBuilder.addOption(new PZeroSummerTire(20,275,"275/40ZR20"));
      int tireChoice;
      try{
        tireChoice = tirePromptBuilder.sort().build().ask();
      }
      catch(Exception except){
        tireChoice = 0;
      }
      SportTire tire = (SportTire)tirePromptBuilder.getOptions().get(tireChoice - 1);

      this.suspension = new SportSuspension(new StockShock(12),new MediumSpring(12),tire,wheel);

      return this;
    }
    //placeholder
    public DodgeRamBuilderInterface askForPackages(){
      return this;
    }

    public Automobile build(){

      DriveLine driveline = new SportRWD(new FrontDeadAxle(),new RearDriveAxle(),new DriveShaft(),new HydraulicSteering(),new TorqueVectorDifferential());

      return new Automobile("Dodge","Challenger","ScatPack",body,driveline,engine,suspension);
    }

}
