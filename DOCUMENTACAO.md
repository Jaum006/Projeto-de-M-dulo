# Documentação do Projeto: Prontuário Eletrônico do Paciente (PEP)

## Visão Geral

O Prontuário Eletrônico do Paciente (PEP) é um sistema desenvolvido para gerenciar informações de pacientes, usuários e consultas médicas de forma digital e segura. O objetivo é facilitar o acesso, registro e acompanhamento do histórico clínico dos pacientes, promovendo agilidade e organização para profissionais de saúde e pacientes.

---

## Estrutura das Classes

### 1. Usuario

**Descrição:**  
Classe base que representa qualquer usuário do sistema, podendo ser paciente, médico ou administrador.

**Atributos:**  
- `nome`: Nome do usuário.
- `cpf`: CPF do usuário, utilizado como identificador único.
- `senha`: Senha para autenticação.
- `perfil`: Perfil do usuário (ex: paciente, médico, admin).

**Métodos:**  
- `getCPF()`: Retorna o CPF do usuário.
- `getSenha()`: Retorna a senha do usuário.
- `getPerfil()`: Retorna o perfil do usuário.

---

### 2. Paciente

**Descrição:**  
Classe que representa um paciente do sistema, herdando atributos e métodos da classe `Usuario`.

**Atributos:**  
- `consultas`: Lista de consultas realizadas ou agendadas pelo paciente.

**Métodos:**  
- Construtor para inicializar o paciente com nome, CPF, senha e perfil.

---

### 3. Consulta

**Descrição:**  
Classe que representa uma consulta médica, contendo informações relevantes para o histórico do paciente.

**Atributos:**  
- `dataHora`: Data e hora da consulta.
- `nomeMedico`: Nome do médico responsável.
- `notas`: Observações ou notas sobre a consulta.

**Métodos:**  
- Construtor para inicializar os atributos da consulta.
- `getNomeMedico()`: Retorna o nome do médico.
- `getNotas()`: Retorna as notas da consulta.
- `toString()`: Retorna uma representação textual da consulta.

---

## Fluxo Básico de Funcionamento

1. **Cadastro de Usuários:**  
   Usuários são cadastrados no sistema com nome, CPF, senha e perfil. Pacientes são um tipo específico de usuário.

2. **Agendamento e Registro de Consultas:**  
   Pacientes podem ter várias consultas associadas, cada uma contendo data, médico responsável e notas.

3. **Acesso ao Prontuário:**  
   Usuários autenticados podem acessar informações conforme seu perfil. Pacientes visualizam suas consultas, médicos podem registrar e consultar informações clínicas.

---

## Possíveis Expansões

- Implementação de perfis para médicos e administradores.
- Cadastro e gerenciamento de prescrições, exames e tratamentos.
- Controle de permissões de acesso conforme o perfil do usuário.
- Interface gráfica ou web para interação com o sistema.

---

## Considerações de Segurança

- Senhas devem ser armazenadas de forma segura (criptografia).
- O acesso aos dados deve ser restrito conforme o perfil do usuário.
- O CPF é utilizado como identificador único para evitar duplicidade de registros.

---

## Conclusão

Este projeto oferece uma base sólida para um sistema de prontuário eletrônico, permitindo o gerenciamento eficiente de pacientes e consultas. A estrutura modular facilita futuras expansões e integrações com outros sistemas de saúde.