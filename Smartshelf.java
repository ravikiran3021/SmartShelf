import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class ConsoleColors 
{
    static String RESET = "\u001B[0m";
    static String BOLD = "\u001B[1m";
    static String DIM = "\u001B[2m"; 
    static String ITALIC = "\u001B[3m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String BLUE = "\u001B[34m";
    static String CYAN = "\u001B[36m";
    static String PURPLE = "\u001B[35m";
    static String WHITE = "\u001B[37m";
}

class Admin 
{
    Scanner sc = new Scanner(System.in);
    private String adminname = "admin";
    private String adminpassword = "admin123";
    private int otp; 
    
    User user;
    GroceryShelf groceryShelf;
    ElectronicsShelf electronicsShelf;
    ToysShelf toysShelf;

    Admin(User user, GroceryShelf groceryShelf, ElectronicsShelf electronicsShelf, ToysShelf toysShelf) 
    {
        this.user = user;
        this.groceryShelf = groceryShelf;
        this.electronicsShelf = electronicsShelf;
        this.toysShelf = toysShelf;
    }
    Admin()
    {
        
    }
    
    void adminLogin() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Admin Login ---" + ConsoleColors.RESET);
        System.out.print("Enter Username : ");
        String adminusername = sc.next();
        System.out.print("Enter Password : ");
        String adminpass = sc.next();


        if (adminusername.equals(adminname) && adminpass.equals(adminpassword)) 
        {
            int choice;
            do
            {
                System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n<----- Welcome to Admin Dashboard ----->" + ConsoleColors.RESET);
                System.out.println("1. View Revenue");
                System.out.println("2. view Report");
                System.out.println("3. Put Shelves in Maintenance");
                System.out.println("4. Remove Shelves from Maintenance");
                System.out.println("5. Exit");
                System.out.print("Enter option: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                switch (choice) 
                {
                    case 1:
                        viewRevenue();
                        break;
                    case 2:
                        viewReport();
                        break;
                    case 3:
                        putShelvesInMaintenance();
                        break;
                    case 4:
                        removeShelvesFromMaintenance();
                        break;
                    case 5:
                        System.out.println("Thank You....");
                        break;
                    default:
                        System.out.println("Invalid Option. Please try again.");
                }
            }while(choice != 5);
        } 
        else 
        {
            System.out.println(ConsoleColors.RED + "Incorrect Username or Password!" + ConsoleColors.RESET);
            System.out.println("Do you want to reset them? (y/n)");
            char ch = sc.next().charAt(0);
            if (ch == 'y' || ch == 'Y') 
            {
                System.out.print("Enter new Username: ");
                adminname = sc.next();
                System.out.print("Enter new Password: ");
                adminpassword = sc.next();
                System.out.println(ConsoleColors.GREEN + "Username & Password reset successfully!" + ConsoleColors.RESET);
            } 
            else 
            {
                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                return;
            }
        }
    }

    void otpGenerate() 
    {
        otp = 1000 + (int) (Math.random() * 9000);
        for(int i=0;i<20;i++)
        {
            try
            {
                Thread.sleep(150);
            }
            catch(Exception e){}
            if(i%3==1)
                System.out.print(ConsoleColors.CYAN + "\r"+"| Sending OTP..." + ConsoleColors.RESET);
            else if(i%3==2)
                System.out.print(ConsoleColors.CYAN + "\r"+"/ Sending OTP..." + ConsoleColors.RESET);
            else
                System.out.print(ConsoleColors.CYAN + "\r"+"- Sending OTP..." + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.GREEN + "\r"+"OTP Sent Successfully......              " + ConsoleColors.RESET);
        System.out.println("Your OTP is : " + otp);
    }

    int getOtp() 
    {
        return otp;
    }
    
    void viewRevenue() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Admin Revenue ---" + ConsoleColors.RESET);
        System.out.println("Total revenue generated : " + AdminRevenue.getRevenue());
        System.out.println("Admin Wallet : " + AdminRevenue.getAdminFunds());
    }
    
     void viewReport() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Admin Report ---" + ConsoleColors.RESET);

        //Current date and time 
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Date & Time: " + now.format(formatter));
        
        System.out.println("Total Revenue: " + AdminRevenue.getRevenue());
    
        // Grocery shelf items report
        System.out.println(ConsoleColors.CYAN + "\nGROCERY SHELF REPORT:" + ConsoleColors.RESET);
        System.out.println();
        System.out.printf("%-5s | %-20s | %-10s | %-10s | %-18s%n", "ID", "Item Name", "Remaining", "Sold", "Restock Count");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getRice().getId(), groceryShelf.getRice().getName(), groceryShelf.getRice().getQuantity(), groceryShelf.getRice().getSoldCount(), groceryShelf.getRice().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getOil().getId(), groceryShelf.getOil().getName(), groceryShelf.getOil().getQuantity(), groceryShelf.getOil().getSoldCount(), groceryShelf.getOil().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getSugar().getId(), groceryShelf.getSugar().getName(), groceryShelf.getSugar().getQuantity(), groceryShelf.getSugar().getSoldCount(), groceryShelf.getSugar().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getMilk().getId(), groceryShelf.getMilk().getName(), groceryShelf.getMilk().getQuantity(), groceryShelf.getMilk().getSoldCount(), groceryShelf.getMilk().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getSalt().getId(), groceryShelf.getSalt().getName(), groceryShelf.getSalt().getQuantity(), groceryShelf.getSalt().getSoldCount(), groceryShelf.getSalt().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getWheat().getId(), groceryShelf.getWheat().getName(), groceryShelf.getWheat().getQuantity(), groceryShelf.getWheat().getSoldCount(), groceryShelf.getWheat().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getBiscuits().getId(), groceryShelf.getBiscuits().getName(), groceryShelf.getBiscuits().getQuantity(), groceryShelf.getBiscuits().getSoldCount(), groceryShelf.getBiscuits().getRestockCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d | %-18s%n", groceryShelf.getBread().getId(), groceryShelf.getBread().getName(), groceryShelf.getBread().getQuantity(), groceryShelf.getBread().getSoldCount(), groceryShelf.getBread().getRestockCount());
        System.out.println("----------------------------------------------------------------------");

        
        // Electronics shelf items report
        System.out.println(ConsoleColors.CYAN + "\nELECTRONICS SHELF REPORT:" + ConsoleColors.RESET);
        System.out.println();
        System.out.printf("%-5s | %-20s | %-10s | %-10s%n", "ID", "Item Name", "Remaining", "Sold");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getHeadphones().getId(), electronicsShelf.getHeadphones().getName(), electronicsShelf.getHeadphones().getQuantity(), electronicsShelf.getHeadphones().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getCharger().getId(), electronicsShelf.getCharger().getName(), electronicsShelf.getCharger().getQuantity(), electronicsShelf.getCharger().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getPowerBank().getId(), electronicsShelf.getPowerBank().getName(), electronicsShelf.getPowerBank().getQuantity(), electronicsShelf.getPowerBank().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getUSBCable().getId(), electronicsShelf.getUSBCable().getName(), electronicsShelf.getUSBCable().getQuantity(), electronicsShelf.getUSBCable().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getBluetoothSpeaker().getId(), electronicsShelf.getBluetoothSpeaker().getName(), electronicsShelf.getBluetoothSpeaker().getQuantity(), electronicsShelf.getBluetoothSpeaker().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getSmartWatch().getId(), electronicsShelf.getSmartWatch().getName(), electronicsShelf.getSmartWatch().getQuantity(), electronicsShelf.getSmartWatch().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getMouse().getId(), electronicsShelf.getMouse().getName(), electronicsShelf.getMouse().getQuantity(), electronicsShelf.getMouse().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", electronicsShelf.getKeyboard().getId(), electronicsShelf.getKeyboard().getName(), electronicsShelf.getKeyboard().getQuantity(), electronicsShelf.getKeyboard().getSoldCount());
        System.out.println("----------------------------------------------------");

         // Toys shelf items report
        System.out.println(ConsoleColors.CYAN + "\nTOYS SHELF REPORT:" + ConsoleColors.RESET);
        System.out.println();
        System.out.printf("%-5s | %-20s | %-10s | %-10s%n", "ID", "Item Name", "Remaining", "Sold");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", toysShelf.getActivityTriangle().getId(), toysShelf.getActivityTriangle().getName(), toysShelf.getActivityTriangle().getQuantity(), toysShelf.getActivityTriangle().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", toysShelf.getRemoteControlHelicopter().getId(), toysShelf.getRemoteControlHelicopter().getName(), toysShelf.getRemoteControlHelicopter().getQuantity(), toysShelf.getRemoteControlHelicopter().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", toysShelf.getMusicalKeyboard().getId(), toysShelf.getMusicalKeyboard().getName(), toysShelf.getMusicalKeyboard().getQuantity(), toysShelf.getMusicalKeyboard().getSoldCount());
        System.out.printf("%-5d | %-20s | %-10d | %-10d%n", toysShelf.getWoodenChessBoard().getId(), toysShelf.getWoodenChessBoard().getName(), toysShelf.getWoodenChessBoard().getQuantity(), toysShelf.getWoodenChessBoard().getSoldCount()); 
        System.out.println("----------------------------------------------------");
    
        // Totals
        System.out.println();
        System.out.println(ConsoleColors.CYAN + "\nTOTAL ITEMS SOLD REPORT:" + ConsoleColors.RESET);
        int totalGrocerySold = groceryShelf.getRice().getSoldCount() + groceryShelf.getOil().getSoldCount() + groceryShelf.getSugar().getSoldCount() + groceryShelf.getMilk().getSoldCount() + groceryShelf.getSalt().getSoldCount() + groceryShelf.getWheat().getSoldCount() + groceryShelf.getBiscuits().getSoldCount() + groceryShelf.getBread().getSoldCount();
        int totalElectronicsSold = electronicsShelf.getHeadphones().getSoldCount() + electronicsShelf.getCharger().getSoldCount() + electronicsShelf.getPowerBank().getSoldCount() + electronicsShelf.getUSBCable().getSoldCount() + electronicsShelf.getBluetoothSpeaker().getSoldCount() + electronicsShelf.getSmartWatch().getSoldCount() + electronicsShelf.getMouse().getSoldCount() + electronicsShelf.getKeyboard().getSoldCount();
        int totalToysSold = toysShelf.getActivityTriangle().getSoldCount() + toysShelf.getRemoteControlHelicopter().getSoldCount() + toysShelf.getMusicalKeyboard().getSoldCount() + toysShelf.getWoodenChessBoard().getSoldCount();
        System.out.println(ConsoleColors.YELLOW + "\nTotal Grocery Items Sold: " + totalGrocerySold + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Total Electronics Items Sold: " + totalElectronicsSold + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Total Toys Items Sold: " + totalToysSold + ConsoleColors.RESET);

        // Shelf Maintenance Status
        System.out.println(ConsoleColors.CYAN + "\nSHELF MAINTENANCE STATUS:" + ConsoleColors.RESET);
        System.out.println("Grocery Shelf Under Maintenance: " + (groceryShelf.isUnderMaintenance() ? "Yes" : "No"));
        System.out.println("Electronics Shelf Under Maintenance: " + (electronicsShelf.isUnderMaintenance() ? "Yes" : "No"));
        System.out.println("Toys Shelf Under Maintenance: " + (toysShelf.isUnderMaintenance() ? "Yes" : "No"));

    }

    void putShelvesInMaintenance() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Set Shelf Under Maintenance ---" + ConsoleColors.RESET);
        System.out.print("Enter shelf name (GROCERY / ELECTRONICS / TOYS): ");
        String shelfName = sc.nextLine().trim().toUpperCase();
        if (shelfName.equals("GROCERY")) 
        {
            groceryShelf.setUnderMaintenance(true);
            System.out.println(ConsoleColors.PURPLE + "GROCERY shelf is now under maintenance." + ConsoleColors.RESET);
        } 
        else if (shelfName.equals("ELECTRONICS")) 
        {
            electronicsShelf.setUnderMaintenance(true);
            System.out.println(ConsoleColors.PURPLE + "ELECTRONICS shelf is now under maintenance." + ConsoleColors.RESET);
        }
        else if (shelfName.equals("TOYS")) 
        {
            toysShelf.setUnderMaintenance(true);
            System.out.println(ConsoleColors.PURPLE + "TOYS shelf is now under maintenance." + ConsoleColors.RESET);
        }  
        else 
            System.out.println(ConsoleColors.RED + "Invalid shelf name." + ConsoleColors.RESET);
    }

    void removeShelvesFromMaintenance() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Remove Shelf Maintenance ---" + ConsoleColors.RESET);
        System.out.print("Enter shelf name (GROCERY / ELECTRONICS / TOYS): ");
        String shelfName = sc.nextLine().trim().toUpperCase();
        if (shelfName.equals("GROCERY")) 
        {
            groceryShelf.setUnderMaintenance(false);
            System.out.println(ConsoleColors.GREEN + "GROCERY shelf maintenance removed." + ConsoleColors.RESET);
        } 
        else if (shelfName.equals("ELECTRONICS")) 
        {
            electronicsShelf.setUnderMaintenance(false);
            System.out.println(ConsoleColors.GREEN + "ELECTRONICS shelf maintenance removed." + ConsoleColors.RESET);
        }
        else if (shelfName.equals("TOYS")) 
        {
            toysShelf.setUnderMaintenance(false);
            System.out.println(ConsoleColors.PURPLE + "TOYS shelf maintenance removed." + ConsoleColors.RESET);
        }  
        else 
            System.out.println(ConsoleColors.RED + "Invalid shelf name." + ConsoleColors.RESET);
    }
}

class User 
{
    Admin admin = new Admin();
    Scanner sc = new Scanner(System.in);
    String savedUsername;
    String savedPassword;
    String savedMobile;
    String savedName;

    double walletBalance = 0.0;
    static double bankBalance = 20000;

    // Cart items
    Item cartItem1 = null;
    Item cartItem2 = null;
    Item cartItem3 = null;
    Item cartItem4 = null;
    Item cartItem5 = null;
    Item cartItem6 = null;
    Item cartItem7 = null;
    Item cartItem8 = null;
    int qty1 = 0;
    int qty2 = 0;
    int qty3 = 0;
    int qty4 = 0;
    int qty5 = 0;
    int qty6 = 0;
    int qty7 = 0;
    int qty8 = 0;

    GroceryShelf groceryShelf;
    ElectronicsShelf electronicsShelf;
    ToysShelf toysShelf;

    User(GroceryShelf groceryShelf, ElectronicsShelf electronicsShelf, ToysShelf toysShelf)
    {
        this.groceryShelf = groceryShelf;
        this.electronicsShelf = electronicsShelf;
        this.toysShelf = toysShelf;
    }

    boolean isValidPassword(String password)
    {
        if(password.length() < 8 )
        {
            System.out.println(ConsoleColors.RED + "Password must be at least 8 characters long." + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
            return false;
        }
        if(!password.matches(".*[!@#$%^&*()].*"))
        {
            System.out.println(ConsoleColors.RED + "Password must contain at least one special character (!@#$%^&*())." + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
            return false;
        }
        if(!password.matches(".*[0-9].*"))
        {
            System.out.println(ConsoleColors.RED + "Password must contain at least one digit." + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
            return false;
        }
        if(!password.matches(".*[a-z].*"))
        {
            System.out.println(ConsoleColors.RED + "Password must contain at least one lowercase letter." + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
            return false;
        }
        if(!password.matches(".*[A-Z].*"))
        {
            System.out.println(ConsoleColors.RED + "Password must contain at least one uppercase letter." + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
            return false;
        }
        return true;
    }

    boolean otpValidation() 
    {
        boolean otpVerified = false;

        for (int i = 1; i <= 3; i++) 
        {
            System.out.print("Enter OTP: ");
            int enteredOtp = sc.nextInt();

            if (enteredOtp == admin.getOtp()) 
            {
                //System.out.println(ConsoleColors.GREEN + "OTP Verified Successfully!" + ConsoleColors.RESET);
                otpVerified = true;
                break;
            } 
            else 
            {
                System.out.println(ConsoleColors.RED + "Invalid OTP! Please try again." + ConsoleColors.RESET);
                int j = 3-i;
                if (j > 0) 
                {
                    System.out.println(ConsoleColors.YELLOW + j + " attempts left." + ConsoleColors.RESET);
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Too many attempts!" + ConsoleColors.RESET);
                }
            }
        }
        return otpVerified;
    }

    void signup() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- User Signup ---" + ConsoleColors.RESET);
        System.out.print("Enter Your Name : ");
        savedName = sc.next();
        System.out.print("Enter username: ");
        savedUsername = sc.next();
        System.out.println(ConsoleColors.YELLOW + ConsoleColors.ITALIC + "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#$%^&*())." + ConsoleColors.RESET);
        while (true) 
        {
            System.out.print("Enter password: ");
            savedPassword = sc.next();
            if (isValidPassword(savedPassword)) 
            {
                break;
            } 
            else 
            {
                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Please try again...\n" + ConsoleColors.RESET);
            }
        } 
        System.out.print("Enter mobile number: ");
        savedMobile = sc.next();
        long mobileNumber = Long.parseLong(savedMobile); // Convert mobile number to long for validation
        if (mobileNumber > 5999999999l && mobileNumber < 100000000000l) 
        {
            admin.otpGenerate();
            if(otpValidation())
                System.out.println(ConsoleColors.GREEN + "Account created successfully!" + ConsoleColors.RESET);
        }
        else
        {
            System.out.println(ConsoleColors.RED + "Invalid Mobile Number! Please enter a Valid Mobile Number." + ConsoleColors.RESET);
            signup();
        }
    }

    void login() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- User Login ---" + ConsoleColors.RESET);
        System.out.print("Enter username: ");
        String uname = sc.next();
        System.out.print("Enter password: ");
        String pass = sc.next();
        if (savedUsername == null || savedPassword == null) 
        {
            System.out.println("No user found! Create your account first.");
            return;
        }
        if (uname.equals(savedUsername) && pass.equals(savedPassword)) 
        {
            System.out.println(ConsoleColors.GREEN + "Login successful!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "Welcome back, " + savedName + "!" + ConsoleColors.RESET);
            showUserMenu();
            return;
        } 
        else if(!uname.equals(savedUsername) && pass.equals(savedPassword))
        {
            System.out.println(ConsoleColors.RED + "Username is Incorrect !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "Do you want to know your username (y/n)" + ConsoleColors.RESET);
            char ch = sc.next().charAt(0);

            if(ch == 'y' || ch == 'Y')
            {
                System.out.println(ConsoleColors.CYAN + "Your Username : " + savedUsername + ConsoleColors.RESET);
                login();
            }
            else if(ch == 'n' || ch == 'N')
            {
                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                return;
            }
            else
            {
                System.out.println(ConsoleColors.RED + "Invalid Choice...!" + ConsoleColors.RESET);
                return;
            }
        }
        else if(uname.equals(savedUsername) && !pass.equals(savedPassword))
        {
            System.out.println(ConsoleColors.RED + "Password is Incorrect !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "Do you want to reset your Password (y/n)" + ConsoleColors.RESET);
            char ch = sc.next().charAt(0);
            if(ch == 'y' || ch == 'Y')
            {
                System.out.print("Enter your Mobile Number : ");
                String mobno = sc.next();
                long mobno1 = Long.parseLong(mobno);
                if (mobno1 > 5999999999l && mobno1 < 100000000000l)
                {
                    if(mobno.equals(savedMobile))
                    {
                        System.out.println(ConsoleColors.YELLOW + ConsoleColors.ITALIC + "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#$%^&*())." + ConsoleColors.RESET);
                        while (true) 
                        {
                            System.out.print("Enter password: ");
                            savedPassword = sc.next();
                            if (isValidPassword(savedPassword)) 
                            {
                                break;
                            } 
                            else 
                            {
                                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Please try again...\n" + ConsoleColors.RESET);
                            }
                        } 
                        System.out.print("Confirm Password : ");
                        String confirmpassword = sc.next();
                        if(savedPassword.equals(confirmpassword))
                        {
                            System.out.println(ConsoleColors.GREEN + "Password Updated Succesfully...." + ConsoleColors.RESET);
                            login();
                        }
                        else
                        {
                            System.out.println(ConsoleColors.RED + "New Password & Confirm Password Mismatched...!" + ConsoleColors.RESET);
                            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                            return;
                        }
                    }
                    else
                    {
                        System.out.println(ConsoleColors.RED + "Mobile Number Mismatch...!" + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                        return;
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "Invalid Mobile Number! Please enter a Valid Mobile Number." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                    return;
                }
            }
            else if(ch == 'n' || ch == 'N')
            {
                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                return;
            }
            else
            {
                System.out.println(ConsoleColors.RED + "Invalid Choice...!" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Main Menu...." + ConsoleColors.RESET);
                return;
            }
        }
        else 
            System.out.println(ConsoleColors.RED + "Invalid credentials! Please try again." + ConsoleColors.RESET);
    }

    void showUserMenu() 
    {
        int choice;
        do
        {
            System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- User Menu ---" + ConsoleColors.RESET);
            System.out.println("1. View Wallet Balance & Bank Balance");
            System.out.println("2. Add Money to wallet");
            System.out.println("3. Shop for Items");
            System.out.println("4. View Cart");
            System.out.println("5. Generate Invoice & Pay Bill");
            System.out.println("6. Modify / Remove Items from Cart");
            System.out.println("7. Logout");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) 
            {
                case 1:
                    viewWalletBalance();
                    break;
                case 2:
                    addMoneyToWallet();
                    break;
                case 3:
                    shopForItems();
                    break;
                case 4:
                    System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- View Cart ---" + ConsoleColors.RESET);
                    viewCart();
                    break;
                case 5:
                    generateInvoice();
                    break;
                case 6:
                    removeItemFromCart();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid option. Please try again." + ConsoleColors.RESET);
            }
        }while(choice != 7);
    }

    void viewWalletBalance() 
    {
        System.out.println(ConsoleColors.GREEN + "Your Wallet Balance : " + walletBalance + ConsoleColors.RESET);
        System.out.printf(ConsoleColors.GREEN + "Your Bank Balance : %.2f "  + ConsoleColors.RESET, bankBalance);
        System.out.println();
    }
    
    void addMoneyToWallet() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Add Money to Wallet ---" + ConsoleColors.RESET);
        System.out.print("Enter amount to add from your bank account: ");
        double amount = sc.nextDouble();
        sc.nextLine(); 

        if (amount <= 0) 
        {
            System.out.println(ConsoleColors.RED + "Invalid amount...!" + ConsoleColors.RESET);
            return;
        }

        if (bankBalance >= amount) 
        {
            bankBalance -= amount;
            walletBalance += amount;
            System.out.println(ConsoleColors.GREEN + "Successfully added " + amount + " to your wallet." + ConsoleColors.RESET);
            System.out.println("Do you Want to Check Your Wallet & Bank Balance (y/n) ? ");
            char ch=sc.next().charAt(0);
            if(ch == 'y' || ch == 'Y')
                viewWalletBalance();
            else
                showUserMenu();
        } 
        else 
        {
            System.out.println(ConsoleColors.RED + "Insufficient bank balance. Transaction failed." + ConsoleColors.RESET);
            System.out.println("Your current bank balance is: " + bankBalance);
        }
    }

    void shopForItems() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Shop for Items ---" + ConsoleColors.RESET);
        System.out.println("1. Grocery Shelf");
        System.out.println("2. Electronics Shelf");
        System.out.println("3. Toys Shelf");
        System.out.println("4. Back to User Menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); 
        switch (choice) 
        {
            case 1:
                groceryShelf.displayItems();
                if(groceryShelf.isUnderMaintenance())
                {
                    System.out.println(ConsoleColors.RED + "Grocery Shelf is currently under maintenance. Please try again later." + ConsoleColors.RESET);
                    return;
                }
                System.out.print("Enter item ID to purchase : ");
                int groceryItemId = sc.nextInt();
                Item grocerySelectedItem = groceryShelf.selectItemById(groceryItemId);
                if (grocerySelectedItem != null) 
                {
                    System.out.print("Enter quantity: ");
                    int groceryQty = sc.nextInt();
                    sc.nextLine();
                    if(groceryQty > grocerySelectedItem.getQuantity())
                    {
                        System.out.println(ConsoleColors.RED + "Insufficient stock for " + grocerySelectedItem.getName() + ". Available quantity: " + grocerySelectedItem.getQuantity() + ConsoleColors.RESET);
                        System.out.print("Do you want to add the available quantity to your cart? (y/n) ");
                        char ch = sc.next().charAt(0);
                        if(ch == 'y' || ch == 'Y')
                        {
                            groceryQty = grocerySelectedItem.getQuantity();
                        }
                        else if(ch == 'n' || ch == 'N')
                        {
                            System.out.print("Do you want to enter the quantity again? (y/n) ");
                            char ch1 = sc.next().charAt(0);
                            if(ch1 == 'y' || ch1 == 'Y')
                            {
                                System.out.print("Enter quantity: ");
                                groceryQty = sc.nextInt();
                            }
                            else if(ch1 == 'n' || ch1 == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Shop Menu...." + ConsoleColors.RESET);
                                shopForItems();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to Shop Menu...." + ConsoleColors.RESET);
                                shopForItems();
                            }
                        }
                        else
                        {
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to Shop Menu...." + ConsoleColors.RESET);
                            shopForItems();
                        }
                    }
                    // Check if item already in cart
                    if (cartItem1 != null && cartItem1.getId() == grocerySelectedItem.getId())
                    { 
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty1 += groceryQty; 
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem2 != null && cartItem2.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty2 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 != null && cartItem3.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty3 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 != null && cartItem4.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty4 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem5 != null && cartItem5.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty5 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem6 != null && cartItem6.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty6 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem7 != null && cartItem7.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty7 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem8 != null && cartItem8.getId() == grocerySelectedItem.getId())
                    {
                        grocerySelectedItem.reserveItem(groceryQty);
                        qty8 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = grocerySelectedItem;
                        qty1 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }   
                    else if (cartItem2 == null)
                    {
                        cartItem2 = grocerySelectedItem;
                        qty2 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 == null)
                    {
                        cartItem3 = grocerySelectedItem;
                        qty3 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 == null)
                    {
                        cartItem4 = grocerySelectedItem;
                        qty4 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem5 == null) 
                    {
                        cartItem5 = grocerySelectedItem;
                        qty5 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }   
                    else if (cartItem6 == null)
                    {
                        cartItem6 = grocerySelectedItem;
                        qty6 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem7 == null)
                    {
                        cartItem7 = grocerySelectedItem;
                        qty7 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem8 == null)
                    {
                        cartItem8 = grocerySelectedItem;
                        qty8 = groceryQty;
                        grocerySelectedItem.reserveItem(groceryQty);
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else 
                        System.out.println(ConsoleColors.YELLOW + "OOPS! Cart is full!" + ConsoleColors.RESET);
                }
                break;
            case 2:
                electronicsShelf.displayItems();
                if(electronicsShelf.isUnderMaintenance())
                {
                    System.out.println(ConsoleColors.RED + "Electronics Shelf is currently under maintenance. Please try again later." + ConsoleColors.RESET);
                    return;
                }
                System.out.print("Enter item ID to purchase : ");
                int electronicsItemId = sc.nextInt();
                Item electronicsSelectedItem = electronicsShelf.selectItemById(electronicsItemId);
                if (electronicsSelectedItem != null) 
                {
                    System.out.print("Enter quantity: ");
                    int electronicsQty = sc.nextInt();
                    sc.nextLine();
                    if(electronicsQty > electronicsSelectedItem.getQuantity())
                    {
                        System.out.println(ConsoleColors.RED + "Insufficient stock for " + electronicsSelectedItem.getName() + ". Available quantity: " + electronicsSelectedItem.getQuantity() + ConsoleColors.RESET);
                        System.out.print("Do you want to add the available quantity to your cart? (y/n)");
                        char ch = sc.next().charAt(0);
                        if(ch == 'y' || ch == 'Y')
                        {
                            electronicsQty = electronicsSelectedItem.getQuantity();
                        }
                        else if(ch == 'n' || ch == 'N')
                        {
                            System.out.print("Do you want to enter the quantity again? (y/n) ");
                            char ch1 = sc.next().charAt(0);
                            if(ch1 == 'y' || ch1 == 'Y')
                            {
                                System.out.print("Enter quantity: ");
                                electronicsQty = sc.nextInt();
                            }
                            else if(ch1 == 'n' || ch1 == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Shop Menu...." + ConsoleColors.RESET);
                                shopForItems();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to Shop Menu...." + ConsoleColors.RESET);
                                shopForItems();
                            }
                        }
                        else
                        {
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to Shop Menu...." + ConsoleColors.RESET);
                            shopForItems();
                        }
                    }

                    // Check if item already in cart
                    if (cartItem1 != null && cartItem1.getId() == electronicsSelectedItem.getId())
                    {
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty1 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem2 != null && cartItem2.getId() == electronicsSelectedItem.getId())
                    { 
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty2 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }   
                    else if (cartItem3 != null && cartItem3.getId() == electronicsSelectedItem.getId())
                    {
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty3 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 != null && cartItem4.getId() == electronicsSelectedItem.getId())
                    {
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty4 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem5 != null && cartItem5.getId() == electronicsSelectedItem.getId())
                    {
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty5 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem6 != null && cartItem6.getId() == electronicsSelectedItem.getId())
                    { 
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty6 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }   
                    else if (cartItem7 != null && cartItem7.getId() == electronicsSelectedItem.getId())
                    {
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty7 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem8 != null && cartItem8.getId() == electronicsSelectedItem.getId())
                    {
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        qty8 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = electronicsSelectedItem;
                        qty1 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    } 
                    else if (cartItem2 == null)
                    { 
                        cartItem2 = electronicsSelectedItem;
                        qty2 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    } 
                    else if (cartItem3 == null)
                    {
                        cartItem3 = electronicsSelectedItem;
                        qty3 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    
                    else if (cartItem4 == null)
                    {
                        cartItem4 = electronicsSelectedItem;
                        qty4 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem5 == null) 
                    {
                        cartItem5 = electronicsSelectedItem;
                        qty5 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    } 
                    else if (cartItem6 == null)
                    { 
                        cartItem6 = electronicsSelectedItem;
                        qty6 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    } 
                    else if (cartItem7 == null)
                    {
                        cartItem7 = electronicsSelectedItem;
                        qty7 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem8 == null)
                    {
                        cartItem8 = electronicsSelectedItem;
                        qty8 = electronicsQty;
                        electronicsSelectedItem.reserveItem(electronicsQty);
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else 
                        System.out.println(ConsoleColors.YELLOW + "OOPS! Cart is full!" + ConsoleColors.RESET);
                }
                break;
            case 3:
                toysShelf.displayItems();
                if(toysShelf.isUnderMaintenance())
                {
                    System.out.println(ConsoleColors.RED + "Toys Shelf is currently under maintenance. Please try again later." + ConsoleColors.RESET);
                    return;
                }
                System.out.print("Enter item ID to purchase : ");
                int toysItemId = sc.nextInt();
                Item toysSelectedItem = toysShelf.selectItemById(toysItemId);
                if (toysSelectedItem != null) 
                {
                    System.out.print("Enter quantity: ");
                    int toyQty = sc.nextInt();
                    sc.nextLine();
                    if(toyQty > toysSelectedItem.getQuantity())
                    {
                        System.out.println(ConsoleColors.RED + "Insufficient stock for " + toysSelectedItem.getName() + ". Available quantity: " + toysSelectedItem.getQuantity() + ConsoleColors.RESET);
                        System.out.print("Do you want to add the available quantity to your cart? (y/n) ");
                        char ch = sc.next().charAt(0);
                        if(ch == 'y' || ch == 'Y')
                        {
                            toyQty = toysSelectedItem.getQuantity();
                        }
                        else if(ch == 'n' || ch == 'N')
                        {
                            System.out.print("Do you want to enter the quantity again? (y/n) ");
                            char ch1 = sc.next().charAt(0);
                            if(ch1 == 'y' || ch1 == 'Y')
                            {
                                System.out.print("Enter quantity: ");
                                toyQty = sc.nextInt();
                            }
                            else if(ch1 == 'n' || ch1 == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to Shop Menu...." + ConsoleColors.RESET);
                                shopForItems();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to Shop Menu...." + ConsoleColors.RESET);
                                shopForItems();
                            }
                        }
                        else
                        {
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to Shop Menu...." + ConsoleColors.RESET);
                            shopForItems();
                        }
                    }
                    // Check if item already in cart
                    if (cartItem1 != null && cartItem1.getId() == toysSelectedItem.getId())
                    { 
                        toysSelectedItem.reserveItem(toyQty);
                        qty1 += toyQty; 
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                        
                    }
                    else if (cartItem2 != null && cartItem2.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty2 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 != null && cartItem3.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty3 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 != null && cartItem4.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty4 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem5 != null && cartItem5.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty5 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem6 != null && cartItem6.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty6 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem7 != null && cartItem7.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty7 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem8 != null && cartItem8.getId() == toysSelectedItem.getId())
                    {
                        toysSelectedItem.reserveItem(toyQty);
                        qty8 += toyQty;
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = toysSelectedItem;
                        qty1 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }   
                    else if (cartItem2 == null)
                    {
                        cartItem2 = toysSelectedItem;
                        qty2 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 == null)
                    {
                        cartItem3 = toysSelectedItem;
                        qty3 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 == null)
                    {
                        cartItem4 = toysSelectedItem;
                        qty4 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem5 == null) 
                    {
                        cartItem5 = toysSelectedItem;
                        qty5 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }   
                    else if (cartItem6 == null)
                    {
                        cartItem6 = toysSelectedItem;
                        qty6 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem7 == null)
                    {
                        cartItem7 = toysSelectedItem;
                        qty7 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem8 == null)
                    {
                        cartItem8 = toysSelectedItem;
                        qty8 = toyQty;
                        toysSelectedItem.reserveItem(toyQty);
                        System.out.println(ConsoleColors.GREEN + toysSelectedItem.getName() + " added to cart." + ConsoleColors.RESET);
                    }
                    else 
                        System.out.println(ConsoleColors.YELLOW + "OOPS! Cart is full!" + ConsoleColors.RESET);
                }
                break;
            case 4:
                showUserMenu();
                break;
            default:
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
    }

    void viewCart() 
    {
        if (cartItem1 != null) 
        {
            System.out.println("Item 1: " + cartItem1.getName() + " x" + qty1);
        }
        if (cartItem2 != null) 
        {
            System.out.println("Item 2: " + cartItem2.getName() + " x" + qty2);
        }
        if( cartItem3 != null)
        {
            System.out.println("Item 3: " + cartItem3.getName() + " x" + qty3);
        }
        if( cartItem4 != null)
        {
            System.out.println("Item 4: " + cartItem4.getName() + " x" + qty4);
        }
        if (cartItem5 != null) 
        {
            System.out.println("Item 5: " + cartItem5.getName() + " x" + qty5);
        }
        if (cartItem6 != null) 
        {
            System.out.println("Item 6: " + cartItem6.getName() + " x" + qty6);
        }
        if( cartItem7 != null)
        {
            System.out.println("Item 7: " + cartItem7.getName() + " x" + qty7);
        }
        if( cartItem8 != null)
        {
            System.out.println("Item 8: " + cartItem8.getName() + " x" + qty8);
        }
        if (cartItem1 == null && cartItem2 == null && cartItem3 == null && cartItem4 == null && cartItem5 == null && cartItem6 == null && cartItem7 == null && cartItem8 == null) 
        {
            System.out.println(ConsoleColors.YELLOW + "Cart is empty." + ConsoleColors.RESET);
        }
    }

    void generateInvoice() 
    {
        if (cartItem1 == null && cartItem2 == null && cartItem3 == null && cartItem4 == null && cartItem5 == null && cartItem6 == null && cartItem7 == null && cartItem8 == null) 
        {
            System.out.println(ConsoleColors.RED + "Cart is empty. Cannot generate invoice." + ConsoleColors.RESET);
            return;
        }
        Invoice.generate(this, cartItem1, qty1, cartItem2, qty2, cartItem3, qty3, cartItem4, qty4, cartItem5, qty5, cartItem6, qty6, cartItem7, qty7, cartItem8, qty8);
    }

    void modifyOrRemoveItem(int itemNumber) 
    {
        Item selectedItem = null;
        int oldQty = 0;

        switch(itemNumber) 
        {
            case 1: 
                selectedItem = cartItem1; 
                oldQty = qty1;
                break;
            case 2: 
                selectedItem = cartItem2; 
                oldQty = qty2;
                break;
            case 3: 
                selectedItem = cartItem3; 
                oldQty = qty3;
                break;
            case 4: 
                selectedItem = cartItem4;
                oldQty = qty4; 
                break;
            case 5: 
                selectedItem = cartItem5;
                oldQty = qty5; 
                break;
            case 6: 
                selectedItem = cartItem6;
                oldQty = qty6; 
                break;
            case 7: 
                selectedItem = cartItem7; 
                oldQty = qty7;
                break;
            case 8: 
                selectedItem = cartItem8; 
                oldQty = qty8;
                break;
        }
        if (selectedItem == null) 
        {
            System.out.println(ConsoleColors.RED + "That cart slot is empty. Nothing to modify." + ConsoleColors.RESET);
            return; 
        }
        System.out.println("1. Update Quantity of " + selectedItem.getName());
        System.out.println("2. Remove " + selectedItem.getName() + " from cart");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if (choice == 1) 
        {
            System.out.print("Enter new quantity: ");
            int newQty = sc.nextInt();
            if (newQty <= 0) 
            {
                System.out.println(ConsoleColors.RED + "Quantity must be positive." + ConsoleColors.RESET);
                return;
            }

            int difference = newQty - oldQty;

            if (difference > 0) // adding quantity
            { 
                if (selectedItem.getQuantity() >= difference) 
                {
                    selectedItem.reserveItem(difference);
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Insufficient stock. Available stock: " + selectedItem.getQuantity() + ConsoleColors.RESET);
                    return;
                }
            } 
            else if (difference < 0) // reducing quantity
            { 
                selectedItem.releaseItem(-difference); // Add stock back
            }
            else 
            {
                System.out.println(ConsoleColors.RED + "Invalid quantity. Available stock: " + selectedItem.getQuantity() + ConsoleColors.RESET);
            }

            switch(itemNumber) 
            {
                case 1: 
                    qty1 = newQty; 
                    break;
                case 2: 
                    qty2 = newQty; 
                    break;
                case 3: 
                    qty3 = newQty; 
                    break;
                case 4: 
                    qty4 = newQty; 
                    break;
                case 5: 
                    qty5 = newQty; 
                    break;
                case 6: 
                    qty6 = newQty; 
                    break;
                case 7: 
                    qty7 = newQty; 
                    break;
                case 8: 
                    qty8 = newQty; 
                    break;
            }
            System.out.println(ConsoleColors.GREEN + "Quantity updated successfully." + ConsoleColors.RESET);
        } 
        else if (choice == 2) 
        {
            System.out.print("Are you sure you want to remove " + selectedItem.getName() + " from cart? (y/n)");
            char confirm = sc.next().charAt(0);
            if (confirm == 'y' || confirm == 'Y') 
            {
                selectedItem.releaseItem(oldQty);
                switch(itemNumber) 
                {
                    case 1: 
                        cartItem1 = null; 
                        qty1 = 0; 
                        break;
                    case 2: 
                        cartItem2 = null; 
                        qty2 = 0; 
                        break;
                    case 3: 
                        cartItem3 = null; 
                        qty3 = 0; 
                        break;
                    case 4: 
                        cartItem4 = null; 
                        qty4 = 0; 
                        break;
                    case 5: 
                        cartItem5 = null; 
                        qty5 = 0; 
                        break;
                    case 6: 
                        cartItem6 = null; 
                        qty6 = 0; 
                        break;
                    case 7: 
                        cartItem7 = null; 
                        qty7 = 0; 
                        break;
                    case 8: 
                        cartItem8 = null; 
                        qty8 = 0; 
                        break;
                }
                System.out.println(ConsoleColors.GREEN + "Item removed." + ConsoleColors.RESET);
            } 
            else if(confirm == 'n' || confirm == 'N')
            {
                System.out.println(ConsoleColors.YELLOW + "Item not Removed." + ConsoleColors.RESET);
            }
            else
            {
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
            }
        } 
        else 
        {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
    }

    void removeItemFromCart()
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Remove Items from Cart ---" + ConsoleColors.RESET);
        if (cartItem1 == null && cartItem2 == null && cartItem3 == null && cartItem4 == null && cartItem5 == null && cartItem6 == null && cartItem7 == null && cartItem8 == null)
        {
            System.out.println(ConsoleColors.YELLOW + "Cart is empty. Nothing to remove." + ConsoleColors.RESET);
            return;
        }
    
        // Display cart items
        System.out.println(ConsoleColors.YELLOW + "Items in Cart:" + ConsoleColors.RESET);
        viewCart();
        System.out.println();
        System.out.println(ConsoleColors.ITALIC + "------ Options -----" + ConsoleColors.RESET);
        System.out.println("Enter the Item Number(1-8) you want to modify/remove");
        System.out.println("9. Clear Entire Cart");
        System.out.println("10. Back to User Menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        if (choice >= 1 && choice <= 8) 
        {
            Item targetItem = null;
            switch(choice) 
            {
                case 1: 
                    targetItem = cartItem1; 
                    break;
                case 2: 
                    targetItem = cartItem2; 
                    break;
                case 3: 
                    targetItem = cartItem3; 
                    break;
                case 4: 
                    targetItem = cartItem4; 
                    break;
                case 5: 
                    targetItem = cartItem5; 
                    break;
                case 6: 
                    targetItem = cartItem6; 
                    break;
                case 7: 
                    targetItem = cartItem7; 
                    break;
                case 8: 
                    targetItem = cartItem8; 
                    break;
            }
            if (targetItem != null) 
            {
                modifyOrRemoveItem(choice);
            } 
            else 
            {
                System.out.println(ConsoleColors.RED + "No Item " + choice +" in cart to remove" + ConsoleColors.RESET);
            }
        } 
        else if (choice == 9) 
        {
            clearCart();
            System.out.println(ConsoleColors.GREEN + "Cart cleared successfully." + ConsoleColors.RESET);
        } 
        else if (choice == 10) 
        {
            System.out.println(ConsoleColors.DIM + " Returning to User Menu..." + ConsoleColors.RESET);
            showUserMenu();
        } 
        else 
        {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
            showUserMenu();
        }
    } 
    
    void clearCart() 
    {
        if (cartItem1 != null) 
        { 
            cartItem1.releaseItem(qty1); 
        }
        if (cartItem2 != null) 
        { 
            cartItem2.releaseItem(qty2); 
        }
        if (cartItem3 != null) 
        { 
            cartItem3.releaseItem(qty3); 
        }
        if (cartItem4 != null) 
        { 
            cartItem4.releaseItem(qty4); 
        }
        if (cartItem5 != null) 
        { 
            cartItem5.releaseItem(qty5); 
        }
        if (cartItem6 != null) 
        { 
            cartItem6.releaseItem(qty6); 
        }
        if (cartItem7 != null) 
        { 
            cartItem7.releaseItem(qty7); 
        }
        if (cartItem8 != null) 
        { 
            cartItem8.releaseItem(qty8); 
        }

        cartItem1 = null;
        cartItem2 = null;
        cartItem3 = null;
        cartItem4 = null;
        cartItem5 = null;
        cartItem6 = null;
        cartItem7 = null;
        cartItem8 = null;
        qty1 = 0;
        qty2 = 0;
        qty3 = 0;
        qty4 = 0;
        qty5 = 0;
        qty6 = 0;
        qty7 = 0;
        qty8 = 0;
    }
}

class Item 
{
    private static int nextId = 1; // Static counter for auto-incrementing ID

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String expiry;
    private int restockprice;

    // Static counters for all items
    private static int totalItemsSold = 0;
    private static int totalItemsInStock = 0;
    private int restockCount = 0;
    
    private int soldCount = 0;

    // Constructor
    Item(String name, double price, int quantity, String expiry, int restockprice) 
    {
        this.id = nextId++; // Assign unique ID and increment the counter
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        totalItemsInStock += quantity; // Add to total stock when item is created
        this.expiry = expiry;
        this.restockprice = restockprice;
    }

    // Getters
    int getId() 
    {
        return id;
    }

    String getName() 
    {
        return name;
    }

    double getPrice() 
    {
        return price;
    }

    int getQuantity() 
    {
        return quantity;
    }
    
    String getExpiry()
    {
        return expiry;
    }

    int getRestockprice()
    {
        return restockprice;
    }

    // Reduces stock when an item is added to the cart
    void reserveItem(int qty) 
    {
        if (quantity >= qty) 
        {
            quantity -= qty;
            totalItemsInStock -= qty;
        }
    }
    
    // Increases stock when an item is removed from the cart
    void releaseItem(int qty) 
    {
        quantity += qty;
        totalItemsInStock += qty;
    }
    
    // Updates sales after a successful payment
    void finalizeSale(int qty) 
    {
        soldCount += qty;
        totalItemsSold += qty;
    }

    int getSoldCount() 
    {
        return soldCount;
    }

    // Auto-restock if quantity is low
    void autoRestock() 
    {
        if (quantity <= 2) 
        {
            int restockQty = 10;
            double restockCost = restockQty * (this.price - this.restockprice);
            if (AdminRevenue.getAdminFunds() >= restockCost) 
            {
                AdminRevenue.adminFunds-=restockCost;
                quantity += restockQty;
                //System.out.println("Quantity: "+quantity);
                totalItemsInStock += restockQty;
                restockCount++;
            } 
        }
    }

    void displayItem() 
    {
        System.out.printf("%-5d | %-20s | %-10.2f | %-10d | %-15s%n", id, name, price, quantity, expiry);
    }

    // Static methods for report
    static int getTotalItemsSold() 
    {
        return totalItemsSold;
    }

    static int getTotalItemsInStock() 
    {
        return totalItemsInStock;
    }

    int getRestockCount()
    {
        return restockCount;
    }

}

class Invoice 
{    
    static Scanner sc = new Scanner(System.in);
    static String phonePePin = null;
    static String gPayPin = null;
    static String paytmPin = null;
    static boolean paymentSuccess = false;

    
    static void takeAddress()
    {
            
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n ----- Add your Address -----  " + ConsoleColors.RESET);
            
        System.out.print("Enter your Country Name : ");
        String countryname = sc.next();
        if(countryname.equalsIgnoreCase("India"))
        {
            System.out.print("Enter your State Name : ");
            String statename = sc.next();
                
            System.out.print("Enter your City Name : ");
            String cityname = sc.next();
                
            System.out.print("Enter your Landmark Name : ");
            String landmark = sc.next();
                
            System.out.print("Enter your Door Number (or) Flat Number Name : ");
            String doorNo = sc.next();
        }
        else
        {
            System.out.println(ConsoleColors.RED + "Sorry :( Currently we are delivering only in India...!" + ConsoleColors.RESET);
            takeAddress();
        }
        
    }

    // static String setUpiPin(String paymentMethod)
    // {
    //     String upiPin;
    //     while(true)
    //     {
    //         System.out.print("Set your " + paymentMethod + " UPI PIN (4 or 6 digits): ");
    //         upiPin = sc.next();
    //         if(upiPin.length() == 4 || upiPin.length() == 6)
    //         {
    //             System.out.println(ConsoleColors.GREEN + "UPI PIN set successfully for " + paymentMethod + "!" + ConsoleColors.RESET);
    //             break;
                
    //         }
    //         else
    //         {
    //             System.out.println(ConsoleColors.RED + "Invalid PIN. UPI PIN must be 4 or 6 digits." + ConsoleColors.RESET);
    //         }
    //     }
    //     return upiPin;
    // }

    static String setUpiPin(String paymentMethod)
    {
        String upiPin;
        for (int i=1;i<=3;i++)
        {
            System.out.print("Set your " + paymentMethod + " UPI PIN (4 or 6 digits): ");
            upiPin = sc.next();

            if(upiPin.length() == 4 || upiPin.length() == 6)
            {
                System.out.println(ConsoleColors.GREEN + "UPI PIN set successfully for " + paymentMethod + "!" + ConsoleColors.RESET);
                return upiPin; 
            }
            else
            {
                System.out.println(ConsoleColors.RED + "Invalid PIN format." + ConsoleColors.RESET);
                int j = 3 - i;
                if (j > 0) 
                {
                    System.out.println(ConsoleColors.YELLOW + j + " attempts left." + ConsoleColors.RESET);
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Too many attempts!" + ConsoleColors.RESET);
                }
            }
        }
        return null; 
    }
    
    
    // Generate invoice and process payment
    static void generate(User user, Item item1, int qty1, Item item2, int qty2, Item item3, int qty3, Item item4, int qty4, Item item5, int qty5, Item item6, int qty6, Item item7, int qty7, Item item8, int qty8) 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n< ----- INVOICE ----- >" + ConsoleColors.RESET);
        System.out.println();
        System.out.println(ConsoleColors.CYAN + ConsoleColors.ITALIC + "Free Shipping Ends Tonight! Don't Miss Out!" + ConsoleColors.RESET);
        System.out.println();

        // Show date & time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Date & Time: " + now.format(format));
        System.out.println("User : " + user.savedName);

        double total = 0.0;

        // Item 1
        if (item1 != null) 
        {
            double price1 = item1.getPrice() * qty1;
            System.out.println(item1.getName() + " x" + qty1 + " = " + price1);
            total += price1;
        }

        // Item 2
        if (item2 != null) 
        {
            double price2 = item2.getPrice() * qty2;
            System.out.println(item2.getName() + " x" + qty2 + " = " + price2);
            total += price2;
        }

        // Item 3
        if (item3 != null)
        {
            double price3 = item3.getPrice() * qty3;
            System.out.println(item3.getName() + " x" + qty3 + " = " + price3);
            total += price3;
        }

        // Item 4
        if (item4 != null)
        {
            double price4 = item4.getPrice() * qty4;
            System.out.println(item4.getName() + " x" + qty4 + " = " + price4);
            total += price4;
        }

        // Item 5
        if (item5 != null)
        {
            double price5 = item5.getPrice() * qty5;
            System.out.println(item5.getName() + " x" + qty5 + " = " + price5);
            total += price5;
        }

        // Item 6
        if (item6 != null)
        {
            double price6 = item6.getPrice() * qty6;
            System.out.println(item6.getName() + " x" + qty6 + " = " + price6);
            total += price6;
        }

        // Item 7
        if (item7 != null)
        {
            double price7 = item7.getPrice() * qty7;
            System.out.println(item7.getName() + " x" + qty7 + " = " + price7);
            total += price7;
        }

        // Item 8
        if (item8 != null)
        {
            double price8 = item8.getPrice() * qty8;
            System.out.println(item8.getName() + " x" + qty8 + " = " + price8);
            total += price8;
        }
        
        
        // Apply discount if total > ₹500
        double discount = 0;
        if (total >= 500) 
        {
            discount = total * 0.10;
            System.out.println(ConsoleColors.GREEN + ConsoleColors.ITALIC + "Hurray! You got 10% discount!" + ConsoleColors.RESET);
            total = total - discount;
            System.out.printf(ConsoleColors.YELLOW + "Discount Applied: %.2f" + ConsoleColors.RESET, discount);
            System.out.println();
        }
        System.out.println("Delivery Charges: 0.00 (Free Shipping)");
        System.out.println("------------------------------------");
        System.out.println("Subtotal: " + total);
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Select Payment Method ---" + ConsoleColors.RESET);
        System.out.println("1. PhonePe");
        System.out.println("2. GPay");
        System.out.println("3. Paytm");
        System.out.println("4. Pay from Wallet");
        System.out.println("5. Cancel Payment");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        double handlingFee = 0;
        String paymentMethod = "";

        switch (choice) 
        {
            case 1:
                handlingFee = 3.2;
                paymentMethod = "PhonePe";
                if (phonePePin == null)
                {
                    phonePePin = setUpiPin(paymentMethod);
                }
                break;
            case 2:
                handlingFee = 2.5;
                paymentMethod = "GPay";
                if (gPayPin == null) 
                {
                    gPayPin = setUpiPin(paymentMethod);
                }
                break;
            case 3:
                handlingFee = 3.9;
                paymentMethod = "Paytm";
                if (paytmPin == null) 
                {
                    paytmPin = setUpiPin(paymentMethod);
                }
                break;
            case 4:
                handlingFee = 0;
                paymentMethod = "Wallet";
                break;
            case 5:
                System.out.println("Payment cancelled. Returning to user menu.");
                return;
            default:
                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to user menu." + ConsoleColors.RESET);
                return;
        }
        System.out.println("Platform Fee (" + paymentMethod + "): " + handlingFee);
        double finalAmount = total + handlingFee;
        System.out.printf(ConsoleColors.BOLD + "FINAL AMOUNT TO PAY: %.2f" + ConsoleColors.RESET, finalAmount);
        System.out.println();
        System.out.println("------------------------------------");

        String enteredPin;


        switch (choice) 
        {
            case 1: 
                System.out.print("Enter your PhonePe UPI PIN to confirm payment: ");
                enteredPin = sc.next();
                if (enteredPin.equals(phonePePin)) 
                {
                    if (User.bankBalance >= finalAmount) 
                    {
                        User.bankBalance -= finalAmount;
                        paymentSuccess = true;
                    } 
                    else 
                    {
                        System.out.println(ConsoleColors.RED + "Insufficient Bank Balance!" + ConsoleColors.RESET);
                        user.showUserMenu();
                    }
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Incorrect UPI PIN! Payment failed." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.DIM + " Returning to User Menu...!" + ConsoleColors.RESET);
                    user.showUserMenu();
                }
                break;
            case 2: 
                System.out.print("Enter your GPay UPI PIN to confirm payment: ");
                enteredPin = sc.next();
                if (enteredPin.equals(gPayPin)) 
                {
                    if (User.bankBalance >= finalAmount) 
                    {
                        User.bankBalance -= finalAmount;
                        paymentSuccess = true;
                    } 
                    else 
                    {
                        System.out.println(ConsoleColors.RED + "Insufficient Bank Balance!" + ConsoleColors.RESET);
                        user.showUserMenu();
                    }
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Incorrect UPI PIN! Payment failed." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.DIM + " Returning to User Menu...!" + ConsoleColors.RESET);
                    user.showUserMenu();
                }
                break;
            case 3: 
                System.out.print("Enter your Paytm UPI PIN to confirm payment: ");
                enteredPin = sc.next();
                if (enteredPin.equals(paytmPin)) 
                {
                    if (User.bankBalance >= finalAmount) 
                    {
                        User.bankBalance -= finalAmount;
                        paymentSuccess = true;
                    } 
                    else 
                    {
                        System.out.println(ConsoleColors.RED + "Insufficient Bank Balance!" + ConsoleColors.RESET);
                        user.showUserMenu();
                    }
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Incorrect UPI PIN! Payment failed." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.DIM + " Returning to User Menu...!" + ConsoleColors.RESET);
                    user.showUserMenu();

                }
                break;
            case 4: 
                if (user.walletBalance >= finalAmount) 
                {
                    user.walletBalance -= finalAmount;
                    paymentSuccess = true;
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Insufficient Wallet Balance!" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.DIM + " Returning to User Menu...!" + ConsoleColors.RESET);
                    user.showUserMenu();
                }
                break;
        }
        if (paymentSuccess) 
        {
            for(int i=0;i<20;i++)
            {
                try
                {
                    Thread.sleep(150);
                }
                catch(Exception e){}
                if(i%3==1)
                    System.out.print(ConsoleColors.CYAN + "\r"+"| Processing Your payment Please Wait..." + ConsoleColors.RESET);
                else if(i%3==2)
                    System.out.print(ConsoleColors.CYAN + "\r"+"/ Processing Your payment Please Wait..." + ConsoleColors.RESET);
                else
                    System.out.print(ConsoleColors.CYAN + "\r"+"- Processing Your payment Please Wait..." + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.GREEN + "\r"+"Payment Successful via " + paymentMethod + "!                 " + ConsoleColors.RESET);
            AdminRevenue.addRevenue(total); 
            
            if (item1 != null) 
            {
                item1.finalizeSale(qty1);
                item1.autoRestock();
            }
            if (item2 != null) 
            {
                item2.finalizeSale(qty2);
                item2.autoRestock();
            }
            if (item3 != null) 
            {
                item3.finalizeSale(qty3);
                item3.autoRestock();
            }
            if (item4 != null) 
            {
                item4.finalizeSale(qty4);
                item4.autoRestock();
            }
            if (item5 != null) 
            {
                item5.finalizeSale(qty5);
                item5.autoRestock();
            }
            if (item6 != null) 
            {
                item6.finalizeSale(qty6);
                item6.autoRestock();
            }
            if (item7 != null) 
            {
                item7.finalizeSale(qty7);
                item7.autoRestock();
            }
            if (item8 != null) 
            {
                item8.finalizeSale(qty8);
                item8.autoRestock();
            }
            
            user.clearCart();
            System.out.println("Enter your Address to deliver your purchased Products ");
            Invoice.takeAddress();
            System.out.println();
            System.out.println(ConsoleColors.GREEN + "Your Order has been placed successfully!" + ConsoleColors.RESET);
            System.out.println("Do you want to view your Invoice (y/n) ? ");
            char ch = sc.next().charAt(0);
            if (ch == 'y' || ch == 'Y')
            {
                System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n< ----- INVOICE ----- >" + ConsoleColors.RESET);
                System.out.println("========================================");
                System.out.println("Date           : " + now.format(format));
                System.out.println("User           : " + user.savedName);
                System.out.println("Payment Method : " + paymentMethod);
                System.out.println("Total Amount   : " + total);
                System.out.println("Platform Fee   : " + handlingFee);
                System.out.printf("Discount       : %.2f" ,discount);
                System.out.println();
                System.out.println("---------------------------------------");
                System.out.printf(ConsoleColors.BOLD + "FINAL AMOUNT   : %.2f" + ConsoleColors.RESET, finalAmount);
                System.out.println();
                System.out.println("========================================");
            }
            System.out.println(ConsoleColors.GREEN + "Your Products will be delivered to your Address soon...!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "Thank you for shopping with us, " + user.savedName + "!" + ConsoleColors.RESET);
        }
        else
        {
            System.out.println(ConsoleColors.RED + "Payment Failed!" + ConsoleColors.RESET);
        } 
    } 
}

class AdminRevenue 
{
    static double totalRevenue = 0.0;
    static double adminFunds = 50000.0;

    static void addRevenue(double amount) 
    {
        totalRevenue += amount;
    }

    static double getRevenue() 
    {
        return totalRevenue;
    }
    static void deductForRestock(double cost) 
    {
        adminFunds -= cost;
    }
    static double getAdminFunds() 
    {
        return adminFunds;
    }
}

abstract class Shelf 
{
    private String shelfName;
    private boolean isUnderMaintenance = false;

    Shelf(String name) 
    {
        this.shelfName = name;
    }

    void setUnderMaintenance(boolean status) 
    {
        isUnderMaintenance = status;
    }
    boolean isUnderMaintenance()
    {
        return isUnderMaintenance;
    }

    abstract void displayItems();

    abstract Item selectItemById(int id);
}

class GroceryShelf extends Shelf 
{
    private Item rice = new Item("Rice(1kg)", 50, 10,"15-05-2026", 5);
    private Item oil = new Item("Cooking Oil(1 litre)", 120, 13,"09-10-2025", 10);
    private Item sugar = new Item("Sugar(1kg)", 40, 20,"01-12-2025", 5);
    private Item Milk = new Item("Milk(500ml)", 30, 15,"25-08-2025", 3);
    private Item Salt = new Item("Salt(1kg)", 10, 25,"30-11-2025", 2);
    private Item Wheat = new Item("Wheat(1kg)", 60, 18,"05-07-2026", 4);
    private Item Biscuits = new Item("Biscuits(1 packet)", 20, 30,"10-09-2025", 1);
    private Item Bread = new Item("Bread(1 packet)", 25, 22,"15-09-2025", 2);

    // Getters for each item

    Item getRice() 
    { 
        return rice; 
    }
    Item getOil()  
    { 
        return oil; 
    }
    Item getSugar() 
    { 
        return sugar; 
    }
    Item getMilk() 
    { 
        return Milk; 
    }
    Item getSalt()
    { 
        return Salt; 
    }
    Item getWheat() 
    { 
        return Wheat; 
    }
    Item getBiscuits() 
    { 
        return Biscuits; 
    }
    Item getBread() 
    { 
        return Bread; 
    }

    GroceryShelf() 
    {
        super("GROCERY");
    }

    
    void displayItems() 
    {
        System.out.println(ConsoleColors.CYAN + "\n< --- Grocery Shelf --- >" + ConsoleColors.RESET);
        System.out.printf("%-5s | %-20s | %-10s | %-10s | %-15s%n", "ID", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("---------------------------------------------------------------------");
        rice.displayItem();
        oil.displayItem();
        sugar.displayItem();
        Milk.displayItem();
        Salt.displayItem();
        Wheat.displayItem();
        Biscuits.displayItem();
        Bread.displayItem();
    }

    Item selectItemById(int id) 
    {

        if (id == rice.getId()) 
        {
            return rice;
        }
        else if (id == oil.getId()) 
        {
            return oil;
        }
        else if (id == sugar.getId())
        {
            return sugar;
        }
        else if (id == Milk.getId()) 
        {
            return Milk;
        }
        else if (id == Salt.getId()) 
        {
            return Salt;
        }
        else if (id == Wheat.getId())
        {
            return Wheat;
        }
        else if (id == Biscuits.getId())
        {
            return Biscuits;
        }
        else if (id == Bread.getId())
        {
            return Bread;
        }
        else 
        {
            System.out.println(ConsoleColors.RED + "Item ID not found." + ConsoleColors.RESET);
            return null;
        }
    }
}

class ElectronicsShelf extends Shelf 
{
    private Item headphones = new Item("Headphones", 1499, 9,"N/A",65);
    private Item charger = new Item("Charger(120w)", 3499, 20,"N/A", 60);
    private Item powerBank = new Item("PowerBank(10000mah)", 999, 15,"N/A", 70);
    private Item USBCable = new Item("USB Cable", 189, 25,"N/A", 20);
    private Item BluetoothSpeaker = new Item("Bluetooth Speaker", 1200, 12,"N/A", 80);
    private Item SmartWatch = new Item("Smart Watch", 2499, 8,"N/A", 100);
    private Item Mouse = new Item("Mouse", 899, 18,"N/A", 50);
    private Item Keyboard = new Item("Keyboard", 999, 10,"N/A", 75);

    // Getters for each item
    
    Item getHeadphones() 
    { 
        return headphones; 
    }
    Item getCharger()    
    {
        return charger; 
    }
    Item getPowerBank() 
    { 
        return powerBank; 
    }
    Item getUSBCable() 
    { 
        return USBCable; 
    }
    Item getBluetoothSpeaker()
    {
        return BluetoothSpeaker; 
    }
    Item getSmartWatch() 
    { 
        return SmartWatch; 
    }
    Item getMouse() 
    { 
        return Mouse; 
    }
    Item getKeyboard() 
    { 
        return Keyboard; 
    }
    
    ElectronicsShelf() 
    {
        super("ELECTRONICS");
    }
    
    void displayItems() 
    {

        System.out.println(ConsoleColors.CYAN + "\n< --- Electronics Shelf --- >" + ConsoleColors.RESET);
        System.out.printf("%-5s | %-20s | %-10s | %-10s | %-15s%n", "ID", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("---------------------------------------------------------------------");

        headphones.displayItem();
        charger.displayItem();
        powerBank.displayItem();
        USBCable.displayItem();
        BluetoothSpeaker.displayItem();
        SmartWatch.displayItem();
        Mouse.displayItem();
        Keyboard.displayItem();
    }

    
    Item selectItemById(int id) 
    {

        if (id == headphones.getId()) 
        {
            return headphones;
        }
        else if (id == charger.getId()) 
        {
            return charger;
        }
        else if (id == powerBank.getId())
        {
            return powerBank;
        }
        else if (id == USBCable.getId()) 
        {
            return USBCable;
        }
        else if (id == BluetoothSpeaker.getId())
        {
            return BluetoothSpeaker;
        }
        else if (id == SmartWatch.getId())
        {
            return SmartWatch;
        }
        else if (id == Mouse.getId())
        {
            return Mouse;
        }
        else if (id == Keyboard.getId())
        {
            return Keyboard;
        }
        else 
        {
            System.out.println(ConsoleColors.RED + "Item ID not found." + ConsoleColors.RESET);
            return null;
        }
        
    }
}

class ToysShelf extends Shelf
{
    private Item ActivityTriangle = new Item("Activity Triangle", 2869, 9,"N/A",150);
    private Item RemoteControlHelicopter = new Item("RC Helicopter", 450, 19,"N/A",50);
    private Item Musicalkeyboard = new Item("Musical Keyboard", 249, 9,"N/A",50);
    private Item WoodenChessBoard = new Item("Wooden Chess Board", 279, 9,"N/A",40);

    // Getters for Each Item

    Item getActivityTriangle()
    {
        return ActivityTriangle;
    }
    Item getRemoteControlHelicopter()
    {
        return RemoteControlHelicopter;
    }
    Item getMusicalKeyboard()
    {
        return Musicalkeyboard;
    }
    Item getWoodenChessBoard()
    {
        return WoodenChessBoard;
    }

    ToysShelf()
    {
        super(" TOYS ");
    }

    void displayItems() 
    {

        System.out.println(ConsoleColors.CYAN + "\n< --- Toys Shelf --- >" + ConsoleColors.RESET);
        System.out.printf("%-5s | %-20s | %-10s | %-10s | %-15s%n", "ID", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("---------------------------------------------------------------------");

        ActivityTriangle.displayItem();
        RemoteControlHelicopter.displayItem();
        Musicalkeyboard.displayItem();
        WoodenChessBoard.displayItem();

    }

    Item selectItemById(int id) 
    {

        if (id == ActivityTriangle.getId()) 
        {
            return ActivityTriangle;
        }
        else if (id == RemoteControlHelicopter.getId()) 
        {
            return RemoteControlHelicopter;
        }
        else if (id == Musicalkeyboard.getId())
        {
            return Musicalkeyboard;
        }
        else if (id == WoodenChessBoard.getId()) 
        {
            return WoodenChessBoard;
        }
        else 
        {
            System.out.println(ConsoleColors.RED + "Item ID not found." + ConsoleColors.RESET);
            return null;
        }
        
    }
}

class Smartshelf
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        GroceryShelf groceryShelf = new GroceryShelf();
        ElectronicsShelf electronicsShelf = new ElectronicsShelf();
        ToysShelf toysShelf = new ToysShelf();
        User user = new User(groceryShelf, electronicsShelf, toysShelf);
        Admin admin = new Admin(user, groceryShelf, electronicsShelf, toysShelf);
        int choice;
        do
        {
            System.out.println(ConsoleColors.CYAN + "=============================" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "   WELCOME TO " + ConsoleColors.GREEN + "SMARTSHELF" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "=============================" + ConsoleColors.RESET);
            System.out.println("1. Admin Login");
            System.out.println("2. User Signup");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

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
                    System.out.println(ConsoleColors.GREEN + ConsoleColors.BOLD + "Thank You for using SmartShelf!" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid Option. Please try again." + ConsoleColors.RESET);
            }
        }while(choice !=4);
    }
}
