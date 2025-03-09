#include <stdio.h>

/*
 * Account:
 * name
 * amount
 * user
 *
 * User:
 * login
 * password
 *
 * Functions:
 * withdraw
 * deposit
 * transfer to other accounts
 *
 */

typedef struct Account account;
struct Account {
	char name[MAX_NAME_SIZE];
	double amount;
	user user;
}

typedef struct User user;
struct User {
	char email[MAX_EMAIL_SIZE];
	char password[MAX_PASSWORD_SIZE];
}

account create_account() {
	printf("Create new user and account\n");


int main() {
	printf("Bank Sim\n");
	printf("What would you like to do?: ");
	scanf(" %c", input);
	switch (input) {
		case 'c':
			account current_account = create_account();
			break;
		case 'w':
			withdraw(current_account);
			break;
		case 'd':
			deposit(current_account);
			break;
		case 't':
			transfer(current_account);
			break;
	return 0;
}
