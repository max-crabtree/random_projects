#include <iostream>
#include <experimental/random>
#include <array>

char generate_ai_move() {
	std::array<char, 3> choices = {'r', 'p', 's'};
	int random_number = std::experimental::randint(0, 2);
	return choices[random_number];
}

char get_status(char player, char ai) {
	if (player == ai) {
		std::cout << "It's a draw!\n";
		return 'd';
	}
	if (player == 'r' && ai == 'p') {
		std::cout << "Player loses!\n";
		return 'l';
	}
	if (player == 'p' && ai == 's') {
		std::cout << "Player loses!\n";
		return 'l';
	}
	if (player == 's' && ai == 'r') {
		std::cout << "Player loses!\n";
		return 'l';
	}
	if (ai == 'r' && player == 'p') {
		std::cout << "Player wins!\n";
		return 'w';
	}
	if (ai == 'p' && player == 's') {
		std::cout << "Player wins!\n";
		return 'w';
	}
	if (ai == 's' && player == 'r') {
		std::cout << "Player wins!\n";
		return 'w';
	}
	return 'e';
}

void tally_points(char status, int player_points, int ai_points) {
	if (status == 'd') {
	}
	if (status == 'l') {
		ai_points = ai_points + 1;
	}
	if (status == 'w') {
		player_points = player_points + 1;
	}
}

int main() {
	int current_round_number = 1;
	int total_rounds;
	char player_move;
	char ai_move;
	char status;
	int player_points = 0;
	int ai_points = 0;

	std::cout << "Rock Paper Scissors Game\n";
	std::cout << "How many rounds would you like to play?: ";
	std::cin >> total_rounds;
	std::cout << "Best of " << total_rounds << " rounds\n";
	while (current_round_number < total_rounds) {
		std::cout << "Round " << current_round_number << "\n";
		std::cout << "Type your move: ";
		std::cin >> player_move;
		ai_move = generate_ai_move();
		std::cout << "AI's move: " << ai_move << "\n";
		status = get_status(player_move, ai_move);
		tally_points(status, player_points, ai_points);
		std::cout << "Player is on: " << player_points << "\tAI is on: " << ai_points << "\n";
		current_round_number = current_round_number + 1;
	}
	if (player_points == ai_points) {
		std::cout << "It's a draw!\n";
	}
	if (player_points > ai_points) {
		std::cout << "Player wins!\n";
	}
	if (player_points < ai_points) {
		std::cout << "AI wins!\n";
	}
	return 0;
}
