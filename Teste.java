public class Teste {
    public static void testarObjetos() {
        System.out.println("=== TESTE DE INSTÂNCIA DOS OBJETOS ===");

        // Teste de Usuario
        Usuario usuarioTeste = new Usuario("Maria Silva", "12345678900", "senha123", "administrativo");
        System.out.println("Usuário criado:");
        System.out.println("  Nome: Maria Silva");
        System.out.println("  CPF: " + usuarioTeste.getCPF());
        System.out.println("  Perfil: " + usuarioTeste.getPerfil());
        System.out.println("  Senha: " + usuarioTeste.getSenha());
        System.out.println("  Objeto: " + usuarioTeste);

        // Teste de Paciente
        Paciente pacienteTeste = new Paciente("João Marcos", "98765432100", "senha456", "paciente");
        System.out.println("\nPaciente criado:");
        System.out.println("  Nome: João Marcos");
        System.out.println("  CPF: " + pacienteTeste.getCPF());
        System.out.println("  Perfil: " + pacienteTeste.getPerfil());
        System.out.println("  Senha: " + pacienteTeste.getSenha());
        System.out.println("  Objeto: " + pacienteTeste);

        // Teste de Consulta
        Consulta consultaTeste = new Consulta("05/10/2025 14:00", "Dra. Ana Paula", "Consulta de rotina");
        System.out.println("\nConsulta criada:");
        System.out.println("  Data/Hora: 05/10/2025 14:00");
        System.out.println("  Médico: " + consultaTeste.getNomeMedico());
        System.out.println("  Notas: " + consultaTeste.getNotas());
        System.out.println("  Objeto: " + consultaTeste);
        System.out.println("  toString: " + consultaTeste.toString());
    }
}