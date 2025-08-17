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

    Admin(User user, GroceryShelf groceryShelf, ElectronicsShelf electronicsShelf) 
    {
        this.user = user;
        this.groceryShelf = groceryShelf;
        this.electronicsShelf = electronicsShelf;
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
                System.out.println("1. View Revenue\n2. View Report\n3. Put Shelves in Maintenance\n4. Remove Shelves from Maintenance\n5. Exit");
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
        System.out.printf("%-18s | %-10s | %-10s%n", "Item Name", "Remaining", "Sold");
        System.out.println("-------------------------------------------");
        System.out.printf("%-18s | %-10s | %-10s%n", "Rice", groceryShelf.getRice().getQuantity(), groceryShelf.getRice().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Cooking Oil", groceryShelf.getOil().getQuantity(), groceryShelf.getOil().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Sugar", groceryShelf.getSugar().getQuantity(), groceryShelf.getSugar().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Milk", groceryShelf.getMilk().getQuantity(), groceryShelf.getMilk().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Salt", groceryShelf.getSalt().getQuantity(), groceryShelf.getSalt().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Wheat", groceryShelf.getWheat().getQuantity(), groceryShelf.getWheat().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Biscuits", groceryShelf.getBiscuits().getQuantity(), groceryShelf.getBiscuits().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Bread", groceryShelf.getBread().getQuantity(), groceryShelf.getBread().getSoldCount());
        System.out.println("-------------------------------------------");

        
        // Electronics shelf items report
        System.out.println(ConsoleColors.CYAN + "\nELECTRONICS SHELF REPORT:" + ConsoleColors.RESET);
        System.out.println();
        System.out.printf("%-18s | %-10s | %-10s%n", "Item Name", "Remaining", "Sold");
        System.out.println("-------------------------------------------");
        System.out.printf("%-18s | %-10s | %-10s%n", "Headphones", electronicsShelf.getHeadphones().getQuantity(), electronicsShelf.getHeadphones().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Charger", electronicsShelf.getCharger().getQuantity(), electronicsShelf.getCharger().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "PowerBank", electronicsShelf.getPowerBank().getQuantity(), electronicsShelf.getPowerBank().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "USB Cable", electronicsShelf.getUSBCable().getQuantity(), electronicsShelf.getUSBCable().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Bluetooth Speaker", electronicsShelf.getBluetoothSpeaker().getQuantity(), electronicsShelf.getBluetoothSpeaker().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Smart Watch", electronicsShelf.getSmartWatch().getQuantity(), electronicsShelf.getSmartWatch().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Mouse", electronicsShelf.getMouse().getQuantity(), electronicsShelf.getMouse().getSoldCount());
        System.out.printf("%-18s | %-10s | %-10s%n", "Keyboard", electronicsShelf.getKeyboard().getQuantity(), electronicsShelf.getKeyboard().getSoldCount());
        System.out.println("-------------------------------------------");
    
        // Totals
        System.out.println();
        System.out.println(ConsoleColors.CYAN + "\nTOTAL ITEMS SOLD REPORT:" + ConsoleColors.RESET);
        int totalGrocerySold = groceryShelf.getRice().getSoldCount() + groceryShelf.getOil().getSoldCount() + groceryShelf.getSugar().getSoldCount() + groceryShelf.getMilk().getSoldCount() + groceryShelf.getSalt().getSoldCount() + groceryShelf.getWheat().getSoldCount() + groceryShelf.getBiscuits().getSoldCount() + groceryShelf.getBread().getSoldCount();
        int totalElectronicsSold = electronicsShelf.getHeadphones().getSoldCount() + electronicsShelf.getCharger().getSoldCount() + electronicsShelf.getPowerBank().getSoldCount() + electronicsShelf.getUSBCable().getSoldCount() + electronicsShelf.getBluetoothSpeaker().getSoldCount() + electronicsShelf.getSmartWatch().getSoldCount() + electronicsShelf.getMouse().getSoldCount() + electronicsShelf.getKeyboard().getSoldCount();
        System.out.println(ConsoleColors.YELLOW + "\nTotal Grocery Items Sold: " + totalGrocerySold + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Total Electronics Items Sold: " + totalElectronicsSold + ConsoleColors.RESET);

        // Shelf Maintenance Status
        System.out.println(ConsoleColors.CYAN + "\nSHELF MAINTENANCE STATUS:" + ConsoleColors.RESET);
        System.out.println("Grocery Shelf Under Maintenance: " + (groceryShelf.isUnderMaintenance() ? "Yes" : "No"));
        System.out.println("Electronics Shelf Under Maintenance: " + (electronicsShelf.isUnderMaintenance() ? "Yes" : "No"));
    }


    void putShelvesInMaintenance() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Set Shelf Under Maintenance ---" + ConsoleColors.RESET);
        System.out.print("Enter shelf name (GROCERY / ELECTRONICS): ");
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
        else 
            System.out.println(ConsoleColors.RED + "Invalid shelf name." + ConsoleColors.RESET);
    }

    void removeShelvesFromMaintenance() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Remove Shelf Maintenance ---" + ConsoleColors.RESET);
        System.out.print("Enter shelf name (GROCERY / ELECTRONICS): ");
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
    boolean isLoggedIn = false;

    double walletBalance = 0.0;
    static double bankBalance = 20000;

    // Cart items
    Item cartItem1 = null;
    Item cartItem2 = null;
    Item cartItem3 = null;
    Item cartItem4 = null;
    int qty1 = 0;
    int qty2 = 0;
    int qty3 = 0;
    int qty4 = 0;

    GroceryShelf groceryShelf;
    ElectronicsShelf electronicsShelf;

    User(GroceryShelf groceryShelf, ElectronicsShelf electronicsShelf)
    {
        this.groceryShelf = groceryShelf;
        this.electronicsShelf = electronicsShelf;
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

        int attempts = 3; 
        boolean otpVerified = false;

        for (int i = 1; i <= attempts; i++) 
        {
            System.out.print("Enter OTP: ");
            int enteredOtp = sc.nextInt();

            if (enteredOtp == admin.getOtp()) 
            {
                System.out.println(ConsoleColors.GREEN + "OTP Verified Successfully!" + ConsoleColors.RESET);
                otpVerified = true;
                break;
            } 
            else 
            {
                System.out.println(ConsoleColors.RED + "Invalid OTP! Please try again." + ConsoleColors.RESET);
                int attemptsLeft = attempts - i;
                if (attemptsLeft > 0) 
                {
                    System.out.println(ConsoleColors.YELLOW + attemptsLeft + " attempts left." + ConsoleColors.RESET);
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
            {
                System.out.println(ConsoleColors.GREEN + "Account created successfully!" + ConsoleColors.RESET);
                isLoggedIn = true;
            }
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
        if (!isLoggedIn)
        {
            System.out.println(ConsoleColors.RED + "No User Found ! Create Your Account First....:)" + ConsoleColors.RESET);
            return;
        }
        if (uname.equals(savedUsername) && pass.equals(savedPassword)) 
        {
            isLoggedIn = true; 
            System.out.println(ConsoleColors.GREEN + "Login successful!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "Welcome back, " + savedName + "!" + ConsoleColors.RESET);
            showUserMenu();
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
            System.out.println("6. Remove Items from Cart");
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
        System.out.println(ConsoleColors.GREEN + "Your Bank Balance : " + bankBalance + ConsoleColors.RESET);
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
        System.out.println("3. Back to User Menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); 
        switch (choice) 
        {
            case 1:
                groceryShelf.displayItems();
                System.out.print("Enter item name to purchase : ");
                String groceryItem = sc.nextLine();
                Item grocerySelectedItem = groceryShelf.selectItemByName(groceryItem);
                if (grocerySelectedItem != null) 
                {
                    System.out.print("Enter quantity: ");
                    int groceryQty = sc.nextInt();
                    sc.nextLine();
                    
                    // Check if item already in cart
                    
                    if (cartItem1 != null && cartItem1.getName().equalsIgnoreCase(grocerySelectedItem.getName()))
                    { 
                        qty1 += groceryQty; 
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem2 != null && cartItem2.getName().equalsIgnoreCase(grocerySelectedItem.getName()))
                    {
                        qty2 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 != null && cartItem3.getName().equalsIgnoreCase(grocerySelectedItem.getName()))
                    {
                        qty3 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 != null && cartItem4.getName().equalsIgnoreCase(grocerySelectedItem.getName()))
                    {
                        qty4 += groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = grocerySelectedItem;
                        qty1 = groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    }   
                    else if (cartItem2 == null)
                    {
                        cartItem2 = grocerySelectedItem;
                        qty2 = groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 == null)
                    {
                        cartItem3 = grocerySelectedItem;
                        qty3 = groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 == null)
                    {
                        cartItem4 = grocerySelectedItem;
                        qty4 = groceryQty;
                        System.out.println(ConsoleColors.GREEN + grocerySelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    }
                    else 
                        System.out.println(ConsoleColors.YELLOW + "OOPS! Cart is full!" + ConsoleColors.RESET);
                }
                break;
            case 2:
                electronicsShelf.displayItems();
                System.out.print("Enter item name to purchase : ");
                String electronicsItem = sc.nextLine();
                Item electronicsSelectedItem = electronicsShelf.selectItemByName(electronicsItem);
                if (electronicsSelectedItem != null) 
                {
                    System.out.print("Enter quantity: ");
                    int electronicsQty = sc.nextInt();
                    sc.nextLine();

                    // Check if item already in cart

                    if (cartItem1 != null && cartItem1.getName().equalsIgnoreCase(electronicsSelectedItem.getName()))
                    {
                        qty1 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem2 != null && cartItem2.getName().equalsIgnoreCase(electronicsSelectedItem.getName())) 
                    {
                        qty2 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem3 != null && cartItem3.getName().equalsIgnoreCase(electronicsSelectedItem.getName()))
                    {
                        qty3 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 != null && cartItem4.getName().equalsIgnoreCase(electronicsSelectedItem.getName()))
                    {
                        qty4 += electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " quantity updated in cart." + ConsoleColors.RESET);
                    }
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = electronicsSelectedItem;
                        qty1 = electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    } 
                    else if (cartItem2 == null)
                    { 
                        cartItem2 = electronicsSelectedItem;
                        qty2 = electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    } 
                    else if (cartItem3 == null)
                    {
                        cartItem3 = electronicsSelectedItem;
                        qty3 = electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    }
                    else if (cartItem4 == null)
                    {
                        cartItem4 = electronicsSelectedItem;
                        qty4 = electronicsQty;
                        System.out.println(ConsoleColors.GREEN + electronicsSelectedItem.getName() + " added to cart Successfully." + ConsoleColors.RESET);
                    }
                    else 
                        System.out.println(ConsoleColors.YELLOW + "OOPS! Cart is full!" + ConsoleColors.RESET);
                }
                break;
            case 3:
                return;
            default:
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
    }

    void viewCart() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- View Cart ---" + ConsoleColors.RESET);
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
        if (cartItem1 == null && cartItem2 == null && cartItem3 == null && cartItem4 == null) 
        {
            System.out.println(ConsoleColors.YELLOW + "Cart is empty." + ConsoleColors.RESET);
        }
    }

    void generateInvoice() 
    {
        if (cartItem1 == null && cartItem2 == null && cartItem3 == null && cartItem4 == null) 
        {
            System.out.println(ConsoleColors.RED + "Cart is empty. Cannot generate invoice." + ConsoleColors.RESET);
            return;
        }
        Invoice.generate(this, cartItem1, qty1, cartItem2, qty2, cartItem3, qty3, cartItem4, qty4);
    }

   void removeItemFromCart()
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Remove Items from Cart ---" + ConsoleColors.RESET);
        if (cartItem1 == null && cartItem2 == null && cartItem3 == null && cartItem4 == null)
        {
            System.out.println(ConsoleColors.YELLOW + "Cart is empty. Nothing to remove." + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to User Menu...." + ConsoleColors.RESET);
            return;
        }

        // Display cart items
        System.out.println(ConsoleColors.YELLOW + "Items in Cart:" + ConsoleColors.RESET);
        if (cartItem1 != null)
            System.out.println("1. " + cartItem1.getName() + " x" + qty1);
        if (cartItem2 != null)
            System.out.println("2. " + cartItem2.getName() + " x" + qty2);
        if (cartItem3 != null)
            System.out.println("3. " + cartItem3.getName() + " x" + qty3);
        if (cartItem4 != null)
            System.out.println("4. " + cartItem4.getName() + " x" + qty4);
        System.out.println();
        System.out.println(ConsoleColors.ITALIC + "------ Options -----" + ConsoleColors.RESET);
        System.out.println("1. Remove Item 1\n 2. Remove Item 2\n 3. Remove Item 3\n 4. Remove Item 4\n 5. Clear Cart\n 6. Back to User Menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1:
                if (cartItem1 != null)
                {
                    System.out.println("Do you want to reduce the quantity of Item 1 in cart?");
                    System.out.print("1. Update Quantity\n 2. Remove Item\n");
                    int choice1 = sc.nextInt();
                    switch(choice1)
                    {
                        case 1:
                            System.out.print("Enter new quantity for Item 1: ");
                            int newQty = sc.nextInt();
                            if(newQty > 0 && newQty <= cartItem1.getQuantity())
                            {
                                qty1 = newQty;
                                System.out.println(ConsoleColors.GREEN + "Quantity of " + cartItem1.getName() + " updated to " + qty1 + " in cart." + ConsoleColors.RESET);
                            }
                            else if(newQty <= 0)
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity must be greater than 0." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else if(newQty > cartItem1.getQuantity())
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity cannot be greater than available stock." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            break;
                        case 2:
                            System.out.print("Are you sure you want to remove " + cartItem1.getName() + " from cart? (y/n)");
                            char confirm = sc.next().charAt(0);
                            String removedname1 = cartItem1.getName();
                            if (confirm == 'y' || confirm == 'Y')
                            {
                                cartItem1 = null;
                                qty1 = 0;
                                System.out.println(ConsoleColors.GREEN + "Removed " + removedname1 + " from cart Successfully." + ConsoleColors.RESET);
                            }
                            else if (confirm == 'n' || confirm == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + "Item not removed from cart." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                                return;
                            }
                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                            return;
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "No Item 1 in cart to remove." + ConsoleColors.RESET);
                    showUserMenu();
                }
                break;
            case 2:
                if (cartItem2 != null)
                {
                    System.out.println("Do you want to reduce the quantity of Item 2 in cart?");
                    System.out.print("1. Update Quantity\n 2. Remove Item\n");
                    int choice2 = sc.nextInt();
                    switch(choice2)
                    {
                        case 1:
                            System.out.print("Enter new quantity for Item 2: ");
                            int newQty2 = sc.nextInt();
                            if(newQty2 > 0 && newQty2 <= cartItem2.getQuantity())
                            {
                                qty2 = newQty2;
                                System.out.println(ConsoleColors.GREEN + "Quantity of " + cartItem2.getName() + " updated to " + qty2 + " in cart." + ConsoleColors.RESET);
                            }
                            else if(newQty2 <= 0)
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity must be greater than 0." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else if(newQty2 > cartItem2.getQuantity())
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity cannot be greater than available stock." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            break;
                        case 2:
                            System.out.print("Are you sure you want to remove " + cartItem2.getName() + " from cart? (y/n)");
                            char confirm2 = sc.next().charAt(0);
                            String removedname2 = cartItem2.getName();
                            if (confirm2 == 'y' || confirm2 == 'Y')
                            {
                                cartItem2 = null;
                                qty2 = 0;
                                System.out.println(ConsoleColors.GREEN + "Removed " + removedname2 + " from cart Successfully." + ConsoleColors.RESET);
                            }
                            else if (confirm2 == 'n' || confirm2 == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + "Item not removed from cart." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                                return;
                            }
                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                            return;
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "No Item 2 in cart to remove." + ConsoleColors.RESET);
                    showUserMenu();
                }
                break;
            case 3:
                if (cartItem3 != null)
                {
                    System.out.println("Do you want to reduce the quantity of Item 3 in cart?");
                    System.out.print("1. Update Quantity\n 2. Remove Item\n");
                    int choice3 = sc.nextInt();
                    switch(choice3)
                    {
                        case 1:
                            System.out.print("Enter new quantity for Item 3: ");
                            int newQty3 = sc.nextInt();
                            if(newQty3 > 0 && newQty3 <= cartItem3.getQuantity())
                            {
                                qty3 = newQty3;
                                System.out.println(ConsoleColors.GREEN + "Quantity of " + cartItem3.getName() + " updated to " + qty3 + " in cart." + ConsoleColors.RESET);
                            }
                            else if(newQty3 <= 0)
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity must be greater than 0." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else if(newQty3 > cartItem3.getQuantity())
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity cannot be greater than available stock." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            break;
                        case 2:
                            System.out.print("Are you sure you want to remove " + cartItem3.getName() + " from cart? (y/n)");
                            char confirm3 = sc.next().charAt(0);
                            String removedname3 = cartItem3.getName();
                            if (confirm3 == 'y' || confirm3 == 'Y')
                            {
                                cartItem3 = null;
                                qty3 = 0;
                                System.out.println(ConsoleColors.GREEN + "Removed " + removedname3 + " from cart Successfully." + ConsoleColors.RESET);  
                            }
                            else if (confirm3 == 'n' || confirm3 == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + "Item not removed from cart." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                                return;
                            }
                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                            return;
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "No Item 3 in cart to remove." + ConsoleColors.RESET);
                    showUserMenu();
                }
                break;
            case 4:
                if (cartItem4 != null)
                {
                    System.out.println("Do you want to reduce the quantity of Item 4 in cart?");
                    System.out.print("1. Update Quantity\n 2. Remove Item\n");
                    int choice4 = sc.nextInt();
                    switch(choice4)
                    {
                        case 1:
                            System.out.print("Enter new quantity for Item 4: ");
                            int newQty4 = sc.nextInt();
                            if(newQty4 > 0 && newQty4 <= cartItem4.getQuantity())
                            {
                                qty4 = newQty4;
                                System.out.println(ConsoleColors.GREEN + "Quantity of " + cartItem4.getName() + " updated to " + qty4 + " in cart." + ConsoleColors.RESET);
                            }
                            else if(newQty4 <= 0)
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity must be greater than 0." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else if(newQty4 > cartItem4.getQuantity())
                            {
                                System.out.println(ConsoleColors.RED + "Invalid quantity. Quantity cannot be greater than available stock." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            break;
                        case 2:
                            System.out.print("Are you sure you want to remove " + cartItem4.getName() + " from cart? (y/n)");
                            char confirm4 = sc.next().charAt(0);
                            String removedname4 = cartItem4.getName();
                            if (confirm4 == 'y' || confirm4 == 'Y')
                            {
                                cartItem4 = null;
                                qty4 = 0;
                                System.out.println(ConsoleColors.GREEN + "Removed " + removedname4 + " from cart Successfully." + ConsoleColors.RESET);
                            }
                            else if (confirm4 == 'n' || confirm4 == 'N')
                            {
                                System.out.println(ConsoleColors.YELLOW + "Item not removed from cart." + ConsoleColors.RESET);
                                showUserMenu();
                            }
                            else
                            {
                                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                                return;
                            }
                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                            return;
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "No Item 4 in cart to remove." + ConsoleColors.RESET);
                    showUserMenu();
                }
                break;
            case 5:
                clearCart();
                System.out.println(ConsoleColors.GREEN + "Cart cleared successfully." + ConsoleColors.RESET);
                break;
            case 6:
                System.out.println(ConsoleColors.YELLOW + ConsoleColors.DIM + "Returning to User Menu...." + ConsoleColors.RESET);
                return;
            default:
                System.out.println(ConsoleColors.RED + "Invalid choice. Returning to User Menu...." + ConsoleColors.RESET);
                return;
        }
    }
    
    void clearCart() 
    {
        cartItem1 = null;
        cartItem2 = null;
        cartItem3 = null;
        cartItem4 = null;
        qty1 = 0;
        qty2 = 0;
        qty3 = 0;
        qty4 = 0;
    }
}

class Item 
{
    private String name;
    private double price;
    private int quantity;
    private String expiry;
    private int restockprice;

    // Static counters for all items
    private static int totalItemsSold = 0;
    private static int totalItemsInStock = 0;
    
    private int soldCount = 0;

    // Constructor
    Item(String name, double price, int quantity, String expiry, int restockprice) 
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        totalItemsInStock += quantity; // Add to total stock when item is created
        this.expiry = expiry;
        this.restockprice = restockprice;
    }

    // Getters
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

    // Reduce quantity when purchased
     void reduceQuantity(int qty) 
    {
        if (quantity >= qty) 
        {
            quantity -= qty;
            soldCount += qty; // track sold count per item
            totalItemsSold += qty;
            totalItemsInStock -= qty;
        }
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
                totalItemsInStock += restockQty;
            } 
        }
    }

    void displayItem() 
    {
        System.out.printf("%-20s | %-10s | %-10s | %-15s%n", name, price, quantity, expiry);
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

}

class Invoice 
{    
    static Scanner sc = new Scanner(System.in);
    
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
    
    // Generate invoice and process payment
    static void generate(User user, Item item1, int qty1, Item item2, int qty2, Item item3, int qty3, Item item4, int qty4) 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n< ----- INVOICE ----- >" + ConsoleColors.RESET);
        System.out.println();
        System.out.println(ConsoleColors.CYAN + ConsoleColors.ITALIC + "Free Shipping Ends Tonight! Don't Miss Out!" + ConsoleColors.RESET);
        System.out.println();
        // Show date & time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Date : " + now.format(format));
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
        
        
        // Apply discount if total > ₹500
        double discount = 0;
        if (total >= 500) 
        {
            discount = total * 0.10;
            System.out.println(ConsoleColors.GREEN + ConsoleColors.ITALIC + "Hurray! You got 10% discount!" + ConsoleColors.RESET);
            total = total - discount;
            System.out.println(ConsoleColors.YELLOW + "Discount Applied: " + discount + ConsoleColors.RESET);
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
                break;
            case 2:
                handlingFee = 2.5;
                paymentMethod = "GPay";
                break;
            case 3:
                handlingFee = 3.9;
                paymentMethod = "Paytm";
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

        System.out.print("Enter Mobile Number to confirm payment: ");
        String mobile = sc.next();
        long mobileNumber = Long.parseLong(mobile);
        if (mobileNumber > 5999999999l && mobileNumber < 100000000000l) 
        {
            if (mobile.equals(user.savedMobile)) 
            {
                user.admin.otpGenerate();
                if(user.otpValidation())
                {
                    boolean paymentSuccess = false;
                    if (paymentMethod.equals("Wallet")) 
                    {
                        if (user.walletBalance >= finalAmount) 
                        {
                            user.walletBalance -= finalAmount;
                            paymentSuccess = true;
                        } 
                        else 
                        {
                            System.out.println(ConsoleColors.RED + "Insufficient Wallet Balance!" + ConsoleColors.RESET);
                        }
                    } 
                    else 
                    {
                        if (User.bankBalance >= finalAmount) 
                        {
                            User.bankBalance -= finalAmount;
                            paymentSuccess = true;
                        } 
                        else 
                        {
                            System.out.println(ConsoleColors.RED + "Insufficient Bank Balance!" + ConsoleColors.RESET);
                        }
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
                            item1.reduceQuantity(qty1);
                            item1.autoRestock();
                        }
                        if (item2 != null) 
                        {
                            item2.reduceQuantity(qty2);
                            item2.autoRestock();
                        }
                        
                        user.clearCart();
                        System.out.println("Enter your Address to deliver your purchased Products ");
                        Invoice.takeAddress();
                        System.out.println(ConsoleColors.GREEN + "Your Order has been placed successfully!" + ConsoleColors.RESET);
                        System.out.println("Do you want to view your Invoice (y/n) ? ");
                        char ch = sc.next().charAt(0);
                        if (ch == 'y' || ch == 'Y')
                        {
                            System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n< ----- INVOICE ----- >" + ConsoleColors.RESET);
                            System.out.println("Date           : " + now.format(format));
                            System.out.println("User           : " + user.savedName);
                            System.out.println("Payment Method : " + paymentMethod);
                            System.out.println("Total Amount   : " + total);
                            System.out.println("Platform Fee   : " + handlingFee);
                            System.out.println("Discount       : " + discount);
                            System.out.println("---------------------------------------");
                            System.out.printf(ConsoleColors.BOLD + "FINAL AMOUNT   : %.2f" + ConsoleColors.RESET, finalAmount);
                            System.out.println();
                            System.out.println("---------------------------------------");
                        }
                        System.out.println(ConsoleColors.GREEN + "Your Products will be delivered to your Address soon...!" + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.CYAN + "Thank you for shopping with us, " + user.savedName + "!" + ConsoleColors.RESET);
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "Payment Failed!" + ConsoleColors.RESET);
                } 
            } 
            else 
            {
                System.out.println(ConsoleColors.RED + "Mobile number mismatch! Payment failed." + ConsoleColors.RESET);
                user.showUserMenu();
            }
        } 
        else 
        {
            System.out.println(ConsoleColors.RED + "Invalid Mobile Number! Payment failed." + ConsoleColors.RESET);
            user.showUserMenu();
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

    abstract Item selectItemByName(String itemName);
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
        System.out.printf("%-20s | %-10s | %-10s | %-15s%n", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("----------------------------------------------------------");
        rice.displayItem();
        oil.displayItem();
        sugar.displayItem();
        Milk.displayItem();
        Salt.displayItem();
        Wheat.displayItem();
        Biscuits.displayItem();
        Bread.displayItem();
    }

    Item selectItemByName(String name) 
    {

        if (name.equalsIgnoreCase("Rice")) 
        {
            return rice;
        } 
        else if (name.equalsIgnoreCase("Cooking Oil")) 
        {
            return oil;
        } 
        else if (name.equalsIgnoreCase("Sugar")) 
        {
            return sugar;
        }
        else if (name.equalsIgnoreCase("Milk")) 
        {
            return Milk;
        }
        else if (name.equalsIgnoreCase("Salt")) 
        {
            return Salt;
        }
        else if (name.equalsIgnoreCase("Wheat"))
        {
            return Wheat;
        }
        else if (name.equalsIgnoreCase("Biscuits")) 
        {
            return Biscuits;
        }
        else if (name.equalsIgnoreCase("Bread"))
        {
            return Bread;
        }
        else 
        {
            System.out.println(ConsoleColors.RED + "Item not found." + ConsoleColors.RESET);
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
        System.out.printf("%-20s | %-10s | %-10s | %-15s%n", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("----------------------------------------------------------");
        headphones.displayItem();
        charger.displayItem();
        powerBank.displayItem();
        USBCable.displayItem();
        BluetoothSpeaker.displayItem();
        SmartWatch.displayItem();
        Mouse.displayItem();
        Keyboard.displayItem();
    }

    
    Item selectItemByName(String name) 
    {
        if (name.equalsIgnoreCase("Headphones")) 
        {
            return headphones;
        }
        else if (name.equalsIgnoreCase("Charger")) 
        {
            return charger;
        }
        else if (name.equalsIgnoreCase("PowerBank"))   
        {
            return powerBank;
        }
        else if (name.equalsIgnoreCase("USB Cable")) 
        {
            return USBCable;
        }
        else if (name.equalsIgnoreCase("Bluetooth Speaker"))
        {
            return BluetoothSpeaker;
        }
        else if (name.equalsIgnoreCase("Smart Watch"))
        {
            return SmartWatch;
        }
        else if (name.equalsIgnoreCase("Mouse"))
        {
            return Mouse;
        }
        else if (name.equalsIgnoreCase("Keyboard"))
        {
            return Keyboard;
        }
        else 
        {
            System.out.println(ConsoleColors.RED + "Item not found." + ConsoleColors.RESET);
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
        User user = new User(groceryShelf, electronicsShelf);
        Admin admin = new Admin(user, groceryShelf, electronicsShelf);
        int choice;
        do
        {
            System.out.println(ConsoleColors.BLUE + ConsoleColors.BOLD + "\nWelcome to SmartShelf!" + ConsoleColors.RESET);
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
                    System.out.println(ConsoleColors.GREEN + ConsoleColors.BOLD + "Thank You for using SmartShelf!" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid Option. Please try again." + ConsoleColors.RESET);
            }
        }while(choice !=4);
    }
}
