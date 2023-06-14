package br.com.mercado.service;

import java.util.Scanner;

public class MainService {
    private static Scanner scn = new Scanner(System.in);
    static String nome = "";
    static double numD = 0.0;
    static int numI = 0;
    public static String perguntaString(String titulo){

        System.out.println(titulo);
        nome = scn.nextLine();
        nome = scn.nextLine();
        return nome;
    }

    public static Double perguntaNumeroDouble(String titulo){

        System.out.println(titulo);
        numD = scn.nextInt();
        return numD;
    }

    public static int perguntaNumeroInt(String titulo){

        System.out.println(titulo);
        numI = scn.nextInt();
        return numI;
    }

}
