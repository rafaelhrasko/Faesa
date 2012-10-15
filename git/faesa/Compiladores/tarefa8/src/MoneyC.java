/* Generated By:JavaCC: Do not edit this line. MoneyC.java */
import java.io.*;
import java.util.*;

public class MoneyC implements MoneyCConstants {
  static Tabela tab = new Tabela();
  public static void main(String args []) throws ParseException
  {
    MoneyC analisador = null;
      try {
         analisador = new MoneyC(new FileInputStream(args [0]));
         analisador.inicio();
      }
      catch(FileNotFoundException e) {
         System.out.println("Erro: arquivo n\u00e3o encontrado");
      }
      catch(TokenMgrError e) {
         System.out.println("Erro l\u00e9xico\u005cn" + e.getMessage());
      }
      catch(ParseException e) {
         System.out.println("Erro sint\u00e1tico\u005cn" + e.getMessage());
      }
  }

  static final public void inicio() throws ParseException {
    trace_call("inicio");
    try {
                 System.out.println("inicio");
      bloco_codigo();
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIPO_STRING:
        case TIPO_INTEIRO:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        funcao();
      }
      jj_consume_token(0);
    } finally {
      trace_return("inicio");
    }
  }

  static final public void funcao() throws ParseException {
    trace_call("funcao");
    try {
      tipo_variavel();
      jj_consume_token(NOME_FUNCAO);
      jj_consume_token(ABRE_EXPRESSAO);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIPO_STRING:
      case TIPO_INTEIRO:
        declaracao_parametros();
        break;
      default:
        jj_la1[1] = jj_gen;
        ;
      }
      jj_consume_token(FECHA_EXPRESSAO);
      bloco_codigo();
    } finally {
      trace_return("funcao");
    }
  }

  static final public void declaracao_parametros() throws ParseException {
    trace_call("declaracao_parametros");
    try {
      if (jj_2_1(3)) {
        tipo_variavel();
        jj_consume_token(VARIAVEL);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIPO_STRING:
        case TIPO_INTEIRO:
          tipo_variavel();
          jj_consume_token(VARIAVEL);
          jj_consume_token(VIRGULA);
          declaracao_parametros();
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } finally {
      trace_return("declaracao_parametros");
    }
  }

  static final public LinkedList<Comando> bloco_codigo() throws ParseException {
    trace_call("bloco_codigo");
    try {
        LinkedList<Comando> comandos = new LinkedList<Comando>();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ABRE_BLOCO:
        jj_consume_token(ABRE_BLOCO);
        label_2:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case VARIAVEL:
          case IDENTIFICADOR_VARIAVEL:
          case SAIDA_DADOS:
          case SE:
          case ENQUANTO:
          case NOME_FUNCAO:
            ;
            break;
          default:
            jj_la1[3] = jj_gen;
            break label_2;
          }
          linha_comando(comandos);
        }
        jj_consume_token(FECHA_BLOCO);
        break;
      default:
        jj_la1[5] = jj_gen;
        label_3:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case VARIAVEL:
          case IDENTIFICADOR_VARIAVEL:
          case SAIDA_DADOS:
          case SE:
          case ENQUANTO:
          case NOME_FUNCAO:
            ;
            break;
          default:
            jj_la1[4] = jj_gen;
            break label_3;
          }
          linha_comando(comandos);
        }
        {if (true) return comandos;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("bloco_codigo");
    }
  }

  static final public void linha_comando(LinkedList<Comando> comandos) throws ParseException {
    trace_call("linha_comando");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SAIDA_DADOS:
        comando_exibe(comandos);
        break;
      case IDENTIFICADOR_VARIAVEL:
        comando_declaracao();
        break;
      default:
        jj_la1[6] = jj_gen;
        if (jj_2_2(2)) {
          comando_atribuicao(comandos);
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case SE:
            comando_fluxo_se(comandos);
            break;
          case ENQUANTO:
            comando_fluxo_enquanto(comandos);
            break;
          default:
            jj_la1[7] = jj_gen;
            if (jj_2_3(2)) {
              chamada_funcao();
            } else {
              jj_consume_token(-1);
              throw new ParseException();
            }
          }
        }
      }
      jj_consume_token(FIM_COMANDO);
    } finally {
      trace_return("linha_comando");
    }
  }

  static final public void comando_exibe(LinkedList<Comando> comandos) throws ParseException {
    trace_call("comando_exibe");
    try {
  Token t; Object o;
      jj_consume_token(SAIDA_DADOS);
      jj_consume_token(ABRE_EXPRESSAO);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        t = jj_consume_token(STRING);
        comandos.add(new Comando('I',t));
        break;
      case VARIAVEL:
      case INT:
      case SUBTRACAO:
      case ABRE_EXPRESSAO:
        o = expressao_aritmetica(null);
        comandos.add(new Comando('I',o));
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VIRGULA:
        jj_consume_token(VIRGULA);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          t = jj_consume_token(STRING);
        comandos.add(new Comando('I',t));
          break;
        case VARIAVEL:
        case INT:
        case SUBTRACAO:
        case ABRE_EXPRESSAO:
          o = expressao_aritmetica(null);
        comandos.add(new Comando('I',o));
          break;
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      jj_consume_token(FECHA_EXPRESSAO);
    } finally {
      trace_return("comando_exibe");
    }
  }

  static final public void comando_declaracao() throws ParseException {
    trace_call("comando_declaracao");
    try {
                            Token t; char tp; Simbolo simb;
      jj_consume_token(IDENTIFICADOR_VARIAVEL);
      tp = tipo_variavel();
      t = jj_consume_token(VARIAVEL);
                    simb = new Simbolo(tp, t.image);
                    if (!(tab.inclui(simb))) System.out.println("Erro semantico\u005cn" + simb.getNome() + " ja existe!");
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VIRGULA:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_4;
        }
        jj_consume_token(VIRGULA);
        t = jj_consume_token(VARIAVEL);
                 simb = new Simbolo(tp, t.image);
                 if (!(tab.inclui(simb))) System.out.println("Erro semantico\u005cn" + simb.getNome() + " ja existe!");
      }
    } finally {
      trace_return("comando_declaracao");
    }
  }

  static final public void comando_atribuicao(LinkedList<Comando> comandos) throws ParseException {
    trace_call("comando_atribuicao");
    try {
  Token t; Object o;
      t = jj_consume_token(VARIAVEL);
      jj_consume_token(ATRIBUICAO);
      o = expressao_aritmetica(null);
    if (!(tab.isExiste(t.image)))
    {
       System.out.println("Erro semantico\u005cn" + t.image + " nao foi declarado!");
    }else
    {
       comandos.add(new Comando('A',t.image,o));
        }
    } finally {
      trace_return("comando_atribuicao");
    }
  }

  static final public void comando_fluxo_se(LinkedList<Comando> comandos) throws ParseException {
    trace_call("comando_fluxo_se");
    try {
        Object expIf; Object blocoIf; Object blocoElse = null;
      jj_consume_token(SE);
      jj_consume_token(ABRE_EXPRESSAO);
      expIf = expressao_booleana();
      jj_consume_token(FECHA_EXPRESSAO);
      blocoIf = bloco_codigo();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SENAO:
        jj_consume_token(SENAO);
        blocoElse = bloco_codigo();
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      jj_consume_token(FIM_SE);
    comandos.add(new Comando('S',expIf,blocoIf,blocoElse));
    } finally {
      trace_return("comando_fluxo_se");
    }
  }

  static final public void comando_fluxo_enquanto(LinkedList<Comando> comandos) throws ParseException {
    trace_call("comando_fluxo_enquanto");
    try {
  Object exp; Object bloco;
      jj_consume_token(ENQUANTO);
      jj_consume_token(ABRE_EXPRESSAO);
      exp = expressao_booleana();
      jj_consume_token(FECHA_EXPRESSAO);
      bloco = bloco_codigo();
      jj_consume_token(FIM_ENQUANTO);
      comandos.add(new Comando('E',exp,bloco));
    } finally {
      trace_return("comando_fluxo_enquanto");
    }
  }

  static final public void chamada_funcao() throws ParseException {
    trace_call("chamada_funcao");
    try {
      jj_consume_token(NOME_FUNCAO);
      jj_consume_token(ABRE_EXPRESSAO);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIAVEL:
      case INT:
      case SUBTRACAO:
      case ABRE_EXPRESSAO:
        lista_parametros();
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      jj_consume_token(FECHA_EXPRESSAO);
    } finally {
      trace_return("chamada_funcao");
    }
  }

  static final public void lista_parametros() throws ParseException {
    trace_call("lista_parametros");
    try {
      expressao_aritmetica(null);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case VIRGULA:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_5;
        }
        jj_consume_token(VIRGULA);
        expressao_aritmetica(null);
      }
    } finally {
      trace_return("lista_parametros");
    }
  }

  static final public Arvore expressao_booleana() throws ParseException {
    trace_call("expressao_booleana");
    try {
  Token t;Item item;
  Arvore listaExp = new Arvore();
      fator_expressao_booleana(listaExp);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LOGICO_E:
        case LOGICO_OU:
          ;
          break;
        default:
          jj_la1[15] = jj_gen;
          break label_6;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LOGICO_E:
          t = jj_consume_token(LOGICO_E);
                                item = new Item('&',t.image);
                                listaExp.add(item);
          break;
        case LOGICO_OU:
          t = jj_consume_token(LOGICO_OU);
                                item = new Item('|',t.image);
                                listaExp.add(item);
          break;
        default:
          jj_la1[16] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        fator_expressao_booleana(listaExp);
      }
      {if (true) return listaExp;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("expressao_booleana");
    }
  }

  static final public Arvore fator_expressao_booleana(Arvore listaExp) throws ParseException {
    trace_call("fator_expressao_booleana");
    try {
        Token t;Item item;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIAVEL:
      case INT:
      case SUBTRACAO:
      case ABRE_EXPRESSAO:
        listaExp = expressao_aritmetica(null);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case REL_MAIOR:
          t = jj_consume_token(REL_MAIOR);
                                item = new Item('>',t.image);
                                listaExp.add(item);
          break;
        case REL_MENOR:
          t = jj_consume_token(REL_MENOR);
                                item = new Item('<',t.image);
                                listaExp.add(item);
          break;
        case REL_MAIOR_IGUAL:
          t = jj_consume_token(REL_MAIOR_IGUAL);
                                item = new Item('M',t.image);
                                listaExp.add(item);
          break;
        case REL_MENOR_IGUAL:
          t = jj_consume_token(REL_MENOR_IGUAL);
                                item = new Item('m',t.image);
                                listaExp.add(item);
          break;
        case REL_IGUALDADE:
          t = jj_consume_token(REL_IGUALDADE);
                                item = new Item('=',t.image);
                                listaExp.add(item);
          break;
        case REL_DIFERENCA:
          t = jj_consume_token(REL_DIFERENCA);
                                item = new Item('!',t.image);
                                listaExp.add(item);
          break;
        default:
          jj_la1[17] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        listaExp = expressao_aritmetica(listaExp);
        break;
      case LOGICO_NAO:
        jj_consume_token(LOGICO_NAO);
        jj_consume_token(ABRE_EXPRESSAO);
        fator_expressao_booleana(listaExp);
        jj_consume_token(FECHA_EXPRESSAO);
        break;
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          {if (true) return listaExp;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("fator_expressao_booleana");
    }
  }

  static final public void concatenacao_string(LinkedList<Item> listaExp_) throws ParseException {
    trace_call("concatenacao_string");
    try {
  Token t; Item item;
  LinkedList<Item> listaExp;
  if (listaExp_ == null)
          listaExp = new LinkedList<Item>();
        else
          listaExp = listaExp_;
      t = jj_consume_token(STRING);
          item = new Item('s',t.image);
          listaExp.add(item);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ADICAO:
          ;
          break;
        default:
          jj_la1[19] = jj_gen;
          break label_7;
        }
        t = jj_consume_token(ADICAO);
                        item = new Item('o',t.image);
                        listaExp.add(item);
        t = jj_consume_token(STRING);
          item = new Item('s',t.image);
          listaExp.add(item);
      }
    } finally {
      trace_return("concatenacao_string");
    }
  }

  static final public Arvore expressao_aritmetica(Arvore listaExp_) throws ParseException {
    trace_call("expressao_aritmetica");
    try {
  Token t;Item item;
  Arvore listaExp;
  listaExp = new Arvore();
      operadorMultiplicativo(listaExp);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ADICAO:
        case SUBTRACAO:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_8;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SUBTRACAO:
          t = jj_consume_token(SUBTRACAO);
          break;
        case ADICAO:
          t = jj_consume_token(ADICAO);
          break;
        default:
          jj_la1[21] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                        item = new Item('o',t.image);
                        listaExp.add(item);
        operadorMultiplicativo(listaExp);
      }
           if (listaExp_ != null)
           {
             listaExp_.add(listaExp);
             System.out.println(listaExp_.meDahPostFix());
           {if (true) return listaExp_;}
           }else
           {
                System.out.println(listaExp.meDahPostFix());
                {if (true) return listaExp;}
                }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("expressao_aritmetica");
    }
  }

  static final public void operadorMultiplicativo(Arvore listaExp) throws ParseException {
    trace_call("operadorMultiplicativo");
    try {
                                                Token t;Item item;
      potenciacao(listaExp);
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MULTIPLICACAO:
        case DIVISAO:
          ;
          break;
        default:
          jj_la1[22] = jj_gen;
          break label_9;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MULTIPLICACAO:
          t = jj_consume_token(MULTIPLICACAO);
          break;
        case DIVISAO:
          t = jj_consume_token(DIVISAO);
          break;
        default:
          jj_la1[23] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                        item = new Item('o',t.image);
                        listaExp.add(item);
        potenciacao(listaExp);
      }
    } finally {
      trace_return("operadorMultiplicativo");
    }
  }

  static final public void potenciacao(Arvore listaExp) throws ParseException {
    trace_call("potenciacao");
    try {
                                     Token t;Item item;
      elemento(listaExp);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case POTENCIA:
          ;
          break;
        default:
          jj_la1[24] = jj_gen;
          break label_10;
        }
        t = jj_consume_token(POTENCIA);
                        item = new Item('o',t.image);
                        listaExp.add(item);
        elemento(listaExp);
      }
    } finally {
      trace_return("potenciacao");
    }
  }

  static final public void elemento(Arvore listaExp) throws ParseException {
    trace_call("elemento");
    try {
                                Token t;Item item;
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SUBTRACAO:
          ;
          break;
        default:
          jj_la1[25] = jj_gen;
          break label_11;
        }
        t = jj_consume_token(SUBTRACAO);
                        item = new Item('o',t.image);
                        listaExp.add(item);
      }
      unario(listaExp);
    } finally {
      trace_return("elemento");
    }
  }

  static final public void unario(Arvore listaExp) throws ParseException {
    trace_call("unario");
    try {
                              Token t;Item item;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        t = jj_consume_token(INT);
          item = new Item('i',t.image);
          listaExp.add(item);
        break;
      case ABRE_EXPRESSAO:
        jj_consume_token(ABRE_EXPRESSAO);
        expressao_aritmetica(listaExp);
        jj_consume_token(FECHA_EXPRESSAO);
        break;
      case VARIAVEL:
        t = jj_consume_token(VARIAVEL);
                if (!(tab.isExiste(t.image))) System.out.println("Erro semantico\u005cn" + t.image + " nao foi declarado!");
        break;
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("unario");
    }
  }

  static final public char tipo_variavel() throws ParseException {
    trace_call("tipo_variavel");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIPO_STRING:
        jj_consume_token(TIPO_STRING);
                  {if (true) return 's';}
        break;
      case TIPO_INTEIRO:
        jj_consume_token(TIPO_INTEIRO);
                                               {if (true) return 'i';}
        break;
      default:
        jj_la1[27] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo_variavel");
    }
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_3_3() {
    if (jj_3R_14()) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(TIPO_STRING)) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(VARIAVEL)) return true;
    if (jj_scan_token(ATRIBUICAO)) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) return true;
    }
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(TIPO_INTEIRO)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(NOME_FUNCAO)) return true;
    if (jj_scan_token(ABRE_EXPRESSAO)) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_12()) return true;
    if (jj_scan_token(VARIAVEL)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MoneyCTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[28];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x60000,0x60000,0x60000,0x8009000,0x8009000,0x0,0x8008000,0x0,0x7000,0x7000,0x0,0x0,0x0,0x3000,0x0,0x0,0x0,0x3f00000,0x3000,0x80000000,0x80000000,0x80000000,0x60000000,0x60000000,0x10000000,0x0,0x3000,0x60000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x8120,0x8120,0x1000,0x0,0x120,0x401,0x401,0x4000,0x4000,0x40,0x401,0x4000,0xc,0xc,0x0,0x411,0x0,0x1,0x1,0x0,0x0,0x0,0x1,0x400,0x0,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[3];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public MoneyC(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MoneyC(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MoneyCTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public MoneyC(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MoneyCTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public MoneyC(MoneyCTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(MoneyCTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 28; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      trace_token(token, "");
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
      trace_token(token, " (in getNextToken)");
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[48];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 28; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 48; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  static private int trace_indent = 0;
  static private boolean trace_enabled = true;

/** Enable tracing. */
  static final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  static final public void disable_tracing() {
    trace_enabled = false;
  }

  static private void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  static private void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  static private void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + t.image + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  static private void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + t1.image + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 3; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
