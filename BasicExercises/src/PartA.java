import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PartA {

    public static void main(String[] args) {
        calc();
    }

    public static void calc(){
        Scanner scan = new Scanner(System.in);
        int x=0;
        int y=0;
        try{
            System.out.print("Enter first integer: ");
            if(scan.hasNextLine())
                x= scan.nextInt();
            System.out.print("Enter second integer: ");
            if(scan.hasNextLine())
                y= scan.nextInt();
            if(!(x>0)||!(y>0)){
                System.out.println("Invalid input");
            }
            if ((x>0 && y>0)){
                int gcd=gcd(x,y);
                int lcm=lcm(x,y,gcd);
                System.out.println("LCM is "+lcm);
            }
            yesOrNo();


        } catch (Exception e) {
            System.out.println("Invalid input");
            yesOrNo();
        }


    }
    public static void yesOrNo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("If you want to continue please type Y, otherwise N.");
        if(scan.hasNextLine()){
            String yn=scan.next();
            if(yn.equalsIgnoreCase("y"))
                calc();
            else if(yn.equalsIgnoreCase("n"))
                System.exit(0);
            else
                yesOrNo();

        }

    }
    //Finding greatest common divisor
    public static Integer gcd(Integer x,Integer y){
        ArrayList<Integer> div=new ArrayList<Integer>();
        ArrayList<Integer> div2=new ArrayList<Integer>();
        for(int i=1;i<=x;i++){
            if (x%i==0)
                div.add(i);
        }
        for(int i=1;i<=y;i++){
            if (y%i==0)
                div.add(i);
        }
        int count;
        for(int j=0;j<div.size();j++){
            count=Collections.frequency(div, div.get(j));
            if (count==2)
                div2.add(div.get(j));
        }
        return Collections.max(div2);
    }

    public static Integer lcm(Integer x,Integer y,Integer gcd){
        return (x*y)/gcd;
    }
}
