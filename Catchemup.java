import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Catchemup extends Applet implements MouseListener, MouseMotionListener, Runnable {
  
  
 int grabDiamond = 0; // 1= blue, 2 = red
  
  int stopWatch = 180;
  int minutes = 3 , seconds = 1;
  private Image myImg,  blueDiamond, greenDiamond, orangeDiamond, pinkDiamond, yellowDiamond,redDiamond, bomb,score1, startBackground, start,title, point,instructions, fivepoints, lose;
   private AudioClip clickDiamond, bombsound;
   
   int startScreen = 0; // 0 = begin game display start screen. 1 = do not display start screen.
   Graphics bufferGraphics;//Buffering
   Image offscreen;
   Dimension dim;// Get the width and the height
   int startX = 320, startY= 350;
   int redX, redY;
   int lineStartX, lineStartY, lineEndX, lineEndY;//Variables for the string/hook.
 Random generator;
 int x1=0, x2=0, x3=0,x4=0, x5=0;//Random generator for X cordinates of the daimonds.
 int score=0;
 int blueX = 0, greenX = 0, orangeX = 0, pinkX = 0, yellowX = 0;//X value of the diamonds
 
 
 int screenHeight = 481, screenWidth = 770;//Screen Height and Width
 int bombX, bombY, bomb2X, bomb2Y, bomb3X, bomb3Y, bomb4X, bomb4Y, bomb5X, bomb5Y;//moving bombs variables
 int blueY=80, greenY=160, orangeY=240, pinkY=320, yellowY=400;//Y value of the diamonds
 int timer=50;
 
 // these flags are used to determine if the diamonds should be display.
 // true means display the diamond. false means do not show the diamond
 boolean showBlueDiamond = true;
 
 public void init (){//****INIT******INIT*******INIT********INIT**********INIT*************************//
  //****************BUFFERING*********BUFFERING***********BUFFERING************************************//
  dim=getSize();//Ask for the width and the height
  offscreen=createImage (dim.width,dim.height);//Buffer
  bufferGraphics = offscreen.getGraphics();//Buffer
  myImg = this.getImage (this.getDocumentBase(), "game background.jpg");
 //*************************************SOUND****************************SOUND**************************//
       clickDiamond = this.getAudioClip (this.getDocumentBase(), "drum.wav");
       bombsound = this.getAudioClip (this.getDocumentBase(), "bomb.wav");
   //*************************************DIAMOND IMAGES**********************************************//
   blueDiamond = this.getImage (this.getDocumentBase(), "Diamond_Blue.gif");
   greenDiamond = this.getImage (this.getDocumentBase(), "Diamond_Green.gif");
   orangeDiamond = this.getImage (this.getDocumentBase(), "Diamond_Orange.gif");
   pinkDiamond = this.getImage (this.getDocumentBase(), "Diamond_Pink.gif");
   yellowDiamond = this.getImage (this.getDocumentBase(), "Diamond_Yellow.gif");
   redDiamond = this.getImage (this.getDocumentBase(), "Diamond_Red.gif");
  //*******************************STARTING PAGE TITLES************************************************//
   point= this.getImage (this.getDocumentBase(), "1point.gif");
   fivepoints= this.getImage (this.getDocumentBase(), "5points.gif");
   lose= this.getImage (this.getDocumentBase(), "lose.gif");
   instructions = this.getImage (this.getDocumentBase(), "Instructions.gif");
   title = this.getImage (this.getDocumentBase(), "title1.gif");
   score1 = this.getImage (this.getDocumentBase(), "score.gif");
  //*****************************BOMB BOMB BOMB BOMB****************************************************//
   bomb = this.getImage (this.getDocumentBase(), "Bomb.gif");
  //******************START BACKGROUND IAMGE AND START BUTTON******************************************//
   startBackground = this.getImage (this.getDocumentBase(), "Starting.jpg");
   start = this.getImage (this.getDocumentBase(), "start1.gif");
   //**************************************************************************************************//
   lineStartX= 380; //staring X point of the srting.
     lineStartY =481; //starting Y point of the string.
   addMouseListener (this);
   addMouseMotionListener (this);
  
   generator = new Random();// NEW RANDOM Number
   
    blueX = generator.nextInt (730);
    greenX = generator.nextInt (730);
    orangeX = generator.nextInt (730);
    pinkX = generator.nextInt (730);
    yellowX = generator.nextInt (730);
       
        
   
 }//END init
   public void start (){//***********START*********START********START***************************//
    Thread buuBuu= new Thread (this);
      buuBuu.start();
  }//END START
 //**************************MOUSE INTERACTIONS**********MOUSE INTERACTIONS****************MOUSE INTERACTIONS
  public void mouseEntered (MouseEvent e){}
     public void mouseExited (MouseEvent e){}
     public void mouseClicked (MouseEvent e){}
     public void mousePressed (MouseEvent e){
       
       if (lineEndY-50 >= screenHeight)
         grabDiamond = 0;
       
       
       if (grabDiamond == 0){
         lineEndX= e.getX ();
           lineEndY= e.getY ();
       
       
             if (startScreen == 0) {
         
               if ((lineEndX >= startX) && (lineEndX <=startX+140) && (lineEndY >=startY) && (lineEndY<=startY+116)){
                   startScreen=1;
                   minutes = 3;
                   seconds = 1;
               }
       }
         
             if ((lineEndX >= blueX) && (lineEndX <=blueX+45) && (lineEndY >=blueY) && (lineEndY<=blueY+51)){
              score++;
              clickDiamond.play();
              grabDiamond = 1;
       }
            else if ((lineEndX >= orangeX) && (lineEndX <=orangeX+45) && (lineEndY >=orangeY) && (lineEndY<=orangeY+51)){
              score++;
              clickDiamond.play();
              grabDiamond = 2;
       }
            else if ((lineEndX >= greenX) && (lineEndX <=greenX+45) && (lineEndY >=greenY) && (lineEndY<=greenY+51)){
              score++;
              clickDiamond.play();
              grabDiamond = 3;
       }
            else if ((lineEndX >= pinkX) && (lineEndX <=pinkX+45) && (lineEndY >=pinkY) && (lineEndY<=pinkY+51)){
              score++;
              clickDiamond.play();
              grabDiamond = 4;
       }
            else if ((lineEndX >= yellowX) && (lineEndX <=yellowX+45) && (lineEndY >=yellowY) && (lineEndY<=yellowY+51)){
              score++;
              clickDiamond.play();
              grabDiamond = 5;
       }
            else if ((lineEndX >= redX) && (lineEndX <=redX+45) && (lineEndY >=redY) && (lineEndY<=redY+51)){
              score=score+5;
              clickDiamond.play();
              grabDiamond = 6;
       }
            else if ((lineEndX >= bombX) && (lineEndX <=bombX+50) && (lineEndY >=bombY) && (lineEndY<=bombY+66)){
              startScreen = 2;
              bombsound.play();
       }
            else if ((lineEndX >= bomb2X) && (lineEndX <=bomb2X+50) && (lineEndY >=bomb2Y) && (lineEndY<=bomb2Y+66)){
              startScreen = 2;
              bombsound.play();
       }
            else if ((lineEndX >= bomb3X) && (lineEndX <=bomb3X+50) && (lineEndY >=bomb3Y) && (lineEndY<=bomb3Y+66)){
              startScreen = 2;
              bombsound.play();
       }
            else if ((lineEndX >= bomb4X) && (lineEndX <=bomb4X+50) && (lineEndY >=bomb4Y) && (lineEndY<=bomb4Y+66)){
              startScreen = 2;
              bombsound.play();
       }
            else if ((lineEndX >= bomb5X) && (lineEndX <=bomb5X+50) && (lineEndY >=bomb5Y) && (lineEndY<=bomb5Y+66)){
              startScreen = 2;
              bombsound.play();
       }
      }//End Grabdiamond = 0
     }//END MOUSE PRESSED
     public void mouseMoved (MouseEvent e){
       
         if (lineEndY-50 >= screenHeight)
           grabDiamond = 0;
       // only move the mouse if no diamond has been grabbed.
       // grabDiamond is set to zero if nothing has been grabbed.
         if (grabDiamond == 0) {
           lineEndX= e.getX ();
            lineEndY= e.getY ();
       }
       repaint();
       e.consume();
     }
     public void mouseDragged (MouseEvent e){}
     public void mouseReleased (MouseEvent e){}
  
   
   public void paint (Graphics g){//****PAINT*************PAINT*****************PAINT****************PAINT**********//
     bufferGraphics.clearRect(0,0,dim.width,dim.height);
 //***************************START SCREEN  
   if (startScreen == 0) {
     bufferGraphics.drawImage (startBackground, 1,1, this);
     bufferGraphics.drawImage (start, startX,startY, this);
     bufferGraphics.setColor(Color.orange);
     bufferGraphics.setFont (new Font ("Algerian", Font.BOLD, 35));
     bufferGraphics.drawImage (title, 270,1, this);
     //*************************************************************************************************//
     bufferGraphics.setColor(Color.blue);
     bufferGraphics.setFont (new Font ("CAMPBELL", Font.BOLD, 30));
   
     bufferGraphics.drawImage (instructions, 280,40, this);
     bufferGraphics.setColor(Color.red);
     bufferGraphics.setFont (new Font ("CHINAONE", Font.BOLD, 25));
     bufferGraphics.drawString("The goal of this game is to gain as many points as possible! To get   ", 20,100);
     bufferGraphics.drawString(" these points you will need to interact with the mouse and click on ", 20,140);
     bufferGraphics.drawString("the diamonds.", 300,180);
     bufferGraphics.drawString("MAKE SURE THAT YOU DON'T CLICK ON THE MOVING BOMBS! The ", 10,220);
     bufferGraphics.drawString("game ends when you click on a bomb or the time runs out! ", 20,260);
     //*****************************************************************************************//
     bufferGraphics.drawImage (blueDiamond, 20,270, this);
     bufferGraphics.drawImage (greenDiamond, 70,270, this);
     bufferGraphics.drawImage (orangeDiamond, 120,270, this);
     bufferGraphics.drawImage (pinkDiamond, 170,270, this);
     bufferGraphics.drawImage (yellowDiamond, 220,270, this);
     bufferGraphics.setColor(Color.red);
     bufferGraphics.drawImage (point, 270,270, this);
     bufferGraphics.drawImage (redDiamond, 20,330, this);
     bufferGraphics.drawImage (fivepoints, 60,335, this);
     bufferGraphics.drawImage (bomb, 20,390, this);
     bufferGraphics.drawImage (lose, 70,410, this);
     bufferGraphics.drawImage (score1, 450,310, this);
   }
   else if (startScreen == 1){
  //****************************************************************Backgorund Image, Score and Time
     bufferGraphics.drawImage (myImg, 1,1, this);
     bufferGraphics.setColor(Color.white);
     bufferGraphics.setFont (new Font ("Helvetica", Font.BOLD, 18));
     bufferGraphics.drawString("Score:"+score, 10,20);
     bufferGraphics.drawString("Time:" + minutes + ":" + seconds, 650,20);
  //****************************************************************Mouse interaction String with a hook
     bufferGraphics.setColor (Color.red);
     bufferGraphics.drawLine (lineStartX, lineStartY, lineEndX-5,lineEndY);
     bufferGraphics.drawLine (lineStartX+1, lineStartY, lineEndX-5,lineEndY);
     bufferGraphics.drawLine (lineStartX+1, lineStartY, lineEndX-5,lineEndY);
     bufferGraphics.drawLine (lineStartX+1, lineStartY, lineEndX-5,lineEndY);
     bufferGraphics.drawLine (lineStartX+1, lineStartY, lineEndX-5,lineEndY);
     bufferGraphics.drawLine (lineStartX+1, lineStartY, lineEndX-5,lineEndY);
     bufferGraphics.setColor (Color.black);
     bufferGraphics.drawArc (lineEndX-15, lineEndY-30, 20, 30, 180, 180);
   //***************************************************************Random DIAMONDS
     bufferGraphics.drawImage (blueDiamond, blueX,blueY, this);
     bufferGraphics.drawImage (greenDiamond, greenX,greenY, this);
     bufferGraphics.drawImage (orangeDiamond, orangeX,orangeY, this);
     bufferGraphics.drawImage (pinkDiamond, pinkX,pinkY, this);
     bufferGraphics.drawImage (yellowDiamond, yellowX,yellowY, this);
     bufferGraphics.drawImage (redDiamond, redX,redY, this);
     bufferGraphics.drawImage (bomb, bombX,bombY, this);
     bufferGraphics.drawImage (bomb, bomb2X,bomb2Y, this);
   if (score >= 10)
     bufferGraphics.drawImage (bomb, bomb3X,bomb3Y, this);
   if (score >=20)
     bufferGraphics.drawImage (bomb, bomb4X,bomb4Y, this);
   if (score >=50)
     bufferGraphics.drawImage (bomb, bomb5X,bomb5Y, this);
 //****************************************************************************************************//
   if (grabDiamond == 1){
     bufferGraphics.drawImage (blueDiamond, lineEndX-30,lineEndY-50, this);
     lineEndY++;
          if (lineEndY-50 >= screenHeight)
            showBlueDiamond = false;
   }
   if (grabDiamond == 2){
     bufferGraphics.drawImage (orangeDiamond, lineEndX-30,lineEndY-50, this);
     lineEndY++;
         if (lineEndY-50 >= screenHeight)
           showBlueDiamond = false;
   }
   if (grabDiamond == 3){
     bufferGraphics.drawImage (greenDiamond, lineEndX-30,lineEndY-50, this);
     lineEndY++;
         if (lineEndY-50 >= screenHeight)
           showBlueDiamond = false;
   }
   
   if (grabDiamond == 4){
     bufferGraphics.drawImage (pinkDiamond, lineEndX-30,lineEndY-50, this);
     lineEndY++;
         if (lineEndY-50 >= screenHeight)
           showBlueDiamond = false;
   }
   if (grabDiamond == 5){
     bufferGraphics.drawImage (yellowDiamond, lineEndX-30,lineEndY-50, this);
     lineEndY++;
        if (lineEndY-50 >= screenHeight)
          showBlueDiamond = false;
   }
  if (grabDiamond == 6){
     bufferGraphics.drawImage (redDiamond, lineEndX-30,lineEndY-50, this);
     lineEndY++;
        if (lineEndY-50 >= screenHeight)
          showBlueDiamond = false;
   }
  
   //***************************************************************************
    } // startSceen else if

   else  { //end game screen
     bufferGraphics.drawImage (startBackground, 1,1, this); 
     bufferGraphics.setColor(Color.green);
     bufferGraphics.setFont (new Font ("CHINAONE", Font.BOLD, 60));
     bufferGraphics.drawString("Your Score: "+ score, 255          ,250);
     
   }
    g.drawImage(offscreen,0,0,this);//Buffer
      } //end paint
   
//END PAINT
  
    public void run (){//****RUN************RUN*******************RUN*************************RUN*********************//
      while (stopWatch > 0) {
      
        stopWatch--; 
        seconds = seconds - 1;
       if (seconds == 0) {
         minutes = minutes - 1;
         seconds = 60;
     }
     //*******************************************************************************************************//
   //RANDOM GENERATOR FOR THE BOMBS AND THE RED DIAMOND
     bombX = generator.nextInt (750);//BOMB 1 X
     bombY = generator.nextInt (430);//BOMB 1 Y
     bomb2X = generator.nextInt (750);//BOMB 2 X
     bomb2Y = generator.nextInt (430);//BOMB 2 Y
     bomb3X = generator.nextInt (750);//BOMB 3 X
     bomb3Y = generator.nextInt (430);//BOMB 3 Y
     bomb4X = generator.nextInt (750);//BOMB 4 X
     bomb4Y = generator.nextInt (430);//BOMB 4 Y
     bomb5X = generator.nextInt (750);//BOMB 5 X
     bomb5Y = generator.nextInt (430);//BOMB 5 Y
     redX= generator.nextInt (750);//DIAMOND RED  X
     redY = generator.nextInt (430);//DIAMOND RED Y
//***************************************************************************************************************//
          timer--;//Game Timer
      
//*************************MOVING DIAMONDS ACROSS THE SCREEN****************************************************//       
    blueX = blueX + 100;//Blue
      if (blueX >=screenWidth) 
        blueX = 0;
    greenX = greenX + 90;//Green
      if (greenX >=screenWidth) 
        greenX = 0;
    orangeX = orangeX + 110;//Orange
      if (orangeX >=screenWidth) 
        orangeX = 0;
    pinkX = pinkX + 70;//Pink
      if (pinkX >=screenWidth) 
        pinkX = 0;
    yellowX = yellowX + 130;//Yellow
      if (yellowX >=screenWidth) 
        yellowX = 0;
      //*****************************************************************************************************//
       repaint();
   try{
     Thread.sleep (600);
   }
   catch(InterruptedException e){}
   
    }//end while
 
   startScreen = 3;
   repaint();
   
} //end run;

public void update (Graphics g) {
 paint(g);
}
}
  
   
  


