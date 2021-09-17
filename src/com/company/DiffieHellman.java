package com.company;

import java.util.Random;

public class DiffieHellman {
    private final Random random;

    public DiffieHellman() {
        random = new Random();
    }

    private int calculatePowerByMod(int a, int x, int p) {
        int result = 1;
        while (x > 0) {
            if ((x & 1) == 1)
                result = (result * a) % p;
            a = (a * a) % p;
            x = x >> 1;
        }
        return result;
    }

    private int generateRandomSecretKey(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public void calculateKeys(int g, int p) {
        int Xa = generateRandomSecretKey(100000, 1000000);
        int Xb = generateRandomSecretKey(100000, 1000000);

        int Ya = calculatePowerByMod(g, Xa, p);
        int Yb = calculatePowerByMod(g, Xb, p);

        System.out.println("Alice. Secret key: " + Xa + ". Public key: " + Ya + ".");
        System.out.println("Bob. Secret key: " + Xb + ". Public key: " + Yb + ".");

        int Zab = calculatePowerByMod(Yb, Xa, p);
        int Zba = calculatePowerByMod(Ya, Xb, p);

        System.out.println("Alice took Bob's public key: " + Yb + " and computed common secret key: " + Zab);
        System.out.println("Bob took Alice's public key: " + Ya + " and computed common secret key: " + Zba);
    }

}