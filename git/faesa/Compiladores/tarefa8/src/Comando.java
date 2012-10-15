public class Comando {
      private char tipo;   
      /* 'A' para atibuições, 
       * 'I' para impressoes,
       * 'L' para leitura,
       * 'E' para loop enquanto,
       * 'C' para condicional, 
       */
      private Object ref1;
      private Object ref2;
      private Object ref3;
      public Comando(char _tipo, Object _ref1) {
          this.tipo = _tipo;
          this.ref1 = _ref1;
    }
      public Comando(char _tipo, Object _ref1, Object _ref2) {
          this.tipo = _tipo;
          this.ref1 = _ref1;
          this.ref2 = _ref2;
    }
      public Comando(char _tipo, Object _ref1, Object _ref2, Object _ref3) {
            this.tipo = _tipo;
            this.ref1 = _ref1;
            this.ref2 = _ref2;
            this.ref3 = _ref3;
      }
      public char getTipo() {
            return this.tipo;
      }
      public Object getRef1() {
            return this.ref1;
      }
      public Object getRef2() {
            return this.ref2;
      }
      public Object getRef3() {
          return this.ref3;
      }
      public String toString() {
            return this.tipo + "/" + this.ref1.toString() +  "/" + this.ref2.toString() +  "/" + this.ref3.toString();
      }
}