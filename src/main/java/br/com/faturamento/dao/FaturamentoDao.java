// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FaturamentoDao.java

package br.com.faturamento.dao;

import br.com.faturamento.connection.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class FaturamentoDao{

    public FaturamentoDao()
    {
        valorServicos = new BigDecimal(0);
        valorPecas = new BigDecimal(0);
    }

    public BigDecimal buscaServicos()
        throws SQLException, ClassNotFoundException
    {
        Connection conn = new ConnectionFactory().getConnection();
        try
        {
            int diaFinal = LocalDate.now().lengthOfMonth();
            int mes = LocalDate.now().getMonthValue();
            int ano = LocalDate.now().getYear();
            String dataInicial = (new StringBuilder(String.valueOf(ano))).append("/0").append(mes).append("/").append("01").toString();
            String dataFinal = (new StringBuilder(String.valueOf(ano))).append("/0").append(mes).append("/").append(diaFinal).toString();
            String sql = (new StringBuilder("SELECT CASE GROUPING(P.SECCOD) + GROUPING(SECDES) WHEN 2 THEN 'TOTAL' ELSE P.SECCOD + '-' + S.SECDES END AS AGRUPAMENTO, SUM(ISNULL(CASE WHEN R.REFCMP = 'K' THEN 0 ELSE ITSTOTFAT END, 0)) AS FATURAMENTO FROM ITEM_SAIDA I INNER JOIN V_LOJA LH WITH (NOLOCK) ON LH.LOJCOD = I.LOJCOD INNER JOIN HOLDING H WITH (NOLOCK) ON H.HOLCOD = LH.HOLCOD INNER JOIN REFERENCIA R WITH (NOLOCK) ON R.REFPLU = I.REFPLU INNER JOIN PRODUTO P WITH (NOLOCK) ON P.PROCOD = R.PROCOD LEFT JOIN FINALIDADE_PRODUTO_LOJA FPL WITH (NOLOCK) ON FPL.PROCOD = P.PROCOD AND FPL.LOJCOD = I.LOJCOD INNER JOIN AGENTE A WITH (NOLOCK) ON A.AGECOD = I.ITSAGECOD LEFT JOIN HOLDING HC WITH (NOLOCK) ON HC.HOLCOD = A.HOLCOD INNER JOIN SECAO S WITH (NOLOCK) ON S.SECCOD = P.SECCOD where I.ITSDOCDATEMI BETWEEN '")).append(dataInicial).append("' AND '").append(dataFinal).append("' AND EXISTS (select EXISTS_H.HOLCOD from HOLDING EXISTS_H inner join V_LOJA EXISTS_L on EXISTS_L.HOLCOD = EXISTS_H.HOLCOD inner join V_LOJA_ACESSO_FUNCIONARIO VLAF on VLAF.LOJCOD = EXISTS_L.LOJCOD where H.HOLCOD = EXISTS_L.HOLCOD AND VLAF.FUNCOD = '000119' group by EXISTS_H.HOLCOD) AND EXISTS (select VLAF.LOJCOD from V_LOJA_ACESSO_FUNCIONARIO VLAF where I.LOJCOD = VLAF.LOJCOD and VLAF.FUNCOD = '000119') AND P.SECCOD ='21' AND P.GRPCOD BETWEEN '001' AND '001' AND I.ITSDOCSTA = 'E' AND I.ITSTIP = 'E' AND I.ITSORICOD NOT IN ('085', '086') AND (I.ITSDOCSTANFE IS NULL OR I.ITSDOCSTANFE = 'A') GROUP BY P.SECCOD, S.SECDES WITH ROLLUP HAVING GROUPING(P.SECCOD) + GROUPING(SECDES) IN (0, 2)").toString();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs;
            for(rs = pst.executeQuery(); rs.next();)
                valorServicos = rs.getBigDecimal("faturamento");

            System.out.println(valorServicos);
            rs.close();
            pst.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }        
        conn.close(); 
        return valorServicos;
    }

    public BigDecimal buscaPecas()
        throws Exception
    {
        Connection conn = new ConnectionFactory().getConnection();
        try
        {
            int diaFinal = LocalDate.now().lengthOfMonth();
            int mes = LocalDate.now().getMonthValue();
            int ano = LocalDate.now().getYear();
            String dataInicial = (new StringBuilder(String.valueOf(ano))).append("/0").append(mes).append("/").append("01").toString();
            String dataFinal = (new StringBuilder(String.valueOf(ano))).append("/0").append(mes).append("/").append(diaFinal).toString();
            String sql = (new StringBuilder("SELECT CASE GROUPING(P.SECCOD) + GROUPING(SECDES) WHEN 2 THEN 'TOTAL' ELSE P.SECCOD + '-' + S.SECDES END AS AGRUPAMENTO, SUM(ISNULL(CASE WHEN R.REFCMP = 'K' THEN 0 ELSE ITSTOTFAT END, 0)) AS FATURAMENTO FROM ITEM_SAIDA I INNER JOIN V_LOJA LH WITH (NOLOCK) ON LH.LOJCOD = I.LOJCOD INNER JOIN HOLDING H WITH (NOLOCK) ON H.HOLCOD = LH.HOLCOD INNER JOIN REFERENCIA R WITH (NOLOCK) ON R.REFPLU = I.REFPLU INNER JOIN PRODUTO P WITH (NOLOCK) ON P.PROCOD = R.PROCOD LEFT JOIN FINALIDADE_PRODUTO_LOJA FPL WITH (NOLOCK) ON FPL.PROCOD = P.PROCOD AND FPL.LOJCOD = I.LOJCOD INNER JOIN AGENTE A WITH (NOLOCK) ON A.AGECOD = I.ITSAGECOD LEFT JOIN HOLDING HC WITH (NOLOCK) ON HC.HOLCOD = A.HOLCOD INNER JOIN SECAO S WITH (NOLOCK) ON S.SECCOD = P.SECCOD where I.ITSDOCDATEMI BETWEEN '")).append(dataInicial).append("' AND '").append(dataFinal).append("' AND EXISTS (select EXISTS_H.HOLCOD from HOLDING EXISTS_H inner join V_LOJA EXISTS_L on EXISTS_L.HOLCOD = EXISTS_H.HOLCOD inner join V_LOJA_ACESSO_FUNCIONARIO VLAF on VLAF.LOJCOD = EXISTS_L.LOJCOD where H.HOLCOD = EXISTS_L.HOLCOD AND VLAF.FUNCOD = '000119' group by EXISTS_H.HOLCOD) AND EXISTS (select VLAF.LOJCOD from V_LOJA_ACESSO_FUNCIONARIO VLAF where I.LOJCOD = VLAF.LOJCOD and VLAF.FUNCOD = '000119') AND P.SECCOD BETWEEN '04' AND '04' AND ISNULL(I.ITSDOCCMPVDA, 'N') <> 'N' AND I.ITSDOCSTA = 'E' AND I.ITSTIP = 'E' AND I.ITSORICOD NOT IN ('085', '086') AND (I.ITSDOCSTANFE IS NULL OR I.ITSDOCSTANFE = 'A')   GROUP BY P.SECCOD, S.SECDES WITH ROLLUP HAVING GROUPING(P.SECCOD) + GROUPING(SECDES) IN (0, 2)").toString();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs;
            for(rs = pst.executeQuery(); rs.next();)
                valorPecas = rs.getBigDecimal("faturamento");

            System.out.println(valorPecas);
            rs.close();
            pst.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        
        conn.close();
        return valorPecas;
    }

    public BigDecimal buscaTotal()
    {
        BigDecimal valorTotal = new BigDecimal(0);
        valorTotal = valorServicos.add(valorPecas);
        return valorTotal;
    }

    BigDecimal valorServicos;
    BigDecimal valorPecas;
}
