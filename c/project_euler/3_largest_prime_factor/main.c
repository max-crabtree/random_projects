#include <stdio.h>

#define MAX_PRIME 100

int* sieve_of_eratosthenes(int n) {
	int inc = 2; //smallest prime
	int primes[n-2];

	for (int i = 0; i <= n-2; i++) {
		primes[i] = i+2;
	}

	while (inc < n) {
		for (int i = 0; i <= n-2; i+=inc) {
			//if (primes[i] == inc && primes[i] == -1) { break; }
			if (primes[i] == inc) { continue; }

			primes[i] = -1; // set to -1 if not a prime
		}
		inc++;
	}

	for (int i = 0; i <= n; i++) {
		printf("primes[%d] = %d\n", i, primes[i]);
	}

	return primes;
}

int main() {
	int* primes = sieve_of_eratosthenes(MAX_PRIME);
	return 0;
}
