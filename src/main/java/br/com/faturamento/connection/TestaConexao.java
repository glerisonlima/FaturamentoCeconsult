// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TestaConexao.java

package br.com.faturamento.connection;


import java.sql.*;

// Referenced classes of package br.com.faturamento.connection:
//            ConnectionFactory

public class TestaConexao extends ConnectionFactory
{

    public TestaConexao()
    {
    }

    public static void main(String args[])
        throws SQLException
    {
        Connection conn = null;
        try
        {
            conn = (new ConnectionFactory()).getConnection();
            String sql = "SELECT CASE GROUPING(P.SECCOD) + GROUPING(SECDES) WHEN 2 THEN 'TOTAL' ELSE P.SECCOD + '-' + S.SECDES END AS AGRUPAMENTO, SUM(ISNULL(CASE WHEN R.REFCMP = 'K' THEN 0 ELSE ITSTOTFAT END, 0)) AS FATURAMENTO FROM ITEM_SAIDA I INNER JOIN V_LOJA LH WITH (NOLOCK) ON LH.LOJCOD = I.LOJCOD INNER JOIN HOLDING H WITH (NOLOCK) ON H.HOLCOD = LH.HOLCOD INNER JOIN REFERENCIA R WITH (NOLOCK) ON R.REFPLU = I.REFPLU INNER JOIN PRODUTO P WITH (NOLOCK) ON P.PROCOD = R.PROCOD LEFT JOIN FINALIDADE_PRODUTO_LOJA FPL WITH (NOLOCK) ON FPL.PROCOD = P.PROCOD AND FPL.LOJCOD = I.LOJCOD INNER JOIN AGENTE A WITH (NOLOCK) ON A.AGECOD = I.ITSAGECOD LEFT JOIN HOLDING HC WITH (NOLOCK) ON HC.HOLCOD = A.HOLCOD INNER JOIN SECAO S WITH (NOLOCK) ON S.SECCOD = P.SECCOD where I.ITSDOCDATEMI BETWEEN '2016/03/02' AND '2016/03/30' AND EXISTS (select EXISTS_H.HOLCOD from HOLDING EXISTS_H inner join V_LOJA EXISTS_L on EXISTS_L.HOLCOD = EXISTS_H.HOLCOD inner join V_LOJA_ACESSO_FUNCIONARIO VLAF on VLAF.LOJCOD = EXISTS_L.LOJCOD where H.HOLCOD = EXISTS_L.HOLCOD AND VLAF.FUNCOD = '000119' group by EXISTS_H.HOLCOD) AND EXISTS (select VLAF.LOJCOD from V_LOJA_ACESSO_FUNCIONARIO VLAF where I.LOJCOD = VLAF.LOJCOD and VLAF.FUNCOD = '000119') AND P.SECCOD in ('04','21') AND P.GRPCOD BETWEEN '001' AND '001' AND I.ITSDOCSTA = 'E' AND I.ITSTIP = 'E' AND I.ITSORICOD NOT IN ('085', '086') AND (I.ITSDOCSTANFE IS NULL OR I.ITSDOCSTANFE = 'A') GROUP BY P.SECCOD, S.SECDES WITH ROLLUP HAVING GROUPING(P.SECCOD) + GROUPING(SECDES) IN (0, 2)";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            double valor;
            for(valor = 0.0D; rs.next(); valor = rs.getDouble("faturamento"));
            System.out.println(valor);
            rs.close();
            pst.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        
        conn.close(); 
    }
}
