import java.util.*;
import java.text.DecimalFormat;

class CurrencyConvertor {
    public static void main(String[] args) {
        double inr, usd, pound, code, euro, yen;
        DecimalFormat f = new DecimalFormat("##.###");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the currency code \n1:Rupees\n2:Dollar\n3:Pound\n4:Euro\n5:Yen");
        code = sc.nextInt();

        if (code == 1) {
            System.out.println("Enter amount in INR:");
            inr = sc.nextDouble();
            usd = inr / 82;
            System.out.println("USD: " + f.format(usd));
            euro = inr / 88;
            System.out.println("EURO: " + f.format(euro));
            pound = inr / 103;
            System.out.println("POUND: " + f.format(pound));
            yen = inr / 1.69;
            System.out.println("YEN: " + f.format(yen));
        }

        else if (code == 2) {
            System.out.println("Enter amount in USD:");
            usd = sc.nextDouble();
            inr = usd * 82;
            System.out.println("INR: " + f.format(inr));
            euro = usd * 1.08;
            System.out.println("EURO: " + f.format(euro));
            pound = usd * 1.25;
            System.out.println("POUND: " + f.format(pound));
            yen = usd * 0.0072;
            System.out.println("YEN: " + f.format(yen));
        }

        else if (code == 3) {
            System.out.println("Enter amount in EURO:");
            euro = sc.nextDouble();
            inr = euro * 0.011;
            System.out.println("INR: " + f.format(inr));
            usd = euro * 0.93;
            System.out.println("USD: " + f.format(usd));
            pound = euro * 1.16;
            System.out.println("POUND: " + f.format(pound));
            yen = euro * 0.0067;
            System.out.println("YEN: " + f.format(yen));
        }

        else if (code == 4) {
            System.out.println("Enter amount in POUND:");
            pound = sc.nextDouble();
            inr = pound * 0.0097;
            System.out.println("INR: " + f.format(inr));
            euro = pound * 0.86;
            System.out.println("EURO: " + f.format(euro));
            usd = pound * 0.80;
            System.out.println("USD: " + f.format(usd));
            yen = pound * 0.0057;
            System.out.println("YEN: " + f.format(yen));
        }

        else if (code == 5) {
            System.out.println("Enter amount in YEN:");
            yen = sc.nextDouble();
            inr = yen * 1.69;
            System.out.println("INR: " + f.format(inr));
            euro = yen * 149.56;
            System.out.println("EURO: " + f.format(euro));
            pound = yen * 173.98;
            System.out.println("POUND: " + f.format(pound));
            usd = yen * 138.87;
            System.out.println("USD: " + f.format(usd));
        }

        else {
            System.out.println("INVALID REQUEST");
        }
    }

}