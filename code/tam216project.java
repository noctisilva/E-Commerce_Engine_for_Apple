import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
/**
 *
 * @author username
 */
public class Cse241projRound2 {
    public static void main (String[] arg)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        Connection con = null;
        Statement s = null;
        String q = "";
        ResultSet result = null;
        Scanner krb = new Scanner(System.in);
        Boolean notDone = true;
        String user = "";
        String pw = "";
        while (notDone) {
            try {
                System.out.println ("Hello!\n");
                System.out.print ("Enter your user id for Oracle: ");
                user= krb.nextLine();
                System.out.print ("Enter password for user " + user + ": ");
                pw = krb.nextLine();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con= //insert your own connection url
                s=con.createStatement();
                notDone = false;
            } catch(Exception e){
                System.out.println ("Login Error: ");
                System.out.println (e.getMessage());
            }
        }
//        Class.forName ("oracle.jdbc.driver.OracleDriver");
//        Connection con = DriverManager.getConnection("connection url:cse241","username","password");   
        welcomeUI(con);
    }
    
    public static void welcomeUI(Connection con)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        System.out.println("*****************************************************\n");
        System.out.println("          Welcome to the Apple Store!                  ");
        System.out.println("*****************************************************\n");  
        System.out.println("Log In | Sign up | Shop | Exit");
        System.out.println("*If you don't have an account, enter shop.\n");
        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();
        input1 = input1.replace(" ","").toLowerCase();
        System.out.println(input1);
        switch (input1) {
            case "login":
                preLogInScreen(con);
                break;
            case "signup":
                signUpScreen(con);
                break;
            case "shop":
                signUpScreen();
                break;
            case "exit":
                quit();
                break;
            default:
                //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Please enter a valid option.");
                welcomeUI(con);
                break;
        }
    }

    public static void signUpScreen(Connection con)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("      Hello! Welcome to Apple!\n");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("You can create an account by filling in the following blanks:.\n");
        System.out.print("First Name: ");
        String fname = input.nextLine();
        System.out.print("Last Name: ");
        String lname = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Are you a customer, employee, or manager?\n");
        String i_type = input.nextLine();
        i_type.toLowerCase();
        if(!i_type.equals("customer") && !i_type.equals("employee")&& !i_type.equals("manager")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            signUpScreen(con);
        }
        System.out.println("Hello "+ fname + " " + lname + ".");
        System.out.println("Please wait while we create your profile.");
        for(int i = 1; i < 4; i++){
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("Congratulations "+ fname + " " + lname + "! You are now an account holder for Apple!");
        int max = 10;
        int min = 5;
        int cusid= min + (int)(Math.random() * ((100000) + 1)); 
        int cartId= min + (int)(Math.random() * ((100000) + 1)); 
        int billingId= min + (int)(Math.random() * ((100000) + 1));
        if(i_type.equals("customer")){
            String cusidSQL = "INSERT INTO customer (c_id,first_name,last_name,c_email, i_type, cart) VALUES (" +  cusid + ",'" + fname + "','" + lname + "','"+ email +"','" + i_type +"',"+0+")";
            String createCart = "insert into cart\n" +
"(cart_id,c_Mac,c_iphone,c_iPad,c_iPod,c_tot,c_totAmt,ccid)\n" +
"values\n" +
"(" +cartId+",0,0,0,0,0,0," + cusid + ")";
            String createBilling = "insert into billing (bi_id,bc_id,b_date,b_amt) values ("+billingId+","+cusid+",0,0)";
            Statement temp = con.createStatement();
            Statement temp2 = con.createStatement();
            Statement temp3 = con.createStatement();
            ResultSet result = temp.executeQuery(cusidSQL);
            ResultSet result2 = temp2.executeQuery(createCart);
            ResultSet result3 = temp3.executeQuery(createBilling);
            System.out.println("Your customer ID is :" + cusid+".");
            System.out.println("Your customer email is :" + email+".");
            System.out.println("You will need your ID # to log into your Apple profile.");
            System.out.println("You will be redirected to log in, in a moment.");
            Thread.sleep(1000);
            welcomeUI(con);
        }else if(i_type.equals("employee")){
            String cusidSQL = "INSERT INTO employee (e_id,se_id,first_name,last_name,e_email,position,i_type) VALUES (" +  cusid+","+ "50343" +",'"+fname + "','" + lname + "','" + email + "','cashier','" + i_type +"')";
            Statement temp = con.createStatement();
            ResultSet result = temp.executeQuery(cusidSQL);
            System.out.println("Your employee ID is :" + cusid+".");
            System.out.println("Your employee email is :" + email+".");
            System.out.println("You will need your ID # to log into your Apple profile.");
            System.out.println("You will be redirected to log in, in a moment.");
            Thread.sleep(1000);
            welcomeUI(con);
        }else if(i_type.equals("manager")){
            String cusidSQL = "INSERT INTO manager (m_id,sm_id,mfname,mlname,m_email,i_type) VALUES (" +  cusid+","+ "50343" +",'"+fname + "','" + lname + "','" + email + "','" + i_type +"')";
            Statement temp = con.createStatement();
            ResultSet result = temp.executeQuery(cusidSQL);
            System.out.println("Your Manager ID is :" + cusid+".");
            System.out.println("Your Manager email is :" + email+".");
            System.out.println("You will need your ID # to log into your Apple profile.");
            System.out.println("You will be redirected to log in, in a moment.");
            Thread.sleep(1000);
            welcomeUI(con);
        }
    } 

    public static void quit() throws InterruptedException{
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Thank you for using our services.");
        Thread.sleep(1000);
        System.out.println("Good Bye.");
        System.exit(0);
    }

    public static void preLogInScreen(Connection con)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Are you a customer, employee, or manager?");
        String i_type = input.nextLine();
        i_type.toLowerCase();
        if(i_type.equals("customer")){
            clogInScreen(con);
        }else if(i_type.equals("employee")){
            elogInScreen(con);
        }else if(i_type.equals("manager")){
            mlogInScreen(con);
        }else{
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            preLogInScreen(con);
        }
    }

    public static void clogInScreen(Connection con)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your ID number.");
        String cusid2 = "";
        cusid2 = input.nextLine();
        System.out.println("Please enter your email.");
        String c_email = "";
        c_email = input.nextLine();
        int cusid = Integer.parseInt(cusid2);
//        while (!input.hasNextInt()){
//            System.out.println("This account does not exist or is a wrong input.\nPlease try again.");
//            clogInScreen(con);
//        }
        if(preMenu(con,cusid,"c_id","customer") == true && preMenu2(con,c_email,cusid,"c_email","customer","c_id") == true){
            con.close();
            welcomeMenua(cusid);
        }else{
            System.out.println("This account does not exist or is a wrong input.\nPlease try again.");
            clogInScreen(con);            
        }
    }

    public static void elogInScreen(Connection con)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your ID number.");
        String empid2 = "";
        empid2 = input.nextLine();
        int empid = Integer.parseInt(empid2);
        System.out.println("Please enter your email.");
        String e_email = "";
        e_email = input.nextLine();
        
//        while (!input.hasNextInt()){
//            System.out.println("This account does not exist or is a wrong input.\nPlease try again.");
//            clogInScreen(con);
//        }
        if(preMenu(con,empid,"e_id","employee") == true && preMenu2(con,e_email,empid,"e_email","employee","e_id") == true){
            con.close();
            welcomeMenub(con,empid);
        }else{
            System.out.println("This account does not exist or is a wrong input.\nPlease try again.");
            elogInScreen(con);            
        }
    }

    public static void mlogInScreen(Connection con)throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your ID number.");
        String manid2 = "";
        manid2 = input.nextLine();
        int manid = Integer.parseInt(manid2);
        System.out.println("Please enter your email.");
        String m_email = "";
        m_email = input.nextLine();
//        while (!input.hasNextInt()){
//            System.out.println("This account does not exist or is a wrong input.\nPlease try again.");
//            clogInScreen(con);
//        }
        if(preMenu(con,manid,"m_id","manager") == true && preMenu2(con,m_email,manid,"m_email","manager","m_id") == true){
            con.close();
            welcomeMenuc(con,manid);
        }else{
            System.out.println("This account does not exist or is a wrong input.\nPlease try again.");
            mlogInScreen(con);            
        }
    }

    public static String getNameEmp(Connection con, String accountInput, int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        String getNameSql = "Select FIRST_NAME from employee where e_id = " +empid;
        String cusName = " ";
        Statement temp = con.createStatement(); 
        ResultSet result = temp.executeQuery(getNameSql);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                for(int i = 0; i <columnsNumber; i++){
                    cusName = result.getString(i+1); 
                }
            } while (result.next());
        }
        return cusName;
    }
    
    public static String getName(Connection con, String accountInput, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        String getNameSql = "Select first_name from customer where c_id = " + cusid;
        String cusName = " ";
        Statement temp = con.createStatement(); 
        ResultSet result = temp.executeQuery(getNameSql);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                for(int i = 0; i <columnsNumber; i++){
                    cusName = result.getString(i+1); 
                }
            } while (result.next());
        }
        return cusName;
    }
    
    public static String getAmt(Connection con, String type) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        String getNameSql = "Select unique p_price from product where p_type = '" + type +"'";
        String typeIn = "";
        String cusName = " ";
        Statement temp = con.createStatement(); 
        ResultSet result = temp.executeQuery(getNameSql);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result from getAmt().");
        else {
            do {
                for(int i = 0; i <columnsNumber; i++){
                    cusName = result.getString(i+1); 
                    //System.out.println("asdfasfasfasfasdfasdf"+cusName);
                }
            } while (result.next());
        }
        return cusName;
    }
    
    public static String getAmtTot(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        String getNameSql = "Select c_totAmt from cart where ccid = "+ cusid;
        String typeIn = "";
        String cusName = " ";
        Statement temp = con.createStatement(); 
        ResultSet result = temp.executeQuery(getNameSql);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                for(int i = 0; i <columnsNumber; i++){
                    cusName = result.getString(i+1); 
                    //System.out.println("asdfasfasfasfasdfasdf"+cusName);
                }
            } while (result.next());
        }
        return cusName;
    }
    
    public static String getId(Connection con, int id) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException {
        String getNameSql = "select a.inv_id from inventory a, manager b, store c where b.m_id= "+id+" and b.sm_id = c.st_id and c.st_id = a.ist_id";
        String typeIn = "";
        String cusName = " ";
        Statement temp = con.createStatement(); 
        ResultSet result = temp.executeQuery(getNameSql);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                for(int i = 0; i <columnsNumber; i++){
                    cusName = result.getString(i+1); 
                    //System.out.println("asdfasfasfasfasdfasdf"+cusName);
                }
            } while (result.next());
        }
        return cusName;
    }
    
    public static void welcomeMenub(Connection con2, int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        con2.close();
        Connection con = DriverManager.getConnection("connection url:cse241","username","password");
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Scanner input = new Scanner(System.in);
        System.out.println("*****************************************************\n");
        System.out.println("               Welcome back to Apple!                  ");
        System.out.println("*****************************************************\n");
        System.out.println ("Please choose from the following list of options.");
        System.out.println ("Enter one of the following options. Enter quit to log out.");
        System.out.println("Inventory  | Transactions | Store | Log Out");
        //System.out.println("Inventory  | Transactions | Store | Shop | Log Out");
        System.out.println("**Shop is with the employee discount of 25%");
        String input1;
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("inventory") && !input1.equals("transactions") && !input1.equals("transaction") && !input1.equals("store") && !input1.equals("stock")&& !input1.equals("shop") && !input1.equals("logout")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            welcomeMenub(con, empid);
        }else if(input1.equals("inventory")){
            viewInv(con,empid);
        }else if(input1.equals("transaction") || input1.equals("transactions") ){
            viewTransactions(con,empid);
        }else if(input1.equals("store")){
            empStore(con,empid);
        }else if(input1.equals("logout")){
            quit();
        }
    }

    public static void welcomeMenuc(Connection con2, int manid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        con2.close();
        Connection con = DriverManager.getConnection("connection url:cse241","username","password");
        Scanner input = new Scanner(System.in);
        System.out.println("*****************************************************\n");
        System.out.println("          Welcome back to Apple,  Manager!             ");
        System.out.println("*****************************************************\n");
        System.out.println ("Please choose from the following list of options.");
        System.out.println ("Enter one of the following options. Enter quit to log out.");
        System.out.println("Inventory  | Transactions | Store | Log Out");
        System.out.println("**Managers get a discount of 35%");
        String input1;
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("inventory") && !input1.equals("transactions") && !input1.equals("transaction") && !input1.equals("store") && !input1.equals("stock")&& !input1.equals("shop") && !input1.equals("logout") ){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            welcomeMenuc(con, manid);
        }else if(input1.equals("inventory")){
            viewInvM(con,manid);
        }else if(input1.equals("transaction")|| input1.equals("transactions") ){
            viewTransactionsM(con,manid);
        }else if(input1.equals("store") || input1.equals("stores") ){
            viewInvStoreInfo(con,manid);
        }else if(input1.equals("logout")){
            quit();
        }
        con.close();
    }
    
    public static void signUpScreen() throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        Connection con = DriverManager.getConnection("connection url:cse241","username","password");
        String accountInput = " ";
        String cusName = " ";
        int max = 10;
        int min = 5;
        int cusid= min + (int)(Math.random() * ((100000) + 1)); 
        int cartId= min + (int)(Math.random() * ((100000) + 1)); 
        int billingId= min + (int)(Math.random() * ((100000) + 1));
        System.out.println("\n");
        System.out.println("*****************************************************\n");
        System.out.println("            Welcome to Apple, Guest!                 \n");
        System.out.println("*****************************************************\n");
        System.out.println ("Please choose from the following list of options.");
        System.out.println ("Enter one of the following options. Enter quit to log out.");
        System.out.println("Shop |  Cart  | Check Out | Store | Sign Up | Log Out");
        String cusidSQL = "INSERT INTO customer (c_id,first_name,last_name,c_email, i_type, cart) VALUES (" +  cusid + ",'Guest','Guest','no email','customer',"+0+")";
        String createCart = "insert into cart\n" +
"(cart_id,c_Mac,c_iphone,c_iPad,c_iPod,c_tot,c_totAmt,ccid)\n" +
"values\n" +
"(" +cartId+",0,0,0,0,0,0," + cusid + ")";
        String createBilling = "insert into billing (bi_id,bc_id,b_date,b_amt) values ("+billingId+","+cusid+",0,0)";
        Statement temp = con.createStatement();
        Statement temp2 = con.createStatement();
        Statement temp3 = con.createStatement();
        ResultSet result = temp.executeQuery(cusidSQL);
        ResultSet result2 = temp2.executeQuery(createCart);
        ResultSet result3 = temp3.executeQuery(createBilling);
        accountInput =input.nextLine();
        accountInput = accountInput.replaceAll("\\s+", "").toLowerCase();
        welcomeMenu2(con,accountInput,cusid);
        temp2.close();
        con.close();
    }

    public static void empStoreM(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        Statement temp = con.createStatement();
        String head = "";
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("This is your store information.");
        String query = "select a.st_address, a.st_city, a.st_zip, a.st_country, a.st_num, a.st_email, a.st_hours from store a and manager b where b.m_id = " + empid + "and b.sm_id = a.st_id";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()){
            System.out.println ("No such id exists. Try again.");
            viewTransactions(con,empid);
        }
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do now?");
        System.out.println("menu | quit");
        String input1;
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            empStoreM(con,empid);
        }else if(input1.equals("menu")){
            welcomeMenuc(con,empid);
        }else if(input1.equals("quit")){
            quit();
        }
    }

    public static void viewTransactionsM(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        String cusida;
        Statement temp = con.createStatement();
        String head = "";
        String input1 = "";
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Please enter the customer's id to view their past transactions");
        cusida =input.nextLine();
        int cusId = Integer.parseInt(cusida);
        String query = "select * from transactions where tc_id=" +cusId;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()){
            System.out.println ("No such id exists. Try again.");
            viewTransactions(con,empid);
        }
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        System.out.println("menu | quit");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewTransactions(con,empid);
        }else if(input1.equals("menu")){
            welcomeMenuc(con,empid);
        }else if(input1.equals("quit")){
            quit();
        }
    }

    public static void viewInvM(Connection con,int manid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Scanner input = new Scanner(System.in);
        String input1;
        System.out.println ("Viewing the inventory.");
        System.out.println("Would you like to view the store's or online inventory?");
        System.out.println("Store | Online");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("store") && !input1.equals("online")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInvM(con,manid);
        }else if(input1.equals("store")){
            viewInvStoreM(con,manid);
        }else if(input1.equals("online") ){
            viewInvOnlineM(con,manid);
        }
    }

    public static void viewInvStoreM(Connection con,int manid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Statement temp = con.createStatement();
        Scanner input = new Scanner(System.in);
        String head = "";
        System.out.println ("Viewing the inventory of the store you currently work at.");
        String query = "select unique d.inv_id,d.ist_id,d.i_type,d.i_Mac,d.i_iphone,d.i_iPad,d.i_iPod from inventory d, store a, manager b where b.m_id = " + manid + " and d.ist_id = a.st_id and b.sm_id = a.st_id";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        String input1;
        System.out.println("Restock | Menu | Quit");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("restock") && !input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInvStoreM(con,manid);
        }else if(input1.equals("restock")){
            restockM(con,manid);
        }else if(input1.equals("menu")){
            welcomeMenuc(con,manid);
        }else if(input1.equals("quit")){
            quit();
        }
    }
    
    public static void viewInvStoreInfo(Connection con,int manid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Statement temp = con.createStatement();
        Scanner input = new Scanner(System.in);
        String head = "";
        String query = "select a.st_address, a.st_city, a.st_zip, a.st_country, a.st_num, a.st_email, a.st_hours from store a, manager b where b.m_id = " + manid + "and b.sm_id = a.st_id";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        String input1;
        System.out.println("Restock | Menu | Quit");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("restock") && !input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInvStoreM(con,manid);
        }else if(input1.equals("restock")){
            restockM(con,manid);
        }else if(input1.equals("menu")){
            welcomeMenuc(con,manid);
        }else if(input1.equals("quit")){
            quit();
        }
    }
    public static void viewInvOnlineM(Connection con,int manid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        String input1;
        Statement temp = con.createStatement();
        Scanner input = new Scanner(System.in);
        System.out.println ("Viewing the international online inventory.");
        String query = "select * from inventory where i_type ='online'";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String head = "";
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        System.out.println("Menu | Quit");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInv(con,manid);
        }else if(input1.equals("menu")){
            welcomeMenuc(con,manid);
        }else if(input1.equals("quit")){
            quit();
        }
    }
    public static void restockM(Connection con,int manid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        Statement temp = con.createStatement();
        String input1;
        String input2;
        System.out.println("Which inventory would you like to restock?");
        System.out.println("Mac | iPhone | iPad | iPod");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase();
        System.out.println("By how much?");
        input2 =input.nextLine();
        input2 = input2.replaceAll("\\s+", "").toLowerCase();
        if(!input1.equals("mac") && !input1.equals("iphone") && !input1.equals("ipad") && !input1.equals("ipod")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            restockM(con,manid);
        }else if(input1.equals("mac")){
            //System.out.println("asfasdf:"+input2);
            String query2 = "Update inventory set i_Mac = (select a.i_Mac from inventory a, manager b, store c where a.i_type ='inventory' and b.m_id = "+manid+" and b.sm_id = c.st_id and a.ist_id = c.st_id)  + " + input2 +"  where inv_id =" +getId(con,manid);
            //System.out.println("poop:"+getId(con,manid)); 
            //System.out.println("poop2:"+getId(con,manid));
//            ResultSet result = temp.executeQuery(query2);
            System.out.println("Your iMac inventory has been updated.");
            Thread.sleep(1000);
            ResultSet result = temp.executeQuery(query2);
            System.out.println("You will now be redirected.");
            welcomeMenuc(con,manid);
        }else if(input1.equals("iphone")){
            String query3 = "Update inventory set i_iphone = (select a.i_iphone from inventory a, manager b, store c where a.i_type ='inventory' and b.m_id = "+manid+" and b.sm_id = c.st_id and a.ist_id = c.st_id)  + " + input2 +" where inv_id =" +getId(con,manid);
            Statement temp3 = con.createStatement(); 
            ResultSet result3 = temp3.executeQuery(query3);
            System.out.println("Your iPhone inventory has been updated.");
            Thread.sleep(1000);
            System.out.println("You will now be redirected.");
            welcomeMenuc(con,manid);
        }else if(input1.equals("ipad")){
            String query4 = "Update inventory set i_iPad = (select a.i_iPad from inventory a, manager b, store c where a.i_type ='inventory' and b.m_id = "+manid+" and b.sm_id = c.st_id and a.ist_id = c.st_id)  + " + input2 +" where inv_id =" +getId(con,manid);
            Statement temp4 = con.createStatement(); 
            ResultSet result4 = temp4.executeQuery(query4);
            System.out.println("Your iPad inventory has been updated.");
            Thread.sleep(1000);
            System.out.println("You will now be redirected.");
            welcomeMenuc(con,manid);
        }else if(input1.equals("ipod")){
            String query5 = "Update inventory set i_iPod = (select a.i_iPod from inventory a, manager b, store c where a.i_type ='inventory' and b.m_id = "+manid+" and b.sm_id = c.st_id and a.ist_id = c.st_id)  + " + input2 +" where inv_id =" +getId(con,manid);
            Statement temp5 = con.createStatement(); 
            ResultSet result5 = temp5.executeQuery(query5);
            System.out.println("Your iPod inventory has been updated.");
            Thread.sleep(1000);
            System.out.println("You will now be redirected.");
            welcomeMenuc(con,manid);
        }
        
    }

    public static void empStore(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        Statement temp = con.createStatement(); 
        String head = "";
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("This is your store information.");
        String query = "select a.st_address, a.st_city, a.st_zip, a.st_country, a.st_num, a.st_email, a.st_hours from store a, employee b where b.e_id = " + empid + "and b.se_id = a.st_id";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()){
            System.out.println ("No such id exists. Try again.");
            viewTransactions(con,empid);
        }
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do now?");
        System.out.println("menu | quit");
        String input1;
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            empStore(con,empid);
        }else if(input1.equals("menu")){
            welcomeMenub(con,empid);
        }else if(input1.equals("quit")){
            quit();
        }
    }

    public static void viewTransactions(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        Statement temp = con.createStatement();
        String head ="";
        String cusida;
        String input1 ="";
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Please enter the customer's id to view their past transactions");
        cusida =input.nextLine();
        int cusId = Integer.parseInt(cusida);
        String query = "select * from transactions where tc_id= " + cusId;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()){
            System.out.println ("No such id exists.");
            System.out.println("What would you like to do?");
            System.out.println("menu | quit");
            input1 =input.nextLine();
            input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
            if(!input1.equals("menu") && !input1.equals("quit")){
                System.out.println("Please input a valid option.");
                Thread.sleep(1000);
                viewTransactions(con,empid);
            }else if(input1.equals("menu")){
                welcomeMenuc(con,empid);
            }else if(input1.equals("quit")){
                quit();
            }
        }
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        System.out.println("menu | quit");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInv(con,empid);
        }else if(input1.equals("menu")){
            welcomeMenub(con,empid);
        }else if(input1.equals("quit")){
            quit();
        }
    }

    public static void viewInv(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Scanner input = new Scanner(System.in);
        String input1;
        System.out.println ("Viewing the inventory.");
        System.out.println("Would you like to view the store's or online inventory?");
        System.out.println("Store | Online");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("store") && !input1.equals("online")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInv(con,empid);
        }else if(input1.equals("store")){
            viewInvStore(con,empid);
        }else if(input1.equals("online") ){
            viewInvOnline(con,empid);
        }
    }

    public static void viewInvStore(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Statement temp = con.createStatement();
        String head = "";
        Scanner input = new Scanner(System.in);
        System.out.println ("Viewing the inventory of the store you currently work at.");
        String query = "select unique d.inv_id,d.ist_id,d.i_type,d.i_Mac,d.i_iphone,d.i_iPad,d.i_iPod from inventory d, store a, employee b where b.e_id = " + empid + " and d.ist_id = a.st_id and b.se_id = a.st_id";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        System.out.println("menu | quit");
        String input1;
        input1 =input.nextLine();
        
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInv(con,empid);
        }else if(input1.equals("menu")){
            welcomeMenub(con,empid);
        }else if(input1.equals("quit")){
            quit();
        }
    }
    public static void viewInvOnline(Connection con,int empid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Statement temp = con.createStatement();
        Scanner input = new Scanner(System.in);
        String input1 ="";
        String head = "";
        System.out.println ("Viewing the international online inventory.");
        String query = "select * from inventory where i_type ='online'";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        System.out.println("menu | quit");
        input1 =input.nextLine();
        input1 = input1.replaceAll("\\s+", "").toLowerCase(); 
        if(!input1.equals("menu") && !input1.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewInv(con,empid);
        }else if(input1.equals("menu")){
            welcomeMenub(con,empid);
        }else if(input1.equals("quit")){
            quit();
        }
    }

    public static boolean preMenu(Connection con,int numInput,String t_type,String t_string) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        String[] accNumArray = new String[100];
        String checkSQL = "Select " + t_type + " from " +t_string +"";
        Statement temp = con.createStatement();
        ResultSet result = temp.executeQuery(checkSQL);
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                if(numInput != result.getInt(t_type)){
                    continue;
                }
                else{
                    return true;
                }
            } while (result.next());
        } 
        System.out.println("This account does not exist or is a wrong input.\nTry again.");
        if(t_type.equals("customer")){
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            clogInScreen(con);
        }else if(t_type.equals("employee")){
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            elogInScreen(con);
        }else if(t_type.equals("manager")){
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            mlogInScreen(con);
        }
        return false;
    }

    public static boolean preMenu2(Connection con,String cmail,int cusid,String t_type,String t_string, String type3) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        Scanner input = new Scanner(System.in);
        String[] accNumArray = new String[100];
        String checkSQL = "Select " + t_type + " from " +t_string +" where " + type3 + " = " + cusid;
        Statement temp = con.createStatement();
        ResultSet result = temp.executeQuery(checkSQL);
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                if(!cmail.equals(result.getString(t_type))){
                    continue;
                }
                else{
                    return true;
                }
            } while (result.next());
        } 
        System.out.println("This account does not exist or is a wrong input.\nTry again.");
        if(t_type.equals("customer")){
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            clogInScreen(con);
        }else if(t_type.equals("employee")){
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            elogInScreen(con);
        }else if(t_type.equals("manager")){
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            mlogInScreen(con);
        }
        return false;
    }

    public static void welcomeMenua(int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Scanner input = new Scanner(System.in);
        Connection con = DriverManager.getConnection("connection url:cse241","username","password");
        String preName = "Select FIRST_NAME from customer where c_id = " +cusid;
        Statement temp2 = con.createStatement(); 
        ResultSet result = temp2.executeQuery(preName);
        ResultSetMetaData rsmd = result.getMetaData();
        String accountInput = " ";
        String cusName = " ";
        cusName = getName(con,accountInput,cusid);
        System.out.println("\n");
        System.out.println("*****************************************************\n");
        System.out.println("Welcome back to Apple " + cusName+"!\n");
        System.out.println("*****************************************************\n");
        System.out.println ("Please choose from the following list of options.");
        System.out.println ("Enter one of the following options. Enter quit to log out.");
        System.out.println("Shop |  Cart  | Check Out | Store | Account | Log Out");
        accountInput =input.nextLine();
        accountInput = accountInput.replaceAll("\\s+", "").toLowerCase();
        welcomeMenu(con,accountInput,cusid);
        temp2.close();
        con.close();
    }

    public static void welcomeMenu2(Connection con, String accountInput, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        if(!accountInput.equals("shop") && !accountInput.equals("cart") && !accountInput.equals("checkout")&& !accountInput.equals("store")&& !accountInput.equals("account")&& !accountInput.equals("signup")&&!accountInput.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            con.close();
            signUpScreen();
        }
        else if(accountInput.equalsIgnoreCase("shop")){
            shop(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("cart")){
            viewCart(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("checkout")){
            checkout(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("store")){
            shopAtStore(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("account")){
            viewAccount(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("signup")){
            signUpScreen(con);
        }
        else if(accountInput.equalsIgnoreCase("logout")){
            quit();
        }
        else{
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Wrong input. Please choose again");
            con.close();
            signUpScreen();
        }
    }

    public static void welcomeMenu(Connection con, String accountInput, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{
        if(!accountInput.equals("shop") && !accountInput.equals("cart") && !accountInput.equals("checkout")&& !accountInput.equals("store")&& !accountInput.equals("account") &&!accountInput.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            welcomeMenua(cusid);
        }
        else if(accountInput.equalsIgnoreCase("shop")){
            shop(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("cart")){
            viewCart(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("checkout")){
            checkout(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("store")){
            shopAtStore(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("account")){
            viewAccount(con,cusid);
        }
        else if(accountInput.equalsIgnoreCase("logout")){
            quit();
        }
        else{
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Wrong input. Please choose again");
            con.close();
            welcomeMenua(cusid);
        }
    }
    
    public static void shopAtStore(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{ 
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("                  Welcome to your the Apple Store!                       \n");
        System.out.println("-------------------------------------------------------------------------\n");
        Thread.sleep(1000);
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the city of the store you wish to find information on.\n");
        String cityInput;
        cityInput = input.nextLine();
        Statement temp = con.createStatement();
        String query = "select * from store where st_city = '" + cityInput + "'";
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String head = "";
        if (!result.next()) {
            System.out.println ("That is not a valid city or input. Please try again!");
            shopAtStore(con,cusid);
        }
        else {
            System.out.println(cityInput + "'s Apple store information is a follows:\n");
            System.out.println("-------------------------------------------------------------------------\n");
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        Thread.sleep(1000);
        System.out.println("What would you like to do now?\n");
        System.out.println("Redo | Shop | Cart | Quit");
        String inputOption;
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("redo") && !inputOption.equals("shop") && !inputOption.equals("cart")&& !inputOption.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopAtStore(con,cusid);
        }else if(inputOption.equals("redo")){
            shopAtStore(con,cusid);
        }else if(inputOption.equals("shop")){
            shop(con,cusid);
        }else if(inputOption.equals("cart")){
            viewCart(con,cusid);
        }else if(inputOption.equals("quit")){
            quit();
        }
    }

    public static void checkout(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{ 
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("                  Welcome to your checkout!                              \n");
        System.out.println("-------------------------------------------------------------------------\n");
        Thread.sleep(1000);
        String inputOption = " ";
        String inputOption2 = "";
//        inputOption =input.nextLine();
//        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase(); 
        System.out.println("Review your cart\n");
        Thread.sleep(1000);
        Statement temp = con.createStatement();
        String query = "select a.c_Mac, a.c_iphone, a.c_iPad, a.c_iPod, a.c_tot as total_quantity, b.b_amt as total_amount from cart a, billing b where a.ccid = " + cusid + " and b.bc_id = " +cusid;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String head = "";
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());;
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        Thread.sleep(1000);
        System.out.println("\n");
        System.out.println("What would you like to do?");
        System.out.println("Pay | Edit Cart | Shop | Quit");
        inputOption2 = input.nextLine();
        inputOption2 = inputOption2.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption2.equals("pay") && !inputOption2.equals("editcart") && !inputOption2.equals("edit")&& !inputOption2.equals("shop")&& !inputOption2.equals("quit")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            checkout(con,cusid);
        }else if(inputOption2.equals("pay")){
            payUp(con,cusid);
        }else if(inputOption2.equals("editcart") || inputOption2.equals("edit")){
            viewCart(con,cusid);
        }else if(inputOption2.equals("shop")){
            shop(con,cusid);
        }else if(inputOption2.equals("quit")){
            quit();
        }
    }

    public static void payUp(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Debit | Credit | Cash");
        Thread.sleep(1000);
        String inputOption2 = "";
        inputOption2 = input.nextLine();
        inputOption2 = inputOption2.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption2.equals("debit") && !inputOption2.equals("credit") && !inputOption2.equals("cash")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            payUp(con,cusid);
        }else if(inputOption2.equals("debit")){
            paySpecific(con,cusid,"debit");
        }else if(inputOption2.equals("credit")){
            paySpecific(con,cusid,"credit");
        }else if(inputOption2.equals("cash")){
            paySpecific(con,cusid,"cash");
        }
    }    

    public static void paySpecific(Connection con, int cusid, String type) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        String q = "";
        String q2 = "";
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Thread.sleep(1000);
        if(type.equals("debit")){
            System.out.println("Please enter your debit number.");
            System.out.println("*Debit card number must be 16 in length.");
            q = input.nextLine();
            if(q.length() != 16){
                System.out.println("This is not a correct length of debit card number");
                paySpecific(con,cusid,type);
            }else if(!q.matches("[+-]?\\d*(\\.\\d+)?")){
                System.out.println("This is not a correct debit card number.");
                paySpecific(con,cusid,type);
            }else{
                checkCredentials(con,cusid,"debit");
            }
        }else if(type.equals("credit")){
            System.out.println("Please enter your credit number. ");
            System.out.println("*Credit card number must be 16 in length.");
            q2 = input.nextLine();
            if(q2.length() != 16){
                System.out.println("This is not a correct length of credit card number");
                paySpecific(con,cusid,type);
            }else if(!q2.matches("[+-]?\\d*(\\.\\d+)?")){
                System.out.println("This is not a correct credit card number.");
                paySpecific(con,cusid,type);
            }else{
                checkCredentials(con,cusid,"credit");
            }
        }else if(type.equals("cash")){
            System.out.println("Thank you! Have a good day.");
            transaction(con,cusid);
            Thread.sleep(1000);
            System.out.println("Redirecting you back to the Welcome scren.");
            for(int i = 0; i < 5; i++){
                System.out.print(i+" ");
                Thread.sleep(1000);
            }
            con.close();
            welcomeMenua(cusid);
        }
    }     

    public static void checkCredentials(Connection con, int cusid, String type) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    
        System.out.println("Please wait while we authorize your credentials");
        for(int i = 0; i < 5; i++){
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.println("You have successfully purchased you item(s)!");
        transaction(con,cusid);
        Thread.sleep(1000);
        System.out.println("Redirecting you back to the Welcome scren.");
        for(int i = 0; i < 5; i++){
            System.out.print(i+" ");
            Thread.sleep(1000);
        }
        con.close();
        welcomeMenua(cusid);
    }

    public static void transaction(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        int rand = getRand();
        String date = genDate();
        String name = getName(con,"",cusid);
        String amtTotAll = getAmtTot(con,cusid);
        String query4 = "INSERT INTO transactions (t_id,tc_id,t_name,t_date,t_prod) VALUES (" + rand + "," + cusid + ",'"+ name +"','" + date + "','+" + amtTotAll + "')";
        Statement temp5 = con.createStatement();
        temp5.executeQuery(query4);
    }
    
    public static void shop(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        String inputOption = "";
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("      Welcome to Apple's shop!\n");
        System.out.println("-------------------------------------------------------------------------\n");
        Thread.sleep(1000);
        System.out.println("What would you like to shop today?\n");
        System.out.println("Macbook | iPhone | iPad | iPod| Go Back | Log Out\n");
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("macbook") && !inputOption.equals("iphone") && !inputOption.equals("ipad") && !inputOption.equals("ipod") && !inputOption.equals("logout")&& !inputOption.equals("goback")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shop(con,cusid);
        } 
        else if(inputOption.toString().equalsIgnoreCase("macbook")){
            shopMac(con,cusid);
        }
        else if(inputOption.toString().equalsIgnoreCase("iphone")){
            shopIphone(con,cusid);
        }
        else if(inputOption.toString().equalsIgnoreCase("ipad")){
            shopIpad(con,cusid);
        }
        else if(inputOption.toString().equalsIgnoreCase("ipod")){
            shopIpod(con,cusid);
        }
        else if(inputOption.toString().equalsIgnoreCase("logout")){
            quit();
        }
        else if(inputOption.toString().equalsIgnoreCase("goback")){
            con.close();
            welcomeMenua(cusid);
        }
        else{
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Wrong input. Please choose again");
            shop(con,cusid);
        }
    }   

    public static void shopMac(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("      Macbook. \n");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("Which inch sized Macbook?");
        System.out.println("13 | 15 | 17");
        String inputOption = "";
        String inputOption2 = "";
        String inputOption3 = "";
        String inputOption4 = "";
        String inputOption5 = "";
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("13") && !inputOption.equals("15") && !inputOption.equals("17")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopMac(con,cusid);
        } 
        System.out.println("Which size of storage? Pick 128, 256 or 512.");
        inputOption2 = input.nextLine();
        inputOption2 = inputOption2.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption2.equals("128") && !inputOption2.equals("256")&& !inputOption2.equals("512")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopMac(con,cusid);
        } 
        System.out.println("Which mac? Pick basic or pro.");
        inputOption3 = input.nextLine();
        inputOption3 = inputOption3.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption3.equals("basic") && !inputOption3.equals("pro")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopMac(con,cusid);
        } 
        System.out.println("Would you like to definitely insert this product into the cart?");
        inputOption4 = input.nextLine();
        inputOption4 = inputOption4.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption4.equals("yes") && !inputOption4.equals("no")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopMac(con,cusid);
        }
        System.out.println("You have successfully added an iMac to your cart.");
        String query1 = "Update customer set cart = cart + 1 where c_id = " +cusid;
        Statement temp2 = con.createStatement(); 
        temp2.executeQuery(query1);
        updateCart(con,cusid,"c_Mac","imac");
        updateInv(con,cusid,"i_Mac");
        updateBilling(con,cusid,"imac");
        Thread.sleep(1000);
        System.out.println("Would you like to check your cart or continue shopping?");
        System.out.println("Cart | Continue");
        inputOption5 = input.nextLine();
        inputOption5 = inputOption5.replaceAll("\\s+", "").toLowerCase();
        if(inputOption5.equals("cart")){
            viewCart(con,cusid);
        }else if(inputOption5.equals("continue")){
            shop(con,cusid);
        }else if(!inputOption5.equals("cart") && !inputOption5.equals("continue")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopMac(con,cusid);
        }
    }

    public static void shopIphone(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("      iPhone. \n");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("Which generation? Pick 5, 5s, or 6.");
        String inputOption = "";
        String inputOption2 = "";
        String inputOption3 = "";
        String inputOption4 = "";
        String inputOption5 = "";
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("5") && !inputOption.equals("5s") && !inputOption.equals("6")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIphone(con,cusid);
        } 
        System.out.println("Which size of storage? Pick 16 or 32.");
        inputOption2 = input.nextLine();
        inputOption2 = inputOption2.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption2.equals("16") && !inputOption2.equals("32")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIphone(con,cusid);
        } 
        System.out.println("Which color? Pick black or white.");
        inputOption3 = input.nextLine();
        inputOption3 = inputOption3.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption3.equals("black") && !inputOption3.equals("white")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIphone(con,cusid);
        } 
        System.out.println("Would you like to definitely insert this product into the cart?");
        inputOption4 = input.nextLine();
        inputOption4 = inputOption4.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption4.equals("yes") && !inputOption4.equals("no")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIphone(con,cusid);
        }
        System.out.println("You have successfully added an iPhone to your cart.");
        String query1 = "Update customer set cart = cart + 1 where c_id = " +cusid;
        Statement temp2 = con.createStatement(); 
        temp2.executeQuery(query1);
        updateCart(con,cusid,"c_iphone","iphone");
        updateInv(con,cusid,"i_iphone");
        updateBilling(con,cusid,"iphone");
        Thread.sleep(1000);
        System.out.println("Would you like to check your cart or continue shopping?");
        System.out.println("Cart | Continue");
        inputOption5 = input.nextLine();
        inputOption5 = inputOption5.replaceAll("\\s+", "").toLowerCase();
        if(inputOption5.equals("cart")){
            viewCart(con,cusid);
        }else if(inputOption5.equals("continue")){
            shop(con,cusid);
        }else if(!inputOption5.equals("cart") && !inputOption5.equals("continue")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIphone(con,cusid);
        }
    }

    public static void shopIpad(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("      iPad. \n");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("Which iPad? Pick retina or air.");
        String inputOption = "";
        String inputOption2 = "";
        String inputOption3 = "";
        String inputOption4 = "";
        String inputOption5 = "";
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("retina") && !inputOption.equals("air")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpad(con,cusid);
        } 
        System.out.println("Which size of storage? Pick 16 or 32.");
        inputOption2 = input.nextLine();
        inputOption2 = inputOption2.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption2.equals("16") && !inputOption2.equals("32")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpad(con,cusid);
        } 
        System.out.println("Which color? Pick black or white.");
        inputOption3 = input.nextLine();
        inputOption3 = inputOption3.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption3.equals("black") && !inputOption3.equals("white")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpad(con,cusid);
        } 
        System.out.println("Would you like to definitely insert this product into the cart?");
        inputOption4 = input.nextLine();
        inputOption4 = inputOption4.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption4.equals("yes") && !inputOption4.equals("no")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpad(con,cusid);
        }
        System.out.println("You have successfully added an iPad to your cart.");
        String query1 = "Update customer set cart = cart + 1 where c_id = " +cusid;
        Statement temp2 = con.createStatement();
        temp2.executeQuery(query1);
        updateCart(con,cusid,"c_iPad","ipad");
        updateInv(con,cusid,"i_iPad");
        updateBilling(con,cusid,"ipad");
        Thread.sleep(1000);
        System.out.println("Would you like to check your cart or continue shopping?");
        System.out.println("Cart | Continue");
        inputOption5 = input.nextLine();
        inputOption5 = inputOption5.replaceAll("\\s+", "").toLowerCase();
        if(inputOption5.equals("cart")){
            viewCart(con,cusid);
        }else if(inputOption5.equals("continue")){
            shop(con,cusid);
        }else if(!inputOption5.equals("cart") && !inputOption5.equals("continue")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpad(con,cusid);
        }
    }

    public static void shopIpod(Connection con, int cusid) throws SQLException, IOException, java.lang.ClassNotFoundException, InterruptedException{  
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("      iPod. \n");
        System.out.println("-------------------------------------------------------------------------\n");
        String inputOption = "";
        String inputOption2 = "";
        String inputOption3 = "";
        String inputOption4 = "";
        String inputOption5 = "";
        System.out.println("Which size of storage? Pick 16 or 32.");
        inputOption2 = input.nextLine();
        inputOption2 = inputOption2.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption2.equals("16") && !inputOption2.equals("32")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpod(con,cusid);
        } 
        System.out.println("Which color? Pick black or white.");
        inputOption3 = input.nextLine();
        inputOption3 = inputOption3.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption3.equals("black") && !inputOption3.equals("white")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpod(con,cusid);
        } 
        System.out.println("Would you like to definitely insert this product into the cart?");
        inputOption4 = input.nextLine();
        inputOption4 = inputOption4.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption4.equals("yes") && !inputOption4.equals("no")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpod(con,cusid);
        }
        System.out.println("You have successfully added an iPhone to your cart.");
        String query1 = "Update customer set cart = cart + 1 where c_id = " +cusid;
        Statement temp2 = con.createStatement(); 
        temp2.executeQuery(query1);
        updateCart(con,cusid,"c_iPod","ipod");
        updateInv(con,cusid,"i_iPod");
        updateBilling(con,cusid,"ipod");
        Thread.sleep(1000);
        System.out.println("Would you like to check your cart or continue shopping?");
        System.out.println("Cart | Continue");
        inputOption5 = input.nextLine();
        inputOption5 = inputOption5.replaceAll("\\s+", "").toLowerCase();
        if(inputOption5.equals("cart")){
            viewCart(con,cusid);
        }else if(inputOption5.equals("continue")){
            shop(con,cusid);
        }else if(!inputOption5.equals("cart") && !inputOption5.equals("continue")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopIpod(con,cusid);
        }
    }

    public static void viewCart(Connection con, int cusid) throws SQLException, InterruptedException, IOException, ClassNotFoundException{
        Statement temp = con.createStatement();
        String query = "select unique a.c_Mac as iMac, a.c_iphone as iPhone, a.c_iPad as iPad, a.c_iPod as iPod, a.c_tot as total_quantity, a.c_totAmt as total_amount from cart a, billing b where a.ccid =" + cusid;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String head ="";
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                        do {
                            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                                System.out.print (result.getString(i));
                                for (int j = result.getString(i) == null ? 4 : result.getString(i).length(); 
                                j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                                    System.out.print (" ");
                                } 
                            }
                            System.out.println();
                        } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        System.out.println("What would you like to do?");
        System.out.println("Check Out | Remove Items | Shop | Go Back");
        Scanner input = new Scanner(System.in);
        String inputOption = "";
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("checkout") && !inputOption.equals("remove") && !inputOption.equals("removeitems") && !inputOption.equals("removeitem") && !inputOption.equals("shop") ){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            con.close();
            welcomeMenua(cusid);
        }else if(inputOption.equals("remove") || inputOption.equals("removeitems") || inputOption.equals("removeitem")){
            //System.out.println("asdf"+inputOption);
            removeFromCart(con,cusid);
        }else if(inputOption.equals("shop")){
            shop(con,cusid);
        }else if(inputOption.equals("checkout")){
            checkout(con,cusid);
        }else if(inputOption.equals("goback")){
            con.close();
            welcomeMenua(cusid);
        }
    }

    public static void removeFromCart(Connection con, int cusid) throws InterruptedException, SQLException, IOException, ClassNotFoundException{
        Thread.sleep(1000);
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("What would you like to remove?");
        System.out.println("Mac | iPhone | iPad | iPod");
        Scanner input = new Scanner(System.in);
        String inputOption = "";
        inputOption = input.nextLine();
        inputOption = inputOption.replaceAll("\\s+", "").toLowerCase();
        if(!inputOption.equals("mac") && !inputOption.equals("iphone") && !inputOption.equals("ipad") && !inputOption.equals("ipod") ){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            removeFromCart(con,cusid);
        }else if(inputOption.equals("mac")){
            removeSpItem(con, cusid, "imac");
        }else if(inputOption.equals("iphone")){
            removeSpItem(con, cusid, "iphone");
        }else if(inputOption.equals("ipad")){
            removeSpItem(con, cusid, "ipad");
        }else if(inputOption.equals("ipod")){
            removeSpItem(con, cusid, "ipod");
        }
    }

    public static void removeSpItem(Connection con, int cusid, String type) throws SQLException, InterruptedException, IOException, ClassNotFoundException{
        Scanner input = new Scanner(System.in);
        String inputOption5 = "";
        System.out.println("You have successfully removed an " + type + " from your cart.");
        String query1 = "Update customer set cart = cart - 1 where c_id = " +cusid;
        Statement temp2 = con.createStatement(); 
        temp2.executeQuery(query1);
        switch (type) {
            case "imac":
                updateCartMin(con,cusid,"c_Mac","imac");
                updateInvMin(con,cusid,"i_Mac");
                updateBillingMin(con,cusid,"imac");
                break;
            case "iphone":
                updateCartMin(con,cusid,"c_iphone","iphone");
                updateInvMin(con,cusid,"i_iphone");
                updateBillingMin(con,cusid,"iphone");
                break;
            case "ipad":
                updateCartMin(con,cusid,"c_iPad","ipad");
                updateInvMin(con,cusid,"i_iPad");
                updateBillingMin(con,cusid,"ipad");
                break;
            case "ipod":
                updateCartMin(con,cusid,"c_iPod","ipod");
                updateInvMin(con,cusid,"i_iPod");
                updateBillingMin(con,cusid,"ipod");
                break;
            default:
                System.out.println("Wrong input please try again");
                removeSpItem(con,cusid,type);
        }
        Thread.sleep(1000);
        System.out.println("Would you like to remove more from your cart or continue shopping?");
        System.out.println("Cart | Continue");
        inputOption5 = input.nextLine();
        inputOption5 = inputOption5.replaceAll("\\s+", "").toLowerCase();
        if(inputOption5.equals("cart")){
            removeFromCart(con,cusid);
        }else if(inputOption5.equals("continue")){
            shop(con,cusid);
        }else if(!inputOption5.equals("cart") && !inputOption5.equals("continue")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            shopMac(con,cusid);
        }
    }

    public static void viewAccount(Connection con, int cusid) throws SQLException, InterruptedException, IOException, ClassNotFoundException{
        Statement temp = con.createStatement();
        Scanner input = new Scanner(System.in);
        String input1 ="";
        String head = "";
        String inputOption5 = "";
        String query = "select * from customer where c_id = " + cusid;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            try{
                int[] width = new int[rsmd.getColumnCount()];
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    width[i-1] = rsmd.getPrecision(i);
                    head += rsmd.getColumnName(i);
                    for (int j = rsmd.getColumnName(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                    head += " ";
                    }
                }
                System.out.println(head);
                for (int i = 0; i < head.length(); i++) {
                    System.out.print ("-");
                }
                System.out.println();
                do {
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        System.out.print (result.getString(i));
                    for (int j = result.getString(i) == null ? 4: result.getString(i).length(); j <= Math.max(width[i-1],rsmd.getColumnName(i).length()); j++) {
                        System.out.print (" ");
                    } 
                }
                    System.out.println();
                } while (result.next());
            }
            catch (SQLException e){
                System.out.println("SQL statement is not executed!");
            }
        }
        Thread.sleep(1000);
        System.out.println("Would you like to remove more from your cart or continue shopping?");
        System.out.println("Go Back | Log Out");
        inputOption5 = input.nextLine();
        inputOption5 = inputOption5.replaceAll("\\s+", "").toLowerCase();
        if(inputOption5.equals("goback")){
            con.close();
            welcomeMenua(cusid);
        }else if(inputOption5.equals("logout")){
            quit();
        }else if(!inputOption5.equals("cart") && !inputOption5.equals("continue")){
            System.out.println("Please input a valid option.");
            Thread.sleep(1000);
            viewAccount(con,cusid);
        }
    }    

    public static void updateCartMin(Connection con, int cusid, String type,String type2) throws SQLException, InterruptedException, IOException, ClassNotFoundException{
        int rand = getRand();
        String date = genDate();
        int cow = Integer.parseInt(getAmt(con,type2));
        //System.out.println("qwerqwerqwer.");
        String name = getName(con,"",cusid);
       // System.out.println("asdfasdfasdf.");
        String query1 = "Update cart set " + type + "=" + type + " - 1 where ccid = " +cusid;
        String query2 = "Update cart set c_tot = c_tot - 1 where ccid = " +cusid;
        String query3 = "Update cart set c_totamt = c_totamt - " + cow + "where ccid = " +cusid;
        String query4 = "INSERT INTO transactions (t_id,tc_id,t_name,t_date,t_prod,t_totamt) VALUES (" + rand + "," + cusid + ",'"+ name +"','" + date + "','"+ type +"'," + cow + ")";
        //System.out.println("r.");
        Statement temp2 = con.createStatement(); 
        //System.out.println("t.");
        Statement temp3 = con.createStatement();
        //System.out.println("98.");
        Statement temp4 = con.createStatement();
        //System.out.println("yt.");
        Statement temp5 = con.createStatement();
        //System.out.println("asdf.");
        temp2.executeQuery(query1);
       // System.out.println("2");
        temp3.executeQuery(query2);
       // System.out.println("3");
        temp4.executeQuery(query3);
        //System.out.println("4");
        temp5.executeQuery(query4);
        System.out.println("Redirecting you back to the Welcome scren.");
        for(int i = 0; i < 5; i++){
            System.out.print(i+" ");
            Thread.sleep(1000);
        }
        con.close();
        
        welcomeMenua(cusid);
    }

    public static void updateInvMin(Connection con, int cusid, String type) throws SQLException{
        String query1 = "Update inventory set " + type + "=" + type + "+1";
        Statement temp2 = con.createStatement(); 
        temp2.executeQuery(query1);
    }

    public static void updateBillingMin(Connection con, int cusid, String type) throws SQLException{
        String priceOf = " ";
        Statement temp = con.createStatement();
        Statement temp2 = con.createStatement();
        String query = "select p_price from product where pc_id = " + cusid;
        String query1 = "Update billing set b_amt=b_amt-"+priceOf+" where p_type ='"+type+"' and pc_id = " +cusid;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd2 = result.getMetaData();
        int columnsNumber2 = rsmd2.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                for(int i = 0; i <columnsNumber2; i++){
                           priceOf = result.getString(i+1);
                }
            } while (result.next());
        }
        temp2.executeQuery(query1);
    }

    public static int getRand() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }
    
    public static String genDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = new Date(); // returns a java.util.Date
        return dateFormat.format(d);
    }   
    
    public static void updateCart(Connection con, int cusid, String type, String type2) throws SQLException, InterruptedException, IOException, ClassNotFoundException{
        int rand = getRand();
        int cow = Integer.parseInt(getAmt(con,type2));
        String date = genDate();
        String name = getName(con,"",cusid);
        String query1 = "Update cart set " + type + "=" + type + "+1 where ccid = " +cusid;
        String query2 = "Update cart set c_tot = c_tot + 1 where ccid = " +cusid;
        String query3 = "Update cart set c_totamt = c_totamt + " + cow + "where ccid = " +cusid;
        String query4 = "INSERT INTO transactions (t_id,tc_id,t_name,t_date,t_prod,t_totamt) VALUES (" + rand + "," + cusid + ",'"+ name +"','" + date + "','"+ type +"'," + cow + ")";
        Statement temp2 = con.createStatement(); 
        Statement temp3 = con.createStatement(); 
        Statement temp4 = con.createStatement();
        Statement temp5 = con.createStatement();
        temp2.executeQuery(query1);
        temp3.executeQuery(query2);
        temp4.executeQuery(query3);
        temp5.executeQuery(query4);
        System.out.println("Redirecting you back to the Welcome scren.\n");
        for(int i = 0; i < 5; i++){
            System.out.print(i+" ");
            Thread.sleep(1000);
        }
        con.close();
        welcomeMenua(cusid);
    }

    public static void updateInv(Connection con, int cusid, String type) throws SQLException{
        String query1 = "Update inventory set " + type + "=" + type + "-1";
        Statement temp2 = con.createStatement(); 
        temp2.executeQuery(query1);
    }
    

    public static void updateBilling(Connection con, int cusid, String type) throws SQLException, IOException, InterruptedException, ClassNotFoundException{
        String priceOf = getAmt(con,type);
        int rand = getRand();
        String date = genDate();
        String name = getName(con,"",cusid);
        System.out.println(priceOf);
        Statement temp = con.createStatement();
        Statement temp2 = con.createStatement();
        String query = "select p_price from product where pc_id = " + cusid;
        String query1 = "Update billing set b_amt = b_amt + " + priceOf + " where bc_id = " + cusid;
        ResultSet result = temp.executeQuery(query);
        ResultSetMetaData rsmd2 = result.getMetaData();
        int columnsNumber2 = rsmd2.getColumnCount();
        if (!result.next()) System.out.println ("Empty result.");
        else {
            do {
                for(int i = 0; i <columnsNumber2; i++){
                           priceOf = result.getString(i+1);
                }
            } while (result.next());
        }
        ResultSet executeQuery = temp2.executeQuery(query1);
    }

}
