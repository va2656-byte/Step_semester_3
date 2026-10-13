
import java.util.Scanner;

public class Problem4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter train code: ");
        String code = sc.nextLine();

        int left = 0;
        int right = code.length() - 1;
        boolean palindrome = true;

        while (left < right) {
            if (code.charAt(left) != code.charAt(right)) {
                palindrome = false;
                break;
            }

            left++;
            right--;
        }

        if (palindrome) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }

        sc.close();
    }
}
