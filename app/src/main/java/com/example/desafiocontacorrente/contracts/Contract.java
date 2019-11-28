package com.example.desafiocontacorrente.contracts;

public class Contract {

    public interface View{
        void logInto();
        void fail();
    }

    public interface Presenter{

        void authenticate();

    }
}
