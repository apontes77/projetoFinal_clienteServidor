package br.com.alexandrepontes.controllers;

import org.zkoss.zul.Messagebox;

/**
 * Classe de auxílio para emissão de mensagens de confirmação e advertências.
 * @author alepq
 *
 */

public class Utilidades {
	public static void mensagem (String msg) throws InterruptedException{
		Messagebox.show(msg);
	}
}

