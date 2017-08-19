package csvtrial;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import commons.io.FileUtils;

public class Csvtrial {
    
    
     public static int games=200; //all league games played cumulatively by all teams
    public static int gamesHome=10; // games played by home team so far
    public static int gamesAway=10; //games played by away team so far
    public static double HAS_H2H,AAS_H2H,HDS_H2H,ADS_H2H;
    public static double HAS_FORM,AAS_FORM,HDS_FORM,ADS_FORM;
     public static int HPHS,HPAS,HPHS2,HPAS2,HPHS3,HPAS3;
     public static double HP,HP2,HP3;
  
    
    
    public static String dirName = "D:\\";
    public static String homeTeam="Everton";
    public static String awayTeam="Manchester City";
    public static String League="E0"; // English Premier League
     public static int[][]HF_temp=new int[38][2];
     public static int[][]AF_temp=new int[38][2];
     public static int x=0;
      public static int y=0;
      
      //div is number of games used to find averages
       public static  double div = 0;
       public static  double div2 = 0;

       
       public static int season_var1 = 0;
      public static int season_var2 = 0;
       
    public static void main(String[] args) throws MalformedURLException, IOException {
           //String csvFile=saveUrl("http://www.football-data.co.uk/mmz4281/1617/E0.csv", "c://");
           
        
        saveFileFromUrlWithJavaIO(
 dirName + "\\a.csv",
 "http://www.football-data.co.uk/mmz4281/"+getSeason()+"/"+League+".csv");
        ReadCsv_HFAF();
        
        H2H();
        HomeForm();
        AwayForm();
       matrix();
       
      
       System.out.println(getSeason());
    }
 
    
// Using Java IO
    
 public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
 throws MalformedURLException, IOException {
 BufferedInputStream in = null;
 FileOutputStream fout = null;
 try {
 in = new BufferedInputStream(new URL(fileUrl).openStream());
 fout = new FileOutputStream(fileName);

byte data[] = new byte[1024];
 int count;
 while ((count = in.read(data, 0, 1024)) != -1) {
 fout.write(data, 0, count);
 }
 } finally {
 if (in != null)
 in.close();
 if (fout != null)
 fout.close();
 }
 }

 public static void ReadCsv_HFAF(){
 
 
 String csvFile = dirName + "a.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] team = line.split(cvsSplitBy);
                 //HF_temp=new int[38][2];
                 
                 //if home
                if(team[2].equals(homeTeam)||team[3].equals(homeTeam)){
                    if(team[2].equals(homeTeam)){
                  
                   HF_temp[x][0]=Integer.parseInt(team[4]);
                   HF_temp[x][1]=Integer.parseInt(team[5]);
                    }else if(team[3].equals(homeTeam)){
                    
                   HF_temp[x][0]=Integer.parseInt(team[5]);
                   HF_temp[x][1]=Integer.parseInt(team[4]);
                    //System.out.println(HF[x][0]+" - "+HF[x][1]);
                    }
                     System.out.println(HF_temp[x][0]+" - "+HF_temp[x][1]);  
                    x++;
               
               //System.out.println("Home= " + team[2] +" "+ team[4] +  " , Away=" +team[3] +" "+ team[5]);
                }
                
               
                
                //AF_temp=new int[38][2];
                  //if home
                if(team[2].equals(awayTeam)||team[3].equals(awayTeam)){
                    if(team[2].equals(awayTeam)){
                  
                   AF_temp[y][0]=Integer.parseInt(team[4]);
                   AF_temp[y][1]=Integer.parseInt(team[5]);
                    }else if(team[3].equals(awayTeam)){
                    
                   AF_temp[y][0]=Integer.parseInt(team[5]);
                   AF_temp[y][1]=Integer.parseInt(team[4]);
                    //System.out.println(HF[x][0]+" - "+HF[x][1]);
                    }
                     System.out.println(AF_temp[y][0]+" - "+AF_temp[y][1]);  
                    y++;
               
               //System.out.println("Home= " + team[2] +" "+ team[4] +  " , Away=" +team[3] +" "+ team[5]);
                }
          
            }
            System.out.println(x);
            System.out.println(y);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

 
 }
     //UNIQUE
    public static String H2H(){//HEAD TO HEAD
        
        //TO USE REAL GAME STATS
    int[][]h2h=new int[6][2];
    //ARSvsTOT
    //[x][0]=home
    //[x][1]=awqay
    //game1
    h2h[0][0]=2;
    h2h[0][1]=2;
    
    //game2
    h2h[1][0]=1;
    h2h[1][1]=1;
 
    //game3
    h2h[2][0]=2;
    h2h[2][1]=1;
    
    //game4
    h2h[3][0]=1;
    h2h[3][1]=2;
    
    //game5
    h2h[4][0]=1;
    h2h[4][1]=1;
    
    //game6
    h2h[5][0]=1;
    h2h[5][1]=0;

    //HOME ATTACKING STRENGTH
      int x1;
        int goals1=0;
        for(x1=0;x1<=5;x1++){
            
        goals1=goals1+h2h[x1][0];
        }
      double avGoals1=goals1/6.0; 
      double homeAttackingStrength=avGoals1/LeagueAverages();
        HomeAttackingStrength_H2H(homeAttackingStrength);
        
      //AWAY ATTACKING STRENGTH
      int x2;
        int goals2=0;
        for(x2=0;x2<=5;x2++){
            
        goals2=goals2+h2h[x2][1];
        }
      double avGoals2=goals2/6.0; 
      double awayAttackingStrength=avGoals2/LeagueAverages();
         AwayAttackingStrength_H2H(awayAttackingStrength);
      
      
     //HOME DEFENSIVE STRENGTH
      int x3;
        int goals3=0;
        for(x3=0;x3<=5;x3++){
            
        goals3=goals3+h2h[x3][1];
        }
      double avGoals3=goals3/6.0; 
      double homeDefensiveStrength=avGoals3/LeagueAverages();
       HomeDefensiveStrength_H2H(homeDefensiveStrength);
        
      //AWAY DEFENSIVE STRENGTH
      int x4;
        int goals4=0;
        for(x4=0;x4<=5;x4++){
            
        goals4=goals4+h2h[x4][0];
        }
      double avGoals4=goals4/6.0; 
      double awayDefensiveStrength=avGoals4/LeagueAverages();
       AwayDefensiveStrength_H2H(awayDefensiveStrength);
        
        
    
    return null;
    }
    
    public static String HomeForm(){//HEAD TO HEAD
        
        //TO USE REAL GAME STATS
    int[][]HF=new int[6][2];
    //ARSvsTOT
    //Home=arsenal
    //[x][0]=home team in focus
    //[x][1]=away all other teams played against
    
    
    //IN DESCENDING ORDER FROM LATEST MATCH
    //game1
    //HF[0][0]=4;
    //HF[0][1]=1;
    
    //game2
    //HF[1][0]=0;
    ///HF[1][1]=0;
 
    //game3
    //HF[2][0]=3;
    //HF[2][1]=2;
    
    //game4
    //HF[3][0]=1;
    //HF[3][1]=0;
    
    //game5
    //HF[4][0]=3;
    //HF[4][1]=0;
    
    //game6
    //HF[5][0]=4;
    //HF[5][1]=1;

    
    int i=x-1;
    //method
    int lim=5;
    
 
    
    if(i<=5) {
    for(i=x-1;i>=0;i--){
    
       
        HF[i][0]=HF_temp[i][0];
        HF[i][1]=HF_temp[i][1];
        
        System.out.println("One" + HF[i][0]);
        
    }
    div=x;
    
    }else if(i>5){
       for(i=x-1;i>=x-6;i--){
    
       
        HF[lim][0]=HF_temp[i][0];
        HF[lim][1]=HF_temp[i][1];
        
        System.out.println("One" + HF[lim][0]);
        System.out.println("Two" + HF[lim][1]);
        
        
        lim--;
        
        
    }   
      div=6.0;  
     
    }
    
    //HOME ATTACKING STRENGTH
      int x1;
        int goals1=0;
        for(x1=0;x1<=5;x1++){
            
        goals1=goals1+HF[x1][0];
        }
    
      double avGoals1=goals1/div; 
      double homeAttackingStrength=avGoals1/LeagueAverages();
        HomeAttackingStrength_FORM(homeAttackingStrength);
  
     //HOME DEFENSIVE STRENGTH
      int x3;
        int goals3=0;
        for(x3=0;x3<=5;x3++){
            
        goals3=goals3+HF[x3][1];
        }
       
      double avGoals3=goals3/div; 
      double homeDefensiveStrength=avGoals3/LeagueAverages();
       HomeDefensiveStrength_FORM(homeDefensiveStrength);
 
    //System.out.println(goals3/6.0);
    return null;
    }
    public static String AwayForm(){//HEAD TO HEAD
        
        //TO USE REAL GAME STATS
    int[][]AF=new int[6][2];
    //ARSvsTOT
    //home=TOT
    //[x][0]=home team in focus
    //[x][1]=away all othe teams played 
    
    //LATEST GAMES FIRST
    //game1
    //AF[0][0]=1;
    //AF[0][1]=1;
    
    //game2
    //AF[1][0]=1;
    //AF[1][1]=1;
 
    //game3
    //AF[2][0]=0;
    //AF[2][1]=0;
    
    //game4
    //AF[3][0]=1;
    //AF[3][1]=1;
    
    //game5
    //AF[4][0]=2;
    //AF[4][1]=0;
    
    //game6
    //AF[5][0]=2;
    //AF[5][1]=1;
    
      int i=x-1;
    //method
    int lim=5;
    
 
    
    if(i<=5) {
    for(i=x-1;i>=0;i--){
    
       
        AF[i][0]=AF_temp[i][0];
        AF[i][1]=AF_temp[i][1];
        
        //System.out.println("One" + AF[i][0]);
        
    }
    div2=x;
    
    }else if(i>5){
       for(i=x-1;i>=x-6;i--){
    
       
        AF[lim][0]=HF_temp[i][0];
        AF[lim][1]=HF_temp[i][1];
        
        //System.out.println("One" + AF[lim][0]);
        //System.out.println("Two" + AF[lim][1]);
        
        
        lim--;
        
        
    }   
      div2=6.0;  
     
    }
    

    //AWAY ATTACKING STRENGTH
      int x1;
        int goals1=0;
        for(x1=0;x1<=5;x1++){
            
        goals1=goals1+AF[x1][0];
        }
      double avGoals1=goals1/div2; 
      double awayAttackingStrength=avGoals1/LeagueAverages();
        AwayAttackingStrength_FORM(awayAttackingStrength);
  
     //AWAY DEFENSIVE STRENGTH
      int x3;
        int goals3=0;
        for(x3=0;x3<=5;x3++){
            
        goals3=goals3+AF[x3][1];
        }
      double avGoals3=goals3/div2; 
      double awayDefensiveStrength=avGoals3/LeagueAverages();
       AwayDefensiveStrength_FORM(awayDefensiveStrength);
 
    
    return null;
    }
     //ALL UNIQUE
    public static double HomeAttackingStrength_H2H(double homeAttackingStrength){
     System.out.println(homeAttackingStrength);
      HAS_H2H=homeAttackingStrength;
    return homeAttackingStrength;
    }
    public static double AwayAttackingStrength_H2H(double awayAttackingStrength){
     System.out.println(awayAttackingStrength);
         AAS_H2H=awayAttackingStrength;   
    return awayAttackingStrength;
    }
    public static double HomeDefensiveStrength_H2H(double homeDefensiveStrength){
     System.out.println(homeDefensiveStrength);
        HDS_H2H=homeDefensiveStrength;   
    return homeDefensiveStrength;
    }
    public static double AwayDefensiveStrength_H2H(double awayDefensiveStrength){
     System.out.println(awayDefensiveStrength);
         ADS_H2H=awayDefensiveStrength;   
    return awayDefensiveStrength;
    }
    
     //ALL UNIQUE
    public static double HomeAttackingStrength_FORM(double homeAttackingStrength){
     System.out.println(homeAttackingStrength);
      HAS_FORM=homeAttackingStrength;
    return homeAttackingStrength;
    }
    public static double AwayAttackingStrength_FORM(double awayAttackingStrength){
     System.out.println(awayAttackingStrength);
         AAS_FORM=awayAttackingStrength;   
    return awayAttackingStrength;
    }
    public static double HomeDefensiveStrength_FORM(double homeDefensiveStrength){
     System.out.println(homeDefensiveStrength);
        HDS_FORM=homeDefensiveStrength;   
    return homeDefensiveStrength;
    }
    public static double AwayDefensiveStrength_FORM(double awayDefensiveStrength){
     System.out.println(awayDefensiveStrength);
         ADS_FORM=awayDefensiveStrength;   
    return awayDefensiveStrength;
    }
    //ALL UNIQUE   
    public static double getHAS_H2H(){
       return HAS_H2H;
   }
    public static double getAAS_H2H(){
       return AAS_H2H;
   } 
    public static double getHDS_H2H(){
       return HDS_H2H;
   } 
    public static double getADS_H2H(){
       return ADS_H2H;
   } 
    
    //ALL UNIQUE   
    public static double getHAS_FORM(){
       return HAS_FORM;
   }
    public static double getAAS_FORM(){
       return AAS_FORM;
   } 
    public static double getHDS_FORM(){
       return HDS_FORM;
   } 
    public static double getADS_FORM(){
       return ADS_FORM;
   } 
    
    //SHARED
    public static double LeagueAverages(){
       //Total goals score=total goals conceded
       //TO USE REAL VALUES
  double totalgoalsScored = 270.0;
  double totalgamesPlayed = games;
 
  double average;
  average=totalgoalsScored/totalgamesPlayed;
  
   return average;
   } 
    //SHARED  
    public static double AverageGoalsHome(){
         //TO USE REAL NUMBER OF GOALS
       double totalGoals=23.0;
       
       double averageGoalsHome=totalGoals/gamesHome;
     return averageGoalsHome;
     }  
    
    //SHARED
    public static double AverageGoalsAway(){
         //TO USE REAL NUMBER OF GOALS
         double totalGoals=14.0;
        
         double averageGoalsAway=totalGoals/gamesAway;
     return averageGoalsAway ;
     }
    
    //UNIQUE  
    public static double HomeTeamGoalExpextancy_H2H(){
         double homeTeamGoalExpectancy_H2H=getHAS_H2H()*getADS_H2H()*AverageGoalsHome();
     return homeTeamGoalExpectancy_H2H;
     }
     
    public static double AwayTeamGoalExpextancy_H2H(){
          double awayTeamGoalExpectancy_H2H=getAAS_H2H()*getHDS_H2H()*AverageGoalsAway();
     return awayTeamGoalExpectancy_H2H;
     }
    //UNIQUE  
    public static double HomeTeamGoalExpextancy_FORM(){
         double homeTeamGoalExpectancy_FORM=getHAS_FORM()*getADS_FORM()*AverageGoalsHome();
     return homeTeamGoalExpectancy_FORM;
     }
     
    public static double AwayTeamGoalExpextancy_FORM(){
          double awayTeamGoalExpectancy_FORM=getAAS_FORM()*getHDS_FORM()*AverageGoalsAway();
     return awayTeamGoalExpectancy_FORM;
     }
    public static double AverageHomeTeamGoalExpectancy(){
    double averageHomeTeamGoalExpectancy=(HomeTeamGoalExpextancy_H2H()+HomeTeamGoalExpextancy_FORM())/2;
    return averageHomeTeamGoalExpectancy;
    }
    public static double AverageAwayTeamGoalExpectancy(){
    double averageAwayTeamGoalExpectancy=(AwayTeamGoalExpextancy_H2H()+AwayTeamGoalExpextancy_FORM())/2;
    return averageAwayTeamGoalExpectancy;
    }
    //SHARED 
      public static double matrix(){
      double probability = (probabilityMachine(0,AverageHomeTeamGoalExpectancy())*probabilityMachine(0,AverageAwayTeamGoalExpectancy()))*100;
     
      int i,j;
      double highestProbability=0;
      int highestProbabilityHomeScore=0;
      int highestProbabilityAwayScore=0;
      
      double highestProbability2=0;
       int highestProbabilityHomeScore2=0;
      int highestProbabilityAwayScore2=0;
      
      double highestProbability3=0;
       int highestProbabilityHomeScore3=0;
      int highestProbabilityAwayScore3=0;
      
      for(i=0;i<=8;i++){
       for(j=0;j<=8;j++){
       probability = (probabilityMachine(i,AverageHomeTeamGoalExpectancy())*probabilityMachine(j,AverageAwayTeamGoalExpectancy()))*100;
         
        if(probability>highestProbability){
            highestProbability3=highestProbability2;
            highestProbability2=highestProbability;
            highestProbability=probability;
            
            
            
               
            highestProbabilityHomeScore3=highestProbabilityHomeScore2;
            highestProbabilityAwayScore3=highestProbabilityAwayScore2; 
            highestProbabilityHomeScore2=highestProbabilityHomeScore;
            highestProbabilityAwayScore2=highestProbabilityAwayScore;
            highestProbabilityHomeScore=i;
            highestProbabilityAwayScore=j;
        
        }else if(probability<highestProbability && probability>=highestProbability2){
            highestProbability3=highestProbability2;
            highestProbability2=probability;
            
            highestProbabilityHomeScore3=highestProbabilityHomeScore2;
            highestProbabilityAwayScore3=highestProbabilityAwayScore2;
            highestProbabilityHomeScore2=i;
            highestProbabilityAwayScore2=j;
        }else if(probability<highestProbability2 && probability>=highestProbability3){
            
            highestProbability3=probability;
            
            highestProbabilityHomeScore3=i;
            highestProbabilityAwayScore3=j;
        }
      System.out.println("-----------");
       
      System.out.println(i+"-"+j+"  probability is"+ probability+"%");
        }
      
      }
      //Math.round(x*100.0)/100.0 (2dp)
      
       HPHS3=highestProbabilityHomeScore3;
      HPAS3=highestProbabilityAwayScore3;
      HP3=highestProbability3;
      
       HPHS2=highestProbabilityHomeScore2;
      HPAS2=highestProbabilityAwayScore2;
      HP2=highestProbability2;
      
      HPHS=highestProbabilityHomeScore;
      HPAS=highestProbabilityAwayScore;
      HP=highestProbability;
       System.out.println("-----Highest Probability------");
       
      System.out.println(highestProbabilityHomeScore+"-"+highestProbabilityAwayScore+" Highest probability is "+  highestProbability+"%");
       System.out.println(highestProbabilityHomeScore2+"-"+highestProbabilityAwayScore2+" 2nd Highest probability is "+  highestProbability2+"%");
       System.out.println(highestProbabilityHomeScore3+"-"+highestProbabilityAwayScore3+" 3rd Highest probability is "+  highestProbability3+"%");
      
      /*
      System.out.println("-----------");
      System.out.println(HomeTeamGoalExpextancy());
     System.out.println(AwayTeamGoalExpextancy());
      System.out.println(probability);
      */
      
      return 0; 
      }
      //SHARED
    public static double probabilityMachine(double x, double lambda){
        
            double exp =Math.exp(-lambda);
            double exp2= Math.pow(lambda, x);
            double expResult=exp*exp2;
            //System.out.println(expResult);
            
            int c,fact=1; 
             if(x<0){
             System.out.println("Invalid");
            }else{
             for(c=1;c<=x;c++){
       
             fact=fact*c;
      
       
            }
        //System.out.println(fact);
            }
       
            double PoissonValue=expResult/fact;
       
            //System.out.println(PoissonValue);
    
    return PoissonValue;
    }
    //SHARED
      public static int getHPHS(){
          //HIGHEST PROBABILITY HOME SCORE
      return HPHS;
      }
      public static int getHPAS(){
          //HIGHEST PROBABILITY AWAY SCORE
      return HPAS;
      }
      public static double getHP(){
          //HIGHEST PROBABILITY
      return HP;
      }
       public static int getHPHS2(){
      return HPHS2;
      }
      public static int getHPAS2(){
      return HPAS2;
      }
      public static double getHP2(){
      return HP2;
      }
      public static int getHPHS3(){
      return HPHS3;
      }
      public static int getHPAS3(){
      return HPAS3;
      }
      public static double getHP3(){
      return HP3;
      }
      
      public static String year(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
	   //get current date time with Date()
	   Date dateyear = new Date();
           
	   ////     System.out.println(dateFormat.format(dateyear));

	   
      return dateFormat.format(dateyear);
      }
      
      public static String month(){
        DateFormat dateFormat = new SimpleDateFormat("MM");
	   //get current date time with Date()
	   Date datemonth = new Date();
           
	  /////// System.out.println(dateFormat.format(datemonth));

	   
      return dateFormat.format(datemonth);
      }
      
      public static String getSeason(){
     
      String season=null;
      int year=Integer.parseInt(year());
      int month=Integer.parseInt(month());
      if(month>=6){
      
         season_var1=(year)-2000;
         season_var2=(year+1)-2000;
         
         season=Integer.toString(season_var1)+Integer.toString(season_var2);
         
      }else if(month<6){
      
         season_var1=(year-1)-2000;
         season_var2=(year)-2000;
         
         season=Integer.toString(season_var1)+Integer.toString(season_var2);
         
      }
      //System.out.println(season);    
      return season;
      
      }
       
      
      
///was doing getseason
	//it can now determine the current season given the time of year and use this info to fetch the appropriate match records. 
}
