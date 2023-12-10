package org.example;

import java.util.Scanner;

interface CoinHandler {
    void handle(int amount);
}

class CoinDenominationHandler implements CoinHandler {
    private int denomination;
    private CoinHandler nextHandler;

    public CoinDenominationHandler(int denomination) {
        this.denomination = denomination;
    }

    public void setNextHandler(CoinHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(int amount) {
        int numberOfCoins = amount / denomination;
        int remainder = amount % denomination;

        if (numberOfCoins > 0) {
            System.out.println(numberOfCoins + " x " + denomination + " cents");
        }

        if (remainder > 0 && nextHandler != null) {
            nextHandler.handle(remainder);
        }
    }
}

public class ChangeMakingProblem {
    public static void main(String[] args) {
        CoinDenominationHandler quartersHandler = new CoinDenominationHandler(25);
        CoinDenominationHandler dimesHandler = new CoinDenominationHandler(10);
        CoinDenominationHandler nickelsHandler = new CoinDenominationHandler(5);
        CoinHandler penniesHandler = new CoinDenominationHandler(1);

        quartersHandler.setNextHandler(dimesHandler);
        dimesHandler.setNextHandler(nickelsHandler);
        nickelsHandler.setNextHandler(penniesHandler);

        Scanner scanner = new Scanner(System.in);

        int change = scanner.nextInt();

        System.out.printf("Change for %d cents:%n", change);
        quartersHandler.handle(change);
    }
}
