package br.com.alexandrepontes.controllers;

import org.zkoss.zul.Messagebox;

/**
 * Classe de aux�lio para emiss�o de mensagens de confirma��o e advert�ncias.
 * @author alepq
 *
 */

public class Utilidades {
	public static void mensagem (String msg) throws InterruptedException{
		Messagebox.show(msg);
	}
}

