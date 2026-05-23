import java.sql.*;
import  java.util.Scanner;
public class   Main {
    static  Scanner sc= new Scanner(System.in);
    static   String url="jdbc:mysql://localhost:3307/phonepay";
    static  String uname="root";
    static  String pass="Vigneshpatel45";
    static  void createPayment(){
        try{
            String query="insert into phonepay(id,name,amount) values (?,?,?)";
            Connection con = DriverManager.getConnection(url,uname,pass);
            PreparedStatement st= con.prepareStatement(query);
            System.out.println("Enter The Count Of Transactions....");

            int n=sc.nextInt();
            sc.nextLine();
            for(int i=0;i<n;i++) {
                System.out.println("Enter The Sender id");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter The Sender Name");
                String name = sc.nextLine();
                System.out.println("Enter The Transaction Amount");
                Long amount = sc.nextLong();
                st.setInt(1, id);
                st.setString(2, name);
                st.setLong(3, amount);
                int count = st.executeUpdate();
                System.out.println(count);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //read--view Transactions..
    static  void viewTransaction(){
        try {
            String query="select * from phonepay";
            Connection con = DriverManager.getConnection(url,uname,pass);
            Statement st= con.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.printf("%-5s %-15s %-10s%n","ID","NAME","AMOUNT");
            while(rs.next()) {
                int id=rs.getInt("id");
                String name= rs.getString("name");
                long amount=     rs.getLong("amount");
                System.out.printf("%-5d %-15s %-10d%n", id, name, amount);
            }

            rs.close();
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //update Transaction
    static  void updateTransaction(){
        try{
            String query="update phonepay set name=? ,amount=? where id=?";
            Connection con = DriverManager.getConnection(url,uname,pass);
            PreparedStatement st= con.prepareStatement(query);
            System.out.println("Enter The New New User Name");
            String name=sc.nextLine();
            System.out.println("Enter the new Transaction Amount");
            String amount=sc.nextLine();
            System.out.println("Enter Id number");
            int id=sc.nextInt();
            sc.nextLine();
            st.setString(1,name);
            st.setString(2,amount);
            st.setInt(3,id);
            int count=st.executeUpdate();
            if (count>0){
                System.out.println("Transaction updated Successfully");
            }
            else{
                System.out.println("no such Transaction found");
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Delete Transaction
    static  void deleteTransaction(){
        try{
            String query="delete from phonepay where id=?";
            Connection con = DriverManager.getConnection(url,uname,pass);
            PreparedStatement st= con.prepareStatement(query);
            System.out.println("Enter  Transaction  id to Delete");
            int id=sc.nextInt();
            sc.nextLine();
            st.setInt(1,id);
            int count=st.executeUpdate();
            if (count > 0) {

                System.out.println("user deleted successfully");

            }else{
                System.out.println("no such id or name");
            }
            st.close();
            con.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static  void main(String args[])throws  Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        while ((true)){
            System.out.println("=========welcomr to phonepay menu bar============");
            System.out.println("1.Insert transaction");
            System.out.println("2.View Transaction ");
            System.out.println("3.update Transaction");
            System.out.println("4.Delete Transaction");
            System.out.println("5.Exit");
            System.out.println("Enter your choice");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    createPayment();
                    break;
                case  2:
                    viewTransaction();
                    break;
                case 3:
                    updateTransaction();
                    break;
                case  4:
                    deleteTransaction();
                    break;
                case  5:
                    System.out.println("Thank you Boss...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");

            }
        }
    }
}