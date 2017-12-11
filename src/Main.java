import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n;
        System.out.println("Welcome to the JPMC Redux.");
        System.out.println("Problem: Given an integer n, where 0<n<1 billion, print n's textual representation");
        do{
            System.out.print("Enter n: ");
            n=in.nextLine();

            String result=solution(n);
            System.out.println("Result: "+result);
        }while (!"Q".equalsIgnoreCase(n));
    }

    public static String solution(String n){

        String workingSolution="";
        switch(n.length()){
            case 3: {
                        workingSolution+= onesTensText(""+n.charAt(0));
                        workingSolution+= "Hundred";
                        workingSolution+= solution(n.substring(1));
                        break;
            }

            case 2: {
                        workingSolution+= onesTensText(n);
                        break;
                    }
            case 1: {
                        workingSolution+= onesTensText(n);
                        break;
                    }
            default:{
                        //Values larger than 3 sig digits are handled by another function
                        workingSolution+=thousandsPlusText(n);
                        break;
                    }
        }
        //1_000_000_000
        return workingSolution;
    }
    enum TextualValues{
        Zero("0"), One("1"), Two("2"), Three("3"), Four("4"), Five("5"),
        Six("6"), Seven("7"), Eight("8"), Nine("9"), Ten("10"), Eleven("11"),
        Twelve("12"), Thirteen("13"), Fourteen("14"), Fifteen("15"), Sixteen("16"), Seventeen("17"),
        Eighteen("18"), Nineteen("19"), Twenty("20"), Thirty("30"), Forty("40"), Fifty("50"),
        Sixty("60"), Seventy("70"), Eighty("80"), Ninety("90");

        String text="";

        TextualValues(String passed){
            text=passed;
        }
    }
    enum PlaceValues{
        Thousand(4,5,6), Million(7,8,9);

        int[] sigDigits;

        PlaceValues(int... passed){
            sigDigits=passed;
        }
        public boolean contains(int length) {
            for (int x = 0; x < sigDigits.length; x++) {
                if (sigDigits[x] == length) {
                    return true;
                }
            }
            return false;
        }
    }

    public static String thousandsPlusText(String n){
        String workingSolution="";

        for (PlaceValues pv : PlaceValues.values()){
            if (pv.contains(n.length())){

                workingSolution+=solution(n.substring(0, n.length()-3));
                workingSolution+=pv.toString();
                workingSolution+=solution(n.substring(n.length()-3));


            }
        }

        return workingSolution;
    }

    public static String onesTensText(String n){
        for (TextualValues tv : TextualValues.values()){
            if (n.equals(tv.text)){
                return tv.toString();
            }
        }

        for (TextualValues tv : TextualValues.values()){
            if (tv.text.equals(n.charAt(0)+"0")){
                return (tv.toString()+ onesTensText(""+n.charAt(1)));
            }
        }

        return "";
    }



}
