# Documentação do Projeto: Prontuário Eletrônico do Paciente (PEP)

**Visão Geral**

*O Prontuário Eletrônico do Paciente (PEP) é um sistema desenvolvido para gerenciar informações de usuários, pacientes, médicos e consultas médicas de forma digital, organizada e acessível.*
*Seu foco é oferecer simplicidade, clareza e funcionalidades pensadas para a terceira idade, como menus simplificados, letras grandes e opções diretas.*

*O objetivo principal é facilitar o acesso a informações essenciais de saúde e melhorar a comunicação entre pacientes, médicos e administradores.*

**Estrutura das Classes**

# 1. Usuario

**Descrição:**

Classe base que representa um usuário geral do sistema (Paciente, Médico ou Administrador).

**Atributos:**

- cpf: Identificador único do usuário;
- nome: Nome completo do usuário;
- senha: Senha para autenticação;
- perfil: Tipo de usuário (PACIENTE, MEDICO, ADM);

**Métodos:**

- getCpf(): Retorna o CPF;
- getNome(): Retorna o nome;
- getSenha(): Retorna a senha;
- getPerfil(): Retorna o perfil do usuário;

# 2. Paciente

**Descrição:**

Classe que representa um paciente cadastrado no sistema. Herda de Usuario.

**Atributos adicionais:**

- historicoConsultas: Lista de consultas já realizadas;
- fichaMedica: Informações médicas essenciais (alergias, comorbidades, tipo sanguíneo etc);
- contatoEmergencia: Contato de emergência do paciente;
- anamnese: Informações básicas coletadas pelo sistema (questionário rápido);
- lembretes: Lembretes personalizados de consultas futuras;

**Funcionalidades do Paciente:**

- Visualizar histórico de consultas;
- Agendar nova consulta;
- Visualizar ficha médica;
- Editar informações pessoais;
- Adicionar ou visualizar contato de emergência;
- Preencher anamnese simplificada;
- Receber lembretes de consultas;

# 3. Médico

**Descrição:**

Classe que representa o médico que utiliza o sistema.

**Atributos adicionais:**

- especialidade: Especialidade médica do profissional;
- agenda: Lista de consultas agendadas para atendimento;

**Funcionalidades do Médico:**

- Visualizar consultas agendadas;
- Registrar atendimento (incluindo anamnese e observações);
- Atualizar ficha médica do paciente;
- Adicionar observações ao histórico do paciente;
- Gerar relatórios básicos;

# 4. Administrador

**Descrição:**

Classe responsável por gerenciar o sistema.

**Funcionalidades do Administrador:**

- Cadastrar usuários;
- Remover usuários;
- Buscar usuários por CPF;
- Gerar relatórios gerais do sistema;
- Manter banco de dados local (arquivo usuarios.txt);
- Gerenciar logs e dados de consultas;

# 5. Consulta

**Descrição:** 

Classe que representa uma consulta médica registrada no sistema.

**Atributos:**

- id: Identificador numérico da consulta;
- cpfPaciente: CPF do paciente envolvido;
- cpfMedico: CPF do médico responsável;
- dataHora: Data e hora agendada;
- notas: Observações registradas pelo médico;
- status: Pode ser “Agendada”, “Realizada”, “Cancelada”;

**Métodos:**

- toString(): Retorna todos os dados formatados;
- Métodos de acesso para cada atributo;
- Fluxo Básico de Funcionamento;

*1. Cadastro de Usuário*

O administrador ou próprio usuário preenche:

- Nome;
- CPF;
- Senha;
- Perfil (Paciente, Médico, Administrador);

***Os dados são armazenados em arquivo único: usuarios.txt.***

*2. Login*

O sistema verifica:

- CPF;
- Senha;

E redireciona o usuário para seu menu específico.

*3. Menu do Paciente*

O paciente pode:

- Visualizar histórico;
- Agendar consultas;
- Preencher anamnese;
- Ver ficha médica;
- Ver lembretes;
- Atualizar informações pessoais;
- Ver contato de emergência;

*4. Menu do Médico*

O médico pode:

- Visualizar agenda;
- Registrar atendimento;
- Preencher notas da consulta;
- Atualizar ficha médica;
- Gerar pequenos relatórios;

*5. Menu do Administrador*

O administrador pode:

- Cadastrar usuários;
- Buscar usuário por CPF;
- Remover usuário;
- Gerar relatórios gerais;
- Acompanhar estatísticas do sistema;

# Funcionalidades Implementadas para Terceira Idade

✔ Menu simplificado com letras grandes
Layout amigável, menus curtos e sem poluição visual.

✔ Anamnese simplificada

Perguntas diretas e essenciais:

- Sente dores frequentemente?
- Faz uso de medicamentos?
- Possui doenças crônicas?
- Possui alergias?
- Tem dificuldade de locomoção?

✔ Contato de emergência
Cada paciente pode registrar alguém para ser acionado.

✔ Ficha médica

Contém informações essenciais:

- Alergias;
- Tipos sanguíneo;
- Medicamentos;
- Condições pré-existentes;

✔ Histórico e lembretes
O paciente recebe lembretes das consultas agendadas ao acessar o menu.

# Conclusão

- Este projeto oferece uma base sólida e escalável para um sistema de prontuário eletrônico com foco em usabilidade para idosos.