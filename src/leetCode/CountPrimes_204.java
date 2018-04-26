package leetCode;

import java.util.Arrays;

public class CountPrimes_204 {
    public int countPrimes(int n) {
        int ans = 0;

        boolean[] isPrime = new boolean[n]; // default all to false (all not prime)
        Arrays.fill(isPrime, true);

        for(int i = 2; i < n; i++ ) {
            // if is not prime, skip; else, mark all multiples to be "not prime"
            if(!isPrime[i]) {
                continue;
            }
            ans++;

            for(int j= i * i; j < n; j = j+i) { // mark all multiples of i to be false (not prime)
                isPrime[j] = false;
            }
        }

//        for(int k = 2; k < n; k++) {
//            if(isPrime[k]) {
//                ans++;
//            }
//        }
        return ans;
    }

//    public int countPrimes(int n) {
//        if (n <= 2) return 0;
//        boolean[] vis = new boolean[n];
//        for (int i = 2; i * i < n; i++) {
//            if (vis[i]) continue;
//            for (int j = i; j * i < n; j++)
//                vis[i * j] = true;
//        }
//        int ans = 0;
//        for (int i = 2; i < n; i++)
//            if (!vis[i]) ans++;
//        return ans;
//    }

    public int countPrimes2(int n) {
        int ans = 0;

        // step 1. mark all integer numbers samller than n
        boolean[] notPrime = new boolean[n];

        for(int i = 2; i < Math.sqrt(n); i++ ) {
            // if not prime, skip; else, mark all multiples of i to be "not prime"
            if(notPrime[i]) {
                continue;
            }

            ans++;
            for(int j= i * i; j < n; j = j+i) {
                notPrime[j] = true;
            }
        }

        return ans;
    }

    public int countPrimes3(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (notPrime[i] == false) {
                count++;
//                for (int j = i; i*j < n; j++) {
//                    notPrime[i*j] = true;
//                }
                for(int j= i * i; j < n; j = j+i) {
                    notPrime[j] = true;
                }
            }
        }

        int ans = 0;
        for(int k=2; k<n; k++) {
            if(!notPrime[k]){
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        CountPrimes_204 myclass = new CountPrimes_204();
        int ans = myclass.countPrimes(10000);
        System.out.println(ans); // 1229h
    }
}
