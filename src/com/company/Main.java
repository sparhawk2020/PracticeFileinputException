package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Main {


    static HashMap<String, Double> employee = new HashMap<String,Double>();



    public static void display(){
        for (Map.Entry<String, Double> set : employee.entrySet()) {

            System.out.println("Name: " + set.getKey());
            System.out.println("Salary" + set.getValue());


        }

    }

    public static void readfile() throws IOException {

        Path file = Paths.get("Myfile.txt");




        if(Files.exists(file)==false){

            System.out.println("No record yet ");
            return;
        }

        String[] data;
        String s;

        InputStream input = new BufferedInputStream(Files.newInputStream(file));

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        s = reader.readLine();

        while(s!=null){

            data = s.split(" ");

            employee.put(data[0], Double.parseDouble(data[1]));

            s = reader.readLine();



        }



        display();

    }


    public static void savedata() throws IOException {

        Path file = Paths.get("Myfile.txt");
        String data ="";

        OutputStream output = new BufferedOutputStream(Files.newOutputStream(file,CREATE));


        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));


        for (Map.Entry<String, Double> set : employee.entrySet()) {

            data = set.getKey() + " " + set.getValue() + " " ;




            writer.write(data, 0,data.length());

            writer.newLine();



        }

        writer.close();

    }

    public static void main(String[] args) throws IOException {


        Scanner key = new Scanner(System.in);
        String ename="";
        double sal=0.0;
        boolean flag = true;


        readfile();

        do {

            System.out.println("Enter the employee name ");
            ename = key.nextLine();

            if(ename.equals("Ex")){

                continue;
            }



            while(flag) {
                System.out.println("Enter the salary ");

                try {
                    sal = key.nextDouble();


                    key.nextLine();


                    if(sal < 2000){
                        throw new Myexception("Salary cannot be below 2000");
                    }
                    break;
                }
                catch (InputMismatchException e){
                    System.out.println("You must enter a number");


                }
                catch (Myexception e) {
                    System.out.println(e.getMessage());


                }

            }


            //save in the hashmap

            employee.put(ename,sal);

        }while (!(ename.equals("Ex")));



        savedata();

    }
}
