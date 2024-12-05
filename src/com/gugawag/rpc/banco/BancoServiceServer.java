package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private ArrayList<Conta> contas = new ArrayList<>();

    public BancoServiceServer() throws RemoteException {
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public Double saldo(String numeroConta) throws RemoteException {
        return pesquisarConta(numeroConta).getSaldo();
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    public void adicionarConta(String numeroConta) {
        if(pesquisarConta(numeroConta) == null) {
            contas.add(new Conta(numeroConta, 0.0));
        }
    }

    public Conta pesquisarConta(String numeroConta) {
        for (Conta c : contas) {
            if (c.getNumero().equals(numeroConta)) {
                return c;
            }
        }
        return null;
    }

    public void removerConta(String numeroConta) {
        Conta conta = pesquisarConta(numeroConta);
        if(conta != null) {
            contas.remove(conta);
        }
    }
}
