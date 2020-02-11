package br.edu.udc.sistemas.IAVI.emanuelvictor.util;

/**
 * Created by emanuelvictor on 26/08/14.
 */
public class Query {

    String sintaxe = "insert into tabela values (valores)";

    public static final String getSQLInsert(Object obj) throws Exception {
        return "insert into " + "TABELA" + " " + "("
                + "NOMES" + ") " + "values("
                + "VALORES" + ")";
    }

    public static final String getSQLUpdate(Object obj) throws Exception {
        return "update " + "TABELA" + " " + "set "
                + "COLUNA1=VALOR1,COLUNA2=VALOR2" + " " + "where " + "ID DO OBJETO" // AQUI ENTRA A CONDIÇÃO, POR DEFAULT, USAREMOS O ID
                + " = " + "VALOR DO ID DO OBJETO";
    }

    public static final String getSQLDelete(Object obj) throws Exception {
        String sql = "delete from " + "TABELA" + " " + "where "
                + "ID DO OBJETO" + " = " + "VALOR DO ID DO OBJETO";
        return sql;
    }

    public static final String getSQLSelect(Object obj) throws Exception {
        String sql = "select " + "CAMPOS A SEREM BUSCADOS" //selecione tal, podemos usar só '*'
                + " " + "from "
                + "NOME DA TABELA" + " "
                + "ISSO FOR IGUAL A ISSO"; // QUANDO ESSA CONDIÇÃO FOR ATENDIDA
        return sql;
    }


}
