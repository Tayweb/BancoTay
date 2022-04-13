/*
 * Nome: Tainá Fontes Santos
 * Matrícula: 16020026
 * 
 * */

package model;

import java.util.Objects;

public class Conta {

	private int numeroConta;
	private double saldo;
	private double creditoEspecial = 100;
	private Cliente cliente;

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public double getCreditoEspecial() {
		return creditoEspecial;
	}

	public void setCreditoEspecial(double creditoEspecial) {
		this.creditoEspecial = creditoEspecial;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return numeroConta == other.numeroConta;
	}

}
