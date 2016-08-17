// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PeriodoTeste.java

package br.com.faturamento.dao;


import java.time.LocalDate;
import java.util.Calendar;

public class PeriodoTeste
{

    public PeriodoTeste()
    {
    }

    public static void main(String args[])
    {
        Calendar c = Calendar.getInstance();
        c.set(2, 2);
        //java.text.DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println((new StringBuilder("Menor dia do mes:")).append(c.getActualMinimum(5)).toString());
        System.out.println((new StringBuilder("Maior dia do mes:")).append(c.getActualMaximum(5)).toString());
        int diaFinal = LocalDate.now().lengthOfMonth();
        int mes = LocalDate.now().getMonthValue();
        int ano = LocalDate.now().getYear();
        String dataInicial = (new StringBuilder(String.valueOf(ano))).append("-0").append(mes).append("-").append("01").toString();
        String dataFinal = (new StringBuilder(String.valueOf(ano))).append("-0").append(mes).append("-").append(diaFinal).toString();
        System.out.println(dataInicial);
        System.out.println(dataFinal);
    }
}
