public enum Genero {
   // enumeração dos gêneros possíveis para candidatos;
   MASCULINO(2),
   FEMININO(4);

   private final int numGenero;

   private Genero(int codigoGenero) {
      this.numGenero = codigoGenero;
   }

   // retorna o enum Genero correspondente ao valor inteiro;
   public static Genero fromValue(int codigoGenero) {
      Genero[] codigo = values();

      for (int i = 0; i < codigo.length; ++i) {
         Genero genero = codigo[i];
         if (genero.numGenero == codigoGenero) {
            return genero;
         }
      }

      return null;
   }

   // retorna o valor inteiro associado ao gênero;
   public int value() {
      return this.numGenero;
   }
}
