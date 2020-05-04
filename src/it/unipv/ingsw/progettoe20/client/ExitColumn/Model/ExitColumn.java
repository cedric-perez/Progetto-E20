package it.unipv.ingsw.progettoe20.client.ExitColumn.Model;
/*
  Questa classe rappresenta la colonna di uscita dal parcheggio, si occupa di controllare
  che il ticket sia valido per l'uscita verficandone l'obliterazione e il tempo intercorso,
  in caso positivo permette l'uscita del veicolo
  in caso negativo richiede di recarsi alla colonnina di obliterazione
*/

public class ExitColumn {


    //Metodo che richiede la conferma di obliterazione e ritorna un booleano
    public Boolean checkObliteration(String id) {

        if (id.equals("prova1")) return true; //Ticket corretto per test
        else return false;

    }

}
