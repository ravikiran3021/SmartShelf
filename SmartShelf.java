import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class ConsoleColors 
{
    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String BLUE = "\u001B[34m";
    static String CYAN = "\u001B[36m";
    static String PURPLE = "\u001B[35m";
    static String WHITE = "\u001B[37m";
    static String BOLD = "\u001B[1m";
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
                System.out.println("Returning to main menu.");
                return;
            }
        }
    }

    void otpGenerate() 
    {
        otp = 1000 + (int) (Math.random() * 9000);
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
    }
    
    void viewReport() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- Admin Report ---" + ConsoleColors.RESET);
        System.out.println("Total Revenue: " + AdminRevenue.getRevenue());
    
        // Grocery shelf items report
        System.out.println(ConsoleColors.CYAN + "\nGROCERY SHELF REPORT:" + ConsoleColors.RESET);
        System.out.println("Rice --> Remaining: " + groceryShelf.getRice().getQuantity()  + " || Sold: " + groceryShelf.getRice().getSoldCount());
        System.out.println("Cooking Oil --> Remaining: " + groceryShelf.getOil().getQuantity() + " || Sold: " + groceryShelf.getOil().getSoldCount());
    
        // Electronics shelf items report
        System.out.println(ConsoleColors.CYAN + "\nELECTRONICS SHELF REPORT:" + ConsoleColors.RESET);
        System.out.println("Headphones --> Remaining: " + electronicsShelf.getHeadphones().getQuantity()  + " || Sold: " + electronicsShelf.getHeadphones().getSoldCount());
        System.out.println("Charger --> Remaining: " + electronicsShelf.getCharger().getQuantity()   + " || Sold: " + electronicsShelf.getCharger().getSoldCount());
    
        // Totals
        int totalGrocerySold = groceryShelf.getRice().getSoldCount() + groceryShelf.getOil().getSoldCount();
        int totalElectronicsSold = electronicsShelf.getHeadphones().getSoldCount() + electronicsShelf.getCharger().getSoldCount();
        System.out.println(ConsoleColors.YELLOW + "\nTotal Grocery Items Sold: " + totalGrocerySold + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Total Electronics Items Sold: " + totalElectronicsSold + ConsoleColors.RESET);
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

    double walletBalance = 0.0;
    static  double bankBalance = 20000;

    // Cart items
    Item cartItem1 = null;
    Item cartItem2 = null;
    int qty1 = 0;
    int qty2 = 0;

    GroceryShelf groceryShelf;
    ElectronicsShelf electronicsShelf;

    User(GroceryShelf groceryShelf, ElectronicsShelf electronicsShelf)
    {
        this.groceryShelf = groceryShelf;
        this.electronicsShelf = electronicsShelf;
    }

    Boolean isValidPassword(String password)
    {
        if(savedPassword.length() >= 8)
        {
            if(!savedPassword.matches(".*[!@#$%^&*()].*"))
            {
                System.out.println(ConsoleColors.RED + "Password must contain at least one special character (!@#$%^&*())." + ConsoleColors.RESET);
                return false;
            }
            else if(!savedPassword.matches(".*[0-9].*"))
            {
                System.out.println(ConsoleColors.RED + "Password must contain at least one digit." + ConsoleColors.RESET);
                return false;
            }
            else if(!savedPassword.matches(".*[a-z].*"))
            {
                System.out.println(ConsoleColors.RED + "Password must contain at least one lowercase letter." + ConsoleColors.RESET);
                return false;
            }
            else if(!savedPassword.matches(".*[A-Z].*"))
            {
                System.out.println(ConsoleColors.RED + "Password must contain at least one uppercase letter." + ConsoleColors.RESET);
                return false;
            }
        }
        else
        {
            System.out.println(ConsoleColors.RED + "Password must be at least 8 characters long." + ConsoleColors.RESET);
            return false;
        }
        return true;
    }
    void signup() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- User Signup ---" + ConsoleColors.RESET);
        System.out.print("Enter Your Name : ");
        savedName = sc.next();
        System.out.print("Enter username: ");
        savedUsername = sc.next();
        System.out.println(ConsoleColors.YELLOW + "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#$%^&*())." + ConsoleColors.RESET);
        System.out.print("Enter password: ");
        savedPassword = sc.next();
        if(isValidPassword(savedPassword))
        {
            System.out.print("Enter mobile number: ");
            savedMobile = sc.next();
            long mobileNumber = Long.parseLong(savedMobile); // Convert mobile number to long for validation
            if (mobileNumber > 5999999999l && mobileNumber < 100000000000l) 
            {
                    admin.otpGenerate();
                    System.out.print("Enter OTP : ");
                    int enteredOtp = sc.nextInt();
                    if(enteredOtp==admin.getOtp())
                    {
                        // walletBalance = 20000;
                        System.out.println(ConsoleColors.GREEN + "Account created successfully!" + ConsoleColors.RESET);
                    }
                    else
                    {
                        System.out.println(ConsoleColors.RED + "Invalid OTP !" + ConsoleColors.RESET);
                        //signup();
                        return;
                    }
            }
            else
            {
                System.out.println(ConsoleColors.RED + "Invalid Mobile Number! Please enter a Valid Mobile Number." + ConsoleColors.RESET);
                signup();
                //return;
            }
        }
    }

    void login() 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n--- User Login ---" + ConsoleColors.RESET);
        System.out.print("Enter username: ");
        String uname = sc.next();
        System.out.print("Enter password: ");
        String pass = sc.next();

        if (uname.equals(savedUsername) && pass.equals(savedPassword)) 
        {
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
                System.out.println(ConsoleColors.YELLOW + "Returning to Main Menu...." + ConsoleColors.RESET);
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
                        System.out.println(ConsoleColors.YELLOW + "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#$%^&*())." + ConsoleColors.RESET);
                        System.out.print("Enter your New Password : ");
                        savedPassword = sc.next();
                        if(isValidPassword(savedPassword))
                        {
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
                                return;
                            }
                        }
                    }
                    else
                    {
                        System.out.println(ConsoleColors.RED + "Mobile Number Mismatch...!" + ConsoleColors.RESET);
                        return;
                    }
                }
                else
                {
                    System.out.println(ConsoleColors.RED + "Invalid Mobile Number! Please enter a Valid Mobile Number." + ConsoleColors.RESET);
                    return;
                }
            }
            else if(ch == 'n' || ch == 'N')
            {
                System.out.println(ConsoleColors.YELLOW + "Returning to Main Menu...." + ConsoleColors.RESET);
                return;
            }
            else
            {
                System.out.println(ConsoleColors.RED + "Invalid Choice...!" + ConsoleColors.RESET);
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
        sc.nextLine(); // consume newline
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
                    
                    // // Before adding to cart
                    // if (grocerySelectedItem.getQuantity() < groceryQty) 
                    // {
                    //     System.out.println(ConsoleColors.RED + "Not enough stock available!" + ConsoleColors.RESET);
                    //     break;
                    // }

                    // Check if item already in cart

                    if (cartItem1 != null && cartItem1.getName().equalsIgnoreCase(grocerySelectedItem.getName())) 
                        qty1 += groceryQty; 
                    else if (cartItem2 != null && cartItem2.getName().equalsIgnoreCase(grocerySelectedItem.getName()))
                        qty2 += groceryQty;
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = grocerySelectedItem;
                        qty1 = groceryQty;
                    }   
                    else if (cartItem2 == null)
                    {
                        cartItem2 = grocerySelectedItem;
                        qty2 = groceryQty;
                    } 
                    else 
                        System.out.println(ConsoleColors.YELLOW + "Cart is full!" + ConsoleColors.RESET);
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

                    // if (electronicsSelectedItem.getQuantity() < electronicsQty) 
                    // {
                    //     System.out.println(ConsoleColors.RED + "Not enough stock available!" + ConsoleColors.RESET);
                    //     break;
                    // }

                    // Check if item already in cart

                    if (cartItem1 != null && cartItem1.getName().equalsIgnoreCase(electronicsSelectedItem.getName()))
                        qty1 += electronicsQty;
                    else if (cartItem2 != null && cartItem2.getName().equalsIgnoreCase(electronicsSelectedItem.getName())) 
                        qty2 += electronicsQty;
                    else if (cartItem1 == null) 
                    {
                        cartItem1 = electronicsSelectedItem;
                        qty1 = electronicsQty;
                    } 
                    else if (cartItem2 == null)
                    { 
                        cartItem2 = electronicsSelectedItem;
                        qty2 = electronicsQty;
                    } 
                    else 
                        System.out.println(ConsoleColors.YELLOW + "Cart is full!" + ConsoleColors.RESET);
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
        if (cartItem1 == null && cartItem2 == null) 
        {
            System.out.println(ConsoleColors.YELLOW + "Cart is empty." + ConsoleColors.RESET);
        }
    }

    void generateInvoice() 
    {
        if (cartItem1 == null && cartItem2 == null) 
        {
            System.out.println(ConsoleColors.RED + "Cart is empty. Cannot generate invoice." + ConsoleColors.RESET);
            return;
        }
        Invoice.generate(this, cartItem1, qty1, cartItem2, qty2);
    }

    void clearCart() 
    {
        cartItem1 = null;
        cartItem2 = null;
        qty1 = 0;
        qty2 = 0;
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
                //     System.out.println(ConsoleColors.YELLOW + "Restocking " + restockQty + " more of: " + name + ConsoleColors.RESET);
                //     System.out.println(ConsoleColors.PURPLE + "Cost of restocking (" + restockCost + ") deducted from Admin Funds." + ConsoleColors.RESET);
                //     System.out.println(ConsoleColors.GREEN + "New quantity for " + name + " is now: " + quantity + ConsoleColors.RESET);
            } 
            // else 
            // {
            //     System.out.println(ConsoleColors.RED + "Auto-restock for " + name + " failed. Insufficient Admin Funds." + ConsoleColors.RESET);
            // }
        }
    }

    void displayItem() 
    {
        System.out.printf("%-15s | %-10s | %-10s | %-15s%n", name, price, quantity, expiry);
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
            
        System.out.print("Enter your State Name : ");
        String statename = sc.next();
            
        System.out.print("Enter your City Name : ");
        String cityname = sc.next();
            
        System.out.print("Enter your Landmark Name : ");
        String landmark = sc.next();
            
        System.out.print("Enter your Door Number (or) Flat Number Name : ");
        String doorNo = sc.next();
        
    }
    
    // Generate invoice and process payment
    static void generate(User user, Item item1, int qty1, Item item2, int qty2) 
    {
        System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + "\n< ----- INVOICE ----- >" + ConsoleColors.RESET);

        // Show date & time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
        
        // Apply discount if total > ₹500
        double discount = 0;
        if (total >= 500) 
        {
            discount = total * 0.10;
            total = total - discount;
            System.out.println(ConsoleColors.YELLOW + "Discount Applied: ₹" + discount + ConsoleColors.RESET);
        }
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
                handlingFee = 3;
                paymentMethod = "PhonePe";
                break;
            case 2:
                handlingFee = 2;
                paymentMethod = "GPay";
                break;
            case 3:
                handlingFee = 3;
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
        System.out.println(ConsoleColors.BOLD + "FINAL AMOUNT TO PAY: " + finalAmount + ConsoleColors.RESET);
        System.out.println("------------------------------------");

        System.out.print("Enter Mobile Number to confirm payment: ");
        String mobile = sc.next();
        long mobileNumber = Long.parseLong(mobile);
        if (mobileNumber > 5999999999l && mobileNumber < 100000000000l) 
        {
            if (mobile.equals(user.savedMobile)) 
            {
                user.admin.otpGenerate();
                System.out.print("Enter OTP: ");
                int enteredOtp = sc.nextInt();
                sc.nextLine();
                if (enteredOtp == user.admin.getOtp()) 
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
                        System.out.println(ConsoleColors.GREEN + "Payment Successful via " + paymentMethod + "!" + ConsoleColors.RESET);
                        AdminRevenue.addRevenue(total); // Add original amount to revenue
                        
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
                        System.out.println("We deliver Your Products your Doorstep soon.....!!");
                        System.out.println("Thank you for Shopping with us... " + user.savedName + " !");
                    }
                } 
                else 
                {
                    System.out.println(ConsoleColors.RED + "Invalid OTP! Payment failed." + ConsoleColors.RESET);
                }
            } 
            else 
            {
                System.out.println(ConsoleColors.RED + "Mobile number mismatch! Payment failed." + ConsoleColors.RESET);
            }
        } 
        else 
        {
            System.out.println(ConsoleColors.RED + "Invalid Mobile Number! Payment failed." + ConsoleColors.RESET);
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

    boolean isAccessible() 
    {
        return !isUnderMaintenance;
    }

    abstract void displayItems();

    abstract Item selectItemByName(String itemName);
}

class GroceryShelf extends Shelf 
{
    private Item rice = new Item("Rice", 50, 10,"15-05-2026", 5);
    private Item oil = new Item("Cooking Oil", 120, 13,"09-10-2025", 10);
    private Item sugar = new Item("Sugar", 40, 20,"01-12-2025", 5);
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

    GroceryShelf() 
    {
        super("GROCERY");
    }

    @Override
    void displayItems() 
    {
        if (!isAccessible()) 
        {
            System.out.println("Grocery Shelf is under maintenance.");
            return;
        }
        System.out.println(ConsoleColors.CYAN + "\n< --- Grocery Shelf --- >" + ConsoleColors.RESET);
        System.out.printf("%-15s | %-10s | %-10s | %-15s%n", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("----------------------------------------------------------");
        rice.displayItem();
        oil.displayItem();
        sugar.displayItem();
    }

    @Override
    Item selectItemByName(String name) 
    {
        if (!isAccessible()) 
        {
            System.out.println("Grocery Shelf is under maintenance.");
            return null;
        }
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
        else 
        {
            System.out.println(ConsoleColors.RED + "Item not found." + ConsoleColors.RESET);
            return null;
        }
    }
}

class ElectronicsShelf extends Shelf 
{
    private Item headphones = new Item("Headphones", 500, 9,"30-09-2029",65);
    private Item charger = new Item("Charger", 300, 20,"23-08-2030", 60);
    private Item powerBank = new Item("Power Bank", 800, 15,"15-12-2028", 70);
    
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
    
    ElectronicsShelf() 
    {
        super("ELECTRONICS");
    }

    @Override
    void displayItems() 
    {
        if (!isAccessible()) 
        {
            System.out.println("Electronics Shelf is under maintenance.");
            return;
        }
        System.out.println(ConsoleColors.CYAN + "\n< --- Electronics Shelf --- >" + ConsoleColors.RESET);
        System.out.printf("%-15s | %-10s | %-10s | %-15s%n", "Item Name", "Price", "Quantity", "Expiry Date");
        System.out.println("----------------------------------------------------------");
        headphones.displayItem();
        charger.displayItem();
        powerBank.displayItem();
    }

    @Override
    Item selectItemByName(String name) 
    {
        if (!isAccessible()) 
        {
            System.out.println("Electronics Shelf is under maintenance.");
            return null;
        }

        if (name.equalsIgnoreCase("Headphones")) 
        {
            return headphones;
        }
        else if (name.equalsIgnoreCase("Charger")) 
        {
            return charger;
        }
        else if (name.equalsIgnoreCase("Power Bank"))   
        {
            return powerBank;
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
        do{
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
