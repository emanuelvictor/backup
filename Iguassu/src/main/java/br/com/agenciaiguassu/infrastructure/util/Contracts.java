package br.com.agenciaiguassu.infrastructure.util;

import br.com.agenciaiguassu.domain.entity.Candidato;
import br.com.agenciaiguassu.domain.entity.Encaminhamento;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * Created by emanuel on 07/11/14.
 */
public abstract class Contracts {

    public final static String[] getContractCandidate(Candidato candidato) throws  Exception {

        String CPF = "__________________";
        if (candidato.getCpf()!=null){
            CPF = candidato.getCpf().toUpperCase();
        }
        String RG = "__________________";
        if (candidato.getRg()!=null){
            RG = candidato.getRg().toUpperCase();
        }

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        Paragraph titleDoc = new Paragraph("Um mundo de oportunidades para VOCÃŠ!", new Font(Font.FontFamily.HELVETICA, 15, Font.UNDEFINED));
        titleDoc.setAlignment(Element.TITLE);

        Paragraph underline1 = new Paragraph("_________________________________________________________________________________________________________________________________________________________________________________________________________________", new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL));
        underline1.setAlignment(Element.ALIGN_CENTER);
        underline1.setSpacingAfter(0);
        Paragraph nameDoc = new Paragraph("Contrato de prestaÃ§Ã£o de serviÃ§os", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        nameDoc.setAlignment(Element.TITLE);
        nameDoc.setSpacingAfter(8);
        nameDoc.setSpacingBefore(0);

        Paragraph footer = new Paragraph("Matriz: Avenida Juscelino Kubitschek - 201 - Sala 14 - Centro - Foz do IguaÃ§u - PR â€“ Fone: (45) 3572-1977\n" +
                "Filial: Rua JoÃ£o XXIII - 521 - Centro - Santa Terezinha de Itaipu - PR â€“ Fone: (45) 3541-0889\n" +
                "e-mail: iguassuagencia@hotmail.com", new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL));
        footer.setAlignment(Element.ALIGN_CENTER);

        Paragraph espacoEmBranco = new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL));


        Paragraph dadosDoContrato = (new Paragraph("Pelo presente instrumento particular de contrato de prestaÃ§Ã£o de serviÃ§os, de um " +
                "lado "+candidato.getNome().toUpperCase()+", portador (a) do RG: " +
                RG+" e inscrito (a) no CPF sob o nÂº "+CPF+", doravante " +
                "denominado simplesmente CONTRATANTE; de outro lado IGUASSU AGÃŠNCIA DE EMPREGOS, " +
                "localizada na AVENIDA JUSCELINO KUBITSCHEK â€“ 201 â€“ GALERIA WANNI â€“ SALA 14 - CENTRO â€“ FOZ DO IGUAÃ‡U â€“ PR," +
                " doravante denominada simplesmente CONTRATADA, convencionam:", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL)));
        dadosDoContrato.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph daPrestacaoDeServicos = new Paragraph("DA PRESTAÃ‡ÃƒO DE SERVIÃ‡OS E PROCEDIMENTOS", new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        daPrestacaoDeServicos.setAlignment(Element.ALIGN_CENTER);
        daPrestacaoDeServicos.setSpacingAfter(5);
        daPrestacaoDeServicos.setSpacingBefore(3);


        Paragraph clausulaPrimeira = new Paragraph("CLÃ�USULA PRIMEIRA: A prestaÃ§Ã£o de serviÃ§os tratada no presente instrumento serÃ¡ de qualificaÃ§Ã£o " +
                "profissional do CONTRATANTE e tentativa de recolocaÃ§Ã£o do CONTRATANTE no mercado de trabalho.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaPrimeira.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaPrimeira.setSpacingAfter(8);
        Paragraph clausulaSegunda = new Paragraph("CLÃ�USULA SEGUNDA: A qualificaÃ§Ã£o citada na clÃ¡usula primeira serÃ¡ compreendida pela prestaÃ§Ã£o de" +
                " serviÃ§os ao CONTRATANTE, sendo que lhe serÃ¡ disponibilizado junto Ã  sede da empresa CONTRATADA: ", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaPrimeira.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaSegunda.setSpacingAfter(8);

        Paragraph topicosClausulaSegunda = new Paragraph("a) AnÃ¡lise psicolÃ³gica, por uma vez, durante o perÃ­odo de 06 (seis) meses;\n" +
                "b) AuxÃ­lio na elaboraÃ§Ã£o de curriculum vitae;\n"+
                "c) participaÃ§Ã£o em cursos semanais, realizados na sede da empresa CONTRATADA;",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        topicosClausulaSegunda.setIndentationLeft(20);
        topicosClausulaSegunda.setSpacingAfter(8);

        Paragraph clausulaTerceira = new Paragraph("CLÃ�USULA TERCEIRA: A recolocaÃ§Ã£o no mercado de trabalho citada na clÃ¡usula primeira serÃ¡ " +
                "compreendida pela busca de vagas de emprego e encaminhamento para entrevistas junto Ã s empresas, desde que o CONTRATANTE" +
                " se enquadre no perfil exigido por quem estiver interessado na contrataÃ§Ã£o.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaTerceira.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaTerceira.setSpacingAfter(8);
        Paragraph clausulaQuarta = new Paragraph("CLÃ�USULA QUARTA: Feita a taxa de cadastro e realizados os procedimentos preparatÃ³rios, " +
                "o CONTRATANTE estarÃ¡ apto a ser encaminhado para uma vaga de emprego adequada ao seu perfil, assim que esta seja encontrada " +
                "pela parte CONTRATADA.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaQuarta.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaQuarta.setSpacingAfter(8);


        Paragraph clausulaQuartaParagrafo1 = new Paragraph("Â§1Âº â€“ Encontrada alguma vaga que se enquadre no perfil do CONTRATANTE, este deverÃ¡ pessoalmente " +
                "comparecer Ã  sede da CONTRATADA para retirar a ficha de encaminhamento, a qual lhe servirÃ¡ para fins de agendamento de entrevista junto " +
                "ao potencial empregador interessado.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaQuartaParagrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaQuartaParagrafo1.setIndentationLeft(20);
        clausulaQuartaParagrafo1.setSpacingAfter(7);

        Paragraph clausulaQuartaParagrafo2e3 = new Paragraph("Â§ 2Âª â€“ NÃ£o serÃ£o repassadas via telefone, e-mail, SMS ou quaisquer outros meios que nÃ£o seja pessoalmente " +
                "informaÃ§Ãµes acerca da vaga ofertada. \n" + "Â§ 3Âª â€“ NÃ£o serÃ£o informados ao CONTRATANTE, mesmo que possua cadastro ativo, quaisquer informaÃ§Ãµes de vagas de " +
                "emprego as quais nÃ£o se enquadrem dentro de seu perfil.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaQuartaParagrafo2e3.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaQuartaParagrafo2e3.setIndentationLeft(20);
        clausulaQuartaParagrafo2e3.setSpacingAfter(7);

        Paragraph clausulaQuinta = new Paragraph("CLÃ�USULA QUINTA: A CONTRATADA nÃ£o garante a contrataÃ§Ã£o do CONTRATANTE, mas sim oferece todos os recursos os quais dispÃµem para capacitaÃ§Ã£o" +
                " e encaminhamento a novo emprego que se enquadre em seu perfil.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaQuinta.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph daTaxaDeCadastro = new Paragraph("DA TAXA DE CADASTRO E DOS HONORÃ�RIOS PELO ÃŠXITO", new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        daTaxaDeCadastro.setAlignment(Element.ALIGN_CENTER);
        daTaxaDeCadastro.setSpacingAfter(3);
        daTaxaDeCadastro.setSpacingBefore(3);

        Paragraph clausulaSexta = new Paragraph("CLÃ�USULA SEXTA: A contraprestaÃ§Ã£o da prestaÃ§Ã£o de serviÃ§os contratada pelo presente instrumento " +
                "serÃ¡ dividida em taxa de cadastro e honorÃ¡rios pelo Ãªxito.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaSexta.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaSexta.setSpacingAfter(8);

        Paragraph clausulaSetima = new Paragraph("CLÃ�USULA SÃ‰TIMA: A taxa de cadastro Ã© uma Ãºnica prestaÃ§Ã£o, paga pela parte CONTRATANTE no ato da assinatura do presente contrato, " +
                "no importe de R$ 30,00 (trinta reais), e que lhe dÃ¡ " +
                "pelo prazo de 06 (seis) meses a possibilidade de dispor das prestaÃ§Ãµes de serviÃ§os contidas nas CLÃ�USULAS SEGUNDA e TERCEIRA do presente instrumento. ", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaSetima.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaSetima.setSpacingAfter(8);

        Paragraph paragrafosClausulaSetima = new Paragraph("Â§1Âº - A taxa de cadastro tem validade de 06 (seis) meses, sendo que expirado este prazo, caso haja interesse de a parte CONTRATANTE continuar a fruir das prestaÃ§Ãµes de serviÃ§os contidas nas CLÃ�USULAS SEGUNDA e TERCEIRA do presente instrumento, deverÃ¡ comparecer Ã  sede da CONTRATADA e fazer novo pagamento de taxa de cadastro. \n" +
                "Â§2Âº - Na hipÃ³tese da parte CONTRATANTE ser admitido por alguma empresa por indicaÃ§Ã£o da parte CONTRATADA, e dentro do perÃ­odo de seis meses buscarem novamente seus serviÃ§os para recolocaÃ§Ã£o profissional, a TAXA DE CADASTRO serÃ¡ reaproveitada, sendo que atÃ© o limite de 06 (seis) meses, a parte CONTRATANTE poderÃ¡ usufruir das prestaÃ§Ãµes de serviÃ§os contidas nas CLÃ�USULAS SEGUNDA e TERCEIRA do presente instrumento, portanto se houver nova admissÃ£o sob indicaÃ§Ã£o da CONTRATADA, o mesmo deverÃ¡ efetuar o pagamento da taxa de agenciamento.\n" +
                "Â§ 3Âº - A taxa de cadastro Ã© INDIVIDUAL e INTRANSFERÃ�VEL, nÃ£o podendo ser repassada para qualquer outra pessoa, mesmo com desistÃªncia ou admissÃ£o do CONTRATANTE antes do prazo de 06 (seis) meses.",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        paragrafosClausulaSetima.setIndentationLeft(20);
        paragrafosClausulaSetima.setSpacingAfter(8);

        Paragraph clausulaOitava = new Paragraph("CLÃ�USULA OITAVA: Os honorÃ¡rios pelo Ãªxito sÃ£o uma contraprestaÃ§Ã£o a ser paga em favor da parte CONTRATADA " +
                "em decorrÃªncia desta obter Ãªxito em recolocar a parte CONTRATANTE no mercado de trabalho, em emprego por ela indicada" +
                ", nos valores:", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaOitava.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaOitava.setSpacingAfter(8);

        Paragraph paragrafosClausulaOitava = new Paragraph("Â§ 1Âº - 30% (trinta por cento), sobre o salÃ¡rio base (salÃ¡rio bruto) a ser pago pelo CONTRATANTE;",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        paragrafosClausulaOitava.setIndentationLeft(20);
        paragrafosClausulaOitava.setSpacingAfter(8);

        Paragraph clausulaNona = new Paragraph("CLÃ�USULA NONA: O pagamento dos honorÃ¡rios pelo Ãªxito serÃ¡ feitos atÃ© o quinto dia Ãºtil do mÃªs" +
                " subsequente da admissÃ£o do CONTRATANTE para vaga de trabalho por encaminhamento feito pela CONTRATADA.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaNona.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaNona.setSpacingAfter(8);


        Paragraph paragrafosClausulaNona = new Paragraph("Â§1Âº - O pagamento serÃ¡ cobrado em parcela Ãºnica em dinheiro ou cartÃ£o de crÃ©dito",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        paragrafosClausulaNona.setIndentationLeft(20);
        paragrafosClausulaNona.setSpacingAfter(8);


        Paragraph clausulaDecima = new Paragraph("CLÃ�USULA DÃ‰CIMA: Em nÃ£o sendo efetuado o pagamento na data acordada, serÃ¡ cobrado MULTA de R$ 7,91 (sete reais e noventa e " +
                "um centavos) e juros de mora no importe de R$ 0,27 (vinte e sete centavos) por dia de atraso.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaDecima.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaDecima.setSpacingAfter(8);

        Paragraph clausulaDecimaPrimeira = new Paragraph("CLÃ�USULA DÃ‰CIMA PRIMEIRA: ApÃ³s 30 (trinta) dias de atraso no pagamento dos honorÃ¡rios de Ãªxito, a CONTRATADA procederÃ¡ Ã  " +
                "inclusÃ£o do nome do CONTRATANTE no banco de dados dos serviÃ§os de proteÃ§Ã£o ao crÃ©dito (SCPC E SERASA) e ajuizarÃ¡ a competente aÃ§Ã£o de cobranÃ§a judicial, " +
                "sendo este responsÃ¡vel pelo pagamento de custas e honorÃ¡rios advocatÃ­cios.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaDecimaPrimeira.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaDecimaPrimeira.setSpacingAfter(8);

        Paragraph clausulaDecimaSegunda = new Paragraph("CLÃ�USULA DÃ‰CIMA SEGUNDA: Os honorÃ¡rios pelo Ãªxito sÃ£o uma contraprestaÃ§Ã£o de serviÃ§os paga A CADA indicaÃ§Ã£o de emprego com" +
                " sucesso feito pela parte CONTRATADA. Assim, sucessivas indicaÃ§Ãµes com Ãªxito irÃ£o gerar sucessivos honorÃ¡rios, tantas quantas forem Ã s admissÃµes por indicaÃ§Ã£o.",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaDecimaSegunda.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaDecimaSegunda.setSpacingAfter(8);

        Paragraph daVigencia = new Paragraph("DA VIGÃŠNCIA E EXTINÃ‡ÃƒO DO PRESENTE INSTRUMENTO", new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        daVigencia.setAlignment(Element.ALIGN_CENTER);
        daVigencia.setSpacingAfter(3);
        daVigencia.setSpacingBefore(3);

        Paragraph clausulaDecimaTerceira = new Paragraph("CLÃ�USULA DÃ‰CIMA TERCEIRA: A vigÃªncia do presente contrato Ã© de 06 (seis) meses, iniciando na data abaixo assinada, podendo ser prorrogado, sempre que o CONTRATANTE manifeste sua vontade em fazÃª-lo mediante o pagamento de nova taxa de cadastro. O nÃ£o pagamento de nova taxa de cadastro no prazo acordado implica automaticamente em extinÃ§Ã£o do presente instrumento.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaDecimaTerceira.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaDecimaTerceira.setSpacingAfter(8);

        Paragraph clausulaDecimaQuarta = new Paragraph("CLÃ�USULA DÃ‰CIMA QUARTA: A admissÃ£o do CONTRATANTE em emprego por encaminhamento feito pela CONTRATADA extingue as obrigaÃ§Ãµes entre as partes, remanescendo somente o pagamento dos honorÃ¡rios pelo Ãªxito estipulado na CLÃ�USULA OITAVA no prazo acordado na CLÃ�USULA NONA.", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaDecimaQuarta.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaDecimaQuarta.setSpacingAfter(8);

        Paragraph clausulaDecimaQuinta = new Paragraph("CLÃ�USULA DÃ‰CIMA QUINTA: Caso o CONTRATANTE venha a desistir do presente instrumento apÃ³s a sua assinatura PERDERÃ� a favor da CONTRATADA o valor pago referente a taxa de inscriÃ§Ã£o, tendo em vista que a mesma destina-se a despesas de abertura de cadastro e disponibilizaÃ§Ã£o de serviÃ§os, conforme exposto." +
                ", nos valores:", new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        clausulaDecimaQuinta.setAlignment(Element.ALIGN_JUSTIFIED);
        clausulaDecimaQuinta.setSpacingAfter(8);

        Paragraph asPartesEmComumAcordo = new Paragraph("As partes de comum acordo elegem o foro desta comarca de Foz do IguaÃ§u em declÃ­nio ao qualquer outro, por mais privilegiado que seja, para dirimir quaisquer dÃºvidas por ventura suscitadas, o qual vai lavrado e assinado desde via.",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        asPartesEmComumAcordo.setAlignment(Element.ALIGN_JUSTIFIED);

        Paragraph date = new Paragraph("Foz do IguaÃ§u, "+new SimpleDateFormat("dd/MM/yyyy").format(candidato.getDataDeContrato().getTime()),
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingBefore(100);
        date.setSpacingAfter(100);


        Paragraph assinaturas = new Paragraph("                _____________________                _____________________                ",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        assinaturas.setAlignment(Element.ALIGN_CENTER);

        Paragraph nomes = new Paragraph("                   Contratante                                  Iguassu agÃªncia de empregos                ",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        nomes.setAlignment(Element.ALIGN_CENTER);

        Paragraph cpfRg = new Paragraph("                     CPF:\n                      RG:",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        cpfRg.setAlignment(Element.ALIGN_LEFT);
        cpfRg.setSpacingAfter(100);

        Paragraph testeMunha1 = new Paragraph("Testemunha (01): __________________________________________\n" +
                "                        RG: __________________________________________",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        testeMunha1.setAlignment(Element.ALIGN_LEFT);

        Paragraph testeMunha2 = new Paragraph("Testemunha (02): __________________________________________\n" +
                "                        RG: __________________________________________",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        testeMunha2.setAlignment(Element.ALIGN_LEFT);
        testeMunha2.setSpacingAfter(118);

        //Pegando e abrindo o documento
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/candidatos/Contrato_" + candidato.getId() + ".pdf"));


        document.open();
        document.add(topImageDoc);
        document.add(titleDoc);
        document.add(underline1);
        document.add(nameDoc);

        document.add(dadosDoContrato);
        document.add(daPrestacaoDeServicos);
        document.add(clausulaPrimeira);
        document.add(clausulaSegunda);
        document.add(topicosClausulaSegunda);
        document.add(clausulaTerceira);
        document.add(clausulaQuarta);
        document.add(clausulaQuartaParagrafo1);
        document.add(underline1);
        document.add(footer);
        document.newPage();


        document.add(clausulaQuartaParagrafo2e3);
        document.add(clausulaQuinta);
        document.add(daTaxaDeCadastro);
        document.add(clausulaSexta);
        document.add(clausulaSetima);
        document.add(paragrafosClausulaSetima);
        document.add(clausulaOitava);
        document.add(underline1);
        document.add(footer);
        document.add(paragrafosClausulaOitava);
        document.add(clausulaNona);
        document.add(paragrafosClausulaNona);
        document.add(clausulaDecima);
        document.add(clausulaDecimaPrimeira);
        document.add(clausulaDecimaSegunda);

        document.add(daVigencia);
        document.add(clausulaDecimaTerceira);
        document.add(clausulaDecimaQuarta);
        document.add(clausulaDecimaQuinta);
        document.add(underline1);
        document.add(footer);
        document.add(asPartesEmComumAcordo);
        document.add(date);
        document.add(assinaturas);
        document.add(nomes);
        document.add(cpfRg);
        document.add(testeMunha1);
        document.add(testeMunha2);
        document.add(underline1);
        document.add(footer);
        document.close();

        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/candidatos/Contrato_"+candidato.getId()+".pdf"};

    }

    public final static String[] getContractForward(Encaminhamento encaminhamento) throws  Exception {

        Image topImageDoc = Image.getInstance("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/title.png");
        topImageDoc.setAlignment(Element.ALIGN_CENTER);

        String empresaString = new String();
        String enderecoEmpresaString = new String();
        String contatosEmpresaString = new String();
        String emailEmpresaString = new String();

        if (encaminhamento.getVaga().getEndereco()!=null){
            enderecoEmpresaString = encaminhamento.getVaga().getEndereco().toString();
        }
        if (encaminhamento.getVaga().getEmpresa()!=null){
            empresaString = encaminhamento.getVaga().getEmpresa().getNome();
            if (encaminhamento.getVaga().getEmpresa().getTelefoneComercial()!=null){
                contatosEmpresaString = encaminhamento.getVaga().getEmpresa().getTelefoneComercial();
            }
            if (encaminhamento.getVaga().getEmpresa().getTelefoneResidencial()!=null){
                contatosEmpresaString = contatosEmpresaString + " / " + encaminhamento.getVaga().getEmpresa().getTelefoneResidencial();
            }
            if (encaminhamento.getVaga().getEmpresa().getEmail()!=null){
                emailEmpresaString = encaminhamento.getVaga().getEmpresa().getEmail();
            }
        }

        //Pegando e abrindo o documento
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_" + encaminhamento.getId() + ".pdf"));

        Paragraph titleDoc = new Paragraph("Encaminhamento", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD));
        titleDoc.setAlignment(Element.TITLE);
        titleDoc.setSpacingAfter(8);
        titleDoc.setSpacingBefore(0);

        Paragraph empresa = new Paragraph("Empresa: "+empresaString, new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL));
        empresa.setAlignment(Element.ALIGN_LEFT);

        Paragraph endereco = new Paragraph("EndereÃ§o da vaga: "+enderecoEmpresaString, new Font(Font.FontFamily.COURIER, 15, Font.NORMAL));
        endereco.setAlignment(Element.ALIGN_LEFT);

        Paragraph contatos = new Paragraph("Contatos: "+contatosEmpresaString, new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL));
        contatos.setAlignment(Element.ALIGN_LEFT);

        Paragraph email = new Paragraph("Email: "+emailEmpresaString, new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL));
        email.setAlignment(Element.ALIGN_LEFT);

        Paragraph horarioDaEntrevista = new Paragraph("HorÃ¡rio: __________________________________", new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL));
        horarioDaEntrevista.setAlignment(Element.ALIGN_LEFT);


        Paragraph estamosEncaminhando = new Paragraph("Estamos encaminhando o Sr.(a) "+encaminhamento.getCandidato().getNome()+
                " para entrevista para a funÃ§Ã£o de "+encaminhamento.getVaga().getCargo().getNome()+" igualmente, caso a entrevista seja favorÃ¡vel por gentileza entrar em contato com a agÃªncia pelo tel.: 3572- 1977.", new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL));
        estamosEncaminhando.setAlignment(Element.ALIGN_JUSTIFIED);
        estamosEncaminhando.setSpacingBefore(10);
        estamosEncaminhando.setSpacingAfter(10);
        estamosEncaminhando.setFirstLineIndent(60);

        Paragraph agradecimentos = new Paragraph("Agradecemos Ã  costumeira atenÃ§Ã£o.", new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL));
        agradecimentos.setSpacingBefore(10);
        agradecimentos.setAlignment(Element.ALIGN_CENTER);


        Paragraph assinatura = new Paragraph("                __________________________________________                ",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        assinatura.setAlignment(Element.ALIGN_CENTER);
        assinatura.setSpacingBefore(30);

        Paragraph date = new Paragraph("Foz do IguaÃ§u, "+new SimpleDateFormat("dd/MM/yyyy").format(encaminhamento.getDataDeCadastro().getTime()),
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingBefore(40);
        date.setSpacingAfter(50);

        Paragraph nomeIguassu = new Paragraph("              Iguassu AgÃªncia de Empregos                ",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        nomeIguassu.setAlignment(Element.ALIGN_CENTER);


        Paragraph nomeCandidato = new Paragraph("              "+encaminhamento.getCandidato().getNome()+"                ",
                new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD));
        nomeCandidato.setAlignment(Element.ALIGN_CENTER);

        Paragraph enderecoIguassu = new Paragraph("AVENIDA J.Kâ€“ 201 â€“ GALERIA WANI â€“ SALA 25 â€“ CENTRO - TEL. 3572 -1977",
                new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL));
        enderecoIguassu.setAlignment(Element.ALIGN_CENTER);



        document.open();
        document.add(topImageDoc);

        document.add(titleDoc);
        if (empresaString!=null&&empresaString.trim()==""){
            document.add(empresa);
        }

        if (contatosEmpresaString!=null&&contatosEmpresaString.trim()==""){
            document.add(contatos);
        }

        if (emailEmpresaString!=null&&emailEmpresaString.trim()==""){
            document.add(email);
        }

        document.add(horarioDaEntrevista);

        if (enderecoEmpresaString!=null&&enderecoEmpresaString.trim()==""){
            document.add(endereco);
        }

        document.add(estamosEncaminhando);
        document.add(agradecimentos);

        document.add(assinatura);
        document.add(nomeCandidato);

        document.add(assinatura);
        document.add(nomeIguassu);

        document.add(date);
        document.add(enderecoIguassu);


        document.close();


        return new String[]{"/app/Iguassu/app/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/reports/encaminhamentos/Encaminhamento_" + encaminhamento.getId() + ".pdf"};
    }

}
