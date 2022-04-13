/*
 * Nome: Tainá Fontes Santos
 * Matrícula: 16020026
 * 
 * */

package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Cliente;
import model.Conta;

public class main {

	static List<Cliente> clientes = new ArrayList<Cliente>();
	static List<Conta> contas = new ArrayList<Conta>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		boolean continuar = true;
		System.out.println();
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("                      Bem vindo(a) ao Banco Tay ^_^ ");
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println();
		System.out.println();
		System.out.println();
		do {

			try {

				System.out.println("------------------------------------ MENU --------------------------------");
				System.out.println("1-Cadastrar Conta");
				System.out.println("2-Excluir Conta");
				System.out.println("3-Listar Contas");
				System.out.println("4-Efetuar Depositos");
				System.out.println("5-Efetuar Saques");
				System.out.println("6-Consultar Conta");
				System.out.println("7-Efetuar transferência entre contas");
				System.out.println("0- Sair");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println();
				System.out.println("Escolha uma opção:");
				int opcao = sc.nextInt();

				switch (opcao) {

				case 1: {
					cadastrarConta();
					break;
				}
				case 2: {
					excluirConta();
					break;
				}
				case 3: {
					listarConta();
					break;
				}
				case 4: {
					efetuarDeposito();
					break;
				}
				case 5: {
					efetuarSaque();
					break;
				}
				case 6: {
					consultarConta();
					break;
				}
				case 7: {
					transferenciaConta();
					break;
				}
				case 0: {
					continuar = false;
					break;

				}
				default:
					System.out.println("Opção Inválida!");
					System.out.println();
					break;
				}

			} catch (Exception e) {
				System.out.println("Valor inválido");
				break;
			}

		} while (continuar);

		System.out.print("Sistema Finalizado!");

	}

	private static void cadastrarConta() {
		Scanner sc = new Scanner(System.in);

		try {

			Cliente cliente = new Cliente();
			Conta conta = new Conta();
			System.out.println("Digite o nome do cliente");
			cliente.setNomeCliente(sc.nextLine());
			conta.setCliente(cliente);
			System.out.println("Digite o CPF");
			Long cpf = sc.nextLong();

			Conta con = buscarCpf(cpf);
			if (con != null) {
				System.out.println("CPF já cadastrado");
			} else {

				cliente.setCpf(cpf);
				conta.setCliente(cliente);

				conta.setNumeroConta(gerarNumeroConta());
				contas.add(conta);

				for (Conta c : contas) {
					if (c.getCliente().getCpf() == conta.getCliente().getCpf()) {
						System.out.println("---------------------CADASTRO EFETUADO COM SUCESSO!----------------------");
						System.out.println();
						System.out.println("Bem vindo(a) " + c.getCliente().getNomeCliente());
						System.out.println("Esse é número da sua conta: " + c.getNumeroConta());
						System.out.println();
						System.out
								.println("--------------------------------------------------------------------------");
						System.out.println();
					}

				}
			}

		} catch (Exception e) {

			System.out.println("Valor inválido!");

		}

	}

	private static void excluirConta() {

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Qual conta deseja excluir?");
			int numeroConta = sc.nextInt();
			Conta conta = buscarConta(numeroConta);
			if (conta == null) {
				System.out.println("Conta não encontrada");
			} else {
				contas.remove(conta);
				System.out.println("Conta excluida com sucesso!");
			}

		} catch (Exception e) {
			System.out.println("Valor inválido");
		}

	}

	private static void efetuarDeposito() {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Digite o número da conta que deseja fazer o depósito");
			int numeroConta = sc.nextInt();
			Conta conta = buscarConta(numeroConta);

			if (conta == null) {
				System.out.println("Conta não encontrada");

			} else {

				System.out.println("Digite o valor R$ que deseja depositar");
				double valorDeposito = sc.nextDouble();
				deposito(numeroConta, valorDeposito);

			}

		} catch (Exception e) {
			System.out.println("Valor inválido");
		}

	}

	private static void efetuarSaque() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Digite o número da conta para a realização do saque");
			int numeroConta = sc.nextInt();
			Conta conta = buscarConta(numeroConta);
			if (conta == null) {
				System.out.println("Conta não encontrada");
			} else {
				System.out.println("Digite o valor R$ que deseja sacar");
				double valorSaque = sc.nextDouble();
				saque(numeroConta, valorSaque);

			}
		} catch (Exception e) {
			System.out.println("Valor inválido");
		}

	}

	private static void consultarConta() {
		Scanner sc = new Scanner(System.in);
		try {

			Conta con = new Conta();
			System.out.println("Digite o número da conta");
			int numero = sc.nextInt();
			Conta cont = buscarConta(numero);
			if (cont == null) {
				System.out.println("Conta não encontrada!");
			} else {

				con.setNumeroConta(numero);

				for (Conta c : contas) {
					if (c.getNumeroConta() == con.getNumeroConta()) {
						System.out.println();
						System.out.println("**************************** CONTA ******************************");
						System.out.println();
						System.out.println("Número da conta: " + c.getNumeroConta());
						System.out.println("Cliente: " + c.getCliente().getNomeCliente());
						System.out.println("CPF: " + c.getCliente().getCpf());
						System.out.println("Saldo: " + c.getSaldo());
						System.out.println("Crédito Especial: " + c.getCreditoEspecial());
						System.out.println();
						System.out.println("****************************************************************** ");
						System.out.println();
					}

				}
			}
		} catch (Exception e) {
			System.out.println("Valor inválido");
		}
	}

	private static void transferenciaConta() {
		Scanner sc = new Scanner(System.in);
		try {

			Conta con = new Conta();
			System.out.println("Digite o número da sua conta");
			int numeroContaOrigem = sc.nextInt();
			Conta conta = buscarConta(numeroContaOrigem);
			if (conta == null) {
				System.out.println("Conta não encontrada!");
			}
			System.out.println("Digite o número da conta que deseja transferir");
			int numerocontaDestino = sc.nextInt();
			Conta conta2 = buscarConta(numerocontaDestino);
			if (conta2 == null) {
				System.out.println("Conta não encontrada!");
			}

			System.out.println("Digite o valor R$ da transferência");
			double valorTransferencia = sc.nextDouble();

			transferencia(numeroContaOrigem, numerocontaDestino, valorTransferencia);
		} catch (Exception e) {
			System.out.println("Valor inválido");
		}
	}

	private static void listarConta() {
		System.out.println("*********************** LISTA DE CONTAS ************************");
		for (Conta conta : contas) {
			System.out.println();
			System.out.println();
			System.out.print(conta.getNumeroConta());
			System.out.print("  "+conta.getCliente().getNomeCliente());
			System.out.print("  "+conta.getCliente().getCpf());
			System.out.print("  "+conta.getSaldo());

		}
		System.out.println();
		System.out.println();
		System.out.println("******************************************************************");
		System.out.println();
	}

	private static void deposito(int numeroConta, double valorDeposito) {
		Conta con = new Conta();
		con.setNumeroConta(numeroConta);
		for (Conta c : contas) {
			if (c.getNumeroConta() == con.getNumeroConta()) {
				if (c.getCreditoEspecial() == 100) {
					c.setSaldo(c.getSaldo() + valorDeposito);
				}

				if (c.getCreditoEspecial() < 100) {
					c.setCreditoEspecial(c.getCreditoEspecial() + valorDeposito);
					if (c.getCreditoEspecial() == 100) {
						c.setSaldo(0);
					}
				}

				if (c.getCreditoEspecial() > 100) {
					double resto = c.getCreditoEspecial() - 100;
					c.setSaldo(c.getSaldo() + resto);
					c.setCreditoEspecial(100);
				}
			}

		}

		System.out.println();
		System.out.println("---------------------- Depósito efetuado com sucesso! -----------------------");
		System.out.println();
	}

	private static void saque(int numeroConta, double valorSaque) {
		Conta con = new Conta();
		con.setNumeroConta(numeroConta);

		for (Conta c : contas) {
			if (c.getNumeroConta() == con.getNumeroConta()) {

				if (valorSaque > c.getSaldo() && valorSaque > c.getCreditoEspecial()) {
					double valorTotal = c.getSaldo() + c.getCreditoEspecial();

					if (valorTotal >= valorSaque) {

						double valorSaldo = (valorSaque - c.getSaldo());
						double cred = (c.getCreditoEspecial() - valorSaldo);
						c.setCreditoEspecial(cred);
						c.setSaldo(0);

					} else {
						System.out.println("Você não possui saldo suficiente para o saque.");
						break;
					}

				} else if (valorSaque > c.getSaldo() && c.getSaldo() == 0) {

					if (valorSaque <= c.getCreditoEspecial()) {
						double cred = (c.getCreditoEspecial() - valorSaque);
						c.setCreditoEspecial(cred);
					}
				} else if (valorSaque > c.getSaldo() && c.getSaldo() < valorSaque) {

					if (valorSaque <= c.getCreditoEspecial()) {
						double valorSaldo = (valorSaque - c.getSaldo());
						double cred = (c.getCreditoEspecial() - valorSaldo);
						c.setCreditoEspecial(cred);
						c.setSaldo(0);
					}

				} else if (valorSaque <= c.getSaldo()) {
					double saldo = (c.getSaldo() - valorSaque);
					c.setSaldo(saldo);

				}

				System.out.println();
				System.out.println("------------------------ Saque efetuado com sucesso! ----------------------");

				System.out.println();
			}

		}

	}

	private static void transferencia(int numeroContaOrigem, int numeroContaDestino, double valorTransferencia) {
		Conta con = new Conta();
		Conta conta2 = new Conta();
		con.setNumeroConta(numeroContaOrigem);
		conta2.setNumeroConta(numeroContaDestino);

		for (Conta c : contas) {
			if (c.getNumeroConta() == con.getNumeroConta()) {
				if (c.getSaldo() < valorTransferencia) {
					System.out.println("Você não possui saldo suficiente para a realização da transferência");
				} else {
					double valor = c.getSaldo() - valorTransferencia;
					c.setSaldo(valor);
					for (Conta c2 : contas) {
						if (c2.getNumeroConta() == conta2.getNumeroConta()) {

							c2.setSaldo(c2.getSaldo() + valorTransferencia);
							System.out.println(
									"------------------- Transferência Realizada com Sucesso -------------------");
						}
					}
				}

			}
		}

	}

	public static Conta buscarConta(int numeroConta) {
		for (int i = 0; i < contas.size(); i++) {
			Conta conta = contas.get(i);
			if (conta.getNumeroConta() == numeroConta) {
				return conta;
			}
		}
		return null;
	}

	public static Conta buscarCpf(Long cpf) {
		for (int i = 0; i < contas.size(); i++) {
			Conta conta = contas.get(i);
			if (conta.getCliente().getCpf() == cpf) {
				return conta;
			}
		}
		return null;
	}

	public static int gerarNumeroConta() {
		Random numeroConta = new Random();
		int numero = numeroConta.nextInt(10000) * 100;
		return numero;

	}

}
