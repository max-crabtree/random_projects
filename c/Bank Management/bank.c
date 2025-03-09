#include <stdio.h>

int main() {
	char command;
	printf("Bank Management System\n");
	printf("Type to:\nCreate account\nDeposit\nWithdraw\nPay an account\n");
	scanf(" %c", &command);
	switch (command) {
		case 'c':
			createAccount();
			break;
		case 'd':
			break;
		case 'w':
			break;
		case 'p':
			break;
	}
	return 0;
}
