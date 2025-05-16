public class App{
    public static void main(String[] args) {
        Empresa empresa = new Empresa("Empresa");

        Departamento d1 = new Departamento("d1");
        Departamento d2 = new Departamento("d2");

        empresa.addDepartamento(d1);
        empresa.addDepartamento(d2);

        Pessoa p1 = new Pessoa("pedro", 2002, 11, 20);
        Pessoa p2 = new Pessoa("Isabela", 2003, 03, 18);

        d1.addPessoa(p1);
        d2.addPessoa(p2);

        System.out.println(empresa.getMediaIdadeEmpresa());
    }
}