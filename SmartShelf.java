import java.util.Scanner;
class Smartshelf 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        do
        {
            System.out.println("\nWelcome to SmartShelf!");
            System.out.println("1. Admin Login");
            System.out.println("2. User Signup");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) 
            {
                case 1:
                    admin.adminLogin();
                    break;
                case 2:
                    user.signup();
                    break;
                case 3:
                    user.login();
                    break;
                case 4:
                    System.out.println("Thank You for using SmartShelf!");
                    return;
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }while(choice !=4);
    }
}
